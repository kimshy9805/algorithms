
public class BestTimeToBuyAndSellStock {
	
    public int maxProfit(int[] prices) {
    	
    	
    	int left = 0;
    	int right = 1;
    	
    	int res = 0;
    	while(right < prices.length) {
    		if (prices[left] < prices[right]) {
    			res = Math.max(res, prices[right] - prices[left]);
    			right++;
    		} else {
    			left = right;
    			right++;
    		}
    	}
    	
    	return res;
    	
    	
    }
	
}
