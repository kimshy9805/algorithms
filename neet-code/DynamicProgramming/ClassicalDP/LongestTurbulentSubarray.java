package ClassicalDP;

public class LongestTurbulentSubarray {

	
	
    public int maxTurbulenceSize(int[] arr) {
        
    	int[][] dp = new int[arr.length][2];
    	int res = 1;
    	
    	dp[0][0] = 1;
    	dp[0][1] = 1;
    	
    	for (int i = 1; i < arr.length; i++) {
    		if (arr[i] > arr[i-1]) {
    			dp[i][0] = dp[i-1][1] + 1;
    			dp[i][1] = 1;
    		} 
    		if (arr[i] < arr[i-1]) {
    			dp[i][0] = 1;
    			dp[i][1] = dp[i-1][0] + 1;
    		}
    		if (arr[i] == arr[i-1]) {
    			dp[i][0] = 1;
    			dp[i][1] = 1;
    		}
    		
    		res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
    	}
    	
    	return res;
    	
    	
    }
}
