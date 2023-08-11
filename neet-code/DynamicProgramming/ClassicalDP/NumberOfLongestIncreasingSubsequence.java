package ClassicalDP;

import java.util.Arrays;

public class NumberOfLongestIncreasingSubsequence {
	
	
	
	
    public int findNumberOfLIS(int[] nums) {
        
    	int[] dp = new int[nums.length];
    	int[] count = new int[nums.length]; // store the total number of LIS ending at count[i]
    	
    	Arrays.fill(dp, 1);
    	Arrays.fill(count, 1);
    	
    	int res = 0;
    	int lis = 0;
    	
    	for (int i = 0; i < nums.length; i++) {
    		for (int j = 0 ; j < i; j++) {
    			if (nums[i] > nums[j]) {	
    				if (dp[i] == dp[j] + 1) { // found different subsequence that can be made using jth 
    					count[i] += count[j]; // jth element까지 만들수 있는 count수를 그대로 더함. 
    				} else if (dp[i] < dp[j] + 1){ // found longer one 
    					dp[i] = dp[j] + 1;
    					count[i] = count[j]; // jth count가 유일할테니 
    				}
    			}
    		}
    		
    		if (lis == dp[i]) {
    			res += count[i];
    		} else if (lis < dp[i]) {
    			res = count[i];
    			lis = dp[i];
    		}
    		
    	}
    	
    	
    	return res;
    	
    }
}
