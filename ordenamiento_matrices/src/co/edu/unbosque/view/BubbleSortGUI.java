package co.edu.unbosque.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.*;

public class BubbleSortGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtN, txtK, txtM;
	private JButton btnGenerar;
	private JTable tablaOriginal, tablaDinero, tablaEdad;

	public BubbleSortGUI() {
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

	private void crearPanelInputs(JPanel PANEL) {
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

		inputPanel.add(txtN);
		inputPanel.add(txtK);
		inputPanel.add(txtM);
		inputPanel.add(btnGenerar);

		PANEL.add(inputPanel, BorderLayout.NORTH);
	}

	private JTextField crearCampo(String titulo) {
		JTextField campo = new JTextField();
		campo.setFont(new Font("Arial", Font.PLAIN, 14));
		campo.setBorder(BorderFactory.createTitledBorder(titulo));
		return campo;
	}

	private void crearPanelTablas(JPanel PANEL) {
		JPanel tablesPanel = new JPanel(new GridLayout(3, 1, 15, 15));
		tablesPanel.setBackground(new Color(200, 230, 210));

		tablaOriginal = crearTabla();
		tablaDinero = crearTabla();
		tablaEdad = crearTabla();

		tablesPanel.add(wrapTablaConTitulo(tablaOriginal, " Matriz Original"));
		tablesPanel.add(wrapTablaConTitulo(tablaDinero, " Ordenado por Dinero (Filas)"));
		tablesPanel.add(wrapTablaConTitulo(tablaEdad, " Ordenado por Edad (Columnas)"));

		PANEL.add(tablesPanel, BorderLayout.CENTER);
	}

	private JTable crearTabla() {
		JTable table = new JTable();
		table.setFont(new Font("Consolas", Font.PLAIN, 13));
		table.setRowHeight(32);
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

	public JTextField getTxtN() {
		return txtN;
	}

	public void setTxtN(JTextField txtN) {
		this.txtN = txtN;
	}

	public JTextField getTxtK() {
		return txtK;
	}

	public void setTxtK(JTextField txtK) {
		this.txtK = txtK;
	}

	public JTextField getTxtM() {
		return txtM;
	}

	public void setTxtM(JTextField txtM) {
		this.txtM = txtM;
	}

	public JButton getBtnGenerar() {
		return btnGenerar;
	}

	public void setBtnGenerar(JButton btnGenerar) {
		this.btnGenerar = btnGenerar;
	}

	public JTable getTablaOriginal() {
		return tablaOriginal;
	}

	public void setTablaOriginal(JTable tablaOriginal) {
		this.tablaOriginal = tablaOriginal;
	}

	public JTable getTablaDinero() {
		return tablaDinero;
	}

	public void setTablaDinero(JTable tablaDinero) {
		this.tablaDinero = tablaDinero;
	}

	public JTable getTablaEdad() {
		return tablaEdad;
	}

	public void setTablaEdad(JTable tablaEdad) {
		this.tablaEdad = tablaEdad;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
