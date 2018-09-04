package LinearneStrukture.Abstraktne;

public interface IQueue {

	public boolean isFull();

	public boolean isEmpty();

	public boolean enqueue(int element);

	public int dequeue() throws Exception;

	public int peekFirst() throws Exception;

	public int peekLast() throws Exception;
}
