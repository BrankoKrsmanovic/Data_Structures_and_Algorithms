package jul2016;

import labis.cvorovi.CvorStabla;
import labis.stabla.ABinarnoStablo;

public class BinarnoStablo extends ABinarnoStablo{

	public boolean daLiSuDvaStablaSlicna(CvorStabla k1, CvorStabla k2) {
		if(k1 == null && k2 == null)
			return true;
		if(k1 != null && k2 != null)
			return daLiSuDvaStablaSlicna(k1.levo, k2.levo) && daLiSuDvaStablaSlicna(k1.desno, k2.desno);
		return false;
	}
	
	public CvorStabla minCvor(CvorStabla k) {
		if(k == null)
			return k;
		CvorStabla min = k;
		CvorStabla minl = minCvor(k.levo);
		CvorStabla mind = minCvor(k.desno);
		
		if(min == null || (minl != null && minl.podatak < min.podatak))
			min = minl;
		if(min == null || (mind != null && mind.podatak < min.podatak))
			min = mind;
		return min;
	}
	
	public void pretvoriStabloUMinHeap(CvorStabla k) {
		if(k == null)
			return;
		CvorStabla min = minCvor(k);
		int pom;
		
		if(k.podatak < min.podatak) {
			pom = min.podatak;
			min.podatak = k.podatak;
			k.podatak = pom;
		}
		pretvoriStabloUMinHeap(k.levo);
		pretvoriStabloUMinHeap(k.desno);
	}
}
