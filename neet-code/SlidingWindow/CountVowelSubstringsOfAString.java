import java.util.HashMap;
import java.util.Map;

public class CountVowelSubstringsOfAString {
	  public int countVowelSubstrings(String word) {
	    	Map<Character, Integer> hash = new HashMap<>();
	    	
	    	
	    	char[] ar = word.toCharArray();
	    	
	    	int res = 0;
	    	
	    	// iterate until size - 5
	    	for (int i = 0; i <= ar.length - 5; i++) {
	    		// check if char is vowel
	    		// consonant -> clear hash iterate next
	    		if (isVowel(ar[i])) {
	    			hash = new HashMap<>();
	    			hash.put(ar[i], hash.getOrDefault(ar[i], 0) + 1);
	        		
	        		// iterate rest of characters
	        		for (int j = i + 1; j < ar.length; j++) {
	        			if (!isVowel(ar[j])) {
	            			hash.clear();
	            			break;
	            		}
	        			
	            		hash.put(ar[j], hash.getOrDefault(ar[j], 0) + 1);
	        			
	        			res += isVowelSubstring(hash) ? 1 : 0;
	        		}
	    		}
	    	
	    	}
	    	
	    	return res;
	    	
	    }
	    
	    private boolean isVowelSubstring(Map<Character, Integer> hash) {
	    	if (hash.getOrDefault('a', 0) > 0 
	    			&& hash.getOrDefault('e', 0) > 0 
	    			&& hash.getOrDefault('i', 0) > 0 
	    			&& hash.getOrDefault('o', 0) > 0 
	    			&& hash.getOrDefault('u', 0) > 0) {
	    		return true;
	    	}
	    	
	    	return false;
	    }
	    
	    private boolean isVowel(Character key) {
			if (key == 'a' || key == 'e' || key == 'i' || key == 'o' || key == 'u') {
				return true;
			}
			
			return false;

	    }
}
