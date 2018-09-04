package LinearneStrukture.StekiRed;

import java.util.EmptyStackException;

import LinearneStrukture.Abstraktne.IStack;

public class StaticStack implements IStack{

	private int stack [];
	private int top;
	
	public StaticStack() {
		stack = new int[3];
		top = -1;
	}
	
	public StaticStack(int capacity) {
		stack = new int[capacity];
		top = -1;
	}
	
	public int NumOfEl() {
		return top + 1;
	}
	
	@Override
	public boolean isFull() {
		return top == stack.length - 1;
	}

	@Override
	public boolean isEmpty() {
		return top == -1;
	}

	@Override
	public boolean push(int element) {
		if(isFull()) 
			return false;
		top++;
		stack[top] = element;
		return true;
	}

	@Override
	public int pop() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException();
		int pom = stack[top];
		top--;
		return pom;
	}

	@Override
	public int peek() throws EmptyStackException {
		if(isEmpty())
			throw new EmptyStackException();
		
		return stack[top];
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
		StaticStack s = new StaticStack(5);
		s.push(17);
		s.push(3);
		s.push(5);
		s.push(6);
		
		s.ispisiStackObrnuto();
	}
}
