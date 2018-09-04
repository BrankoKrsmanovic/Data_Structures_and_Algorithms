package jun2016;

import labis.cvorovi.CvorJSListe;
import labis.liste.AJSLista;
import labis.test.ListGenerator;

public class JSLista extends AJSLista{
	
	public void ubaciElement(int podatak) {
		if(prvi == null || prvi.podatak >= podatak) {
			prvi = new CvorJSListe(podatak, prvi);
			return;
		}
		int zbir = prvi.podatak;
		CvorJSListe pom = prvi;
		
		while(pom.sledeci != null) {
			if(zbir >= podatak)
				break;
			zbir+= pom.sledeci.podatak;
			pom = pom.sledeci;
		}
		
		CvorJSListe novi = new CvorJSListe(podatak, pom.sledeci);
		pom.sledeci = novi;
		
	}

	public static void main(String[] args) {
		JSLista l = new JSLista();
		int niz[] = { 11, 8, 44, 123, 5, 44, 123, 66 };
		ListGenerator.napraviJSListuCommon(l, niz, false);
		ListGenerator.ispisiJSListu(l.prvi);
		System.out.println("\n\n\n");
	
		l.ubaciElement(5);
		ListGenerator.ispisiJSListu(l.prvi);
		
	}
}
