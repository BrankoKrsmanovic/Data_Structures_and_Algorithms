package klk2018;

import labis.cvorovi.CvorDSListe;
import labis.liste.ADSLista;
import labis.test.ListGenerator;

public class DSLista extends ADSLista {

	public void parNepar(CvorDSListe k) {
		if (k == null)
			return;
		CvorDSListe pom = null;
		while (k.sledeci != null) {
			if (k.podatak % 2 == 0) {
				pom = k;
				k = pom.sledeci;
				pom.prethodni.sledeci = pom.sledeci;
				pom.sledeci.prethodni = pom.prethodni;
				pom.sledeci = prvi;
				pom.prethodni = null;
				prvi.prethodni = pom;
				prvi = pom;
			} else
				k = k.sledeci;
		}
	}

	public static void main(String[] args) {
		int niz[] = { 11, 22, 32, 42, 55, 6, 7 };
		DSLista l = new DSLista();
		ListGenerator.napraviDSListuCommon(l, niz, false);
		ListGenerator.ispisiDSListu(l.prvi);
		l.parNepar(l.prvi);
		System.out.println("\n\n");
		ListGenerator.ispisiDSListu(l.prvi);
	}
}
