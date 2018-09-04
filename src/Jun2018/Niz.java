package Jun2018;

import labis.exception.LabisException;

public class Niz {
	
	public static int pozicijaTrecegMax(int niz[]) throws LabisException {
		if(niz == null || niz.length < 3)
			throw new LabisException();
		int m1 = Integer.MIN_VALUE;
		int m2 = Integer.MIN_VALUE;
		int m3 = Integer.MIN_VALUE;
		int i1 = -1, i2 = -1, i3 = -1;
		for (int i = 0; i < niz.length; i++) {
			if(niz[i] > m1) {
				m3 = m2; 
				i3 = i2;
				m2 = m1;
				i2 = i1;
				m1 = niz[i];
				i1 = i;
			}else if(niz[i] > m2) {
				m3 = m2;
				i3 = i2;
				m2 = niz[i];
				i2 = i;
			}else if(niz[i] > m3) {
				m3 = niz[i];
				i3 = i;
			}
		}
		return i3;
	}

	public static int maxUgnjezdenost(String s) throws LabisException {
		if(s == null || s.length() == 0 || s == "")
			throw new LabisException();
	
		int max = Integer.MIN_VALUE;
		int brojac = 0;
		
		for (int i = 0; i < s.length(); i++) {
			if(s.charAt(i) == '[') {
				brojac++;
				if(brojac >= max)
					max = brojac;
			}else {
				if(brojac > 0)
					brojac--;
				else
					throw new LabisException("String nije balansiran!!!");
			}
		}
		if(brojac != 0)
			throw new LabisException("String nije balansiran!!!");
		return max;
	}
	
	public static void main(String[] args) {
//		int niz[] = { 8, 3, 10, 11, 2, 55, 10 };
		String s = "[[][[[]][]]][]";
		try {
			System.out.println(maxUgnjezdenost(s));
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
	}
}
