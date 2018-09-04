package sve2007;

import labis.cvorovi.CvorStabla;
import labis.generator.StabloGenerator;
import labis.stabla.ABSTStablo;

public class BSTStablo extends ABSTStablo{

	public void stampajPozitivneOpadajuce(CvorStabla k) {
		if(k == null)
			return;
		stampajPozitivneOpadajuce(k.desno);
		if(k.podatak>0)
			System.out.print(k.podatak + "\t");
		stampajPozitivneOpadajuce(k.levo);
	}
	
	public static void main(String[] args) {
		BSTStablo bs = new BSTStablo();
		StabloGenerator.izgenerisiStablo(bs);
		StabloGenerator.zameniVrednost(bs.koren, 217, -1);
		StabloGenerator.zameniVrednost(bs.koren, 153, 0);
		StabloGenerator.zameniVrednost(bs.koren, 56, -12);
		
		bs.stampajPozitivneOpadajuce(bs.koren);
	}
}
