package ClassicalDP;

public class MaximumSubarraySumWithOneDeletion {
	
	
	
	
    public int maximumSum(int[] arr) {
        
    	int[][] dp = new int[arr.length][2];

    	int res = arr[0];
    	
    	dp[0][0] = arr[0];
    	dp[0][1] = arr[0];
    	
    	for (int i = 1; i < arr.length; i++) {
    		dp[i][0] = Math.max(arr[i], dp[i-1][0] + arr[i]); // 안 지웠으니 kadane algo 쓰면 
    		dp[i][1] = Math.max(dp[i-1][0], dp[i-1][1] + arr[i]); // 지웠을꺼라 가정하면 이전 안지운겂을 무조건 써야함. 혹은 이전에 지운값을 이용해서 만들꺼
    		
    		res = Math.max(res, Math.max(dp[i][0], dp[i][1]));
    		
    	}
    	
    	return res;
    	
    }
    
}
