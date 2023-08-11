
public class UniquePathsII {

	
	public int uniquePathsWithObstacles(int[][] grid) {
	     
    	int m = grid.length;
    	int n = grid[0].length;
    	
    	int[][] dp = new int[m][n];     	
    	
        if (grid[0][0] == 1) return 0;

    	// precompute case j = 0
    	
		for (int i = 0; i < m; i++) {
			if (grid[i][0] == 1) {
				break;
			}
			dp[i][0] = 1;
		}
    	
    	for(int i = 0; i < m; i++) {
    		for(int j = 1; j < n; j++) {
    			// check grid[i][j] got obstacle 
    			if (i == 0) {
    				if (grid[i][j] == 1) {
    					break; // no need to process j
    				} else {
    					dp[i][j] = 1;
    				}
                    continue;
    			}


                if (grid[i][j] == 1) continue;
    			
    			dp[i][j] = dp[i-1][j] + dp[i][j-1];
    		}
    	}
    	
    	
    	
    	return dp[m-1][n-1];
    	
    }
}
