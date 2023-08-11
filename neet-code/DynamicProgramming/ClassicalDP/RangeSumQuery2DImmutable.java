package ClassicalDP;

public class RangeSumQuery2DImmutable {
	
	class NumMatrix {
		private int[][] dp;
		
	    public NumMatrix(int[][] matrix) {
	        dp = new int[matrix.length + 1][matrix[0].length + 1];
	    	
	    	for (int i = 1; i <= matrix.length; i++) {
	    		for (int j = 1; j <= matrix[0].length; j++) {
	    			dp[i][j] = dp[i-1][j] + dp[i][j-1] + matrix[i-1][j-1] - dp[i-1][j-1];
	    		}
	    	}
	    }
	    
	    public int sumRegion(int row1, int col1, int row2, int col2) {
	    	int total = dp[row2+1][col2+1];
	    	int common = dp[row1][col1];
	    	int substract = dp[row1][col2+1] + dp[row2+1][col1];
	    	
	    	return total - substract + common;
	    	
	    }
	}
}
