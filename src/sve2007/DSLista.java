package sve2007;

import labis.cvorovi.CvorDSListe;
import labis.liste.ADSLista;
import labis.test.ListGenerator;

public class DSLista extends ADSLista{

	public void ubaciPreDatog(CvorDSListe neki, int podatak) {
		if(prvi == null || neki == null)
			return;
		if(neki == prvi) {
			prvi = new CvorDSListe(podatak, null, prvi);
			return;
		}
		CvorDSListe pom = prvi;
		
		while(pom.sledeci != null && pom.sledeci.podatak != neki.podatak)
			pom = pom.sledeci;
		
		CvorDSListe novi = new CvorDSListe(podatak, pom, neki);
		pom.sledeci = novi;
		neki.prethodni = novi;
	}
	
	public void ubaciNaKrajCiklicne(CvorDSListe k, int p) {
		if(k == null) {
			prvi = new CvorDSListe(p, prvi, prvi);
			prvi.prethodni = prvi;
			prvi.sledeci = prvi;
		}else {
			CvorDSListe novi = new CvorDSListe(p, prvi.prethodni, prvi);
			prvi.prethodni.sledeci = novi;
			prvi.prethodni = novi;
		}
	}
	
	
	
	
	public static void main(String[] args) {
		int niz[] = null;
		DSLista l = new DSLista();
		
		ListGenerator.napraviDSListuCommon(l, niz, true);
		//l.ubaciPreDatog(l.prvi.sledeci.sledeci.sledeci, 5);
		l.ubaciNaKrajCiklicne(l.prvi, 5);
		l.ubaciNaKrajCiklicne(l.prvi, 7);
		l.ubaciNaKrajCiklicne(l.prvi, 8);
		l.ubaciNaKrajCiklicne(l.prvi, 6);
		ListGenerator.ispisiDSListu(l.prvi);
		
//		System.out.println(l.prvi.prethodni.podatak);
	
	}
}
