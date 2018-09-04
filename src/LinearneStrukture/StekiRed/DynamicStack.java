package LinearneStrukture.StekiRed;

import java.util.EmptyStackException;

import Cvorovi.CvorJSListe;
import LinearneStrukture.Abstraktne.IStack;

public class DynamicStack implements IStack{

	private CvorJSListe top;
	
	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return top == null;
	}

	@Override
	public boolean push(int element) {
		top = new CvorJSListe(element, top);
		return true;
	}

	@Override
	public int pop() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException();
		int pom = top.podatak;
		top = top.sledeci;
		return pom;
	}

	@Override
	public int peek() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException();
		
		return top.podatak;
	}

	public void ispisiStackObrnuto() {
		if(isEmpty())
			return;
		int e = pop();
		ispisiStackObrnuto();
		System.out.println(e);
		push(e);
	}
	
	public static void main(String[] args) {
	
		DynamicStack s = new DynamicStack();
		
		s.push(17);
		s.push(3);
		s.push(5);
		s.push(6);
		
		s.ispisiStackObrnuto();
	}
}
