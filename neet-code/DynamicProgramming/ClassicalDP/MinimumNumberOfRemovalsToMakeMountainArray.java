package ClassicalDP;

import java.util.Arrays;

public class MinimumNumberOfRemovalsToMakeMountainArray {
	
	
	
    public int minimumMountainRemovals(int[] nums) {
    	int[] dp = new int [nums.length];
    	
    	Arrays.fill(dp, 1);
    	
    	int[] dp2 = new int[nums.length];
    	
    	Arrays.fill(dp2, 1);
    	
    	for (int i = 0; i < nums.length; i++) {
    		for (int j = 0; j < i; j++) {
    			if (nums[i] > nums[j]) {
    				dp[i] = Math.max(dp[i], 1 + dp[j]);
    			}
    		}
    	}
    	
    	for (int i = nums.length-1; i >= 0; i--) {
    		for (int j = nums.length-1; j > i; j--) {
    			if (nums[i] > nums[j]) {
    				dp2[i] = Math.max(dp2[i], 1 + dp2[j]);
    			}
    		}
    	}
    	
        //adding the length of LIS from left and right at index i and subtracting 1
        //gives  length of mountain array with its peek at i
        // we need to find the maxLength of a MountainArray
    	
    	int maxi = 0;
    	for (int i = 0; i < nums.length; i++) {
    		if (dp[i] > 1 && dp2[i] > 1) { // if either is 1, then do not consider first, last, or local maxima 
    	        // the first and last element can obviously not be a peek since they don't fulfill the property
    	        // i.e: resulting mountain would be only ascending or descending
    	        // one of LIS cannot be 1 since that would mean that LIS starts and ends there. i.e same as described above
    			maxi = Math.max(maxi, dp[i] + dp2[i] - 1);
    		}
    	}
    	
    	return nums.length - maxi;
    	
    }
}
