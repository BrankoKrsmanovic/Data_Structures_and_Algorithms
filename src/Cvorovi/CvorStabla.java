package Cvorovi;

public class CvorStabla {

	public int podatak;
	public CvorStabla levo;
	public CvorStabla desno;

	public CvorStabla(int p, CvorStabla l, CvorStabla d) {
		podatak = p;
		levo = l;
		desno = d;
	}

	public CvorStabla(int p) {
		this(p, null, null);
	}
}
