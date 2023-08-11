
public class PartitionEqualSubsetSum {
	
	
	public boolean canPartition(int[] ar) {
		int sum = 0;

		for (int i = 0; i < ar.length; i++) {
			sum += ar[i];
		}

		if (sum < 0 || sum % 2 != 0)
			return false;

		int s1 = sum / 2;

		boolean[][] dp = new boolean[ar.length + 1][s1 + 1];

		dp[0][0] = true;
		for (int i = 1; i <= ar.length; i++) {
			for (int j = 0; j <= s1; j++) {
				if (ar[i - 1] <= j) {
					dp[i][j] = dp[i - 1][j] || dp[i - 1][j - ar[i - 1]];
				} else {
					dp[i][j] = dp[i - 1][j];
				}
			}
		}

		return dp[ar.length][s1];

	}
	

}
/*
 * 0/1 knapsack의 핵심 1. dp[index][currSum] 2d array를 만들어서 현재 element를 가지고
 * currSum을 만들때 넣을꺼냐 않넣을꺼냐 선택
 * 
 * 2. 결국 return하고 싶은거는 dp[n][s1] 이기 때문에 처음 dp initialize할때 i+1 을 하고 실제
 * looping시에는 i-1로 array의 curr element를 access한다.
 * 
 * 
 */