package GfG;

import java.util.LinkedList;
import java.util.Queue;

import labis.cvorovi.CvorStabla;
import labis.exception.LabisException;
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
	 * Replace each node in binary tree with the sum of its inorder predecessor and
	 * successor
	 */

	private int brojCvorova(CvorStabla k) {
		if (k == null)
			return 0;
		return 1 + brojCvorova(k.levo) + brojCvorova(k.desno);
	}

	private int ubaciUNiz(CvorStabla k, int niz[], int i) {
		if (k == null)
			return i;

		i = ubaciUNiz(k.levo, niz, i);
		niz[i] = k.podatak;
		i++;
		i = ubaciUNiz(k.desno, niz, i);

		return i;
	}

	private int izNizaUStablo(CvorStabla k, int niz[], int i) {
		if (k == null)
			return i;
		i = izNizaUStablo(k.levo, niz, i);
		k.podatak = niz[i - 1] + niz[i + 1];
		i++;
		i = izNizaUStablo(k.desno, niz, i);

		return i;
	}

	public void f1(CvorStabla k) {
		if (k == null || brojCvorova(k) == 1)
			return;
		int niz[] = new int[2 + brojCvorova(k)];
		ubaciUNiz(k, niz, 1);
		izNizaUStablo(k, niz, 1);
	}

	/**
	 * Given a binary tree and a node, we need to write a program to find inorder
	 * successor of this node.
	 */

	private CvorStabla vratiRoditelja(CvorStabla k, CvorStabla neki) {
		if (k == null || neki == null)
			return null;
		if ((k.levo != null && k.levo == neki) || (k.desno != null && k.desno == neki))
			return k;
		CvorStabla s = vratiRoditelja(k.levo, neki);
		if (s != null)
			return s;
		return vratiRoditelja(k.desno, neki);
	}

	private CvorStabla leftMostNode(CvorStabla k) {
		while (k != null && k.levo != null)
			k = k.levo;
		return k;
	}

	private CvorStabla rightMostNode(CvorStabla k) {
		while (k != null && k.desno != null)
			k = k.desno;
		return k;
	}

	public CvorStabla f2(CvorStabla k, CvorStabla neki) {
		if (k == null || neki == null || neki == rightMostNode(koren))
			return null;

		if (k.levo == neki) {
			if (k.levo.desno == null)
				return k;
			return leftMostNode(k.levo.desno);
		}
		if (k.desno == neki) {
			if (k.desno.desno != null)
				return leftMostNode(k.desno.desno);
			else
				return vratiRoditelja(koren, neki);
		}
		CvorStabla s = f2(k.levo, neki);
		if (s != null)
			return s;
		return f2(k.desno, neki);
	}

	/**
	 * Level Order Tree Traversal
	 */

	public int visina(CvorStabla k) {
		if (k == null)
			return 0;
		return 1 + Math.max(visina(k.levo), visina(k.desno));
	}

	private void stampajNaZadatomNivou(CvorStabla k, int nivo) {
		if (k == null || nivo < 1)
			return;
		if (nivo == 1)
			System.out.print(k.podatak + "\t");
		else {
			stampajNaZadatomNivou(k.levo, nivo - 1);
			stampajNaZadatomNivou(k.desno, nivo - 1);
		}
	}

	public void f3(CvorStabla k) {
		if (k == null)
			return;
		int h = visina(k);
		for (int i = 1; i <= h; i++) {
			stampajNaZadatomNivou(k, i);
			System.out.println("\n");
		}
	}

	/**
	 * Check for Children Sum Property in a Binary Tree
	 */

	public boolean f4(CvorStabla k) {
		if (k == null || (k.levo == null && k.desno == null))
			return true;
		if (k.levo != null && k.desno != null)
			if (k.podatak != k.levo.podatak + k.desno.podatak)
				return false;
		if (k.levo == null && k.podatak != k.desno.podatak)
			return false;
		if (k.desno == null && k.podatak != k.levo.podatak)
			return false;

		return f4(k.levo) && f4(k.desno);
	}

	/**
	 * Check if a given Binary Tree is SumTree
	 */

	private int sumaStabla(CvorStabla k) {
		if (k == null)
			return 0;
		return k.podatak + sumaStabla(k.levo) + sumaStabla(k.desno);
	}

	public boolean f5(CvorStabla k) {
		if (k == null || (k.levo == null && k.desno == null))
			return true;
		if (k.podatak != sumaStabla(k.levo) + sumaStabla(k.desno))
			return false;

		return f5(k.levo) && f5(k.desno);
	}

	/**
	 * Check if two nodes are cousins in a Binary Tree. Two nodes are cousins of
	 * each other if they are at same level and have different parents.
	 */

	private boolean daLiPostoji(CvorStabla k, CvorStabla neki) {
		if (k == null || neki == null)
			return false;
		if (k.podatak == neki.podatak)
			return true;

		return daLiPostoji(k.levo, neki) || daLiPostoji(k.desno, neki);
	}

	private int visinaCvora(CvorStabla k, CvorStabla neki) {
		if (k == null || neki == null)
			return 0;
		if (k.podatak == neki.podatak)
			return 1;
		if (daLiPostoji(k.levo, neki))
			return 1 + visinaCvora(k.levo, neki);
		return 1 + visinaCvora(k.desno, neki);
	}

	public boolean f6(CvorStabla k, CvorStabla a, CvorStabla b) {
		if (k == null || a == null || b == null)
			return false;
		if (visinaCvora(koren, a) == visinaCvora(koren, b) && vratiRoditelja(koren, a) != vratiRoditelja(koren, b))
			return true;
		return f6(k.levo, a, b) || f6(k.desno, a, b);
	}

	/**
	 * Check if all leaves are at same level
	 */

	public boolean f7(CvorStabla k) throws LabisException {
		if (k == null)
			throw new LabisException("Stablo ne postoji!");
		int h = visina(koren);

		return f7(k, h);
	}

	private boolean f7(CvorStabla k, int h) {
		if (k == null)
			return true;
		if (k.levo == null && k.desno == null)
			return visinaCvora(koren, k) == h;

		return f7(k.levo, h) && f7(k.desno, h);
	}

	/**
	 * Check if a binary tree is subtree of another binary tree
	 */

	private CvorStabla nadjiCvor(CvorStabla k, CvorStabla neki) {
		if (k == null || neki == null)
			return null;
		if (k.podatak == neki.podatak)
			return k;
		CvorStabla s = nadjiCvor(k.levo, neki);
		if (s != null)
			return s;
		return nadjiCvor(k.desno, neki);
	}

	public boolean f8(CvorStabla k1, CvorStabla k2) {
		if (k1 == null && k2 == null)
			return true;
		CvorStabla cvor = nadjiCvor(k1, k2);
		return f8Private(cvor, k2);
	}

	private boolean f8Private(CvorStabla k1, CvorStabla k2) {
		if (k2 == null)
			return true;

		if (k1 == null)
			return false;

		return k1.podatak == k2.podatak && f8Private(k1.levo, k2.levo) && f8Private(k1.desno, k2.desno);
	}

	/**
	 * Check if there is a root to leaf path with given sequence
	 * 
	 * @throws LabisException
	 */

	public boolean f9(CvorStabla k, int niz[]) throws LabisException {
		if (k == null && niz != null)
			throw new LabisException();

		return f9Private(k, niz, niz.length, 0);
	}

	private boolean f9Private(CvorStabla k, int niz[], int n, int i) {
		if (k == null)
			return n == 0;

		if ((k.levo == null && k.desno == null) && i == n - 1 && k.podatak == niz[i])
			return true;

		return (i < n) && (k.podatak == niz[i])
				&& (f9Private(k.levo, niz, n, i + 1) || f9Private(k.desno, niz, n, i + 1));
	}

	/**
	 * Print middle level of perfect binary tree without finding height
	 */

	public void f10(CvorStabla k) {
		printMiddleLevelUtil(k, k);
	}

	private void printMiddleLevelUtil(CvorStabla a, CvorStabla b) {
		if (a == null || b == null)
			return;
		if ((b.levo == null) && (b.desno == null) || (b.levo.levo == null && b.levo.desno == null)) {
			System.out.print(a.podatak + "\t");
			return;
		}
		printMiddleLevelUtil(a.levo, b.levo.levo);
		printMiddleLevelUtil(a.desno, b.levo.levo);
	}

	/**
	 * Print cousins of a given node in Binary Tree
	 */

	public void f11(CvorStabla k, CvorStabla neki) {
		if (k == null)
			return;
		if (f6(koren, k, neki))
			System.out.println(k.podatak);
		f11(k.levo, neki);
		f11(k.desno, neki);
	}

	/**
	 * Given a binary tree, print out all of its root-to-leaf paths one per line.
	 */

	private void stampajPutanjuDoCvora(CvorStabla k, CvorStabla neki) {
		if (k == null || neki == null)
			return;
		System.out.print(k.podatak + "\t");
		if (k.podatak == neki.podatak)
			return;

		if (daLiPostoji(k.levo, neki))
			stampajPutanjuDoCvora(k.levo, neki);
		else
			stampajPutanjuDoCvora(k.desno, neki);
	}

	public void f12(CvorStabla k) {
		if (k == null)
			return;

		Queue<CvorStabla> q = new LinkedList<>();
		f12Private(k, q);
		CvorStabla cvor = q.poll();

		while (cvor != null) {
			stampajPutanjuDoCvora(k, cvor);
			System.out.println("\n");
			cvor = q.poll();
		}

	}

	private void f12Private(CvorStabla k, Queue<CvorStabla> q) {
		if (k == null)
			return;
		if (k.levo == null && k.desno == null)
			q.add(k);
		f12Private(k.levo, q);
		f12Private(k.desno, q);
	}

	/**
	 * Print the longest leaf to leaf path in a Binary tree
	 */
	
