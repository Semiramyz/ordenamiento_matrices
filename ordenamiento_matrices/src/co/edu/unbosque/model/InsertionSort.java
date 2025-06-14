package co.edu.unbosque.model;

public class InsertionSort {

	public static void ordenarPorDinero(Politico[] fila) {
		for (int i = 1; i < fila.length; i++) {
			Politico actual = fila[i];
			int j = i - 1;
			while (j >= 0 && fila[j].getDineroRobado() > actual.getDineroRobado()) {
				fila[j + 1] = fila[j];
				j--;
			}
			fila[j + 1] = actual;
		}
	}

	public static void ordenarPorEdad(Politico[] columna) {
		for (int i = 1; i < columna.length; i++) {
			Politico actual = columna[i];
			int j = i - 1;
			while (j >= 0 && columna[j].getEdad() > actual.getEdad()) {
				columna[j + 1] = columna[j];
				j--;
			}
			columna[j + 1] = actual;
		}
	}

	public static long[] ordenarPorDineroContando(Politico[] fila) {
		long comparaciones = 0, intercambios = 0;
		for (int i = 1; i < fila.length; i++) {
			Politico actual = fila[i];
			int j = i - 1;
			while (j >= 0) {
				comparaciones++;
				if (fila[j].getDineroRobado() > actual.getDineroRobado()) {
					fila[j + 1] = fila[j];
					intercambios++;
					j--;
				} else {
					break;
				}
			}
			fila[j + 1] = actual;
		}
		return new long[] { comparaciones, intercambios };
	}

	public static long[] ordenarPorEdadContando(Politico[] columna) {
		long comparaciones = 0, intercambios = 0;
		for (int i = 1; i < columna.length; i++) {
			Politico actual = columna[i];
			int j = i - 1;
			while (j >= 0) {
				comparaciones++;
				if (columna[j].getEdad() > actual.getEdad()) {
					columna[j + 1] = columna[j];
					intercambios++;
					j--;
				} else {
					break;
				}
			}
			columna[j + 1] = actual;
		}
		return new long[] { comparaciones, intercambios };
	}
}
