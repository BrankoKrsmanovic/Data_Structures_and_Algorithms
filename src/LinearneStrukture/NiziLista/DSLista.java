package LinearneStrukture.NiziLista;

import Cvorovi.CvorDSListe;

public class DSLista {

	CvorDSListe prvi;

	public boolean praznaLista() {
		return prvi == null;
	}

	public void stampajListu(CvorDSListe cvor) {
		CvorDSListe pom = cvor;
		while (pom != null) {
			System.out.print(pom.podatak + "\t");
			pom = pom.sledeci;
		}
		System.out.println();
	}

	public void stampajListuSaGranicom(CvorDSListe cvor, CvorDSListe kraj) {
		CvorDSListe pom = cvor;
		while (pom != kraj) {
			System.out.print(pom.podatak + "\t");
			pom = pom.sledeci;
		}
		System.out.println();
	}

	public void ubaciNaKraj(int e) {
		if (prvi == null) {
			prvi = new CvorDSListe(e, null, null);
			return;
		}
		CvorDSListe pom = prvi;

		while (pom.sledeci != null)
			pom = pom.sledeci;

		pom.sledeci = new CvorDSListe(e, pom, null);

	}

	public CvorDSListe max() {
		if (prvi == null)
			return null;
		CvorDSListe max = prvi;
		CvorDSListe pom = prvi;
		while (pom != null) {
			if (max.podatak <= pom.podatak)
				max = pom;
			pom = pom.sledeci;
		}
		return max;
	}

	public CvorDSListe maxNaPocetak() throws Exception {
		if (prvi == null)
			throw new Exception("Lista je prazna!!!");

		CvorDSListe cvor = max();
		CvorDSListe pom = prvi;
		while (pom.sledeci != cvor)
			pom = pom.sledeci;

		pom.sledeci = pom.sledeci.sledeci;
		cvor.sledeci.prethodni = cvor.prethodni;
		prvi.prethodni = cvor;
		cvor.sledeci = prvi;
		cvor.prethodni = null;
		prvi = cvor;

		return prvi;

	}

	public void veciDeo(CvorDSListe cvor) throws Exception {
		if (prvi == null || prvi.sledeci == null || cvor == null)
			throw new Exception();
		int zbir1 = 0;
		int zbir2 = 0;
		CvorDSListe pom = prvi;

		if (cvor == prvi) {
			stampajListu(cvor.sledeci);
			return;
		}

		if (cvor.sledeci == null) {
			stampajListuSaGranicom(prvi, cvor);
			return;
		}

		while (pom.sledeci != cvor) {
			zbir1 += pom.podatak;
			pom = pom.sledeci;
		}

		pom = cvor.sledeci;

		while (pom != null) {
			zbir2 += pom.podatak;
			pom = pom.sledeci;
		}

		if (zbir1 > zbir2)
			stampajListuSaGranicom(prvi, cvor);
		else
			stampajListu(cvor.sledeci);
	}

	public boolean palindrom(CvorDSListe cvor) throws Exception {
		if (cvor == null)
			throw new Exception();

		CvorDSListe brzi = prvi;
		CvorDSListe spori = prvi;
		// CvorDSListe pom = prvi;
		int brojac = 0;

		while (spori != null) {
			brojac++;
			spori = spori.sledeci;
		}
		System.out.println("Brojac: " + brojac);

		spori = prvi;

		if (brojac % 2 == 0) {
			while (brzi.sledeci.sledeci != null) {
				spori = spori.sledeci;
				brzi = brzi.sledeci.sledeci;
			}
			brzi = spori.sledeci;
		} else {
			while (brzi.sledeci != null) {
				spori = spori.sledeci;
				brzi = brzi.sledeci.sledeci;
			}
			brzi = spori.sledeci;
			spori = spori.prethodni;
		}

		while (spori != null) {
			if (spori.podatak != brzi.podatak)
				return false;
			spori = spori.prethodni;
			brzi = brzi.sledeci;
		}
		return true;
	}

