
public class BestTimeToBuyAndSellStockWithCooldown {
	
	
	
    public int maxProfit(int[] prices) {
        
    	if (prices == null || prices.length == 0) return 0;
    	
    	
    	int n = prices.length;
    	int[] dp = new int [n]; // keep tracking maxProfit you have at ith day 
    	
    	for (int i = 0 ; i < n; i++) {
    		if (i == 0) dp[0] = 0; // if 0 day -> no profit so far 0 
    		else if (i == 1) dp[1] = Math.max(prices[1] - prices[0], 0); // if 1 day -> you could have bought stock at day 0 and sell day 1
    		else {
    			dp[i] = dp[i-1]; // update maxProfit from prev day
    			// linear scan
    			for (int j = 0 ; j < i; j++) { 
    				int prevProfit = j >= 2 ? dp[j-2] : 0; // j - 1 must be cooldown if j is buy day. 
    				dp[i] = Math.max(dp[i], prevProfit + prices[i] - prices[j]);
    			}
    		}
    	}
    	
    	
    	return dp[n-1];
    	
    	
    }
}

/*
	 Ref: https://leetcode.com/problems/best-time-to-buy-and-sell-stock-with-cooldown/solutions/240097/come-on-in-you-will-not-regret-most-general-java-code-like-all-other-dp-solutions/?languageTags=java

*/