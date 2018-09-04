package sve2007;

import LinearneStrukture.StekiRed.DynamicStack;
import labis.exception.LabisException;

public class DinamickiStek extends DynamicStack {

	private class Pom{
		int brojac = 0;
	}
	
	public int brElVecihOdVrha() throws LabisException {
		if(isEmpty())
			throw new LabisException();
		
		int pom = peek();
		Pom p = new Pom();
		prebroj(pom, p);
		
		return p.brojac;
	}
	
	private void prebroj(int max, Pom p) {
		if(isEmpty())
			return;
		int pom = pop();
		prebroj(max, p);
		if(pom > max) 
			p.brojac ++;
		push(pom);
	}
	
	public static void main(String[] args) {
		DinamickiStek s = new DinamickiStek();
		s.push(5);
		s.push(2);
		s.push(3);
		s.push(6);
		s.push(7);
		
		try {
			System.out.println(s.brElVecihOdVrha());
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
