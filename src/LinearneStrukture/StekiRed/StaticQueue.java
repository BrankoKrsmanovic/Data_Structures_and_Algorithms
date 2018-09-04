package LinearneStrukture.StekiRed;

import LinearneStrukture.Abstraktne.IQueue;

public class StaticQueue implements IQueue {

	private int queue[];
	private int first;
	private int last;
	private int numOfEl;

	public StaticQueue(int capacity) {
		queue = new int[capacity];
		numOfEl = 0;
		first = 0;
		last = -1;
	}

	@Override
	public boolean isFull() {
		return numOfEl == queue.length;
	}

	@Override
	public boolean isEmpty() {
		return last == -1;
	}

	@Override
	public boolean enqueue(int element) {
		if (isFull())
			return false;
		queue[++last % queue.length] = element;
		numOfEl++;
		return true;
	}

	@Override
	public int dequeue() throws Exception {
		if (isEmpty())
			throw new Exception();
		first = ++first % queue.length;
		numOfEl--;
		return 0;
	}

	@Override
	public int peekFirst() throws Exception {
		if (isEmpty())
			throw new Exception();
		return queue[first];
	}

	@Override
	public int peekLast() throws Exception {
		if (isEmpty())
			throw new Exception();
		return queue[last];
	}

	public static void main(String[] args) {
		StaticQueue q = new StaticQueue(6);
		q.enqueue(5);
		q.enqueue(8);
		q.enqueue(2);
		q.enqueue(11);
		q.enqueue(4);

		try {
			q.enqueue(25);
			q.dequeue();
			q.dequeue();
			System.out.println("BREL: " + "\t" + q.numOfEl);
			for (int i = 0; i < 6; i++) {
				System.out.println(q.queue[i]);
			}
			q.enqueue(12);
			q.dequeue();
			q.enqueue(99);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
			
	}
}
