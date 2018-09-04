package random;

import labis.cvorovi.CvorJSListe;
import labis.exception.LabisException;
import labis.liste.AJSLista;
import labis.test.ListGenerator;

public class JSLista extends AJSLista{

	public int maxZbirZaSvakiPar(CvorJSListe k1, CvorJSListe k2) throws LabisException{
		if(k1 == null && k2 == null)
			throw new LabisException();
		int zbir = 0;
		int max = Integer.MIN_VALUE;
		while(k1 != null && k2 != null) {
			zbir = k1.podatak + k2.podatak;
			if(zbir >= max)
				max = zbir;
			k1 = k1.sledeci;
			k2 = k2.sledeci;
		}
		while(k1 != null) {
			if(k1.podatak > max)
				max = k1.podatak;
			k1 = k1.sledeci;
		}
		while(k2 != null) {
			if(k2.podatak > max)
				max = k2.podatak;
			k2 = k2.sledeci;
		}
		return max;
	}
	
	public static void main(String[] args) {
		JSLista l1 = new JSLista();
		JSLista l2 = new JSLista();
		int niz1[] = {1,-1,2,20,15,60};
		int niz2[] = {13,39,2,2,10};
		
		ListGenerator.napraviJSListuCommon(l1, niz1, false);
		ListGenerator.napraviJSListuCommon(l2, niz2, false);
	
	
	}
}
