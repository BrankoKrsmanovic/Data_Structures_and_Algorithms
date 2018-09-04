package GfG;

import labis.exception.LabisException;

public class Niz {
	int niz[] = { 4,4, 5, 6, 0,1,1,1,1};

	public void invertujNiz() {
		int pom;
		int n = niz.length;
		for (int i = 0; i < n / 2; i++) {
			pom = niz[n - 1 - i];
			niz[n - 1 - i] = niz[i];
			niz[i] = pom;
		}
		for (int i = 0; i < niz.length; i++)
			System.out.print(niz[i] + "\t");
	}

	// Elementi na parnim pozicijama su veci od svojih prethodnika a na neparnim
	// manji

	public void srediNiz(int niz[]) {
		int pom[] = new int[niz.length];

		for (int i = 0; i < niz.length; i++) {
			pom[i] = niz[i];
		}

		pom = sort(pom, pom.length);

		int s;
		if (niz.length % 2 == 0)
			s = niz.length / 2 - 1;
		else
			s = niz.length / 2;
		int j = s;
		for (int i = 0; i < niz.length; i += 2) {
			niz[i] = pom[j];
			j--;
		}
		s++;
		for (int i = 1; i < niz.length; i += 2) {
			niz[i] = pom[s];
			s++;
		}

		for (int i = 0; i < niz.length; i++) {
			System.out.println(niz[i]);
		}
	}

	public int[] sort(int niz[], int n) {
		int pom;
		for (int i = 0; i < n; i++) {
			for (int j = 0; j < n; j++) {
				if (niz[j] > niz[i]) {
					pom = niz[i];
					niz[i] = niz[j];
					niz[j] = pom;
				}
			}
		}
		return niz;
	}

	// Negativni na neparne pozicije, a pozitivni na parne

	public void parNepar(int niz[]) {
		int pom;
		for (int i = 0; i < niz.length; i++) {
			if (i % 2 == 0 && niz[i] > 0) {
				for (int j = i + 1; j < niz.length; j++) {
					if (j % 2 != 0 && niz[j] < 0) {
						pom = niz[i];
						niz[i] = niz[j];
						niz[j] = pom;
						break;
					}
				}
			}
		}
		for (int i = 0; i < niz.length; i++) {
			System.out.println(niz[i]);
		}
	}

	// Minimalni broj premestanja da se broj k i svi brojevi manji od njega stave
	// zajedno

	public int brojPremestanja(int niz[], int k) {
		int brojac = 0;
		int bad = 0, min;
		for (int i = 0; i < niz.length; i++) {
			if (niz[i] >= k)
				brojac++;
		}

		for (int i = 0; i < brojac; i++) {
			if (niz[i] < k)
				bad++;
		}
		min = bad;
		for (int i = 0, j = brojac; j < niz.length; i++, j++) {
			if (niz[i] > k)
				bad--;
			if (niz[j] > k)
				bad++;

			min = Math.min(min, bad);
		}
		return min;
	}

	public void nuleNaKraj(int niz[]) {
		int brojac = 0;
		for (int i = 0; i < niz.length; i++) {
			if (niz[i] != 0)
				niz[brojac++] = niz[i];
		}
		while (brojac < niz.length)
			niz[brojac++] = 0;

		for (int i = 0; i < niz.length; i++) {
			System.out.println(niz[i]);
		}
	}

	public void negativniLevo() {
		int pom, j = -1;
		for (int i = 0; i < niz.length; i++) {
			if (niz[i] < 0) {
				j++;
				pom = niz[j];
				niz[j] = niz[i];
				niz[i] = pom;
			}
		}
		for (int i = 0; i < niz.length; i++) {
			System.out.println(niz[i]);
		}
	}

	// Kadanov algoritam: Nadji max zbir u podnizu

	public int kadanovAlgoritam(int niz[]) {
		int max = Integer.MIN_VALUE;
		int zbir = 0;
		for (int i = 0; i < niz.length; i++) {
			zbir += niz[i];
			if (max <= zbir)
				max = zbir;
			if (niz[i] < 0)
				zbir = 0;

		}
		return max;
	}

	// Rearrange an array such that ‘arr[j]’ becomes ‘i’ if ‘arr[i]’ is ‘j’

	public void funkcija(int niz[]) {
		for (int i = 0; i < niz.length; i++)
			niz[niz[i] % niz.length] += i * niz.length;

		for (int i = 0; i < niz.length; i++) {
			System.out.print(niz[i] / niz.length + "\t");
		}
	}

	// Zameni svaki element proizvodom njegovog prethodnika i sledbenika

	public void f1(int niz[]) {
		if (niz.length <= 1)
			return;
		int p = niz[0];
		int s;
		niz[0] = niz[0] * niz[1];

		for (int i = 1; i < niz.length - 1; i++) {
			s = niz[i];
			niz[i] = p * niz[i + 1];
			p = s;
		}
		niz[niz.length - 1] = niz[niz.length - 1] * p;

	}

