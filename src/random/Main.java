package random;

import Cvorovi.CvorJSListe;

public class Main {

	CvorListe prvi;

	public void ubaci(CvorListe cvor, int e) {

		if (cvor == null) {
			cvor = new CvorListe();
			cvor.podatak = new CvorJSListe(e, null);
		} else {
			while(cvor != null && cvor.podatak.podatak <= e)
				cvor = cvor.sledeci;
			if(cvor.podatak.podatak <= e) {
				cvor.sledeci = new CvorListe();
				cvor.sledeci.prethodni = cvor;
				cvor.sledeci.podatak = new CvorJSListe(e,null);
			}else {
				CvorJSListe pom = cvor.podatak;
				while(pom.sledeci != null)
					pom = pom.sledeci;
				
				pom.sledeci = new CvorJSListe(e,null); 
			}
		}
	}

	public static void main(String[] args) {

	}
}
