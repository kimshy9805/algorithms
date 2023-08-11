public class MaximumProductSubarray {
	
	
    public int maxProduct(int[] nums) {
        
    	int n = nums.length;
    	
    	int[][] dp = new int[n][2];
    	
    	dp[0][0] = nums[0];
    	dp[0][1] = nums[0];
    	
    	int res = nums[0];
    	
    	for (int i = 1 ; i < nums.length; i++) {
    		// Positive, 
    		if (nums[i] >= 0) {
    			dp[i][0] = Math.max(dp[i-1][0] * nums[i], nums[i]);
    			dp[i][1] = Math.min(dp[i-1][1] * nums[i], nums[i]);
    			
    		// Negative -> max는 prev min과 비교. Min은 prev max와 비교
    		} else {
    			dp[i][0] = Math.max(dp[i-1][1] * nums[i], nums[i]);
    			dp[i][1] = Math.min(dp[i-1][0] * nums[i], nums[i]);
    		}
    		
    		res = Math.max(res, dp[i][0]);
    		
    	}
    	
    	return res;
    	
    }
}
