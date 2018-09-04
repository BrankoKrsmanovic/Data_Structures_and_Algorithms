package jun2016;

import labis.cvorovi.CvorStabla;
import labis.exception.LabisException;
import labis.generator.StabloGenerator;
import labis.stabla.ABinarnoStablo;

public class BinarnoStablo extends ABinarnoStablo{

	public void ispisiParneOdKorenaDoCvora(CvorStabla k, CvorStabla cvor) throws LabisException{
		if(koren == null || cvor == null)
			throw new LabisException();
		
		if(k.podatak % 2 == 0)
			System.out.println(k.podatak);
		if(k.podatak == cvor.podatak)
			return;
		if(daLiPostoji(k.levo, cvor))
			ispisiParneOdKorenaDoCvora(k.levo, cvor);
		else
			ispisiParneOdKorenaDoCvora(k.desno, cvor);
	}
	
	public boolean daLiPostoji(CvorStabla k, CvorStabla cvor) {
		if(k == null || cvor == null)
			return false;
		if(k.podatak == cvor.podatak)
			return true;
		
		return daLiPostoji(k.levo, cvor) || daLiPostoji(k.desno, cvor);
	}
	
	public static void main(String[] args) {
		BinarnoStablo bs = new BinarnoStablo();
		StabloGenerator.izgenerisiStablo(bs);
		StabloGenerator.zameniVrednost(bs.koren, 217, 218);
		StabloGenerator.zameniVrednost(bs.koren, 153, 150);
		try {
			bs.ispisiParneOdKorenaDoCvora(bs.koren, bs.koren.levo.desno.desno.levo);
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
