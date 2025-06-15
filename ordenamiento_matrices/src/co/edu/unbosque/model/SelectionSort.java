package co.edu.unbosque.model;

public class SelectionSort {

	public static void ordenarPorDinero(Politico[] fila) {
		for (int i = 0; i < fila.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < fila.length; j++) {
				if (fila[j].getDineroRobado() < fila[minIdx].getDineroRobado()) {
					minIdx = j;
				}
			}
			if (minIdx != i) {
				Politico temp = fila[i];
				fila[i] = fila[minIdx];
				fila[minIdx] = temp;
			}
		}
	}

	public static void ordenarPorEdad(Politico[] columna) {
		for (int i = 0; i < columna.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < columna.length; j++) {
				if (columna[j].getEdad() < columna[minIdx].getEdad()) {
					minIdx = j;
				}
			}
			if (minIdx != i) {
				Politico temp = columna[i];
				columna[i] = columna[minIdx];
				columna[minIdx] = temp;
			}
		}
	}

	public static long[] ordenarPorDineroContando(Politico[] fila) {
		long comparaciones = 0, intercambios = 0;
		for (int i = 0; i < fila.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < fila.length; j++) {
				comparaciones++;
				if (fila[j].getDineroRobado() < fila[minIdx].getDineroRobado()) {
					minIdx = j;
				}
			}
			if (minIdx != i) {
				Politico temp = fila[i];
				fila[i] = fila[minIdx];
				fila[minIdx] = temp;
				intercambios++;
			}
		}
		return new long[] { comparaciones, intercambios };
	}

	public static long[] ordenarPorEdadContando(Politico[] columna) {
		long comparaciones = 0, intercambios = 0;
		for (int i = 0; i < columna.length - 1; i++) {
			int minIdx = i;
			for (int j = i + 1; j < columna.length; j++) {
				comparaciones++;
				if (columna[j].getEdad() < columna[minIdx].getEdad()) {
					minIdx = j;
				}
			}
			if (minIdx != i) {
				Politico temp = columna[i];
				columna[i] = columna[minIdx];
				columna[minIdx] = temp;
				intercambios++;
			}
		}
		return new long[] { comparaciones, intercambios };
	}
}
