package jun2016;

import labis.cvorovi.CvorStabla;
import labis.generator.StabloGenerator;
import labis.stabla.ABSTStablo;

public class BSTStablo extends ABSTStablo{
	
	public void ispisiRastuceNaZadatomNivou(CvorStabla k, int h) {
		if(k == null || h < 1)
			return;
		
		if(h == 1)
			System.out.println(k.podatak);
		else {
			ispisiRastuceNaZadatomNivou(k.levo, h - 1);
			ispisiRastuceNaZadatomNivou(k.desno, h - 1);
		}
	}
	
	public static void main(String[] args) {
		BSTStablo s = new BSTStablo();
		StabloGenerator.izgenerisiStablo(s);
		
		s.ispisiRastuceNaZadatomNivou(s.koren, 3);
	}
}