	// QuickSort

	int partition(int arr[], int low, int high) {
		int pivot = arr[high];
		int i = (low - 1);
		for (int j = low; j < high; j++) {
			if (arr[j] <= pivot) {
				i++;
				int temp = arr[i];
				arr[i] = arr[j];
				arr[j] = temp;
			}
		}
		int temp = arr[i + 1];
		arr[i + 1] = arr[high];
		arr[high] = temp;

		return i + 1;
	}

	void sort(int arr[], int low, int high) {
		if (low < high) {
			int pi = partition(arr, low, high);
			sort(arr, low, pi - 1);
			sort(arr, pi + 1, high);
		}
	}

	// Nadji k najmanjih elemenata u redosledu u kom se pojavljuju bez koriscenja
	// dodatne strukture

	public void f2(int niz[], int k) {
		for (int i = k; i < niz.length; i++) {
			int max = niz[k - 1];
			int pozicija = k - 1;

			for (int j = k - 2; j >= 0; j--) {
				if (niz[j] > max) {
					max = niz[j];
					pozicija = j;
				}
			}
			if (max > niz[i]) {
				int j = pozicija;
				while (j < k - 1) {
					niz[j] = niz[j + 1];
					j++;
				}
				niz[k - 1] = niz[i];
			}
		}

		for (int i = 0; i < k; i++) {
			System.out.print(niz[i] + "\t");
		}
	}

	// Nadji k elemenata sa najvecim frekvencijama, ako su frekvencije iste veci od
	// njih se prihvata

	private class Pom {
		int br;
		int f;
	}

	private boolean obradjen(int niz[], int n, int podatak) {
		for (int i = 0; i < n; i++) {
			if (niz[i] == podatak)
				return true;
		}
		return false;
	}

	private int frekvencija(int niz[], int podatak) {
		int brojac = 0;
		for (int i = 0; i < niz.length; i++) {
			if (niz[i] == podatak)
				brojac++;
		}
		return brojac;
	}

	private Pom[] sort(Pom[] p, int brel) {
		Pom pom;
		for (int i = 0; i < brel; i++) {
			for (int j = 0; j < brel; j++) {
				if (p[j].f < p[i].f) {
					pom = p[j];
					p[j] = p[i];
					p[i] = pom;
				}
			}
		}

		for (int i = 0; i < brel; i++) {
			for (int j = 0; j < brel; j++) {
				if (p[j].f == p[i].f) {
					if (p[j].br < p[i].br) {
						pom = p[j];
						p[j] = p[i];
						p[i] = pom;
					}
				}
			}
		}

		return p;
	}

	public void f3(int niz[], int k) {
		Pom[] p = new Pom[niz.length];
		int j = 0;
		for (int i = 0; i < niz.length; i++) {
			if (!obradjen(niz, i, niz[i])) {
				p[j] = new Pom();
				p[j].br = niz[i];
				p[j].f = frekvencija(niz, niz[i]);
				j++;
			}
		}
		p = sort(p, j);
		for (int i = 0; i < k; i++) {
			System.out.println(p[i].br + "\t frekvencija: " + p[i].f);
		}
	}

	// Offset O(logN)

	public int offset(int niz[]) throws LabisException {
		if (niz == null || niz.length < 1)
			throw new LabisException();
		return offsetR(niz, 0, niz.length - 1);
	}

	private int offsetR(int niz[], int dg, int gg) {
		if (dg <= gg) {
			int s = (gg + dg) / 2;
			if (niz[s] > niz[s + 1])
				return niz.length - 1 - s;
			if (niz[s] > niz[niz.length - 1])
				return offsetR(niz, s + 1, gg);
			else
				return offsetR(niz, dg, s - 1);
		}
		return 0;
	}

	// Window sliding technique

	public int najveciZbirOdKElemenata(int niz[], int k) throws LabisException {
		if (niz == null || niz.length <= k || k < 1)
			throw new LabisException();
		int zbir = 0;
		int max = 0;
		for (int i = 0; i < k; i++)
			max += niz[i];

		for (int i = k; i < niz.length; i++) {
			zbir += niz[i] - niz[i - k];
			max = Integer.max(max, zbir);
		}
		return max;
	}

	public static void main(String[] args) {
		Niz n = new Niz();
		// n.rearrangeArr(n.niz, 4);
		// n.srediNiz(n.niz);
		// n.partition(n.niz,0, n.niz.length - 1);
		// System.out.println("\n\n");
		for (int i = 0; i < n.niz.length; i++) {
			System.out.println(n.niz[i] + "\t");
		}
		try {
			System.out.println("\n" + n.offset(n.niz));
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
