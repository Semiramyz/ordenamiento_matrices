package co.edu.unbosque.view;

import java.util.Scanner;

public class Consola {

	private Scanner lector;

	public Consola() {
		lector = new Scanner(System.in);
	}

	public int leerEntero() {
		int numero = lector.nextInt();
		return numero;
	}

	public float leerDecimal() {
		float numero = lector.nextFloat();
		return numero;
	}

	public String leerPalabra() {
		String palabra = lector.next();
		return palabra;
	}

	public String leerLinea() {
		String linea = lector.nextLine();
		return linea;
	}

	public void limpiarLinea() {
		lector.nextLine();
	}

	public void mostrarMensaje(String mensaje) {
		System.out.println(mensaje);
	}
}
