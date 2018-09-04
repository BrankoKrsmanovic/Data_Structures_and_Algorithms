package LinearneStrukture.NiziLista;

import Cvorovi.CvorJSListe;

public class JSLista {

	CvorJSListe prvi;

	public boolean praznaLista() {
		return prvi == null;
	}
	
	public void stampajListu(CvorJSListe cvor) {
		CvorJSListe pom = cvor;
		while(pom!=null) {
			System.out.print(pom.podatak + "\t");
			pom = pom.sledeci;
		}
		System.out.println();
	}
	
	public void ubaciNaPocetak(int e) {
		prvi = new CvorJSListe(e, prvi);
	}
	
	public int brojVecihOdPrvog() throws Exception{
		if(praznaLista())
			throw new Exception("Lista je prazna!!!");
		
		CvorJSListe pom = prvi;
		int brojac = 0;
		while(pom != null) {
			if(pom.podatak > prvi.podatak)
				brojac++;
			pom = pom.sledeci;
		}
		return brojac;
	}
	
	public int brojPonavljanja(int e) throws Exception{
		if(praznaLista())
			throw new Exception("Lista je prazna!!!");
		CvorJSListe pom = prvi;
		int brojac = 0;
		while(pom != null) {
			if(pom.podatak == e)
				brojac++;
			pom = pom.sledeci;
		}
		return brojac;
	}
	
	private CvorJSListe min() {
		CvorJSListe minimum = prvi;
		CvorJSListe pom = prvi;
		while(pom.sledeci != null) {
			if(minimum.podatak >= pom.sledeci.podatak)
				minimum = pom.sledeci;
			pom = pom.sledeci;
		}
		return minimum;
	}
	
	public void izbaciNakonMin() throws Exception{
		if(praznaLista())
			throw new Exception("Lista je prazna!!!");
	
		CvorJSListe pom = min();
		pom.sledeci = pom.sledeci.sledeci;
	}

	public int brojPozitivnih() throws Exception{
		if(praznaLista())
			throw new Exception("Lista je prazna!!!");
		
		return brojPozitivnihR(prvi);
	}
	
	private int brojPozitivnihR(CvorJSListe cvor) {
		if(cvor == null)
			return 0;
		if(cvor.podatak > 0)
			return 1 + brojPozitivnihR(cvor.sledeci);
		return brojPozitivnihR(cvor.sledeci);
	}

	public void invertujSaPomocnom() throws Exception{
		if(praznaLista() || prvi.sledeci == null)
			throw new Exception("Nema sta da se invertuje!!!");
		
		CvorJSListe pom = prvi;
		CvorJSListe novi = null;
		
		while(pom != null) {
			novi = new CvorJSListe(pom.podatak, novi);
			pom = pom.sledeci;
		}
		stampajListu(novi);
	}
	
	public void invertujBezPomocne() throws Exception {
		if(praznaLista() || prvi.sledeci == null)
			throw new Exception("Nema sta da se invertuje!!!");
		
		CvorJSListe pom = prvi;
		CvorJSListe tek = prvi;
		
		while(pom.sledeci != null) {
			tek = pom.sledeci;
			pom.sledeci = tek.sledeci;
			tek.sledeci = prvi;
			prvi = tek;
		}
	}
	
	public void ispisiObrnuto(CvorJSListe pom) {
		if(pom == null)
			return;
		ispisiObrnuto(pom.sledeci);
		System.out.println(pom.podatak + " ->");
	}
	
	public CvorJSListe klonirajR(CvorJSListe pom) {
		if(pom == null)
			return null;
		return new CvorJSListe(pom.podatak, klonirajR(pom.sledeci));
	}

	public int[] nizOdListe() throws Exception{
		if(prvi == null)
			throw new Exception();
		CvorJSListe pom = prvi;
		int brel = 0;
		while(pom != null) {
			brel++;
			pom = pom.sledeci;
		}
		int niz[] = new int[brel];
		pom = prvi;
		for (int i = 0; i < niz.length; i++) {
			niz[i] = pom.podatak;
			pom = pom.sledeci;
		}
		return niz;
	}

	public CvorJSListe unijaListi(CvorJSListe p1, CvorJSListe p2) {
		if(p1 != null)
			return new CvorJSListe(p1.podatak, unijaListi(p1.sledeci, p2));
		if(p2 != null)
			return new CvorJSListe(p2.podatak, unijaListi(p1, p2.sledeci));
		return null;
	}
	
	public CvorJSListe odRastucihOpadajuca(CvorJSListe p1, CvorJSListe p2) throws Exception {
		if(p1 == null && p2 == null)
			throw new Exception();
		CvorJSListe p3 = null;
		CvorJSListe pom1 = p1;
		CvorJSListe pom2 = p2;
		while(pom1!= null && pom2!= null) {
			if(pom1.podatak <= pom2.podatak) {
				p3 = new CvorJSListe(pom1.podatak, p3);
				pom1 = pom1.sledeci;
			}else {
				p3 = new CvorJSListe(pom2.podatak, p3);
				pom2 = pom2.sledeci;
			}
		}
		while(pom1 != null) {
			p3 = new CvorJSListe(pom1.podatak, p3);
			pom1 = pom1.sledeci;
		}
		while(pom2 != null) {
			p3 = new CvorJSListe(pom2.podatak, p3);
			pom2 = pom2.sledeci;
		}
		return p3;
	}
	
	public static void main(String[] args) {
		JSLista l = new JSLista();
		JSLista d = new JSLista();
		
		l.ubaciNaPocetak(55);
		l.ubaciNaPocetak(35);
		l.ubaciNaPocetak(-15);
		l.ubaciNaPocetak(-25);
		l.ubaciNaPocetak(-75);
		
		d.ubaciNaPocetak(12);
		d.ubaciNaPocetak(7);
		d.ubaciNaPocetak(4);
		d.ubaciNaPocetak(3);
		d.ubaciNaPocetak(1);

		l.stampajListu(l.prvi);
		d.stampajListu(d.prvi);
		try {
			l.stampajListu(l.unijaListi(l.prvi, d.prvi));
			//System.out.println(l.brojPozitivnih());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//l.stampajListu();
	}
}
