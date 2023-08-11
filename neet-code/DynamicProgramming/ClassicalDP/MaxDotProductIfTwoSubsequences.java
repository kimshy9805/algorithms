package ClassicalDP;

public class MaxDotProductIfTwoSubsequences {

	
    public int maxDotProduct(int[] nums1, int[] nums2) {
    	
    	
    	int[][] dp = new int[nums1.length][nums2.length];
    	
    	return dfs(nums1, nums2, 0, 0, dp);
    	
    	
    	
    }
    
    private int dfs(int[] nums1, int[] nums2, int i, int j, int[][] dp) {
    	if (i >= nums1.length || j >= nums2.length) {
    		return Integer.MIN_VALUE;
    	}
    	
    	
    	if (dp[i][j] != 0) return dp[i][j];
    	
    	// take one from nums1
    	int ans1 = dfs(nums1, nums2, i+1, j, dp);
    	// take one from nums2
    	int ans2 = dfs(nums1, nums2, i, j+1, dp);
    	// dont take any
    	int ans3 = dfs(nums1, nums2, i+1, j+1, dp);
    	
    	int dot = nums1[i] * nums2[j] + (ans3 < 0 ? 0: ans3);
    	
    	
    	return dp[i][j] = Math.max(ans1, Math.max(ans2, Math.max(ans3, dot)));
    }
    
    
    
}
