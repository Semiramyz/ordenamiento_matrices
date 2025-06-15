package co.edu.unbosque.view;

import java.awt.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.JTableHeader;

public class BubbleSortGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JTextField txtN, txtK, txtM;
	private JButton btnGenerar;
	private JButton btnBorrar;
	private JButton btnVerResultados; 
	private JComboBox<String> comboAlgoritmo;
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
		inputPanel.setLayout(new GridLayout(2, 6, 15, 10));
		inputPanel.setBackground(new Color(180, 220, 190));
		inputPanel.setBorder(
				new CompoundBorder(new TitledBorder("Parámetros de generación"), new EmptyBorder(10, 10, 10, 10)));

		txtN = crearCampo("Cantidad de Políticos (n)");
		txtK = crearCampo("Filas (k)");
		txtM = crearCampo("Columnas (m)");

		comboAlgoritmo = new JComboBox<>(new String[] { "Bubble Sort", "Insertion Sort", "Selection Sort", "Merge Sort", "Quick Sort" });
		comboAlgoritmo.setFont(new Font("Arial", Font.PLAIN, 14));
		comboAlgoritmo.setBorder(BorderFactory.createTitledBorder("Algoritmo"));

		btnGenerar = new JButton("Generar y Ordenar");
		btnGenerar.setFont(new Font("Arial", Font.BOLD, 15));
		btnGenerar.setBackground(new Color(100, 180, 120));
		btnGenerar.setFocusPainted(false);

		btnBorrar = new JButton("Borrar Todo");
		btnBorrar.setFont(new Font("Arial", Font.BOLD, 15));
		btnBorrar.setBackground(new Color(200, 80, 80));
		btnBorrar.setFocusPainted(false);

		btnVerResultados = new JButton("Ver Resultados");
		btnVerResultados.setFont(new Font("Arial", Font.BOLD, 15));
		btnVerResultados.setBackground(new Color(80, 120, 200));
		btnVerResultados.setFocusPainted(false);

		inputPanel.add(txtN);
		inputPanel.add(txtK);
		inputPanel.add(txtM);
		inputPanel.add(comboAlgoritmo);
		inputPanel.add(btnGenerar);
		inputPanel.add(btnBorrar);
		inputPanel.add(btnVerResultados); 

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
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF); // Desactiva el ajuste automático de columnas

		// Hace que las columnas no se puedan mover
		JTableHeader header = table.getTableHeader();
		header.setReorderingAllowed(false);

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

		JScrollPane scroll = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.getHorizontalScrollBar().setUnitIncrement(16); // Desplazamiento suave

		panel.add(scroll, BorderLayout.CENTER);
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

	public JButton getBtnBorrar() {
		return btnBorrar;
	}

	public void setBtnBorrar(JButton btnBorrar) {
		this.btnBorrar = btnBorrar;
	}

	public JButton getBtnVerResultados() {
		return btnVerResultados;
	}

	public void setBtnVerResultados(JButton btnVerResultados) {
		this.btnVerResultados = btnVerResultados;
	}

	public JComboBox<String> getComboAlgoritmo() {
		return comboAlgoritmo;
	}

	public void setComboAlgoritmo(JComboBox<String> comboAlgoritmo) {
		this.comboAlgoritmo = comboAlgoritmo;
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

	public void mostrarResultadosTabla(Object[][] datos, String[] columnas) {
		JTable tabla = new JTable(datos, columnas);
		tabla.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		tabla.setFont(new Font("Arial", Font.BOLD, 18));
		tabla.setRowHeight(36);

		DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
		centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
		for (int i = 0; i < tabla.getColumnCount(); i++) {
			tabla.getColumnModel().getColumn(i).setCellRenderer(centerRenderer);
			tabla.getColumnModel().getColumn(i).setPreferredWidth(240); 
		}

		JTableHeader header = tabla.getTableHeader();
		header.setFont(new Font("Arial", Font.BOLD, 20));
		DefaultTableCellRenderer headerRenderer = (DefaultTableCellRenderer) header.getDefaultRenderer();
		headerRenderer.setHorizontalAlignment(SwingConstants.CENTER);

		JScrollPane scroll = new JScrollPane(tabla, JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED, JScrollPane.HORIZONTAL_SCROLLBAR_AS_NEEDED);
		scroll.setPreferredSize(new Dimension(950, 300));

		JPanel panelCentrado = new JPanel(new GridBagLayout());
		panelCentrado.setBackground(new Color(240, 245, 255));
		panelCentrado.add(scroll, new GridBagConstraints());

		JOptionPane.showMessageDialog(this, panelCentrado, "Tabla de Resultados", JOptionPane.INFORMATION_MESSAGE);
	}
}
