package ClassicalDP;

public class LengthOfLongestFibonacciSubsequence {
    public int lenLongestFibSubseq(int[] arr) {
    	int[][] dp = new int[arr.length][arr.length];
    	
    	
    	int res = 0;
    	
    	for (int i = 2; i < arr.length; i++) {
    		int lo = 0;
    		int hi = i-1;
    		
    		while(lo < hi) {
    			int sum = arr[lo] + arr[hi];
    			
    			if (sum < arr[i]) {
    				lo++;
    			} else if (sum > arr[i]) {
    				hi--;
    			} else {
    				dp[hi][i] = dp[lo][hi] + 1;
    				res = Math.max(res, dp[hi][i]);
    				lo++;
    				hi--;
    			}
    		}
    	}
    	return res > 0 ? res + 2: 0;
    }
}
