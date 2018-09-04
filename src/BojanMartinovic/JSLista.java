package BojanMartinovic;

import labis.cvorovi.CvorJSListe;
import labis.exception.LabisException;
import labis.liste.AJSLista;
import labis.test.ListGenerator;

public class JSLista extends AJSLista {

	public int najvecaNeopdajucaSerija(CvorJSListe k) throws LabisException {
		if (k == null)
			throw new LabisException();
		int brojac = 1;
		int max = Integer.MIN_VALUE;

		while (k.sledeci != null) {
			if (k.podatak <= k.sledeci.podatak) {
				brojac++;
				if (brojac > max)
					max = brojac;
			} else
				brojac = 1;
			k = k.sledeci;
		}

		return max;
	}

	public boolean fibonaci(CvorJSListe k) throws LabisException {
		if (k == null)
			throw new LabisException();
		if (k.sledeci == null)
			return k.podatak == 1;
		if (prvi.podatak != 1 || prvi.sledeci.podatak != 1)
			return false;
		while (k.sledeci.sledeci != null) {
			if (k.sledeci.sledeci.podatak != k.podatak + k.sledeci.podatak)
				return false;
			k = k.sledeci;
		}
		return true;
	}

	public int proizvodElemenataDeljivihSvojimSledbenikom(CvorJSListe k) throws LabisException {
		if (k == null || k.sledeci == null)
			throw new LabisException();
		int proizvod = 1;
		while (k.sledeci != null) {
			if (k.podatak % k.sledeci.podatak == 0)
				proizvod *= k.podatak;
			k = k.sledeci;
		}
		return proizvod;
	}

	public void ubaciPoslePrvogNeparnogVecegOdNovog(CvorJSListe k, int p) {
		if (k == null) {
			prvi = new CvorJSListe(p, null);
			return;
		}
		while (k.sledeci != null) {
			if (k.podatak % 2 != 0 && k.podatak > p) {
				CvorJSListe novi = new CvorJSListe(p, k.sledeci);
				k.sledeci = novi;
				return;
			}
			k = k.sledeci;
		}
		CvorJSListe novi = new CvorJSListe(p, k.sledeci);
		k.sledeci = novi;
	}

	public void ubaciNakonVecegZbira(CvorJSListe k, int p) {
		if (k == null || p < k.podatak) {
			prvi = new CvorJSListe(p, prvi);
			return;
		}
		int zbir = 0;
		while (k.sledeci != null) {
			zbir += k.podatak;
			if (zbir > p) {
				k.sledeci = new CvorJSListe(p, k.sledeci);
				return;
			}
			k = k.sledeci;
		}
		k.sledeci = new CvorJSListe(p, k.sledeci);
	}

	public CvorJSListe ukrstiIterativno(CvorJSListe k1, CvorJSListe k2) throws LabisException {
		if (k1 == null && k2 == null) {
			throw new LabisException("Liste su prazne");
		}

		CvorJSListe pom1 = k1.sledeci, pom2 = k2.sledeci;

		while (k1 != null && k2 != null) {
			if(k1.sledeci == null) {
				k1.sledeci = k2;
				break;
			}
			pom1 = k1.sledeci;
			pom2 = k2.sledeci;
			k1.sledeci = k2;
			k2.sledeci = pom1;
			k1 = pom1;
			k2 = pom2;
		}
	
		return prvi;
	}

	public static void main(String[] args) {
		int niz[] = { 1, 2 };
		JSLista l = new JSLista();
		ListGenerator.napraviJSListuCommon(l, niz, false);
		ListGenerator.ispisiJSListu(l.prvi);

		int niz1[] = { 11, 22, 110, 36, 63 };
		JSLista l1 = new JSLista();
		ListGenerator.napraviJSListuCommon(l1, niz1, false);
		try {
			l.ukrstiIterativno(l.prvi, l1.prvi);
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("\n\n");
		ListGenerator.ispisiJSListu(l.prvi);
	}
}
