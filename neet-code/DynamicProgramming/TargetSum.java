
public class TargetSum {

	
	
	
	
	 public int findTargetSumWays(int[] nums, int target) {
		 
		 int sum = 0;
		 for (int num: nums) {
			 sum += num;
		 }
		 
		 if ((sum-target) % 2 == 1 || target > sum) {
			 return 0;
		 }
		 
		 int s2= (sum - target) / 2;
		 
		 
		 int[][] dp = new int[nums.length+1][s2+1];
		 
		 dp[0][0] = 1;
		 
		 for (int i = 1; i <= nums.length; i++) {
			 for (int j = 0; j <= s2; j++) {
				 if (j - nums[i-1] >= 0) {
					 dp[i][j] = dp[i-1][j] + dp[i-1][j-nums[i-1]];
				 } else {
					 dp[i][j] = dp[i-1][j];
				 }
			 }
		 }
		 
		 
		 
		 return dp[nums.length][s2];
		 
	 }
}
