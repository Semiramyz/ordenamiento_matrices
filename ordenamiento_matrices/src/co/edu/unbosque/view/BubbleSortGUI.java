package co.edu.unbosque.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

import co.edu.unbosque.controller.Controller;
import co.edu.unbosque.model.Politico;
//...importaciones iguales...

public class BubbleSortGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtN, txtK, txtM;
	private JButton btnGenerar;
	private JTable tablaOriginal, tablaDinero, tablaEdad;
	private Controller controller;

	public BubbleSortGUI() {
		controller = new Controller();
		setTitle("Ordenar Políticos por Dinero y Edad");
		setExtendedState(JFrame.MAXIMIZED_BOTH);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setLocationRelativeTo(null);

		JPanel mainPanel = new JPanel(new BorderLayout());
		mainPanel.setBackground(new Color(200, 230, 210));
		mainPanel.setBorder(new EmptyBorder(20, 20, 20, 20));
		setContentPane(mainPanel);

		crearPanelInputs(mainPanel);
		crearPanelTablas(mainPanel);
	}

	private void crearPanelInputs(JPanel parent) {
		JPanel inputPanel = new JPanel();
		inputPanel.setLayout(new GridLayout(2, 4, 15, 10));
		inputPanel.setBackground(new Color(180, 220, 190));
		inputPanel.setBorder(
				new CompoundBorder(new TitledBorder("Parámetros de generación"), new EmptyBorder(10, 10, 10, 10)));

		txtN = crearCampo("Cantidad de Políticos (n)");
		txtK = crearCampo("Filas (k)");
		txtM = crearCampo("Columnas (m)");

		btnGenerar = new JButton("Generar y Ordenar");
		btnGenerar.setFont(new Font("Arial", Font.BOLD, 15));
		btnGenerar.setBackground(new Color(100, 180, 120));
		btnGenerar.setFocusPainted(false);
		btnGenerar.addActionListener(e -> {
			try {
				int n = Integer.parseInt(txtN.getText());
				int k = Integer.parseInt(txtK.getText());
				int m = Integer.parseInt(txtM.getText());

				if (n < 10 || k * m > n) {
					JOptionPane.showMessageDialog(this, "n debe ser al menos 10 y k*m <= n", "Error",
							JOptionPane.ERROR_MESSAGE);
					return;
				}

				Politico[][][] resultado = controller.generarYOrdenarDesdeGUI(n, k, m);
				mostrarTabla(tablaOriginal, controller.convertirMatrizATexto(resultado[0]));
				mostrarTabla(tablaDinero, controller.convertirMatrizATexto(resultado[1]));
				mostrarTabla(tablaEdad, controller.convertirMatrizATexto(resultado[2]));

			} catch (NumberFormatException ex) {
				JOptionPane.showMessageDialog(this, "Por favor ingrese solo números válidos", "Error",
						JOptionPane.ERROR_MESSAGE);
			}
		});

		inputPanel.add(txtN);
		inputPanel.add(txtK);
		inputPanel.add(txtM);
		inputPanel.add(btnGenerar);

		parent.add(inputPanel, BorderLayout.NORTH);
	}

	private JTextField crearCampo(String titulo) {
		JTextField campo = new JTextField();
		campo.setFont(new Font("Arial", Font.PLAIN, 14));
		campo.setBorder(BorderFactory.createTitledBorder(titulo));
		return campo;
	}

	private void crearPanelTablas(JPanel parent) {
		JPanel tablesPanel = new JPanel(new GridLayout(3, 1, 15, 15));
		tablesPanel.setBackground(new Color(200, 230, 210));

		tablaOriginal = crearTabla();
		tablaDinero = crearTabla();
		tablaEdad = crearTabla();

		tablesPanel.add(wrapTablaConTitulo(tablaOriginal, " Matriz Original"));
		tablesPanel.add(wrapTablaConTitulo(tablaDinero, " Ordenado por Dinero (Filas)"));
		tablesPanel.add(wrapTablaConTitulo(tablaEdad, " Ordenado por Edad (Columnas)"));

		parent.add(tablesPanel, BorderLayout.CENTER);
	}

	private JTable crearTabla() {
		JTable table = new JTable();
		table.setFont(new Font("Consolas", Font.PLAIN, 13));
		table.setRowHeight(32);
		table.setDefaultRenderer(Object.class, new MultilineCellRenderer());
		return table;
	}

	private JPanel wrapTablaConTitulo(JTable tabla, String titulo) {
		JLabel label = new JLabel(titulo, JLabel.CENTER);
		label.setFont(new Font("Arial", Font.BOLD, 16));
		label.setForeground(new Color(40, 80, 50));

		JPanel panel = new JPanel(new BorderLayout());
		panel.setBackground(new Color(210, 240, 220));
		panel.setBorder(BorderFactory.createLineBorder(new Color(160, 200, 160), 2));
		panel.add(label, BorderLayout.NORTH);
		panel.add(new JScrollPane(tabla), BorderLayout.CENTER);
		return panel;
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

	class MultilineCellRenderer extends JTextArea implements TableCellRenderer {
		public MultilineCellRenderer() {
			setLineWrap(true);
			setWrapStyleWord(true);
			setOpaque(true);
			setFont(new Font("Consolas", Font.PLAIN, 13));
		}

		@Override
		public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus,
				int row, int column) {
			setText(value != null ? value.toString() : "");
			setBackground(row % 2 == 0 ? new Color(235, 255, 240) : new Color(220, 240, 225));
			return this;
		}
	}
}
