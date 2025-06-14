package co.edu.unbosque.model;

public class QuickSort {

	public static long[] ordenarPorDineroContando(Politico[] arr) {
		long[] res = new long[2];
		quickSortDinero(arr, 0, arr.length - 1, res);
		return res;
	}

	private static void quickSortDinero(Politico[] arr, int low, int high, long[] res) {
		if (low < high) {
			int pi = partitionDinero(arr, low, high, res);
			quickSortDinero(arr, low, pi - 1, res);
			quickSortDinero(arr, pi + 1, high, res);
		}
	}

	private static int partitionDinero(Politico[] arr, int low, int high, long[] res) {
		Politico pivot = arr[high];
		int i = low - 1;
		for (int j = low; j < high; j++) {
			res[0]++;
			if (arr[j].getDineroRobado() <= pivot.getDineroRobado()) {
				i++;
				Politico temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				res[1]++;
			}
		}
		Politico temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		res[1]++;
		return i + 1;
	}

	public static long[] ordenarPorEdadContando(Politico[] arr) {
		long[] res = new long[2];
		quickSortEdad(arr, 0, arr.length - 1, res);
		return res;
	}

	private static void quickSortEdad(Politico[] arr, int low, int high, long[] res) {
		if (low < high) {
			int pi = partitionEdad(arr, low, high, res);
			quickSortEdad(arr, low, pi - 1, res);
			quickSortEdad(arr, pi + 1, high, res);
		}
	}

	private static int partitionEdad(Politico[] arr, int low, int high, long[] res) {
		Politico pivot = arr[high];
		int i = low - 1;
		for (int j = low; j < high; j++) {
			res[0]++;
			if (arr[j].getEdad() <= pivot.getEdad()) {
				i++;
				Politico temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
				res[1]++;
			}
		}
		Politico temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;
		res[1]++;
		return i + 1;
	}
}
