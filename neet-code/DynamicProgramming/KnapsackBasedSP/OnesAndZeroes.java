package KnapsackBasedSP;

public class OnesAndZeroes {
	
	
    public int findMaxForm(String[] strs, int m, int n) {
        int[][][] dp = new int [strs.length+1][m+1][n+1];
        
        for (int k = 1; k <= strs.length; k++) {
        	int[] temp = count(strs[k-1]);
        	int zeros = temp[0];
        	int ones = temp[1];
        	
        	
        	for (int i = 0; i <= m; i++) {
        		for (int j = 0; j <= n; j++) {
                    dp[k][i][j] = dp[k-1][i][j];
                    if (i - zeros >= 0 && j - ones >= 0) {
        				dp[k][i][j] = Math.max(dp[k][i][j], dp[k-1][i-zeros][j-ones] + 1);
        			}
        		}
        	}
        }
        
        return dp[strs.length][m][n];
        
    
    }
    
    private int[] count(String str) {
    	int ones = 0;
    	int zeroes = 0;
    	
    	for (char c: str.toCharArray()) {
    		if (c == '1') ones++;
    		if (c == '0') zeroes++;
    	}
    	
    	return new int[] {zeroes, ones};
    	
    }
}
