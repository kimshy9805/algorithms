import java.util.Arrays;

public class LongestIncreasingPathInMatrix {
	
	
	int[] dx = {1, 0 , -1, 0};
	int[] dy = {0, -1, 0, 1};
	
    public int longestIncreasingPath(int[][] matrix) {
    	int n = matrix.length;
    	int m = matrix[0].length;
    	
        int[][] dp = new int[n][m];
    	
    	for(int[] temp: dp) {
    		Arrays.fill(temp, 1);
    	}
        
        for (int i = n-1; i >= 0; i--) {
        	for (int j = m-1; j >= 0; j--) {
        		for (int dd = 0; dd < 4; dd++) {
        			int nx = dx[dd] + i;
        			int ny = dy[dd] + j;
        			
        			if (nx >= 0 && nx < n && ny >= 0 && ny < m && matrix[nx][ny] > matrix[i][j]) {
        				dp[nx][ny] = Math.max(dp[nx][ny] + 1, dp[i][j]);
        			}
        		}
        	}
        }
    	
        int res = 0;
        for (int[] temp : dp) {
        	for (int a: temp) {
        		res = Math.max(a, res);
        	}
        }
    	
    	return res;
    	
    }
}
