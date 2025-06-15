package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.model.BubbleSort;
import co.edu.unbosque.model.InsertionSort;
import co.edu.unbosque.model.MergeSort;
import co.edu.unbosque.model.PDFMatriz;
import co.edu.unbosque.model.Politico;
import co.edu.unbosque.model.QuickSort;
import co.edu.unbosque.model.SelectionSort;
import co.edu.unbosque.view.BubbleSortGUI;

public class Controller implements ActionListener {

	private Politico[][] matrizOriginal;
	private Politico[][] matrizPorDinero;
	private Politico[][] matrizPorEdad;
	private BubbleSortGUI b;
	

	private Map<String, long[]> resultadosAlgoritmos = new LinkedHashMap<>();

	public Controller(BubbleSortGUI gui) {
		this.b = gui;
		addListeners();
	}

	private void addListeners() {
		b.getBtnGenerar().setActionCommand("generar");
		b.getBtnGenerar().addActionListener(this);
		b.getBtnBorrar().setActionCommand("borrar");
		b.getBtnBorrar().addActionListener(this);
		b.getBtnVerResultados().setActionCommand("ver_resultados");
		b.getBtnVerResultados().addActionListener(this);
		b.getBtnExportarResultados().setActionCommand("exportar_resultados");
		b.getBtnExportarResultados().addActionListener(this);

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		switch (e.getActionCommand()) {
		case "generar":
			try {
				int n = Integer.parseInt(b.getTxtN().getText());
				int k = Integer.parseInt(b.getTxtK().getText());
				int m = Integer.parseInt(b.getTxtM().getText());

				if (n < 10 || k * m > n) {
					JOptionPane.showMessageDialog(b, "n debe ser al menos 10 y k*m <= n", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				String algoritmo = (String) b.getComboAlgoritmo().getSelectedItem();
				long[] analisis = generarYOrdenarDesdeGUI(n, k, m, algoritmo);

				// Guarda los resultados para la tabla de resultados
				resultadosAlgoritmos.put(algoritmo, analisis);

				Politico[][] original = getMatrizOriginal();
				Politico[][] porDinero = getMatrizPorDinero();
				Politico[][] porEdad = getMatrizPorEdad();

				if (original == null || porDinero == null || porEdad == null) {
					JOptionPane.showMessageDialog(b, "Error: Matrices no inicializadas", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				mostrarTabla(b.getTablaOriginal(), convertirMatrizATexto(original));
				mostrarTabla(b.getTablaDinero(), convertirMatrizATexto(porDinero));
				mostrarTabla(b.getTablaEdad(), convertirMatrizATexto(porEdad));

				JOptionPane.showMessageDialog(b,
					"Comparaciones: " + analisis[0] +
					"\nIntercambios: " + analisis[1] +
					"\nTiempo (ms): " + analisis[2],
					"Análisis Empírico", JOptionPane.INFORMATION_MESSAGE);

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(b, "Por favor ingrese solo números válidos", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			break;
		case "borrar":
			b.getTxtN().setText("");
			b.getTxtK().setText("");
			b.getTxtM().setText("");
			b.getTablaOriginal().setModel(new DefaultTableModel());
			b.getTablaDinero().setModel(new DefaultTableModel());
			b.getTablaEdad().setModel(new DefaultTableModel());
			resultadosAlgoritmos.clear();
			break;
		case "ver_resultados":
			mostrarTablaResultados();
			break;
		case "exportar_resultados":
			if (resultadosAlgoritmos.isEmpty()) {
				JOptionPane.showMessageDialog(b, "No hay resultados para exportar", "Aviso", JOptionPane.WARNING_MESSAGE);
				return;
			}
			PDFMatriz generador = new PDFMatriz();
			generador.generarReporte(resultadosAlgoritmos);
			break;

		}
	}

	public void mostrarTablaResultados() {
		String[] columnas = { "Algoritmo", "Comparaciones", "Intercambios", "Tiempo (ms)" };
		Object[][] datos = new Object[resultadosAlgoritmos.size()][4];
		int i = 0;
		for (Map.Entry<String, long[]> entry : resultadosAlgoritmos.entrySet()) {
			datos[i][0] = entry.getKey();
			datos[i][1] = entry.getValue()[0];
			datos[i][2] = entry.getValue()[1];
			datos[i][3] = entry.getValue()[2];
			i++;
		}
		b.mostrarResultadosTabla(datos, columnas);
	}

	public long[] generarYOrdenarDesdeGUI(int n, int k, int m, String algoritmo) {
		Politico[] politicos = generarPoliticos(n);

		matrizOriginal = new Politico[k][m];
		int idx = 0;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < m; j++) {
				matrizOriginal[i][j] = politicos[idx++];
			}
		}

		matrizPorDinero = copiarMatriz(matrizOriginal);
		matrizPorEdad = null;
		long comparaciones = 0, intercambios = 0;
		long t0 = System.currentTimeMillis();

		if ("Bubble Sort".equals(algoritmo)) {
			for (Politico[] fila : matrizPorDinero) {
				long[] res = BubbleSort.ordenarPorDineroContando(fila);
				comparaciones += res[0];
				intercambios += res[1];
			}
			matrizPorEdad = copiarMatriz(matrizPorDinero);
			for (int col = 0; col < m; col++) {
				Politico[] columna = new Politico[k];
				for (int fila = 0; fila < k; fila++) {
					columna[fila] = matrizPorEdad[fila][col];
				}
				long[] res = BubbleSort.ordenarPorEdadContando(columna);
				comparaciones += res[0];
				intercambios += res[1];
				for (int fila = 0; fila < k; fila++) {
					matrizPorEdad[fila][col] = columna[fila];
				}
			}
		} else if ("Insertion Sort".equals(algoritmo)) {
			for (Politico[] fila : matrizPorDinero) {
				long[] res = InsertionSort.ordenarPorDineroContando(fila);
				comparaciones += res[0];
				intercambios += res[1];
			}
			matrizPorEdad = copiarMatriz(matrizPorDinero);
			for (int col = 0; col < m; col++) {
				Politico[] columna = new Politico[k];
				for (int fila = 0; fila < k; fila++) {
					columna[fila] = matrizPorEdad[fila][col];
				}
				long[] res = InsertionSort.ordenarPorEdadContando(columna);
				comparaciones += res[0];
				intercambios += res[1];
				for (int fila = 0; fila < k; fila++) {
					matrizPorEdad[fila][col] = columna[fila];
				}
			}
		} else if ("Selection Sort".equals(algoritmo)) {
			for (Politico[] fila : matrizPorDinero) {
				long[] res = SelectionSort.ordenarPorDineroContando(fila);
				comparaciones += res[0];
				intercambios += res[1];
			}
			matrizPorEdad = copiarMatriz(matrizPorDinero);
			for (int col = 0; col < m; col++) {
				Politico[] columna = new Politico[k];
				for (int fila = 0; fila < k; fila++) {
					columna[fila] = matrizPorEdad[fila][col];
				}
				long[] res = SelectionSort.ordenarPorEdadContando(columna);
				comparaciones += res[0];
				intercambios += res[1];
				for (int fila = 0; fila < k; fila++) {
					matrizPorEdad[fila][col] = columna[fila];
				}
			}
		} else if ("Merge Sort".equals(algoritmo)) {
			for (Politico[] fila : matrizPorDinero) {
				long[] res = MergeSort.ordenarPorDineroContando(fila);
				comparaciones += res[0];
				intercambios += res[1];
			}
			matrizPorEdad = copiarMatriz(matrizPorDinero);
			for (int col = 0; col < m; col++) {
				Politico[] columna = new Politico[k];
				for (int fila = 0; fila < k; fila++) {
					columna[fila] = matrizPorEdad[fila][col];
				}
				long[] res = MergeSort.ordenarPorEdadContando(columna);
				comparaciones += res[0];
				intercambios += res[1];
				for (int fila = 0; fila < k; fila++) {
					matrizPorEdad[fila][col] = columna[fila];
				}
			}
		} else if ("Quick Sort".equals(algoritmo)) {
			for (Politico[] fila : matrizPorDinero) {
				long[] res = QuickSort.ordenarPorDineroContando(fila);
				comparaciones += res[0];
				intercambios += res[1];
			}
			matrizPorEdad = copiarMatriz(matrizPorDinero);
			for (int col = 0; col < m; col++) {
				Politico[] columna = new Politico[k];
				for (int fila = 0; fila < k; fila++) {
					columna[fila] = matrizPorEdad[fila][col];
				}
				long[] res = QuickSort.ordenarPorEdadContando(columna);
				comparaciones += res[0];
				intercambios += res[1];
				for (int fila = 0; fila < k; fila++) {
					matrizPorEdad[fila][col] = columna[fila];
				}
			}
		}
		long t1 = System.currentTimeMillis();
		return new long[] { comparaciones, intercambios, t1 - t0 };
	}

	private Politico[] generarPoliticos(int cantidad) {
		ArrayList<Integer> ids = new ArrayList<>();
		for (int i = 1; i <= cantidad; i++) {
			ids.add(i);
		}
		Collections.shuffle(ids);

		Random rand = new Random();
		Politico[] lista = new Politico[cantidad];
		for (int i = 0; i < cantidad; i++) {
			int id = ids.get(i);
			int dinero = 100000 + rand.nextInt(900000);
			int anio = 1950 + rand.nextInt(51);
			int mes = 1 + rand.nextInt(12);
			int dia = 1 + rand.nextInt(28);
			LocalDate fecha = LocalDate.of(anio, mes, dia);

			lista[i] = new Politico(id, dinero, fecha);
		}
		return lista;
	}

	private Politico[][] copiarMatriz(Politico[][] original) {
		int filas = original.length;
		int columnas = original[0].length;
		Politico[][] copia = new Politico[filas][columnas];
		for (int i = 0; i < filas; i++) {
			System.arraycopy(original[i], 0, copia[i], 0, columnas);
		}
		return copia;
	}

	public String[][] convertirMatrizATexto(Politico[][] matriz) {
		String[][] resultado = new String[matriz.length][matriz[0].length];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				resultado[i][j] = matriz[i][j].toString();
			}
		}
		return resultado;
	}

	private void mostrarTabla(JTable tabla, String[][] datos) {
		String[] columnas = new String[datos[0].length];
		for (int i = 0; i < columnas.length; i++) {
			columnas[i] = "Col " + (i + 1);
		}

		DefaultTableModel model = new DefaultTableModel(columnas, 0);
		for (String[] fila : datos) {
			model.addRow(fila);
		}
		tabla.setModel(model);

		// Ajustar el ancho de todas las columnas para que se vean completas
		int ancho = 300; // Puedes ajustar este valor si lo deseas más ancho o más angosto
		for (int i = 0; i < tabla.getColumnCount(); i++) {
			tabla.getColumnModel().getColumn(i).setPreferredWidth(ancho);
		}
	}

	public Politico[][] getMatrizOriginal() {
		return matrizOriginal;
	}

	public Politico[][] getMatrizPorDinero() {
		return matrizPorDinero;
	}

	public Politico[][] getMatrizPorEdad() {
		return matrizPorEdad;
	}
}