package Cvorovi;

public class CvorDSListe {

	public int podatak;
	public CvorDSListe sledeci;
	public CvorDSListe prethodni;
	
	public CvorDSListe() {
		
	}
	
	public CvorDSListe(int p, CvorDSListe pret, CvorDSListe sled) {
		podatak = p;
		prethodni = pret;
		sledeci = sled;
	}
}
