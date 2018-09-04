package nelinearneStrukture;

import labis.cvorovi.CvorStabla;
import labis.exception.LabisException;
import labis.generator.StabloGenerator;
import labis.stabla.ABinarnoStablo;

public class BinarnoStablo extends ABinarnoStablo {

	public boolean praznoStablo() {
		return koren == null;
	}

	public void prefiksniProlaz(CvorStabla cvor) {
		if (cvor == null)
			return;
		System.out.print(cvor.podatak + "\t");
		prefiksniProlaz(cvor.levo);
		prefiksniProlaz(cvor.desno);
	}

	public void infiksniProlaz(CvorStabla cvor) {
		if (cvor == null)
			return;
		infiksniProlaz(cvor.levo);
		System.out.print(cvor.podatak + "\t");
		infiksniProlaz(cvor.desno);
	}

	public void postfiksniProlaz(CvorStabla cvor) {
		if (cvor == null)
			return;
		postfiksniProlaz(cvor.levo);
		postfiksniProlaz(cvor.desno);
		System.out.print(cvor.podatak + "\t");
	}

	public int brojElemenata(CvorStabla cvor) {
		if (cvor == null)
			return 0;
		return 1 + brojElemenata(cvor.levo) + brojElemenata(cvor.desno);
	}

	public int zbirElemenata(CvorStabla cvor) throws LabisException {
		if (cvor == null)
			throw new LabisException();
		return zbirElemenataPrivate(cvor);
	}

	private int zbirElemenataPrivate(CvorStabla cvor) {
		return cvor == null ? 0 : cvor.podatak + zbirElemenataPrivate(cvor.levo) + zbirElemenataPrivate(cvor.desno);
	}

	private class Pom {
		private int brel;
		private double zbir;
	}

	public double prosekJedanProlaz(CvorStabla cvor) throws LabisException {
		if (cvor == null)
			throw new LabisException();
		Pom p = new Pom();

		return prosekJedanProlazPrivate(cvor, p);
	}

	private double prosekJedanProlazPrivate(CvorStabla cvor, Pom p) {
		if (cvor == null)
			return 0;
		p.brel++;
		p.zbir += cvor.podatak;

		prosekJedanProlazPrivate(cvor.levo, p);
		prosekJedanProlazPrivate(cvor.desno, p);

		return p.zbir / p.brel;
	}

	@Override
	public CvorStabla maxCvor(CvorStabla k) throws LabisException {
		if (k == null)
			return k;

		CvorStabla max = k;
		CvorStabla maxl = maxCvor(k.levo);
		CvorStabla maxd = maxCvor(k.desno);

		if (maxl != null && maxl.podatak >= max.podatak)
			max = maxl;
		if (maxd != null && maxd.podatak >= max.podatak)
			max = maxd;

		return max;
	}

	@Override
	public int maxVrednost(CvorStabla k) throws LabisException {
		if (k == null)
			throw new LabisException();
		return maxVrednostPrivate(k);
	}

	private int maxVrednostPrivate(CvorStabla k) {
		if (k == null)
			return Integer.MIN_VALUE;

		int max = k.podatak;
		int maxl = maxVrednostPrivate(k.levo);
		int maxd = maxVrednostPrivate(k.desno);

		return Math.max(maxd, Math.max(max, maxl));
	}

	public int minVrednost(CvorStabla k) throws LabisException {
		if (k == null)
			throw new LabisException();

		return minVrednostPrivate(k);
	}

	private int minVrednostPrivate(CvorStabla k) {
		if (k == null)
			return Integer.MAX_VALUE;

		return Math.min(k.podatak, Math.min(minVrednostPrivate(k.levo), minVrednostPrivate(k.desno)));
	}

	@Override
	public int visina(CvorStabla k) throws LabisException {
		if (k == null)
			return 0;

		return 1 + Math.max(visina(k.levo), visina(k.desno));
	}

	private boolean daLiPostoji(CvorStabla k, CvorStabla neki) {
		if (k == null || neki == null)
			return false;

		if (k.podatak == neki.podatak)
			return true;

		return daLiPostoji(k.levo, neki) || daLiPostoji(k.desno, neki);
	}

	@Override
	public void ispisiOdKorenaDoDatog(CvorStabla k, CvorStabla neki) throws LabisException {
		if (k == null || neki == null)
			return;

		System.out.println(k.podatak);
		if (k == neki)
			return;

		if (daLiPostoji(k.levo, neki))
			ispisiOdKorenaDoDatog(k.levo, neki);
		else
			ispisiOdKorenaDoDatog(k.desno, neki);

	}

	@Override
	public void ispisiOdDatogDoKorena(CvorStabla k, CvorStabla neki) throws LabisException {
		if (k == null || neki == null)
			return;

		if (k == neki) {
			System.out.println(k.podatak);
			return;
		}

		if (daLiPostoji(k.levo, neki))
			ispisiOdDatogDoKorena(k.levo, neki);
		else
			ispisiOdDatogDoKorena(k.desno, neki);

		System.out.println(k.podatak);

	}

