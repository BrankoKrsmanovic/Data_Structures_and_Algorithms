package Iklk2017;

public class Niz {

	int niz[] = { -2, 11, -4, 7, -8, -9, 11, -7 };

	public int[] srediNizJedanProlaz() {
		if(niz == null || niz.length == 0)
			return null;
		int i = -1; 
		int pom;
		
		for (int j = 0; j < niz.length; j++) {
			if(niz[j] < 0) {
				i++;
				pom = niz[i];
				niz[i] = niz[j];
				niz[j] = pom;
			}
		}
		return niz;
	}
	
	private void ispisi() {
		for (int s = 0; s < niz.length; s++) {
			System.out.print("\t" + niz[s]);
		}
	}
	
	public static void main(String[] args) {

		Niz n = new Niz();
		
		n.srediNizJedanProlaz();
		n.ispisi();
	}
}
