import java.util.PriorityQueue;

public class LastStoneWeight {
	
	
	
    public int lastStoneWeight(int[] stones) {
        PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b - a);
    	
    	for (int stone: stones) {
    		pq.add(stone);
    	}
    	
    	
    	while(!pq.isEmpty()) {
    		if (pq.size() == 1) break;
    		
    		if (pq.size() >= 2) {
    			int first = pq.poll();
    			int second = pq.poll();
    			
    			int newStone = Math.abs(first - second);
    			pq.add(newStone);
    		}
    	}
        
        
        
        
    	
    	return pq.size() == 1 ? pq.poll() : 0;
    }
}
