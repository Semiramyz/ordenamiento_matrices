package co.edu.unbosque.controller;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

import co.edu.unbosque.model.BubbleSort;
import co.edu.unbosque.model.Politico;

public class Controller {

	private Politico[][] matrizOriginal;
	private Politico[][] matrizPorDinero;
	private Politico[][] matrizPorEdad;

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

	public String[][] convertirMatrizATexto(Politico[][] matriz) {
		String[][] resultado = new String[matriz.length][matriz[0].length];
		for (int i = 0; i < matriz.length; i++) {
			for (int j = 0; j < matriz[0].length; j++) {
				resultado[i][j] = matriz[i][j].toString();
			}
		}
		return resultado;
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