	public void popuni(CvorDSListe cvor) throws Exception{
		if(cvor == null || cvor.sledeci == null)
			throw new Exception();
		
		CvorDSListe pom = prvi; 
		
		while(pom.sledeci != null) {
			if(pom.sledeci.podatak - pom.podatak > 1 ) {
				CvorDSListe novi = new CvorDSListe(pom.podatak + 1, pom, pom.sledeci);
				pom.sledeci = novi;
				pom.sledeci.prethodni = novi;
			}
			pom = pom.sledeci;
		}
	}
	
	public void ubaciNti(int pozicija, int e) throws Exception{
		if(pozicija <= 0)
			throw new Exception();
		
		if(pozicija == 1) {
			CvorDSListe novi = new CvorDSListe(e, null, prvi);
			prvi.prethodni = novi;
			prvi = novi;
			return;
		}
		
		CvorDSListe pom = prvi;
		int brojac = 1;
		
		while(pom != null && brojac < pozicija) {
			System.out.println("PODATAK: " + pom.podatak + "\tBROJAC: " + brojac);
			brojac++;
			pom = pom.sledeci;
		}
		
		if(brojac == pozicija) {
			CvorDSListe novi = new CvorDSListe(e, pom.prethodni, pom);
			pom.prethodni.sledeci = novi;
			pom.prethodni = novi;
		}else {
			ubaciNaKraj(e);
		}
	}
	
	public void invertujListu() throws Exception{
		if(prvi == null || prvi.sledeci == null)
			throw new Exception();
		
		CvorDSListe pom = prvi;
		CvorDSListe novi = null;
		
		while(pom != null) {
			novi = new CvorDSListe(pom.podatak, null, novi);
			if(novi.sledeci != null)
				novi.sledeci.prethodni = novi;
			pom = pom.sledeci;
		}
		stampajListu(novi);
	}	

	private boolean postoji(int e, CvorDSListe lista) {
		if(lista == null)
			return false;
		
		while(lista != null) {
			if(e == lista.podatak)
				return true;
			lista = lista.sledeci;
		}
		return false;
	}
	
	public CvorDSListe razlikaSkupova(CvorDSListe c1, CvorDSListe c2) throws Exception{
		if(c1 == null)
			throw new Exception();
		CvorDSListe novi = null;
		
		while(c1 != null) {
			if(!postoji(c1.podatak, c2))
				novi = new CvorDSListe(c1.podatak, null, novi);
			if(novi != null && novi.sledeci != null)
				novi.sledeci.prethodni = novi;
			c1 = c1.sledeci;
		}
		return novi;
		
	}
	
	public int zbirElemenataKojiSePonavaljaju() throws Exception{
		if(prvi == null || prvi.sledeci == null)
			throw new Exception();
		int zbir = 0;
		CvorDSListe pom = prvi;
		CvorDSListe novi = null;
		
		while(pom != null) {
			if(postoji(pom.podatak, pom.sledeci) && !postoji(pom.podatak, novi)) {
				zbir += pom.podatak;
				novi = new CvorDSListe(pom.podatak, null, novi);
				if(novi != null && novi.sledeci != null)
					novi.sledeci.prethodni = novi;
			}
			pom = pom.sledeci;
		}
		return zbir;
	}
	
	public static void main(String[] args) {
		DSLista l = new DSLista();
//		DSLista m = new DSLista();
		l.ubaciNaKraj(5);
		l.ubaciNaKraj(7);
		l.ubaciNaKraj(12);
//		l.ubaciNaKraj(2);
//		l.ubaciNaKraj(5);
//		l.ubaciNaKraj(2);
//		m.ubaciNaKraj(5);

		l.stampajListu(l.prvi);

		try {
			l.popuni(l.prvi);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		l.stampajListu(l.prvi);
	}
}
