
public class MinCostClimbingStairs {
	
	
	
	
    public int minCostClimbingStairs(int[] cost) {
        int n = cost.length;
    	
    	int[] ar = new int[n+ 1];
    	
    	for (int i = 0 ; i < n; i++) {
    		ar[i] = cost[i];
    	}
    	
    	// [10, 15, 20, 0]
    	
    	// n = 3
    	// 0    1   2   3
    	
    	int[] dp = new int[n+1];
    	
    	
    	// base case
    	
    	
    	// 0   0   0   0
    	
    	dp[0] = ar[0];
    	dp[1] = ar[1];

    	
    	for (int i = 2; i < ar.length; i++) {
    		dp[i] = Math.min(dp[i-1], dp[i-2]) + ar[i];    		
    	}
    	
    	
    	// dp[n];
    	return dp[n];
    	
    }
}
