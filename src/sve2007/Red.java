package sve2007;

import LinearneStrukture.StekiRed.DynamicQueue;

public class Red extends DynamicQueue {

	private void invertujRedPrivate(DynamicQueue r1, DynamicQueue r2) throws Exception {
		if (r1.isEmpty())
			return;
		int pom = r1.dequeue();	
		invertujRedPrivate(r1, r2);
		r1.enqueue(pom);
		r2.enqueue(pom);	
	}
	
	public void invertujRed(DynamicQueue r1, DynamicQueue r2) throws Exception {
		if (r1.isEmpty())
			return;
		invertujRedPrivate(r1, r2);
		invertujRedPrivate(r1);
	}
	
	private void invertujRedPrivate(DynamicQueue r) throws Exception {
		if (r.isEmpty())
			return;
		int pom = r.dequeue();
		invertujRedPrivate(r);
		r.enqueue(pom);
	}
	
	public static void main(String[] args) {
		DynamicQueue r1 = new DynamicQueue();
		r1.enqueue(1);
		r1.enqueue(2);
		r1.enqueue(3);
		r1.enqueue(4);
		r1.enqueue(5);
		DynamicQueue r2  = new DynamicQueue();
		Red red = new Red();
		try {
			//System.out.println(r1.dequeue() + " "  + r1.dequeue() + " "  + r1.dequeue() + " "  + r1.dequeue() + " "  + 	r1.dequeue());
			red.invertujRed(r1, r2);
			System.out.println(r2.dequeue() + " "  + r2.dequeue() + " "  + r2.dequeue() + " "  + r2.dequeue() + " "  + 	r2.dequeue());
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
