package klk2018;

import labis.cvorovi.CvorJSListe;
import labis.liste.AJSLista;
import labis.test.ListGenerator;

public class JSLista extends AJSLista {

	public void izbaciDuplikate(CvorJSListe k) {
		if (k == null || k.sledeci == null)
			return;
		while (k.sledeci != null) {
			if (k.podatak == k.sledeci.podatak) {
				k.sledeci = k.sledeci.sledeci;
			} else
				k = k.sledeci;
		}
	}

	public static void main(String[] args) {
		int niz[] = { 11, 11, 11, 11, 11, 12, 12, 12, 13, 14, 14, 14, 14, 14, 14 };
		JSLista l = new JSLista();
		ListGenerator.napraviJSListuCommon(l, niz, false);
		ListGenerator.ispisiJSListu(l.prvi);
		System.out.println("\n\n");
		l.izbaciDuplikate(l.prvi);
		ListGenerator.ispisiJSListu(l.prvi);
	}
}
