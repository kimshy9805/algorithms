import java.util.HashMap;
import java.util.Map;

public class LongestSubstringWithoutRepeatingCharacters {
	
	
    public int lengthOfLongestSubstring(String s) {
    	Map<Character, Integer> hash = new HashMap<>();
    	
    	
    	int res = 0;
    	
    	int left = 0;
    	
    	char[] ar = s.toCharArray();
    	
    	
    	for (int right = 0; right < ar.length; right++) {
    		hash.put(ar[right], hash.getOrDefault(ar[right], 0) + 1);
    		
    		while(left < ar.length && hash.get(ar[right]) > 1) {
    			hash.put(ar[left], hash.get(ar[left]) - 1);
    			left++;
    		}
    		
    		res = Math.max(res, right - left + 1);
    		
    	}
    	
    	return res;
    	
    	
    }
}
