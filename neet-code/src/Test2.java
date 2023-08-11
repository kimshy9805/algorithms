import java.util.Comparator;
import java.util.Iterator;
import java.util.PriorityQueue;

public class Test2 {

	public static void main(String[] args) {
		
		
		
		PriorityQueue<Pair> pq = new PriorityQueue<>();
		
		pq.add(new Pair(5, 1, 1));
		pq.add(new Pair(5, 2, 2));
		pq.add(new Pair(5, 3, 3));
		pq.add(new Pair(5, 4, 4));
		pq.add(new Pair(5, 5, 5));
		pq.add(new Pair(5, 6, 6));
		
		Iterator it = pq.iterator();
		while (it.hasNext()) { 
		    Pair order1 = (Pair) it.next();
		    System.out.println(order1.val + " " + order1.ts + " " + order1.n+ " " );
		}
	}
	

}

class Pair implements Comparator<Pair>{
	int val;
	int ts;
	int n;
	
	Pair(int val, int ts, int n) {
		this.val = val;
		this.ts = ts;
		this.n = n;
	}

	@Override
	public int compare(Pair o1, Pair o2) {
		if (o1.val > o2.val) return 1;
		else if (o1.val < o2.val) return -1;
		else {
			if (o1.ts > o2.ts) return 1;
			else if (o1.ts < o2.ts) return -1;
			else return 0;
		}
	}
	
}
