import java.util.HashMap;
import java.util.Map;

public class MinimumWindowSubstring {
	
    public String minWindow(String s, String t) {
        
    	Map<Character, Integer> hashT = new HashMap<>();
    	for (char c: t.toCharArray()) {
    		hashT.put(c, hashT.getOrDefault(c, 0) + 1);
    	}
    	
    	int matchingFreq = 0;
    	int res = Integer.MAX_VALUE;
    	int left = 0;
    	String winString = "";
    	
    	for (int right = 0; right < s.length(); right++) {
    		char c = s.charAt(right);
    		
    		if (hashT.containsKey(c)) {
    			hashT.put(c, hashT.get(c) - 1);  // update hashT
    			if (hashT.get(c) == 0) matchingFreq++;
    		}
    		
    		while(left < s.length() && matchingFreq == hashT.size()) {
                if (res > right - left + 1) {
                    res = right - left + 1;
                    winString = s.substring(left, right+1);
                }
    			
    			if (hashT.containsKey(s.charAt(left))) {
                    if (hashT.get(s.charAt(left)) == 0) matchingFreq--;
    				hashT.put(s.charAt(left), hashT.get(s.charAt(left)) + 1);
    			}
                left++;
    		}
    	}
    	
    	
    	
    	return winString;
    	
    	
    }
}	




/*
	해당 window가 t의 모든 character들을 포함하는가 
	그걸 tracking 하는 방법은 
	matchingFreq 변수 도임 
	requiredHash.containsKey(c)
		requiredHash.get(c) == windowHash.get(c)
			matchingFreq++;
	
	
	totalFreq은 requiredHash의 size 
	
	그래서 matchingFreq 가 == totalFreq일때만 window adjust 
	





*/