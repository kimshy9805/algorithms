
public class DecodeWays {
	
	
	public static void main(String[] args) {
		
		
		numDecodings("123");
	}
	
	
	
    public static int numDecodings(String s) {
        
    	int[] ar = new int [s.length()];
    	
    	
    	char[] charAr = s.toCharArray();
    	
    	for (int i = 0 ; i < charAr.length; i++) {
    		ar[i] = charAr[i] - '0';
    	}
    	
    	// base case
    	int[] dp = new int[s.length() + 1];
    	
    	
    	dp[0] = 0;
    	dp[1] = ar[0] == 0 ? 0 : 1;
    	
    	
    	for (int i = 2; i <= ar.length; i++) {
    		if (ar[i - 1] != 0) {
        		dp[i] += dp[i-1];
    		}
    		
    		if (ar[i-2] == 1 || (ar[i-2] == 2 && ar[i - 1] <= 6)) {
    			dp[i] += dp[i-2];
    		}
    	}
    	
    	
    	return dp[ar.length];
    	
    }
}
