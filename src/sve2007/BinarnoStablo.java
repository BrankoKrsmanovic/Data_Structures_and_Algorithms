package sve2007;

import labis.cvorovi.CvorStabla;
import labis.exception.LabisException;
import labis.generator.StabloGenerator;
import labis.stabla.ABinarnoStablo;

public class BinarnoStablo extends ABinarnoStablo {

	public void transformisiUMaxHeap(CvorStabla k) {
		if (k == null)
			return;
		int pom;
		if (k.levo != null && k.podatak < k.levo.podatak) {
			pom = k.podatak;
			k.podatak = k.levo.podatak;
			k.levo.podatak = pom;
		}
		if (k.desno != null && k.desno.podatak > k.podatak) {
			pom = k.podatak;
			k.podatak = k.desno.podatak;
			k.desno.podatak = pom;
		}
		transformisiUMaxHeap(k.levo);
		transformisiUMaxHeap(k.desno);
	}

	public void infiks(CvorStabla k) {
		if (k == null)
			return;
		infiks(k.levo);
		System.out.print(k.podatak + "\t");
		infiks(k.desno);
	}

	public boolean slicnaUOgledalu(CvorStabla k1, CvorStabla k2) {
		if (k1 == null && k2 == null)
			return true;
		if (k1 == null || k2 == null)
			return false;

		return slicnaUOgledalu(k1.levo, k2.desno) && slicnaUOgledalu(k1.desno, k2.levo);
	}

	public void ubaci(CvorStabla k, int podatak) {
		if (k == null) {
			koren = new CvorStabla(podatak);
			return;
		}
		if (k.podatak > podatak) {
			if (k.levo == null) {
				k.levo = new CvorStabla(podatak);
				return;
			} else {
				ubaci(k.levo, podatak);
				return;
			}
		} else {
			if (k.desno == null) {
				k.desno = new CvorStabla(podatak);
				return;
			} else {
				ubaci(k.desno, podatak);
				return;
			}
		}
	}

	public int zbir(CvorStabla k) {
		if (k == null)
			return 0;

		return k.podatak + zbir(k.levo) + zbir(k.desno);
	}

	public int leviZbirVeciOdDesnog(CvorStabla k) {
		if (k == null)
			return 0;

		if (zbir(k.levo) > zbir(k.desno))
			return 1 + leviZbirVeciOdDesnog(k.levo) + leviZbirVeciOdDesnog(k.desno);
		return leviZbirVeciOdDesnog(k.levo) + leviZbirVeciOdDesnog(k.desno);
	}

	private boolean daLiPostoji(CvorStabla k, CvorStabla neki) {
		if (k == null || neki == null)
			return false;
		if (k.podatak == neki.podatak)
			return true;
		return daLiPostoji(k.levo, neki) || daLiPostoji(k.desno, neki);
	}

	public CvorStabla maxElementNaPutanji(CvorStabla k, CvorStabla neki) throws LabisException {
		if (neki == null)
			throw new LabisException();
		if (k == null || k == neki)
			return k;
		Pom p = new Pom();
		maxElementNaPutanjiPrivate(k, neki, p);

		return p.max;
	}

	private class Pom {
		CvorStabla max = new CvorStabla(Integer.MIN_VALUE);
	}

	private void maxElementNaPutanjiPrivate(CvorStabla k, CvorStabla neki, Pom p) {
		if (k == null || k == neki)
			return;
		if (k.podatak > p.max.podatak)
			p.max = k;
		if (daLiPostoji(k.levo, neki))
			maxElementNaPutanjiPrivate(k.levo, neki, p);
		else
			maxElementNaPutanjiPrivate(k.desno, neki, p);
	}

	public static void main(String[] args) {
		BinarnoStablo s = new BinarnoStablo();
		StabloGenerator.izgenerisiStablo(s);
		s.infiks(s.koren);
		System.out.println("\n\n");

		BinarnoStablo stablo = new BinarnoStablo();

		stablo.ubaci(stablo.koren, 100);
		stablo.ubaci(stablo.koren, 90);
		stablo.ubaci(stablo.koren, 80);
		stablo.ubaci(stablo.koren, 85);
		stablo.ubaci(stablo.koren, 200);
		stablo.ubaci(stablo.koren, 150);
		stablo.ubaci(stablo.koren, 170);
		stablo.ubaci(stablo.koren, 120);
		stablo.ubaci(stablo.koren, 135);
		stablo.ubaci(stablo.koren, 210);
		stablo.ubaci(stablo.koren, 250);
		stablo.ubaci(stablo.koren, 230);

		stablo.ubaci(stablo.koren, 153);
		stablo.ubaci(stablo.koren, 145);
		/**
		 * stablo.ubaci(stablo.koren, 170); stablo.ubaci(stablo.koren, 45);
		 * stablo.ubaci(stablo.koren, 53); stablo.ubaci(stablo.koren, 60);
		 * stablo.ubaci(stablo.koren, 70); stablo.ubaci(stablo.koren, 76);
		 * stablo.ubaci(stablo.koren, 79); stablo.ubaci(stablo.koren, 83);
		 * stablo.ubaci(stablo.koren, 90); stablo.ubaci(stablo.koren, 140);
		 * stablo.ubaci(stablo.koren, 147); stablo.ubaci(stablo.koren, 151);
		 * stablo.ubaci(stablo.koren, 154); stablo.ubaci(stablo.koren, 157);
		 * stablo.ubaci(stablo.koren, 163); stablo.ubaci(stablo.koren, 167);
		 * stablo.ubaci(stablo.koren, 175);
		 */
		stablo.infiks(stablo.koren);

		StabloGenerator.zameniVrednost(s.koren, 113, 250);
		StabloGenerator.zameniVrednost(s.koren, 56, 2500);
		System.out.println("\n\n");

		try {
			System.out.println(s.maxElementNaPutanji(s.koren, s.koren.levo.desno.desno).podatak);
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
