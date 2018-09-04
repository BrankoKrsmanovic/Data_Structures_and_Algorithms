package klk2018;

import labis.exception.LabisException;

public class Niz {

	public static int maxRazlika(int niz[]) throws LabisException {
		if (niz == null || niz.length == 0)
			throw new LabisException();

		int razlika = Integer.MIN_VALUE;
		int max = Integer.MIN_VALUE;
		for (int i = 0; i < niz.length; i++) {
			if (niz[i] > max)
				max = niz[i];
			if (max - niz[i] > razlika)
				razlika = max - niz[i];
		}
		return razlika;
	}

	public static void main(String[] args) {
		int niz[] = { 3, 8, 1, 10, 6, 5, 9 };
		try {
			System.out.println(maxRazlika(niz));
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		for (int i = 0; i < niz.length; i++) {
			System.out.print(niz[i] + "\t");
		}
	}
}
