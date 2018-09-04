package BojanMartinovic;

import labis.cvorovi.CvorStabla;
import labis.generator.StabloGenerator;
import labis.stabla.ABinarnoStablo;

public class BinarnoStablo extends ABinarnoStablo {

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

	public void infiks(CvorStabla k) {
		if (k == null)
			return;
		infiks(k.levo);
		System.out.print(k.podatak + "\t");
		infiks(k.desno);
	}

	/**
	 * proizvod elemenata koji imaju bar jedno dete
	 */

	public int proizvod(CvorStabla k) {
		if (k == null)
			return 1;
		if (k.levo != null || k.desno != null)
			return k.podatak * proizvod(k.levo) * proizvod(k.desno);
		return 1 * proizvod(k.levo) * proizvod(k.desno);
	}

	/**
	 * koliko u stablu ima polulistova vecih od svog deteta
	 */

	public int prebrojPolulistove(CvorStabla k) {
		if (k == null)
			return 0;
		if ((k.levo != null && k.desno == null && k.podatak > k.levo.podatak)
				|| (k.desno != null && k.levo == null && k.desno.podatak < k.podatak))
			return 1 + prebrojPolulistove(k.levo) + prebrojPolulistove(k.desno);
		return prebrojPolulistove(k.levo) + prebrojPolulistove(k.desno);
	}

	/**
	 * proizvod unutrasnjih cvorova cija su oba deteta listovi
	 */

	public boolean daLiJeList(CvorStabla k) {
		if (k == null)
			return false;
		return k.levo == null && k.desno == null;
	}

	public int proizvodUnutrasnjih(CvorStabla k) {
		if (k == null)
			return 1;
		if (daLiJeList(k.levo) && daLiJeList(k.desno))
			return k.podatak;
		return 1 * proizvodUnutrasnjih(k.levo) * proizvodUnutrasnjih(k.desno);
	}

	/**
	 * max negativan
	 */

	public CvorStabla maxNegativan(CvorStabla k) {
		if (k == null)
			return null;
		CvorStabla max = null;
		CvorStabla maxl = maxNegativan(k.levo);
		CvorStabla maxd = maxNegativan(k.desno);
		if (k.podatak < 0)
			max = k;
		if (max == null || (maxl != null && maxl.podatak > max.podatak))
			max = maxl;
		if (max == null || (maxd != null && maxd.podatak > max.podatak))
			max = maxd;
		return max;
	}

	/**
	 * Najplici polulist
	 */

	private boolean daliPostoji(CvorStabla k, CvorStabla neki) {
		if (k == null || neki == null)
			return false;
		if (k == neki)
			return true;
		return daliPostoji(k.levo, neki) || daliPostoji(k.desno, neki);
	}

	private int nivoCvora(CvorStabla k, CvorStabla neki) {
		if (k == null || neki == null)
			return 0;
		if (k == neki)
			return 1;
		if (daliPostoji(k.levo, neki))
			return 1 + nivoCvora(k.levo, neki);
		else
			return 1 + nivoCvora(k.desno, neki);
	}

	public CvorStabla vratiNajpliciPolulist(CvorStabla k) {
		if (k == null)
			return k;
		CvorStabla min = null;
		CvorStabla l = vratiNajpliciPolulist(k.levo);
		CvorStabla d = vratiNajpliciPolulist(k.desno);
		if ((k.levo == null) != (k.desno == null))
			min = k;
		if (min == null || (l != null && nivoCvora(koren, l) < nivoCvora(koren, min)))
			min = l;
		if (min == null || (d != null && nivoCvora(koren, d) < nivoCvora(koren, min)))
			min = d;
		return min;
	}

	public boolean daLiJEStriktno(CvorStabla k) {
		if (k == null)
			return true;
		if ((k.levo == null) != (k.desno == null))
			return false;
		return daLiJEStriktno(k.levo) && daLiJEStriktno(k.desno);
	}

	public int zbirParnih(CvorStabla k) {
		if (k == null)
			return 0;
		if (k.podatak % 2 == 0)
			return k.podatak + zbirParnih(k.levo) + zbirParnih(k.desno);
		return zbirParnih(k.levo) + zbirParnih(k.desno);
	}

	public void transformisiUMinHeap(CvorStabla k) {
		if (k == null)
			return;
		int pom;
		if (k.levo != null && k.levo.podatak < k.podatak) {
			pom = k.levo.podatak;
			k.levo.podatak = k.podatak;
			k.podatak = pom;
		}
		if (k.desno != null && k.desno.podatak < k.podatak) {
			pom = k.desno.podatak;
			k.desno.podatak = k.podatak;
			k.podatak = pom;
		}
	}


	public static void main(String[] args) {
		BinarnoStablo bs = new BinarnoStablo();
		StabloGenerator.izgenerisiStablo(bs);

		// StabloGenerator.zameniVrednost(bs.koren, 217, 5);
		// StabloGenerator.zameniVrednost(bs.koren, 113, 10);
		// StabloGenerator.zameniVrednost(bs.koren, 300, 2);
		// StabloGenerator.zameniVrednost(bs.koren, 342, 3);
		// StabloGenerator.zameniVrednost(bs.koren, 312, 4);
		// StabloGenerator.zameniVrednost(bs.koren, 78, 9);
		// StabloGenerator.zameniVrednost(bs.koren, 56, 11);
		// StabloGenerator.zameniVrednost(bs.koren, 62, 12);
		// StabloGenerator.zameniVrednost(bs.koren, 130, 17);
		// StabloGenerator.zameniVrednost(bs.koren, 126, 13);
		// StabloGenerator.zameniVrednost(bs.koren, 190, 20);
		// StabloGenerator.zameniVrednost(bs.koren, 153, 200);

		BinarnoStablo stablo = new BinarnoStablo();

		stablo.ubaci(stablo.koren, 20);
		stablo.ubaci(stablo.koren, 7);
		stablo.ubaci(stablo.koren, 5);
		stablo.ubaci(stablo.koren, 8);
		stablo.ubaci(stablo.koren, 3);
		stablo.ubaci(stablo.koren, 23);
		stablo.ubaci(stablo.koren, 100);
		stablo.ubaci(stablo.koren, 12);
		stablo.ubaci(stablo.koren, 17);
		stablo.ubaci(stablo.koren, 27);
		stablo.ubaci(stablo.koren, 40);
		stablo.ubaci(stablo.koren, 150);
		stablo.ubaci(stablo.koren, 75);

		stablo.infiks(stablo.koren);
		System.out.println("\n\n");
		stablo.transformisiUMinHeap(stablo.koren);
		stablo.infiks(stablo.koren);
		
	}
}
