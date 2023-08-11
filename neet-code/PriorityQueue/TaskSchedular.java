import java.util.ArrayDeque;
import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TaskSchedular {

	
    public int leastInterval(char[] tasks, int n) {
        
    	Map<Character, Integer> hash = new HashMap<>();
    	
    	for (char c: tasks) {
    		hash.put(c, hash.getOrDefault(c, 0) + 1);
    		
    	}
    	
    	PriorityQueue<Integer> pq = new PriorityQueue<>((a, b) -> b- a);
    	ArrayDeque<Pair> q = new ArrayDeque<>();
    	
    	pq.addAll(hash.values());
    	int time = 0;
    	while(!pq.isEmpty() || !q.isEmpty()) {
    		time++;
    		
    		// update pq
    		if (!pq.isEmpty()) {
    			int cnt = pq.poll();
    			cnt--;
    			if (cnt > 0) q.push(new Pair(cnt, time + n));

    		}
    		
    		// update q
    		if (!q.isEmpty() && q.peek().time == time) {
    			pq.add(q.poll().cnt);
    		}
    	}
    	
    	return time;
    	
    }
    
    public class Pair {
    	int cnt;
    	int time;
    	
    	Pair(int cnt, int time) {
    		this.cnt = cnt;
    		this.time = time;
    	}
    }
	
}
