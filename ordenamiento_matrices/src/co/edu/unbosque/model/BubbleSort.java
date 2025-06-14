package co.edu.unbosque.model;

public class BubbleSort {

	public static void ordenarPorDinero(Politico[] fila) {
		for (int i = 0; i < fila.length - 1; i++) {
			for (int j = 0; j < fila.length - i - 1; j++) {
				if (fila[j].getDineroRobado() > fila[j + 1].getDineroRobado()) {
					Politico temp = fila[j];
					fila[j] = fila[j + 1];
					fila[j + 1] = temp;
				}
			}
		}
	}

	public static void ordenarPorEdad(Politico[] columna) {
		for (int i = 0; i < columna.length - 1; i++) {
			for (int j = 0; j < columna.length - i - 1; j++) {
				if (columna[j].getEdad() > columna[j + 1].getEdad()) {
					Politico temp = columna[j];
					columna[j] = columna[j + 1];
					columna[j + 1] = temp;
				}
			}
		}
	}
}
