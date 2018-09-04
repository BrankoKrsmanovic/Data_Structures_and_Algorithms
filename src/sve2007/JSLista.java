package sve2007;

import labis.cvorovi.CvorJSListe;
import labis.liste.AJSLista;
import labis.test.ListGenerator;

public class JSLista extends AJSLista {

	public CvorJSListe odRastucihOpadajuca(CvorJSListe k1, CvorJSListe k2) {
		if (k1 == null && k2 == null)
			return null;
		CvorJSListe l3 = null;
		while (k1 != null && k2 != null) {
			if (k1.podatak < k2.podatak) {
				l3 = new CvorJSListe(k1.podatak, l3);
				k1 = k1.sledeci;
			} else {
				l3 = new CvorJSListe(k2.podatak, l3);
				k2 = k2.sledeci;
			}
		}

		while(k1 != null) {
			l3 = new CvorJSListe(k1.podatak, l3);
			k1 = k1.sledeci;
		}
		while(k2 != null) {
			l3 = new CvorJSListe(k2.podatak, l3);
			k2 = k2.sledeci;
		}
		return l3;
	}

	private CvorJSListe nadjiMax(CvorJSListe k){
		if(k == null)
			return null;
		CvorJSListe pom = k;
		while(k != null) {
			if(k.podatak > pom.podatak)
				pom = k;
			k = k.sledeci;
		}
		return pom;
	}
	
	public void ubaciNakonMax(CvorJSListe k, int p){
		if(k == null){
			prvi = new CvorJSListe(p, null);
			return;
		}
		CvorJSListe max = nadjiMax(k);
		if(max == prvi) {
			CvorJSListe novi = new CvorJSListe(p, prvi.sledeci);
			prvi.sledeci = novi;
			return;
		}
		while(k.sledeci != null && k.sledeci != max) 
			k = k.sledeci;
		CvorJSListe novi = new CvorJSListe(p, k.sledeci.sledeci);
		k.sledeci.sledeci = novi;
	}
	
	public static void main(String[] args) {
		int niz[] = { 100, 200, 23, 45, 50};
		int arr[] = { 2, 3, 37, 40, 50, 86, 99, 111 };
		JSLista l1 = new JSLista();
		JSLista l2 = new JSLista();
		ListGenerator.napraviJSListuCommon(l1, niz, false);
		ListGenerator.napraviJSListuCommon(l2, arr, false);

		ListGenerator.ispisiJSListu(l1.prvi);
		System.out.println("\n");
		ListGenerator.ispisiJSListu(l2.prvi);
		System.out.println("\n");
		l1.ubaciNakonMax(l1.prvi, 22);
		ListGenerator.ispisiJSListu(l1.prvi);
		System.out.println("\n");
	}
}
