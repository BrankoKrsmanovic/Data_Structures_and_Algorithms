package LinearneStrukture.StekiRed;

import Cvorovi.CvorJSListe;
import LinearneStrukture.Abstraktne.IQueue;

public class DynamicQueue implements IQueue {

	CvorJSListe first;
	CvorJSListe last;

	@Override
	public boolean isFull() {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return first == null;
	}

	@Override
	public boolean enqueue(int element) {
		if (isEmpty())
			first = last = new CvorJSListe(element, null);
		else {
			last.sledeci = new CvorJSListe(element, null);
			last = last.sledeci;
		}
		return true;
	}

	@Override
	public int dequeue() throws Exception {
		if(isEmpty())
			throw new Exception();
		int pom = first.podatak;
		first = first.sledeci;
		return pom;
	}

	@Override
	public int peekFirst() throws Exception {
		if(isEmpty())
			throw new Exception();
		return first.podatak;
	}

	@Override
	public int peekLast() throws Exception {
		if(isEmpty())
			throw new Exception();
		return last.podatak;
	}

}
