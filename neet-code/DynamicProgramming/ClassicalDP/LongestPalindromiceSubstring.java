package ClassicalDP;

public class LongestPalindromiceSubstring {
	
	
	
    public static String longestPalindrome(String s) {
        
   	int res = 0;
   	String resStr = "";
   	
   	if (s.length() < 2) return s;
   	
   	
   	for(int i = 0 ; i < s.length(); i++) {
   		// odd
   		int left = i;
   		int right = i;
   		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
   			if (right-left + 1 > res) {
   				res = right-left+1;
   				resStr = s.substring(left, right+1);
   			}
   			
   			left--;
   			right++;
   		}
   		
   		left = i - 1;
   		right = i;
   		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
   			if (right-left+1 > res) {
   				res = right-left+1;
   				resStr = s.substring(left, right+1);
   			}
   			
   			left--;
   			right++;
   		
   		}
   	}
   	
   	return resStr;
   	
   }
    
    
    
    
}
