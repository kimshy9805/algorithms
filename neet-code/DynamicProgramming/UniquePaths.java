
public class UniquePaths {
	
    public int uniquePaths(int m, int n) {
        
    	int[][] dp = new int [m][n];  // m -> row, n -> col
    	
    	
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (i == 0 || j == 0) dp[i][j] = 1; // 어차피 i든 j든 0이면 오직 한 방법으로 밖에 도달하지 못
    			else {
    				dp[i][j] = dp[i-1][j] + dp[i][j-1]; // 아니면 left top 비교해서 값 compute
    			}
    		}
    	}
    	
    	return dp[m-1][n-1];    	
    	
    }
}


