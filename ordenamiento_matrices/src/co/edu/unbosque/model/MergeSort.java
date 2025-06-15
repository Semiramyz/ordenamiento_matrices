package co.edu.unbosque.model;

public class MergeSort {

	public static long[] ordenarPorDineroContando(Politico[] arr) {
		long[] res = new long[2];
		mergeSortDinero(arr, 0, arr.length - 1, res);
		return res;
	}

	private static void mergeSortDinero(Politico[] arr, int left, int right, long[] res) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSortDinero(arr, left, mid, res);
			mergeSortDinero(arr, mid + 1, right, res);
			mergeDinero(arr, left, mid, right, res);
		}
	}

	private static void mergeDinero(Politico[] arr, int left, int mid, int right, long[] res) {
		int n1 = mid - left + 1;
		int n2 = right - mid;
		Politico[] L = new Politico[n1];
		Politico[] R = new Politico[n2];
		System.arraycopy(arr, left, L, 0, n1);
		System.arraycopy(arr, mid + 1, R, 0, n2);

		int i = 0, j = 0, k = left;
		while (i < n1 && j < n2) {
			res[0]++;
			if (L[i].getDineroRobado() <= R[j].getDineroRobado()) {
				arr[k++] = L[i++];
			} else {
				arr[k++] = R[j++];
				res[1]++;
			}
		}
		while (i < n1) arr[k++] = L[i++];
		while (j < n2) arr[k++] = R[j++];
	}

	public static long[] ordenarPorEdadContando(Politico[] arr) {
		long[] res = new long[2];
		mergeSortEdad(arr, 0, arr.length - 1, res);
		return res;
	}

	private static void mergeSortEdad(Politico[] arr, int left, int right, long[] res) {
		if (left < right) {
			int mid = (left + right) / 2;
			mergeSortEdad(arr, left, mid, res);
			mergeSortEdad(arr, mid + 1, right, res);
			mergeEdad(arr, left, mid, right, res);
		}
	}

	private static void mergeEdad(Politico[] arr, int left, int mid, int right, long[] res) {
		int n1 = mid - left + 1;
		int n2 = right - mid;
		Politico[] L = new Politico[n1];
		Politico[] R = new Politico[n2];
		System.arraycopy(arr, left, L, 0, n1);
		System.arraycopy(arr, mid + 1, R, 0, n2);

		int i = 0, j = 0, k = left;
		while (i < n1 && j < n2) {
			res[0]++;
			if (L[i].getEdad() <= R[j].getEdad()) {
				arr[k++] = L[i++];
			} else {
				arr[k++] = R[j++];
				res[1]++;
			}
		}
		while (i < n1) arr[k++] = L[i++];
		while (j < n2) arr[k++] = R[j++];
	}
}
