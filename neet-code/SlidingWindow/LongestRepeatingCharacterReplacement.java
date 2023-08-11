import java.util.HashMap;
import java.util.Map;

public class LongestRepeatingCharacterReplacement {
	
	
    public int characterReplacement(String s, int k) {
    	Map<Character, Integer> hash = new HashMap<>();
    	
    	int maxRepeating = 0;
    	
    	char[] ar = s.toCharArray();
    	int left = 0;
    	
    	int res = 0;
    	
    	for (int right=0; right < ar.length; right++) {
    		hash.put(ar[right], hash.getOrDefault(ar[right], 0) + 1);
    		maxRepeating = Math.max(hash.get(ar[right]), maxRepeating);
    		
    		while(left < ar.length && right - left + 1 - maxRepeating > k) {
    			hash.put(ar[left], hash.get(ar[left]) - 1);
    			left++;
    		}
    		
    		res = Math.max(res, right - left + 1);
    		
    		
    	}
    	 
    	return res;
    	
    	
    	
    	
    	
    }
}
