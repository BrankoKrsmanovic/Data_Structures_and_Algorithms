package BojanMartinovic;

import labis.exception.LabisException;

public class Niz {

	public static int najvecaRazlika(int niz[]) throws LabisException {
		if (niz == null || niz.length <= 1)
			throw new LabisException();
		int min = Integer.MAX_VALUE;
		int razlika = Integer.MIN_VALUE;

		for (int i = 0; i < niz.length; i++) {
			if (niz[i] < min)
				min = niz[i];
			if (niz[i] - min > razlika)
				razlika = niz[i] - min;
		}
		return razlika;
	}

	public static int pretraziBinarno(int niz[], int p) throws LabisException {
		if (niz == null || niz.length == 0)
			throw new LabisException();
		return binarnoR(niz, 0, niz.length - 1, p);
	}

	private static int binarnoR(int niz[], int dg, int gg, int p) {
		if (dg <= gg) {
			int s = (dg + gg) / 2;
			if (niz[s] == p)
				return s;
			if (niz[s] > p)
				return binarnoR(niz, s + 1, gg, p);
			else
				return binarnoR(niz, dg, s - 1, p);
		}
		return -1;
	}

	public static int brojKojiFali(int niz[]) throws LabisException {
		if (niz == null || niz.length == 0)
			throw new LabisException();
		if (niz[0] != 1)
			return 1;
		if (niz[98] != 100)
			return 100;
		return brojKojiFaliPrivate(niz, 0, niz.length - 1);
	}

	private static int brojKojiFaliPrivate(int niz[], int dg, int gg) {
		if (dg <= gg) {
			int s = (dg + gg) / 2;
			if (niz[s] - niz[s - 1] != 1)
				return niz[s] - 1;
			if (niz[s] - s == 1)
				return brojKojiFaliPrivate(niz, s + 1, gg);
			else
				return brojKojiFaliPrivate(niz, dg, s - 1);
		}
		return -1;
	}

	public static int najvecaFrekvencijaUNizu(int niz[]) throws LabisException {
		if (niz == null || niz.length == 0)
			throw new LabisException();
		quickSort(niz, 0, niz.length - 1);
		int brojac = 1;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < niz.length - 1; i++) {
			if (niz[i] == niz[i + 1]) {
				brojac++;
				if (brojac > max)
					max = brojac;
			} else
				brojac = 1;
		}
		return max;
	}

	private static int partition(int niz[], int dg, int gg) {
		int pivot = niz[gg];
		int i = dg - 1;
		for (int j = dg; j < gg; j++) {
			if (niz[j] < pivot) {
				i++;
				int pom = niz[i];
				niz[i] = niz[j];
				niz[j] = pom;
			}
		}
		int pom = niz[i + 1];
		niz[i + 1] = niz[gg];
		niz[gg] = pom;

		return i + 1;
	}

	private static void quickSort(int niz[], int dg, int gg) {
		if (dg < gg) {
			int p = partition(niz, dg, gg);
			quickSort(niz, dg, p - 1);
			quickSort(niz, p + 1, gg);
		}
	}

	public static int zbirTriNajveca(int niz[]) throws LabisException {
		if (niz == null || niz.length < 3)
			throw new LabisException();

		int m1 = Integer.MIN_VALUE;
		int m2 = Integer.MIN_VALUE;
		int m3 = Integer.MIN_VALUE;
		for (int i = 0; i < niz.length; i++) {
			if (niz[i] >= m1) {
				m3 = m2;
				m2 = m1;
				m1 = niz[i];
			} else {
				if (niz[i] >= m2) {
					m3 = m2;
					m2 = niz[i];
				} else if (niz[i] >= m3)
					m3 = niz[i];
			}
		}
		return m1 + m2 + m3;
	}

	public static void spojiNeopadajuce(int niz1[], int niz2[]) {
		if ((niz1 == null || niz1.length == 0) && (niz2 == null || niz2.length == 0))
			return;
		int brel1 = -1;
		int brel2 = -2;
		if (niz1 != null)
			brel1 = niz1.length - 1;
		if (niz2 != null)
			brel2 = niz2.length - 1;
		int i = 0;
		int j = 0;
		int novi[] = new int[brel1 + brel2 + 2];
		int brel = 0;
		while (i <= brel1 && j <= brel2) {
			if (niz1[i] < niz2[j]) {
				novi[brel] = niz1[i];
				brel++;
				i++;
			} else {
				novi[brel] = niz2[j];
				brel++;
				j++;
			}
		}
		while (i <= brel1) {
			novi[brel] = niz1[i];
			brel++;
			i++;
		}
		while (j <= brel2) {
			novi[brel] = niz2[j];
			brel++;
			j++;
		}
		for (int k = 0; k < novi.length; k++) {
			System.out.print(novi[k] + "\t");
		}
	}

	public static void main(String[] args) {
		int niz[] = { 8, 12, 33, 42, 42, 43, 100 };
		int arr[] = { 1, 2, 15, 50, 66 };
		try {
			System.out.println(zbirTriNajveca(niz));
			System.out.println("\n");
			spojiNeopadajuce(niz, arr);
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println("\n\n");
		for (int i = 0; i < niz.length; i++) {
			System.out.print(niz[i] + "\t");
		}
	}
}
