package co.edu.unbosque.controller;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import co.edu.unbosque.model.BubbleSort;
import co.edu.unbosque.model.Politico;
import co.edu.unbosque.view.BubbleSortGUI;

public class Controller implements ActionListener {

	private Politico[][] matrizOriginal;
	private Politico[][] matrizPorDinero;
	private Politico[][] matrizPorEdad;
	private BubbleSortGUI b;

	public Controller(BubbleSortGUI gui) {
		this.b = gui;
		addListeners();
	}

	private void addListeners() {
		b.getBtnGenerar().setActionCommand("generar");
		b.getBtnGenerar().addActionListener(this);
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

				generarYOrdenarDesdeGUI(n, k, m);

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

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(b, "Por favor ingrese solo números válidos", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
			break;
		}
	}

	public void generarYOrdenarDesdeGUI(int n, int k, int m) {
		Politico[] politicos = generarPoliticos(n);

		matrizOriginal = new Politico[k][m];
		int idx = 0;
		for (int i = 0; i < k; i++) {
			for (int j = 0; j < m; j++) {
				matrizOriginal[i][j] = politicos[idx++];
			}
		}

		matrizPorDinero = copiarMatriz(matrizOriginal);
		for (Politico[] fila : matrizPorDinero) {
			BubbleSort.ordenarPorDinero(fila);
		}

		matrizPorEdad = copiarMatriz(matrizPorDinero);
		for (int col = 0; col < m; col++) {
			Politico[] columna = new Politico[k];
			for (int fila = 0; fila < k; fila++) {
				columna[fila] = matrizPorEdad[fila][col];
			}
			BubbleSort.ordenarPorEdad(columna);
			for (int fila = 0; fila < k; fila++) {
				matrizPorEdad[fila][col] = columna[fila];
			}
		}
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