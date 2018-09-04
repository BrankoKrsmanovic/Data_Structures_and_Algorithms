package Iklk2017;

import labis.cvorovi.CvorJSListe;
import labis.exception.LabisException;
import labis.liste.AJSLista;
import labis.test.ListGenerator;

public class JSLista extends AJSLista {

	public void izbaciPrePoslednjegNajveceg() throws LabisException {
		if (prvi == null)
			throw new LabisException();

		CvorJSListe pom = prvi;
		CvorJSListe t = prvi;
		CvorJSListe max = max(prvi);
		if (max.equals(prvi))
			return;
		if (max.equals(t.sledeci)) {
			prvi = prvi.sledeci;
			return;
		}
		while (t.sledeci.sledeci != null) {
			if (t.sledeci.sledeci.equals(max))
				pom = t;
			t = t.sledeci;
		}
		if (pom.sledeci != null)
			pom.sledeci = pom.sledeci.sledeci;
	}
	
	private CvorJSListe max(CvorJSListe prvi) {
		if (prvi == null)
			return prvi;

		CvorJSListe pom = prvi;
		while (prvi != null) {
			if (pom.podatak <= prvi.podatak)
				pom = prvi;
			prvi = prvi.sledeci;
		}

		return pom;
	}
	
	public static void main(String[] args) {
		JSLista l = new JSLista();
		int niz[] = { 1111, 501, 44, 12, 56, 18, 501 };
		ListGenerator.napraviJSListuCommon(l, niz, false);
		ListGenerator.ispisiJSListu(l.prvi);
		System.out.println("\n\n\n");
		try {
			l.izbaciPrePoslednjegNajveceg();

			ListGenerator.ispisiJSListu(l.prvi);
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
