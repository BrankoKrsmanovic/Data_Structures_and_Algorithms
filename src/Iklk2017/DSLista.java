package Iklk2017;

import labis.liste.ADSLista;
import labis.test.ListGenerator;
import labis.cvorovi.CvorDSListe;
import labis.exception.LabisException;

public class DSLista extends ADSLista {

	public int zbirPozitivnihNaParnimMestima() throws LabisException {
		if (prvi == null)
			throw new LabisException();
		CvorDSListe pom = prvi;
		int zbir = 0;
		int brojac = 0;
		while (pom != null) {
			brojac++;
			if (brojac % 2 == 0 && pom.podatak > 0)
				zbir += pom.podatak;
			pom = pom.sledeci;
		}
		return zbir;
	}

	public static void main(String[] args) {
		DSLista l = new DSLista();
		int niz[] = { 111, 50, 44, 12, 56, 18, 111, 2};
		ListGenerator.napraviDSListuCommon(l, niz, false);
		ListGenerator.ispisiDSListu(l.prvi);
		System.out.println("\n\n\n");

		try {
			System.out.println(l.zbirPozitivnihNaParnimMestima());
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
