package IIklk2017;

import labis.cvorovi.CvorStabla;
import labis.generator.StabloGenerator;
import labis.stabla.ABSTStablo;

public class BSTStablo extends ABSTStablo{

	public void ispisiUnutrasnjeCvoroveOpadajuce(CvorStabla cvor) {
		if(cvor == null)
			return;
		ispisiUnutrasnjeCvoroveOpadajuce(cvor.desno);
		if(cvor.levo != null && cvor.desno != null)
			System.out.print(cvor.podatak + "\t");
		ispisiUnutrasnjeCvoroveOpadajuce(cvor.levo);
	}
	
	public void ispisiNaZadatomNivouOpadajuce(CvorStabla cvor, int h) {
		if(cvor == null || h < 1)
			return;
		if(h == 1) {
			System.out.println(cvor.podatak);
			return;
		}
		ispisiNaZadatomNivouOpadajuce(cvor.desno, h - 1);
		ispisiNaZadatomNivouOpadajuce(cvor.levo, h - 1);
	}
	
	public static void main(String[] args) {
		BSTStablo s = new BSTStablo();
		StabloGenerator.izgenerisiStablo(s);
		s.ispisiUnutrasnjeCvoroveOpadajuce(s.koren);
	}
}
