package Jun2018;

import labis.cvorovi.CvorDSListe;
import labis.exception.LabisException;
import labis.liste.ADSLista;
import labis.test.ListGenerator;

public class DSLista extends ADSLista{

	public double prosekPozitivnih() throws LabisException {
		if(prvi == null)
			throw new LabisException();
		CvorDSListe pom = prvi.sledeci;
		double zbir = 0;
		int brojac = 0;
		
		if(prvi.podatak > 0) {
			zbir = prvi.podatak;
			brojac++;
		}
		
		while(pom != prvi) {
			if(pom.podatak > 0) {
				zbir += pom.podatak;
				brojac++;
			}
			pom = pom.sledeci;
		}		
		
		if(brojac == 0)
			throw new LabisException();
		
		return zbir / brojac;
	}
	
	public static void main(String[] args) {
		int niz[] = {8,3,-10,11,6,12};
		DSLista l = new DSLista();
		ListGenerator.napraviDSListuCommon(l, niz, true);
		ListGenerator.ispisiDSListu(l.prvi);
		try {
			System.out.println(l.prosekPozitivnih());
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
}
