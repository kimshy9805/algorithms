import java.util.HashMap;
import java.util.Map;
import java.util.PriorityQueue;

public class SortCharactersByFrequency {
	
	
	
    public String frequencySort(String s) {
        
    	
    	char[] ar = s.toCharArray();
    	Map<Character, Integer> hash = new HashMap<>();
    	
    	for (char c: ar) {
    		hash.put(c, hash.getOrDefault(c, 0) + 1);
    	}
    	
    	PriorityQueue<Letter> pq = new PriorityQueue<>((a, b) -> b.cnt - a.cnt);
    	
    	for (Map.Entry<Character, Integer> entry: hash.entrySet()) {
    		pq.add(new Letter(entry.getKey(), entry.getValue()));
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	while(!pq.isEmpty()) {
    		Letter letter = pq.poll();
    		
    		while(letter.cnt > 0) {
    			sb.append(letter.c);
    			letter.cnt--;
    		}
    		
    	}
    	
    	return sb.toString();
    	
    }
    
    public class Letter{
    	char c;
    	int cnt;
    	
    	Letter(char c, int cnt) {
    		this.c = c;
    		this.cnt = cnt;
    	}
    	
    }
}
