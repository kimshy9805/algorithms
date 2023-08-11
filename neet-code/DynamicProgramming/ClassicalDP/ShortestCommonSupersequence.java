package ClassicalDP;

public class ShortestCommonSupersequence {

	
    public String shortestCommonSupersequence(String str1, String str2) {
        
    	
    	return lcs(str1, str2);
    	
    }
    
    
    private String lcs(String s1, String s2) {
    	int[][] dp = new int [s1.length()+1][s2.length()+1];
    	
    	
    	for (int i = s1.length()-1; i >= 0; i--) {
    		for (int j = s2.length()-1; j >= 0; j--) {
    			if (s1.charAt(i) == s2.charAt(j)) {
    				dp[i][j] = dp[i+1][j+1]; 
    			} else {
    				dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
    			}
    		}
    	}
    	
    	StringBuilder sb = new StringBuilder();
    	
    	int i = 0;
    	int j = 0;
    	
    	while(i < s1.length() && j < s2.length()) {
    		if (s1.charAt(i) == s2.charAt(j)) {
    			sb.append(s1.charAt(i));
    			i++;
    			j++;
    		} else if (dp[i+1][j] > dp[i][j+1]) {
    			sb.append(s1.charAt(i));
    			i++;
    		} else {
    			sb.append(s2.charAt(j));
    			j++;
    		}
    	}
    	
    	while(i < s1.length()) {
    		sb.append(s1.charAt(i));
    		i++;
    	}
    	
    	while(j < s2.length()) {
    		sb.append(s2.charAt(j));
    		j++;
    	}
    	
    	return sb.toString();
    }
}
