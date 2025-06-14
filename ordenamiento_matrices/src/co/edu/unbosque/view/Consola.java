package co.edu.unbosque.view;

import javax.swing.*;
import java.awt.*;

public class Consola extends JFrame {

	private JTextArea areaMensajes;
	private JTextField campoEntrada;
	private JButton btnEnviar;
	private JScrollPane scroll;
	private String ultimoInput = null;

	public Consola() {
		setTitle("Consola Interactiva");
		setSize(600, 350);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setLocationRelativeTo(null);
		getContentPane().setBackground(new Color(245, 230, 250));
		setLayout(new BorderLayout(10, 10));

		areaMensajes = new JTextArea();
		areaMensajes.setEditable(false);
		areaMensajes.setFont(new Font("Consolas", Font.PLAIN, 16));
		areaMensajes.setBackground(new Color(255, 245, 255));
		areaMensajes.setForeground(new Color(120, 40, 140));
		areaMensajes.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

		scroll = new JScrollPane(areaMensajes);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.setBorder(BorderFactory.createLineBorder(new Color(200, 120, 220), 2));
		add(scroll, BorderLayout.CENTER);

		JPanel panelInferior = new JPanel(new BorderLayout(5, 5));
		panelInferior.setBackground(new Color(245, 230, 250));

		campoEntrada = new JTextField();
		campoEntrada.setFont(new Font("Consolas", Font.PLAIN, 16));
		campoEntrada.setBackground(new Color(255, 245, 255));
		campoEntrada.setForeground(new Color(120, 40, 140));
		campoEntrada.setBorder(BorderFactory.createLineBorder(new Color(200, 120, 220), 2));

		btnEnviar = new JButton("Enviar");
		btnEnviar.setFont(new Font("Arial", Font.BOLD, 15));
		btnEnviar.setBackground(new Color(200, 120, 220));
		btnEnviar.setForeground(Color.WHITE);
		btnEnviar.setFocusPainted(false);

		panelInferior.add(campoEntrada, BorderLayout.CENTER);
		panelInferior.add(btnEnviar, BorderLayout.EAST);

		add(panelInferior, BorderLayout.SOUTH);

		btnEnviar.addActionListener(e -> procesarEntrada());
		campoEntrada.addActionListener(e -> procesarEntrada());
	}

	private void procesarEntrada() {
		ultimoInput = campoEntrada.getText();
		campoEntrada.setText("");
		synchronized (this) {
			this.notify();
		}
	}

	public int leerEntero() {
		return Integer.parseInt(leerLinea());
	}

	public float leerDecimal() {
		return Float.parseFloat(leerLinea());
	}

	public String leerPalabra() {
		String linea = leerLinea();
		if (linea == null) return "";
		String[] partes = linea.trim().split("\\s+");
		return partes.length > 0 ? partes[0] : "";
	}

	public String leerLinea() {
		ultimoInput = null;
		campoEntrada.requestFocus();
		while (ultimoInput == null) {
			try {
				synchronized (this) {
					this.wait();
				}
			} catch (InterruptedException e) {
				Thread.currentThread().interrupt();
			}
		}
		return ultimoInput;
	}

	public void limpiarLinea() {
		campoEntrada.setText("");
	}

	public void mostrarMensaje(String mensaje) {
		areaMensajes.append(mensaje + "\n");
		areaMensajes.setCaretPosition(areaMensajes.getDocument().getLength());
	}
}
