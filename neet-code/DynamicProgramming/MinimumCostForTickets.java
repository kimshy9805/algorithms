
public class MinimumCostForTickets {
	
	
	
    public int mincostTickets(int[] days, int[] costs) {
        
    	
    	int n = days[days.length-1];
    	
    	
    	int[][] dp = new int[4][n+1];
    	
    	for (int i = 0 ; i < dp.length; i++) {
    		if (i == 0) {
    			for (int j = 1; j < dp[i].length; j++) {
    				dp[i][j] = Integer.MAX_VALUE;
    			}
    		}
    	}
    	
    	
    	for (int i = 1; i <= 3; i++) {
    		for (int j = 0; j <= n; j++) {
    			int day = i == 1 ? 1 : i == 2 ? 7 : 30;
    			
    			if (j - day >= 0) {
    				dp[i][j] = Math.min(dp[i-1][j], dp[i][j -day] + costs[i-1]);
    			} else {
    				dp[i][j] = dp[i-1][j];
    			}
    			
    		}
    	}
    	
    	return dp[3][n];
    	
    	
    }
}
