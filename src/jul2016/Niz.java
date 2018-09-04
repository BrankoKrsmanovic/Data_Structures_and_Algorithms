package jul2016;

import labis.exception.LabisException;

public class Niz {

	int niz[] = { 11, 8, 44, 123, 5, 44, 130, 66 };
	
	//Max razlika gde je veci element posle manjeg
	
	public int maxRazlikaJedanProlaz() throws LabisException {
		if(niz == null || niz.length <1)
			throw new LabisException();
		int min = Integer.MAX_VALUE;
		int razlika = Integer.MIN_VALUE;
		
		for (int i = 0; i < niz.length; i++) {
			if(niz[i] < min)
				min = niz[i];
			if(niz[i] - min > razlika)
				razlika = niz[i] - min;
		}
		return razlika;
	}
	
	//Max razlika gde je veci element pre manjeg

	public int maxRazlika() throws LabisException {
		if(niz == null || niz.length <1)
			throw new LabisException();
		
		int max = Integer.MIN_VALUE;
		int razlika = Integer.MIN_VALUE;
		
		for (int i = 0; i < niz.length; i++) {
			if(niz[i] > max) 
				max = niz[i];
			if(max - niz[i] > razlika)
				razlika = max - niz[i];
		}
		
		return razlika;
	}
	
	public static void main(String[] args) {
		Niz n = new Niz();
		try {
			System.out.println(n.maxRazlika());
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
