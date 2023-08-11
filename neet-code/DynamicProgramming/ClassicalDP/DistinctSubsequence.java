package ClassicalDP;

public class DistinctSubsequence {
	
	
	  public int numDistinct(String s, String t) {
	        
	    	int[][] dp = new int[t.length() + 1][s.length() + 1];
	    	
	    	
	    	for (int i = 0; i <= s.length(); i++) {
	    		dp[t.length()][i] = 1;
	    	}
	    	
	    	
	    	for (int i = t.length() - 1; i >= 0; i--) {
	    		for (int j = s.length() - 1; j >= 0; j--) {
	    			if (s.charAt(j) == t.charAt(i)) {
	    				dp[i][j] = dp[i+1][j+1] + dp[i][j+1];
	    			} else {
	    				dp[i][j] = dp[i][j+1];
	    			}
	    		}
	    	}
	    	
	    	
	    	return dp[0][0];
	    	
	    }
    
    
    
}
