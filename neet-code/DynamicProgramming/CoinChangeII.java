
public class CoinChangeII {
	
	
	
	
	  public int change(int amount, int[] coins) {
	        
	    	
	    	int[] dp = new int[amount+1];
	    	
	        dp[0] = 1;
	    	
	    	for (int i = 1; i <= coins.length; i++) { // i-1으로 coin access
	    		for (int j = 0; j <=amount; j++) {
	    			if (j - coins[i-1] >= 0) {
	    				dp[j] = dp[j] + dp[j- coins[i-1]];
	    			}
	    		}
	    	}
	    	
	    	return dp[amount];
	    	
	    }
}
