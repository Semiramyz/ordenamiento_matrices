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

	public static long[] ordenarPorDineroContando(Politico[] fila) {
		long comparaciones = 0, intercambios = 0;
		for (int i = 0; i < fila.length - 1; i++) {
			for (int j = 0; j < fila.length - i - 1; j++) {
				comparaciones++;
				if (fila[j].getDineroRobado() > fila[j + 1].getDineroRobado()) {
					Politico temp = fila[j];
					fila[j] = fila[j + 1];
					fila[j + 1] = temp;
					intercambios++;
				}
			}
		}
		return new long[] { comparaciones, intercambios };
	}

	public static long[] ordenarPorEdadContando(Politico[] columna) {
		long comparaciones = 0, intercambios = 0;
		for (int i = 0; i < columna.length - 1; i++) {
			for (int j = 0; j < columna.length - i - 1; j++) {
				comparaciones++;
				if (columna[j].getEdad() > columna[j + 1].getEdad()) {
					Politico temp = columna[j];
					columna[j] = columna[j + 1];
					columna[j + 1] = temp;
					intercambios++;
				}
			}
		}
		return new long[] { comparaciones, intercambios };
	}
}
