package ClassicalDP;

public class MaximalRectangle {
	
	
	
    public int maximalRectangle(char[][] matrix) {
        
    	int[][] dp = new int [matrix.length+1][matrix[0].length+1];
    	
    	int res = 0;
    	
    	for (int i = 1; i <= matrix.length; i++) {
    		for (int j = 1; j <= matrix[0].length; j++) {
    			if (matrix[i-1][j-1] == '0') continue; // if matrix[i][j] 가 0이면 
    			dp[i][j] = 1;
    			
    			if (i-2 >= 0 && matrix[i-2][j] == '1') {
    				dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + 1);
    			}
    			
    			if (j-2 >= 0 && matrix[i][j-2] == '1') {
    				dp[i][j] = Math.max(dp[i][j], dp[i][j-1] + 1);
    			}
    			
    			if (i-2 >= 0 && j-2 >= 0 && matrix[i-2][j-1] == '1' && matrix[i-1][j-2] == '1' && matrix[i-2][j-2] == '1') {
    				dp[i][j] = Math.max(dp[i][j], dp[i-1][j] + dp[i][j-1] - dp[i-1][j-1] + 1);
    			}
    			
    			res = Math.max(res, dp[i][j]);
    		}
    	}
    	
    	
    	return res;
    	
    	
    }
    
    public int maximalRectangle1(char[][] matrix) {
        if (matrix.length <= 0) return 0;
        int n = matrix.length;
        int m = matrix[0].length;
        int[][] dp = new int[n][m];
        int maxArea = 0;
        for (int i = 0; i < n; i++) {
          for (int j = 0; j < m; j++) {
            if (i == 0)
    		  dp[i][j] = matrix[i][j] == '1' ? 1 : 0;
            else
    		  dp[i][j] = matrix[i][j] == '1' ? (dp[i-1][j] + 1) : 0;
            int min = dp[i][j];
            
            for (int k = j; k >= 0; k--) { // calculate max area col
              if (min == 0) break;
              if (dp[i][k] < min) min = dp[i][k];
              maxArea = Math.max(maxArea, min * (j - k + 1));
            }
          }
        }
        return maxArea;
      }
}


/*
	DP State

	1 0 1 0 0      1 0 1 0 0
	1 0 1 1 1  =>  2 0 2 1 1
	1 1 1 1 1  =>  3 1 3 2 2
	1 0 0 1 0      4 0 0 3 0
	
	Find the max area row by row, as we can see 3 1 3 2 2 contains the max area.




*/