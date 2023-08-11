package ClassicalDP;

public class LongestPalindromicSubsequence {

    public int longestPalindromeSubseq(String s) {
        String p = new StringBuilder(s).reverse().toString();
    	
		int[][] dp = new int [s.length()+1][p.length()+1];
		
		for (int i = s.length()-1; i >= 0; i--) {
			for (int j = p.length()-1; j >= 0; j--) {
				if (s.charAt(i) == p.charAt(j)) {
					dp[i][j] = dp[i+1][j+1] + 1;
				} else {
					dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
				}
			}
		}
		
		return dp[0][0];    
    }
}
