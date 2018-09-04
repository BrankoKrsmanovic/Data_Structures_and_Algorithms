package Vezbe2;

import labis.exception.LabisException;
import labis.stek.AStek;

public class Stek extends AStek{

	public Stek(int kapacitet) {
		super(kapacitet);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void ispisiStekObrnuto() throws LabisException {
		if(prazanStek())
			return;
		int e = pop();
		ispisiStekObrnuto();
		System.out.println(e);
		push(e);
	}
	
	public static void main(String[] args) {
		Stek s = new Stek(4);

		s.push(1);
		s.push(2);
		s.push(3);
		s.push(4);
		try {
			s.ispisiStekObrnuto();
		} catch (LabisException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
