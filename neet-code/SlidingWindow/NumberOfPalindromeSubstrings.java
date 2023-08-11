
public class NumberOfPalindromeSubstrings {
	
	
    public int countSubstrings(String s) {
    	
    	
    	int res = 0;
    	int left = 0;
    	int right = 0;
    	
    	for (int i = 0; i < s.length(); i++) {
    		// odd case
    		left = i;
    		right = i;
    		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
    			res++;
    			left--;
    			right++;
    		}
    		
    		
    		// even case
    		left = i - 1;
    		right = i;
    		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
    			res++;
    			left--;
    			right++;
    		}
    	}
    	
    	return res;
    	
        
    }
}
