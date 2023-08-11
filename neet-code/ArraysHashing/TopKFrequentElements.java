import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class TopKFrequentElements {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public int[] topKFrequent(int[] nums, int k) {
    	PriorityQueue<Pair> pq = new PriorityQueue<>((Pair a, Pair b) -> b.count - a.count);
    	
    	// Come up with freq table
    	Map<Integer, Integer> freq = new HashMap<>();
    	
    	for (int num: nums) {
    		freq.put(num, freq.getOrDefault(num, 0) + 1);
    	}
    	
    	for (Map.Entry<Integer, Integer> entry: freq.entrySet()) {
    		Pair pair = new Pair(entry.getKey(), entry.getValue());
    		pq.add(pair);
    	}
    	
    	int i = 0;
    	int[] res= new int[k];
    	while(k-- > 0) {
    		res[i] = pq.poll().value;
    		i++;
    	}
    	
    	return res;
    }
    
    
    public class Pair {
    	Integer value;
    	Integer count;
    	
    	Pair(Integer value, Integer count) {
    		this.value = value;
    		this.count = count;
    	}
    	
    	
    }

}
