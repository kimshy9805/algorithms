public class KokoEatingBananas {
	
	// Find the range of k
	// Perform BS to find the minimum k given hour to eat all piles of bananas
	
    public int minEatingSpeed(int[] piles, int h) {
     
    	int max = 0;
    	for (int pile: piles) {
    		max = Math.max(max, pile);
    	}
    	
    	int left = 1;
    	int right = max;
    	
    	
    	while(left < right) {
    		int mid = (right - left)/2 + left;
    		
    		int hours = 0;
    		for (int pile: piles) {
    			if (pile % mid == 0) {
    				hours += pile/mid;
    			} else {
    				hours += pile/mid + 1;
    			}
    			
    		}
    		
    		// update bs based on hours
    		if (hours <= h) { // if less than h		
    			// update bs. We can do better
    			right = mid;
    		} else {
    			left = mid + 1;
    		}
    	}
    	
    	
    	return left;
    	
    }
}
