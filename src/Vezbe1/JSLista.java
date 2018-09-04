package Vezbe1;

import labis.cvorovi.CvorJSListe;
import labis.exception.LabisException;
import labis.liste.AJSLista;
import labis.test.ListGenerator;

public class JSLista extends AJSLista {

	@Override
	public void ispisiObrnuto(CvorJSListe prvi) throws LabisException {
		if (prvi == null)
			throw new LabisException();
		obrnutoR(prvi);
	}

	private void obrnutoR(CvorJSListe prvi) {
		if (prvi != null) {
			obrnutoR(prvi.sledeci);
			System.out.println(prvi.podatak);
		}
	}

	@Override
	public void invertovanjeSaPomocnom() throws LabisException {
		if (prvi == null || prvi.sledeci == null)
			throw new LabisException();

		CvorJSListe pom = prvi;
		CvorJSListe novi = null;

		while (pom != null) {
			novi = new CvorJSListe(pom.podatak, novi);
			pom = pom.sledeci;
		}
		ListGenerator.ispisiJSListu(novi);
	}

	@Override
	public void invertovanjeBezPomocne() throws LabisException {
		if (prvi == null || prvi.sledeci == null)
			throw new LabisException();

		CvorJSListe pom = prvi;
		CvorJSListe trenutni = prvi;

		while (pom.sledeci != null) {
			trenutni = pom.sledeci;
			pom.sledeci = trenutni.sledeci;
			trenutni.sledeci = prvi;
			prvi = trenutni;
		}

		ListGenerator.ispisiJSListu(trenutni);
	}

	private boolean postoji(int e) {
		CvorJSListe pom = prvi;
		while (pom != null) {
			if (pom.podatak == e)
				return true;
			pom = pom.sledeci;
		}
		return false;
	}

	@Override
	public int izbaciTrenutni(CvorJSListe t) throws LabisException {
		if (t == null || !postoji(t.podatak))
			throw new LabisException();
		if (prvi.podatak == t.podatak) {
			prvi = prvi.sledeci;
			ListGenerator.ispisiJSListu(prvi);
			return t.podatak;
		}
		CvorJSListe pom = prvi;
		while (pom.sledeci != null) {
			if (pom.sledeci.podatak == t.podatak) {
				pom.sledeci = pom.sledeci.sledeci;
				break;
			}
			pom = pom.sledeci;
		}
		ListGenerator.ispisiJSListu(prvi);
		return t.podatak;
	}

	@Override
	public CvorJSListe klonirajRekurzivno(CvorJSListe pom) throws LabisException {
		if(pom == null || pom.sledeci == null)
			return pom;
		return new CvorJSListe(pom.podatak, klonirajRekurzivno(pom.sledeci));
	}
	

	@Override
	public int zbirElemenataUCiklicnoj() throws LabisException {
		if(prvi == null)
			throw new LabisException();
		CvorJSListe pom = prvi;
		int zbir = 0;
		while(pom.sledeci != prvi) {
			zbir += pom.podatak;
			pom = pom.sledeci;
		}
		return zbir;
	}

	public static void main(String[] args) {
		int niz[] = new int[] { 4, 5, -1, 12 };
		JSLista l = new JSLista();
		ListGenerator.napraviJSListuCommon(l, niz, false);
		ListGenerator.ispisiJSListu(l.prvi);

		System.out.println("-----------------------");

		try {
			ListGenerator.ispisiJSListu(l.klonirajRekurzivno(l.prvi));
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
