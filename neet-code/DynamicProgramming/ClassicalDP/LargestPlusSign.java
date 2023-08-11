package ClassicalDP;

import java.util.Arrays;

public class LargestPlusSign {
	
	
    public int orderOfLargestPlusSign(int n, int[][] mines) {
        int[][] dp = new int [n][n];
        
        Arrays.fill(dp, 1);
    	
        for (int[] temp : dp) {
            Arrays.fill(temp, 1);
        }
        
    	int order = 0;
    	int res = order;
    	
    	for (int i = 0; i < n; i++) {
    		for (int j = 0 ; j < n; j++) {
    			if (dp[i][j] == 1) {
    				order = 1;
        			while(true) {
        				int left = j - order;
        				int right = j + order;
        				int top = i - order;
        				int bottom = i + order;
        				
        				if (left < 0 || right > n-1 || top < 0 || bottom > n-1) break;
        				if (dp[i][left] == 0 || dp[i][right] == 0 || dp[top][j] == 0 || dp[bottom][j] == 0) break;
        				order++;
        			}
        			
        			res = Math.max(res, order);
    			}
    		}
    	}
    	
    	return res;
    	
    	
    }
}
