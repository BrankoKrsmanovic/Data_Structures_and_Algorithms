package LinearneStrukture.Abstraktne;

import java.util.EmptyStackException;

public interface IStack {

	public boolean isFull();

	public boolean isEmpty();

	public boolean push(int element);

	public int pop() throws EmptyStackException;

	public int peek() throws EmptyStackException;
}
