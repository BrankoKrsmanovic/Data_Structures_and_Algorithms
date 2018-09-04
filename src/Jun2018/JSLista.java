package Jun2018;

import labis.cvorovi.CvorJSListe;
import labis.liste.AJSLista;
import labis.test.ListGenerator;

public class JSLista extends AJSLista{
	
	public void ubaciPreProizvoda(int podatak) {
		if(prvi == null || prvi.podatak >= podatak) {
			prvi = new CvorJSListe(podatak, prvi);
			return;
		}
		CvorJSListe pom = prvi;
		int proizvod = prvi.podatak;
		
		while(pom.sledeci != null) {
			proizvod *= pom.sledeci.podatak;
			if(proizvod >= podatak)
				break;
			pom = pom.sledeci;
		}
		
		CvorJSListe novi = new CvorJSListe(podatak, pom.sledeci);
		pom.sledeci = novi;
	}

	public static void main(String[] args) {
		int niz[] = null;
		JSLista l = new JSLista();
		ListGenerator.napraviJSListuCommon(l, niz, true);
		//ListGenerator.ispisiJSListu(l.prvi);
		l.ubaciPreProizvoda(8);
		l.ubaciPreProizvoda(3);
		l.ubaciPreProizvoda(555);
		l.ubaciPreProizvoda(55);
		ListGenerator.ispisiJSListu(l.prvi);
//		try {
//			System.out.println(l.prosekPozitivnih());
//		} catch (LabisException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
		
	}
}
