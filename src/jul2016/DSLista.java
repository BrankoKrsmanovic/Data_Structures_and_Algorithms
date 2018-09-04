package jul2016;

import labis.cvorovi.CvorDSListe;
import labis.exception.LabisException;
import labis.liste.ADSLista;
import labis.test.ListGenerator;

public class DSLista extends ADSLista {

	public double prosekParnihUCiklicnoj(CvorDSListe prvi) throws LabisException {
		if (prvi == null)
			throw new LabisException();
		double zbir = 0;
		int brel = 0;
		CvorDSListe pom = prvi.sledeci;

		if (prvi.podatak % 2 == 0) {
			zbir = prvi.podatak;
			brel = 1;
		}

		while (pom != prvi) {
			if (pom.podatak % 2 == 0) {
				zbir += pom.podatak;
				brel++;
			}
			pom = pom.sledeci;
		}
		return zbir / brel;
	}

	public CvorDSListe vratiPretposlednji(CvorDSListe prvi) {
		if (prvi == null || prvi.sledeci == prvi)
			return null;
		CvorDSListe pom = prvi;
		while (pom.sledeci.sledeci != prvi)
			pom = pom.sledeci;

		return pom;
	}

	public CvorDSListe saberiDveUJednu(CvorDSListe l1, CvorDSListe l2) {
		if (l1 == null && l2 == null)
			return null;
		CvorDSListe nova = null;
		while (l1 != null && l2 != null) {
			nova = new CvorDSListe(l1.podatak + l2.podatak, null, nova);
			if (nova.sledeci != null)
				nova.sledeci.prethodni = nova;
			l1 = l1.sledeci;
			l2 = l2.sledeci;
		}
		while (l1 != null) {
			nova = new CvorDSListe(l1.podatak, null, nova);
			l1 = l1.sledeci;
		}
		while (l2 != null) {
			nova = new CvorDSListe(l2.podatak, null, nova);
			l2 = l2.sledeci;
		}

		return nova;
	}

	public void invertujBezPomocne(CvorDSListe prvi) {
		if (prvi == null)
			return;
		CvorDSListe pom = prvi;
		CvorDSListe temp = prvi;
		while (pom.sledeci != null) {
			temp = pom.sledeci;
			pom.sledeci = temp.sledeci;
			temp.sledeci = prvi;
			prvi = temp;
		}
		ListGenerator.ispisiDSListu(prvi);
	}

	public int veciZbir(CvorDSListe prvi, CvorDSListe cvor) throws LabisException {
		if (prvi == null || cvor == null || prvi.sledeci == null)
			throw new LabisException();
		int zbirl = 0;
		int zbird = 0;

		while (prvi.sledeci != cvor) {
			zbirl += prvi.podatak;
			prvi = prvi.sledeci;
		}
		prvi = cvor.sledeci;
		while (prvi != null) {
			zbird += prvi.podatak;
			prvi = prvi.sledeci;
		}

		return zbirl > zbird ? zbirl : zbird;
	}

	public static void main(String[] args) {
		DSLista l = new DSLista();
		int niz[] = { 10, 8, 44, 123, 5, 44, 123, 66 };
		ListGenerator.napraviDSListuCommon(l, niz, false);
		DSLista l2 = new DSLista();
		int elementi[] = { 10, 8, 44 };
		ListGenerator.napraviDSListuCommon(l2, elementi, false);
		ListGenerator.ispisiDSListu(l.prvi);
		System.out.println("\n\n");
		// ListGenerator.ispisiDSListu(l.saberiDveUJednu(l.prvi, l2.prvi));
		l.invertujBezPomocne(l.prvi);
		// try {
		//
		// } catch (LabisException e) {
		// // TODO Auto-generated catch block
		// e.printStackTrace();
		// }
	}
}
