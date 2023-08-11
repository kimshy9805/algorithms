import java.util.Arrays;

public class CoinChange {
	
	
	
    public int coinChange(int[] coins, int amount) {
        
    	int[] dp = new int[amount+1];
    	
    	// init
    	Arrays.fill(dp, amount + 1);
    	
    	dp[0] = 0;
    	
    	
    	// for each coin
    	for (int coin: coins) {
    		for (int i = 1; i <= amount; i++) {
    			if (i - coin >= 0) {
    				dp[i] = Math.min(dp[i], dp[i-coin] + 1);
    			}
    		}
    	}
    	
    	
    	return dp[amount] != amount + 1? dp[amount] : -1;
    	
    }
}
