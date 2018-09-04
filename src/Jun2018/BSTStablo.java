package Jun2018;

import labis.cvorovi.CvorStabla;
import labis.exception.LabisException;
import labis.generator.StabloGenerator;
import labis.stabla.ABSTStablo;

public class BSTStablo extends ABSTStablo {

	public void stampajUnutrasnjeOpadajuce(CvorStabla k) {
		if (k == null)
			return;
		stampajUnutrasnjeOpadajuce(k.desno);
		if (k.levo != null || k.desno != null)
			System.out.print(k.podatak + "\t");
		stampajUnutrasnjeOpadajuce(k.levo);
	}

	/**
	 * private int nivoCvora(CvorStabla k, CvorStabla neki) { if (k == null || neki
	 * == null) return 0;
	 * 
	 * if (k == neki) return 1; if (daLiPostoji(k.levo, neki)) return 1 +
	 * nivoCvora(k.levo, neki); else return 1 + nivoCvora(k.desno, neki); }
	 */

	/**
	 * Zbir unutrasnjih cvorova koji imaju samo jedno dete na zadatom nivou
	 */
	
	public int zbirNaNivou(CvorStabla k, int nivo) throws LabisException{
		if (k == null || (k.levo == null && k.desno == null) || nivo < 1)
			return 0;
		int zbir = 0;
		if ((k.levo == null || k.desno == null) && nivo == 1)
			zbir = k.podatak;
		else
			zbir += zbirNaNivou(k.levo, nivo - 1) + zbirNaNivou(k.desno, nivo - 1);
		return zbir;
	}

	private boolean daLiPostoji(CvorStabla k, CvorStabla neki) {
		if (k == null || neki == null)
			return false;
		if (k == neki)
			return true;
		return daLiPostoji(k.levo, neki) || daLiPostoji(k.desno, neki);
	}

	public int zbirOdKorenaDoCvora(CvorStabla k, CvorStabla neki) {
		if (k == null || neki == null)
			return 0;

		if (k == neki)
			return k.podatak;
		if (daLiPostoji(k.levo, neki))
			return k.podatak + zbirOdKorenaDoCvora(k.levo, neki);
		else
			return k.podatak + zbirOdKorenaDoCvora(k.desno, neki);
	}

	public void stampajPutanjuIzmedjuDvaCvora(CvorStabla k, CvorStabla n1, CvorStabla n2) throws LabisException {
		if (n1 == null || n2 == null)
			throw new LabisException();
		if (k == null)
			return;
		if (n1 == k) {
			stampajOdKorenaDoDatog(n1, n2);
			return;
		}
		if (n2 == k) {
			stampajOdKorenaDoDatog(n2, n1);
			return;
		}
		CvorStabla pom = lca(k, n1, n2);
		if (daLiPostoji(k.levo, n1))
			stampajOdDatogDoKorena(pom.levo, n1);
		else
			stampajOdDatogDoKorena(pom.desno, n1);
		stampajOdKorenaDoDatog(pom, n2);
	}

	private void stampajOdKorenaDoDatog(CvorStabla k, CvorStabla neki) {
		if (k == null || neki == null)
			return;
		System.out.print(k.podatak + "\t");
		if (k == neki)
			return;
		if (daLiPostoji(k.levo, neki))
			stampajOdKorenaDoDatog(k.levo, neki);
		else
			stampajOdKorenaDoDatog(k.desno, neki);
	}

	private void stampajOdDatogDoKorena(CvorStabla k, CvorStabla neki) {
		if (k == null || neki == null)
			return;
		if (k == neki) {
			System.out.print(k.podatak + "\t");
			return;
		}
		if (daLiPostoji(k.levo, neki))
			stampajOdDatogDoKorena(k.levo, neki);
		else
			stampajOdDatogDoKorena(k.desno, neki);
		System.out.print(k.podatak + "\t");
	}

	private CvorStabla lca(CvorStabla k, CvorStabla n1, CvorStabla n2) throws LabisException {
		if (n1 == null || n2 == null)
			throw new LabisException();
		if (k == null)
			return k;
		if (daLiPostoji(k.levo, n1) && daLiPostoji(k.desno, n2) || daLiPostoji(k.levo, n2) && daLiPostoji(k.desno, n1))
			return k;
		if (daLiPostoji(k.levo, n1) && daLiPostoji(k.levo, n2))
			return lca(k.levo, n1, n2);
		else
			return lca(k.levo, n1, n2);
	}

	/**
	 * Stampaj putanju od korena do lista koja ima najveci proizvod
	 */

	private class Pom {
		CvorStabla cvor = null;
		int max = Integer.MIN_VALUE;
	}

	public void stampajPutanju(CvorStabla k) {
		if (k == null)
			return;
		if (k.levo == null && k.desno == null) {
			System.out.println(k.podatak);
			return;
		}
		Pom p = new Pom();
		najveciProizvodDolista(k, p, 1);
		stampajOdKorenaDoDatog(k, p.cvor);
	}

	private void najveciProizvodDolista(CvorStabla k, Pom p, int proizvod) {
		if(k == null)
			return;
		proizvod *= k.podatak;
		if((k.levo == null && k.desno == null) && proizvod > p.max) {
			p.max = proizvod;
			p.cvor = k;
		}
		najveciProizvodDolista(k.levo, p, proizvod);
		najveciProizvodDolista(k.desno, p, proizvod);
	}
	
	public int proizvodZajednickihPredaka(CvorStabla k, CvorStabla n1, CvorStabla n2) throws LabisException {
		if(n1 == null || n2 == null || koren == n1 || koren == n2)
			throw new LabisException();
		if(k == null)
			return 1;
		if(k.levo == n1 || k.desno == n1 || k.levo == n2 || k.desno == n2)
			return k.podatak;
		if (daLiPostoji(k.levo, n1) && daLiPostoji(k.desno, n2) || daLiPostoji(k.levo, n2) && daLiPostoji(k.desno, n1))
			return k.podatak;
		if (daLiPostoji(k.levo, n1) && daLiPostoji(k.levo, n2))
			return k.podatak * proizvodZajednickihPredaka(k.levo, n1, n2);
		else
			return k.podatak * proizvodZajednickihPredaka(k.desno, n1, n2);
	}
	
	public static void main(String[] args) {
		BSTStablo bs = new BSTStablo();
		StabloGenerator.izgenerisiStablo(bs);

//		 StabloGenerator.zameniVrednost(bs.koren, 217, 2);
//		 StabloGenerator.zameniVrednost(bs.koren, 113, 8);
//		 StabloGenerator.zameniVrednost(bs.koren, 300, 8);
//		 StabloGenerator.zameniVrednost(bs.koren, 342, 4);
//		 StabloGenerator.zameniVrednost(bs.koren, 312, 4);
//		 StabloGenerator.zameniVrednost(bs.koren, 78, 2);
//		 StabloGenerator.zameniVrednost(bs.koren, 56, 9);
//		 StabloGenerator.zameniVrednost(bs.koren, 62, 2);
//		 StabloGenerator.zameniVrednost(bs.koren, 130, 7);
//		 StabloGenerator.zameniVrednost(bs.koren, 126, 3);
//		 StabloGenerator.zameniVrednost(bs.koren, 190, 2);
//		 StabloGenerator.zameniVrednost(bs.koren, 153, 20);

		 try {
			System.out.println(bs.zbirNaNivou(bs.koren, 3));
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
