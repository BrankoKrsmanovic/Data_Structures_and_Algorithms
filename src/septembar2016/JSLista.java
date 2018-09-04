package septembar2016;

import labis.cvorovi.CvorJSListe;
import labis.liste.AJSLista;
import labis.test.ListGenerator;

public class JSLista extends AJSLista {

	public void izbaciPreNekog(CvorJSListe neki) {
		if (prvi == null || neki == null || prvi.sledeci == null || prvi.podatak == neki.podatak)
			return;
		if (prvi.sledeci.podatak == neki.podatak) {
			prvi = prvi.sledeci;
			return;
		}
		CvorJSListe pom = prvi;
		while (pom.sledeci.sledeci != null) {
			if (pom.sledeci.sledeci.podatak == neki.podatak) {
				pom.sledeci = neki;
				return;
			}
			pom = pom.sledeci;
		}
	}

	public void ubaciPreProizvoda(int podatak) {
		if (prvi == null || prvi.podatak >= podatak) {
			prvi = new CvorJSListe(podatak, prvi);
			return;
		}
		
		CvorJSListe pom = prvi; 
		int proizvod = prvi.podatak;
		
		while(pom.sledeci != null) {
			proizvod *= pom.sledeci.podatak;
			if(proizvod >= podatak) {
				pom.sledeci = new CvorJSListe(podatak, pom.sledeci);
				return;
			}
			pom = pom.sledeci;
		}
		
		pom.sledeci = new CvorJSListe(podatak, null);
	}

	public static void main(String[] args) {
		JSLista l = new JSLista();
		int niz[] = { 11, 8, 4, 55 };
		ListGenerator.napraviJSListuCommon(l, niz, false);
		ListGenerator.ispisiJSListu(l.prvi);
		System.out.println("\n\n\n");

		l.ubaciPreProizvoda(40000);
		ListGenerator.ispisiJSListu(l.prvi);
		
	
	}
}
