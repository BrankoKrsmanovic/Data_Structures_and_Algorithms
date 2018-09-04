package LinearneStrukture.NiziLista;

public class Niz {

	public int[] niz;
	public int brel = 6;
	
	public Niz() {
		niz = new int[10];
		niz[0] = 5;
		niz[1] = 15;
		niz[2] = 25;
		niz[3] = 35;
		niz[4] = 45;
		niz[5] = 55;
	}
	
	private void stampajNiz() {
		for (int i = 0; i < brel; i++)
			System.out.print(niz[i] + "\t");
		
		System.out.println();
	}
	
	public void ubaciURastuci(int e) throws Exception{
		if(brel == niz.length)
			throw new Exception("Niz je pun");
		int pom = brel;
		for (int i = 0; i < brel; i++) {
			if(e <= niz[i]) {
				pom = i;
				break;
			}	
		}
		for (int j = brel + 1; j > pom; j--)	
			niz[j] = niz[j - 1];
			niz[pom] = e;
		brel++;
	}
	
	public double prosekParnih() throws Exception{
		if(niz.length == 0)
			throw new Exception();
		
		int sum = 0;
		int brojac = 0;
		
		for (int i = 0; i < brel; i++) {
			if(niz[i] % 2 == 0) {
				sum += niz[i];
				brojac++;
			}
		}
		return (double)sum / brojac;
	}
	
	public int pretraziSekvencijalno(int e) throws Exception{
		if(niz == null || niz.length == 0)
			throw new Exception("Niz je prazan!!!");
		int pom = pretraziSekvencijalnoR(e, 0);
		if(pom == -1)
			throw new Exception("Element ne postoji!!!");
		return pom;
	}
	
	private int pretraziSekvencijalnoR(int e, int pozicija) {
		if(pozicija == brel)
			return -1;
		if(niz[pozicija] == e)
			return pozicija;
		return pretraziSekvencijalnoR(e, ++pozicija);
	}

	public int pretraziBinarno(int e) throws Exception{
		if(niz == null || niz.length == 0)
			throw new Exception("Niz je prazan!!!");
		int pom = pretraziBinarnoR(e, 0, brel - 1);
		if(pom == -1)
			throw new Exception("Element ne postoji!!!");
		return pom;
	}
	
	private int pretraziBinarnoR(int e, int dg, int gg) {
		if(dg > gg)
			return -1;
		if(e == niz[(dg + gg) / 2])
			return (dg + gg) / 2;
		else if(e > niz[(dg + gg) / 2])
			return pretraziBinarnoR(e, (dg + gg) / 2 + 1, gg);
		else 
			return pretraziBinarnoR(e, dg, (dg + gg) / 2 - 1);
	}
	
	public int pretraziInterpolaciono(int e) throws Exception{
		if(niz == null || niz.length == 0)
			throw new Exception("Niz je prazan!!!");
		int pom = pretraziInterpolacionoR(e, 0, brel - 1);
		if(pom == -1)
			throw new Exception("Element ne postoji!!!");
		return pom;
	}
	
	private int pretraziInterpolacionoR(int e, int dg, int gg) {
		if(dg > gg || e > niz[gg] || e < niz[dg])
			return -1;
		int k = dg + (e - niz[dg])/(niz[gg] - niz[dg])*(gg - dg);
		if(e == niz[k])
			return k;
		else if(e > niz[k])
			return pretraziInterpolacionoR(e, k + 1, gg);
		else 
			return pretraziInterpolacionoR(e, dg, k - 1);
	}
	
	public static void main(String[] args) {
		Niz n = new Niz();
		
		System.out.println("************** Niz na pocetku **************");
		n.stampajNiz();
		
		try {
			System.out.println("Pozicija " + n.pretraziInterpolaciono(255));
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		//n.stampajNiz();
	}
}
