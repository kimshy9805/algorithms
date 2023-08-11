package ClassicalDP;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
	
	
    public int lengthOfLIS(int[] nums) {
        
    	int[] dp = new int[nums.length];
    	
    	int res = 0;
    	Arrays.fill(dp, 1);
    	
    	for (int i = 0; i < nums.length; i++) {
     		for (int k = 0; k < i; k++) {
    			if (nums[i] > nums[k]) {
    				dp[i] = Math.max(dp[i], dp[k] + 1);
    			}
    		}
    		
    		res = Math.max(dp[i], res);
    		
    	}
    	
    	return res;
    	
    }
}
