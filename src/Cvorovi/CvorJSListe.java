package Cvorovi;

public class CvorJSListe {

	public int podatak;
	public CvorJSListe sledeci;
	
	public CvorJSListe() {
		
	}
	
	public CvorJSListe(int p, CvorJSListe sled) {
		podatak = p;
		sledeci = sled;
	}
}
