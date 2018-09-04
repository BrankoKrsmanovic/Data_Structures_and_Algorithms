package Vezbe2;

import labis.exception.LabisException;
import labis.niz.ANiz;

public class Niz extends ANiz {

	@Override
	public int[] spojiDva(int[] a, int[] b) throws LabisException {
		if ((a == null || a.length == 0) && (b == null || b.length == 0))
			throw new LabisException();
		int brela = -1;
		if (a != null)
			brela = a.length - 1;
		int brelb = -1;
		if (b != null)
			brelb = b.length - 1;
		int novi[] = new int[brela + brelb + 2];
		int brel = 0;

		while (brela >= 0 || brelb >= 0) {
			if (a[brela] > b[brelb]) {
				novi[brel] = a[brela];
				brela--;
			} else {
				novi[brel] = b[brelb];
				brelb--;
			}
			brel++;
		}
		while (brela >= 0) {
			novi[brel] = a[brela];
			brela--;
			brel++;
		}
		while (brelb >= 0) {
			novi[brel] = b[brelb];
			brelb--;
			brel++;
		}
		for (int i = 0; i < novi.length; i++) {
			System.out.print("\t" + novi[i]);
		}
		return novi;
	}
}