private class Pom {
		CvorStabla cvor;
		int sirina;
	}

	private void sirinaStabla(CvorStabla k, Pom p) {
		if (k == null)
			return;
		int pom = 1;
		if (k.levo != null)
			pom += visina(k.levo);
		if (k.desno != null)
			pom += visina(k.desno);
		if (pom > p.sirina) {
			p.cvor = k;
			p.sirina = pom;
		}
		sirinaStabla(k.levo, p);
		sirinaStabla(k.desno, p);
	}

	private void stampajOdNajnizegDoKorena(CvorStabla k) throws LabisException {
		if (k == null)
			return;
		if (k.levo != null && visina(k.levo) >= visina(k.desno))
			stampajOdNajnizegDoKorena(k.levo);
		if (k.desno != null && visina(k.desno) > visina(k.levo))
			stampajOdNajnizegDoKorena(k.desno);
		System.out.println(k.podatak);
	}

	private void stampajOdKorenaDoNajnizeg(CvorStabla k) throws LabisException {
		if (k == null)
			return;
		System.out.println(k.podatak);

		if (k.levo != null && visina(k.levo) >= visina(k.desno))
			stampajOdKorenaDoNajnizeg(k.levo);
		if (k.desno != null && visina(k.desno) > visina(k.levo))
			stampajOdKorenaDoNajnizeg(k.desno);
	}

	public void f13(CvorStabla k) throws LabisException {
		if (k == null)
			return;
		Pom p = new Pom();
		sirinaStabla(k, p);
		stampajOdNajnizegDoKorena(p.cvor.levo);
		System.out.println(p.cvor.podatak);
		stampajOdKorenaDoNajnizeg(p.cvor.desno);
	}

	/**
	 * Lowest Common Ancestor in a Binary Tree
	 * 
	 * @throws LabisException
	 */

	public CvorStabla f14(CvorStabla k, CvorStabla n1, CvorStabla n2) throws LabisException {
		if (n1 == null || n2 == null)
			throw new LabisException();
		if (k == n1 || k == n2)
			return k;
		return lca(k, n1, n2);
	}

	private CvorStabla lca(CvorStabla k, CvorStabla n1, CvorStabla n2) {
		if (k == null || k == n1 || k == n2)
			return k;
		if (daLiPostoji(k.levo, n1) && daLiPostoji(k.desno, n2) || daLiPostoji(k.levo, n2) && daLiPostoji(k.desno, n1))
			return k;
		if (daLiPostoji(k.levo, n1) && daLiPostoji(k.levo, n2))
			return lca(k.levo, n1, n2);
		else
			return lca(k.levo, n1, n2);
	}

	/**
	 * Find distance between two nodes of a Binary Tree
	 */
	
	public int f15(CvorStabla k, CvorStabla n1, CvorStabla n2) throws LabisException{
		if(n1 == null || n2 == null || k == null)
			throw new LabisException();
	
		CvorStabla pom = lca(k, n1, n2);
		return rastojanjeOdPrethodnikaDoSledbenika(pom, n1) + rastojanjeOdPrethodnikaDoSledbenika(pom, n2);
	}

	private int rastojanjeOdPrethodnikaDoSledbenika(CvorStabla k, CvorStabla neki) {
		if(k == null || k == neki)
			return 0;
		if(daLiPostoji(k.levo, neki))
			return 1 + rastojanjeOdPrethodnikaDoSledbenika(k.levo, neki);
		else
			return 1 + rastojanjeOdPrethodnikaDoSledbenika(k.desno, neki);
	}
	
	/**
	 * Maximum difference between node and its ancestor in Binary Tree
	 */
	
	public int f16(CvorStabla k, CvorStabla neki) throws LabisException{
		if(k == null || neki == null)
			throw new LabisException();
		if(k == neki)
			return 0;
		
		return najvecaRazlika(k, neki);
	}
	
	private int najvecaRazlika(CvorStabla k, CvorStabla neki) {
		if(k == null || k == neki)
			return Integer.MIN_VALUE;
		int max = Integer.MIN_VALUE;
		if(daLiPostoji(k.levo, neki))
			max = najvecaRazlika(k.levo, neki);
		else 
			max = najvecaRazlika(k.desno, neki);
		
		return max > k.podatak - neki.podatak ? max : k.podatak - neki.podatak;
	}
	
	/**
	 * MAIN()
	 */

	public static void main(String[] args) {
		BinarnoStablo bs = new BinarnoStablo();
		StabloGenerator.izgenerisiStablo(bs);
		bs.infiks(bs.koren);

		System.out.println("\n\n");

		 System.out.println(bs.f2(bs.koren, bs.koren.desno.desno).podatak);
		 StabloGenerator.zameniVrednost(bs.koren, 217, 52);
		 StabloGenerator.zameniVrednost(bs.koren, 113, 18);
		 StabloGenerator.zameniVrednost(bs.koren, 300, 8);
		 StabloGenerator.zameniVrednost(bs.koren, 342, 4);
		 StabloGenerator.zameniVrednost(bs.koren, 312, 4);
		 StabloGenerator.zameniVrednost(bs.koren, 78, 2);
		 StabloGenerator.zameniVrednost(bs.koren, 56, 1);
		 StabloGenerator.zameniVrednost(bs.koren, 62, 1);
		 StabloGenerator.zameniVrednost(bs.koren, 130, 7);
		 StabloGenerator.zameniVrednost(bs.koren, 126, 3);
		 StabloGenerator.zameniVrednost(bs.koren, 190, 2);
		 StabloGenerator.zameniVrednost(bs.koren, 153, 2);
		 bs.infiks(bs.koren);

		/**
		 * stablo.ubaci(stablo.koren, 95); stablo.ubaci(stablo.koren, 75);
		 * stablo.ubaci(stablo.koren, 155); stablo.ubaci(stablo.koren, 55);
		 * stablo.ubaci(stablo.koren, 65); stablo.ubaci(stablo.koren, 50);
		 * stablo.ubaci(stablo.koren, 165); stablo.ubaci(stablo.koren, 80);
		 * stablo.ubaci(stablo.koren, 85); stablo.ubaci(stablo.koren, 160);
		 * stablo.ubaci(stablo.koren, 150); stablo.ubaci(stablo.koren, 77);
		 * stablo.ubaci(stablo.koren, 153); stablo.ubaci(stablo.koren, 145);
		 * stablo.ubaci(stablo.koren, 170); stablo.ubaci(stablo.koren, 45);
		 * stablo.ubaci(stablo.koren, 53); stablo.ubaci(stablo.koren, 60);
		 * stablo.ubaci(stablo.koren, 70); stablo.ubaci(stablo.koren, 76);
		 * stablo.ubaci(stablo.koren, 79); stablo.ubaci(stablo.koren, 83);
		 * stablo.ubaci(stablo.koren, 90); stablo.ubaci(stablo.koren, 140);
		 * stablo.ubaci(stablo.koren, 147); stablo.ubaci(stablo.koren, 151);
		 * stablo.ubaci(stablo.koren, 154); stablo.ubaci(stablo.koren, 157);
		 * stablo.ubaci(stablo.koren, 163); stablo.ubaci(stablo.koren, 167);
		 * stablo.ubaci(stablo.koren, 175); stablo.infiks(stablo.koren);
		 * System.out.println("\n\n");
		 * 
		 * stablo.printMiddleLevelUtil(stablo.koren, stablo.koren);
		 */

		// int niz[] = { 217, 113, 78, 56, 63 };

		try {
			System.out.println(bs.f16(bs.koren, bs.koren.desno.desno.levo));
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		// bs.infiks(bs.koren);
	}
}
