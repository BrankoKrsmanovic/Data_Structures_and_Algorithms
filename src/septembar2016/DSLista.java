package septembar2016;

import labis.cvorovi.CvorDSListe;
import labis.exception.LabisException;
import labis.liste.ADSLista;
import labis.test.ListGenerator;

public class DSLista extends ADSLista {

	private int frekvencija(CvorDSListe k, int p) {
		if (k == null)
			return 0;
		int brojac = 0;
		while (k != null) {
			if (k.podatak == p)
				brojac++;
			k = k.sledeci;
		}
		return brojac;
	}

	private boolean obradjen(CvorDSListe k, int p) {
		if (k == null)
			return false;

		while (k != null) {
			if (k.podatak == p)
				return true;
			k = k.prethodni;
		}
		return false;
	}

	public int drugiNajcesci() throws LabisException {
		if (prvi == null || prvi.sledeci == null)
			throw new LabisException();
		CvorDSListe pom = prvi;
		int p = 0;
		int d = 0;
		int pel = 0, del = 0, f = 0;
		while (pom != null) {
			if (!obradjen(pom.prethodni, pom.podatak)) {
				f = frekvencija(prvi, pom.podatak);
				if (f > p) {
					d = p;
					del = pel;
					p = f;
					pel = pom.podatak;
				} else {
					if (f == d && pom.podatak < del) {
						d = f;
						del = pom.podatak;
					} else if (f > d) {
						d = f;
						del = pom.podatak;
					}
				}
			}
			pom = pom.sledeci;
		}
		return del;
	}

	public static void main(String[] args) {
		DSLista l = new DSLista();
		int niz[] = { 11, 88, 55, 88 };
		ListGenerator.napraviDSListuCommon(l, niz, false);
		ListGenerator.ispisiDSListu(l.prvi);
		System.out.println("\n\n\n");

		try {
			System.out.println(l.drugiNajcesci());
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
