package jun2016;

import labis.exception.LabisException;

public class Niz {

	int niz[] = { 11, 11, 11};

	public int vratiTreciNajveciJedanProlaz() throws LabisException {
		if (niz == null || niz.length < 3)
			throw new LabisException();
		int prvi = Integer.MIN_VALUE;
		int drugi = Integer.MIN_VALUE;
		int treci = Integer.MIN_VALUE;
		for (int i = 0; i < niz.length; i++) {
			if(niz[i] > treci) {
				if(niz[i] > drugi) {
					if(niz[i] > prvi) {
						treci = drugi;
						drugi = prvi;
						prvi = niz[i];
					}else {
						treci = drugi;
						drugi = niz[i];
					}
				}else 
					treci = niz[i];
			}
		}
		System.out.println(treci);
		return treci;
	}
	
	public static void main(String[] args) {
		Niz n = new Niz();
		
		try {
			n.vratiTreciNajveciJedanProlaz();
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