	public CvorStabla najdubljiCvor(CvorStabla cvor) throws LabisException {
		if (cvor == null) {
			return null;
		}

		if (cvor.levo == null && cvor.desno == null) {
			return cvor;
		}

		if (visina(cvor.levo) > visina(cvor.desno)) {
			return najdubljiCvor(cvor.levo);
		}

		return najdubljiCvor(cvor.desno);
	}

	@Override
	public CvorStabla vratiRoditelja(CvorStabla k, int podatak) throws LabisException {
		if (k == null || k.podatak == podatak)
			return null;
		if ((k.levo != null && k.levo.podatak == podatak) || (k.desno != null && k.desno.podatak == podatak))
			return k;
		CvorStabla s = vratiRoditelja(k.levo, podatak);

		if (s != null)
			return s;

		return vratiRoditelja(k.desno, podatak);
	}

	public CvorStabla najveciCvorNaPutanji(CvorStabla k, CvorStabla t) throws LabisException {
		if (k == null || t == null)
			throw new LabisException();

		return najveciNaPutanjiPrivate(k, t);
	}

	private CvorStabla najveciNaPutanjiPrivate(CvorStabla k, CvorStabla t) {
		if (k == null || t == null)
			return null;
		if (k == t)
			return k;

		CvorStabla max = null;

		if (daLiPostoji(k.levo, t))
			max = najveciNaPutanjiPrivate(k.levo, t);
		else
			max = najveciNaPutanjiPrivate(k.desno, t);

		return max.podatak > k.podatak ? max : k;
	}

	public int brojCvorovaVecihOdSledbenika(CvorStabla k) throws LabisException {
		if (k == null)
			return 0;
		if (k.podatak == maxVrednostPrivate(k))
			return 1 + brojCvorovaVecihOdSledbenika(k.levo) + brojCvorovaVecihOdSledbenika(k.desno);
		return brojCvorovaVecihOdSledbenika(k.levo) + brojCvorovaVecihOdSledbenika(k.desno);
	}

	public boolean daLiJeBST(CvorStabla k) {
		if (k == null || (k.levo == null && k.desno == null))
			return true;

		if (k.levo != null && k.podatak < k.levo.podatak)
			return false;
		if (k.desno != null && k.podatak > k.desno.podatak)
			return false;

		return daLiJeBST(k.levo) && daLiJeBST(k.desno);
	}

	public boolean daLiJeAVL(CvorStabla k) throws LabisException {
		if (k == null || (k.levo == null && k.desno == null))
			return true;

		if (!daLiJeBST(k))
			return false;

		if (Math.abs(visina(k.levo) - visina(k.desno)) < 2)
			return daLiJeAVL(k.levo) && daLiJeAVL(k.desno);

		return false;
	}

	public boolean daLiPostojiIsti(CvorStabla k) throws LabisException {
		if (k == null || (k.levo == null && k.desno == null))
			return false;

		if (daLiPostoji(k.levo, k) || daLiPostoji(k.desno, k))
			return true;

		return daLiPostojiIsti(k.levo) || daLiPostojiIsti(k.desno);
	}

	public CvorStabla vratiListNaNajmanjojDubini(CvorStabla k) throws LabisException {
		if (k == null || (k.levo == null && k.desno == null))
			return k;

		if (minVisina(k.levo) > minVisina(k.desno))
			return vratiListNaNajmanjojDubini(k.desno);

		return vratiListNaNajmanjojDubini(k.levo);
	}

	private int minVisina(CvorStabla k) {
		if (k == null)
			return Integer.MAX_VALUE;
		if (k.levo == null && k.desno == null)
			return 1;
		return 1 + Math.min(minVisina(k.levo), minVisina(k.desno));
	}
	
	public CvorStabla vratiMaksimalanPolulist(CvorStabla k) throws LabisException {
		if (k == null || (k.levo == null && k.desno == null))
			throw new LabisException();

		CvorStabla max = null;
		
		if((k.levo == null && k.desno != null) || (k.levo != null && k.desno == null))
			max = k;
		
		CvorStabla maxL = vratiMaksimalanPolulist(k.levo);
		CvorStabla maxD = vratiMaksimalanPolulist(k.desno);
		
		if (max == null || (maxL != null && maxL.podatak > max.podatak))
			max = maxL;

		if (max == null || (maxD != null && maxD.podatak > max.podatak))
			max = maxD;

		return max;
	}

	public static void main(String[] args) {

		BinarnoStablo bs = new BinarnoStablo();
		StabloGenerator.izgenerisiStablo(bs);
		StabloGenerator.zameniVrednost(bs.koren, 217, 500);
		StabloGenerator.zameniVrednost(bs.koren, 113, 501);

		bs.postfiksniProlaz(bs.koren);

		try {
			System.out.println("\n\n" + bs.najveciCvorNaPutanji(bs.koren, bs.koren.levo.desno.levo).podatak);

		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
