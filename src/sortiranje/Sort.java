package sortiranje;

public class Sort {

	int niz[] = { 9, 3, 12, 2, 6, 1, 18 };

	private void stampajNiz() {
		for (int i = 0; i < niz.length; i++) {
			System.out.print("\t" + niz[i]);
		}
	}

	public void selectionSort() {
		int pom, min;
		for (int i = 0; i < niz.length - 1; i++) {
			min = i;
			for (int j = i + 1; j < niz.length; j++)
				if (niz[j] >= niz[min])
					min = j;
			pom = niz[i];
			niz[i] = niz[min];
			niz[min] = pom;
			System.out.print("\n PROLAZ:" + i + "");
			stampajNiz();
		}
	}

	public void sinkSort() {
		int pom;
		for (int i = 0; i < niz.length; i++) {
			for (int j = niz.length - 1; j > 0; j--) {
				if (niz[j] <= niz[j - 1]) {
					pom = niz[j];
					niz[j] = niz[j - 1];
					niz[j - 1] = pom;
				}
			}
			System.out.print("\n PROLAZ:" + i + "");
			stampajNiz();
		}
	}

	public void insertionSort() {
		int pom, j;
		for (int i = 1; i < niz.length; i++) {
			pom = niz[i];
			j = i;
			while (j > 0 && niz[j - 1] > pom) {
				niz[j] = niz[j - 1];
				j--;
			}
			niz[j] = pom;
			System.out.print("\n PROLAZ:" + i + "");
			stampajNiz();
		}
	}

	public static void main(String[] args) {
		Sort s = new Sort();

		s.stampajNiz();
		s.sinkSort();
	}
}
