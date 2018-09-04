package nelinearneStrukture;

import labis.cvorovi.CvorStabla;
import labis.exception.LabisException;
import labis.generator.StabloGenerator;
import labis.stabla.ABSTStablo;

public class BSTStablo extends ABSTStablo {

	@Override
	public void ubaci(int podatak) throws LabisException {
		ubaciPrivate(koren, podatak);
	}

	private void ubaciPrivate(CvorStabla k, int podatak) throws LabisException {
		if (k == null)
			koren = new CvorStabla(podatak);
		else {
			if (podatak < k.podatak) {
				if (k.levo != null)
					ubaciPrivate(k.levo, podatak);
				else
					k.levo = new CvorStabla(podatak);
			} else if (podatak > k.podatak) {
				if (k.desno != null)
					ubaciPrivate(k.desno, podatak);
				else
					k.desno = new CvorStabla(podatak);
			} else
				throw new LabisException("Vrednost vec postoji u stablu");
		}
	}

	@Override
	public void izbaci(int podatak) throws LabisException {
		CvorStabla cvor = pronadji(koren, podatak);
		
		if(koren == null || cvor == null)
			return; 
		
		if(cvor.levo != null && cvor.desno != null) {
			CvorStabla maxL = maxCvor(cvor.levo);
			cvor.podatak = maxL.podatak;
			izbaciListPolulist(maxL);
		}else
			izbaciListPolulist(cvor);
		
	}

	private void izbaciListPolulist(CvorStabla cvor) throws LabisException {
		CvorStabla roditelj = vratiRoditelja(koren, cvor);
		CvorStabla dete = null; 
		
		if(cvor.levo != null)
			dete = cvor.levo;
		else 
			dete = cvor.desno;
		
		if(roditelj == null)
			koren = dete;
		else {
			if (roditelj.levo == cvor)
				roditelj.levo = dete;
			else
				roditelj.desno = dete;
		}
	}

	public CvorStabla vratiRoditelja(CvorStabla k, CvorStabla neki) throws LabisException {
		if (k == null || k.podatak == neki.podatak)
			return null;
		if ((k.levo != null && k.levo.podatak == neki.podatak) || (k.desno != null && k.desno.podatak == neki.podatak))
			return k;

		CvorStabla pom = vratiRoditelja(k.levo, neki.podatak);

		if (pom != null)
			return pom;
		return vratiRoditelja(k.desno, neki.podatak);
	}

	private CvorStabla pronadji(CvorStabla k, int podatak) {
		if (k == null || k.podatak == podatak)
			return k;

		CvorStabla pom = pronadji(k.levo, podatak);

		if (pom != null)
			return pom;

		return pronadji(k.desno, podatak);
	}

	@Override
	public void ispisiRastuce(CvorStabla k) throws LabisException {
		if (k == null)
			return;
		ispisiRastuce(k.levo);
		System.out.print(k.podatak + "\t");
		ispisiRastuce(k.desno);
	}

	public static void main(String[] args) {

		BSTStablo bst = new BSTStablo();
		StabloGenerator.izgenerisiStablo(bst);

		try {
			bst.ubaci(5);
			bst.ubaci(0);
			bst.ubaci(-15);
			bst.ubaci(6);
			bst.ispisiRastuce(bst.koren);
			System.out.println(bst.pronadji(bst.koren, -125).podatak);
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
