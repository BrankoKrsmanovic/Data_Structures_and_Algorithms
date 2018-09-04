package klk2018;

import labis.cvorovi.CvorStabla;
import labis.exception.LabisException;
import labis.generator.StabloGenerator;
import labis.stabla.ABinarnoStablo;

public class BinarnoStablo extends ABinarnoStablo {

	public int proizvodListova(CvorStabla k) throws LabisException {
		if (k == null)
			throw new LabisException();
		if (k.levo == null && k.desno == null)
			return k.podatak;
		return proizvodUListovimaPrivate(k);
	}

	private int proizvodUListovimaPrivate(CvorStabla k) {
		if (k == null)
			return 1;
		if (k.levo == null && k.desno == null)
			return k.podatak;
		return proizvodUListovimaPrivate(k.levo) * proizvodUListovimaPrivate(k.desno);
	}

	private class Pom{
		int maxZbir = Integer.MIN_VALUE;
	}
	
	public int najveciZbirDoLista(CvorStabla k) throws LabisException {
		if(k == null)
			throw new LabisException();
		if(k.levo == null && k.desno == null)
			return k.podatak;
	
		Pom p = new Pom();
		najveciZbirPrivate(k, 0, p);
		
		return p.maxZbir;
	}
	
	private void najveciZbirPrivate(CvorStabla k, int sum, Pom p) {
		if(k == null)
			return;
		sum += k.podatak;
		if(sum > p.maxZbir && k.levo == null && k.desno == null)
			p.maxZbir = sum;
		najveciZbirPrivate(k.levo, sum, p);
		najveciZbirPrivate(k.desno, sum, p);
	}
	
	public static void main(String[] args) {
		BinarnoStablo bs = new BinarnoStablo();
		StabloGenerator.izgenerisiStablo(bs);
		StabloGenerator.zameniVrednost(bs.koren, 62, 2);
		StabloGenerator.zameniVrednost(bs.koren, 126, 3);
		StabloGenerator.zameniVrednost(bs.koren, 153, 4);
		StabloGenerator.zameniVrednost(bs.koren, 312, 5);
		StabloGenerator.zameniVrednost(bs.koren, 342, 5);
		StabloGenerator.zameniVrednost(bs.koren, 300, 5);
		try {
			System.out.println(bs.najveciZbirDoLista(bs.koren));
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}
}
