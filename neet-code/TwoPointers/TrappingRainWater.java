
public class TrappingRainWater {
    public int trap(int[] height) { // O(n)
    	int l = 0;
    	int r = height.length - 1;
    	
    	
    	int maxL = height[l];
    	int maxR = height[r];
    	
    	int res = 0;
    	
    	while(l < r) {
    		if (maxL < maxR) {
    			l++;
    			maxL = Math.max(maxL, height[l]);
    			res += maxL - height[l];
    		} else {
    			r--;
    			maxR = Math.max(maxR, height[r]);
    			res += maxR - height[r];
    		}
    	}
    	
    	return res;
    	
    	
    }
    
	
    public int trapBS(int[] height) { // O(n^2)
        int maxL = 0;
        int maxR = 0;
        int l = 0;
        int r = height.length-1;
    	
        
        int res = 0;
    	for (int i = 0; i < height.length; i++) {
    		// update maxL
    		l = i - 1;
    		maxL = 0;
    		while(l >= 0) {
    			maxL = Math.max(maxL, height[l]);
    			l--;
    		}
    		
    		// update maxR
    		r = i + 1;
    		maxR = 0;
    		while(r < height.length) {
    			maxR = Math.max(maxR, height[r]);
    			r++;
    		}
    		
    		
    		// update res
    		res += Math.min(maxL, maxR) - height[i] >= 0 ? Math.min(maxL, maxR) - height[i] : 0; 
    		
    		
    	}
    	
    	return res;
    	
    }
	
}


/*
	Two Pointers
	
	Keep track of MaxL, MaxR
	
	At this current position, how much water can be filled?
	Min(MaxL, MaxR) - height[i]
	 
	if MaxL < MaxR 
		l++;
		MaxL = Math.max(MaxL, height[i])
		res += MaxL - heigh[i]
	else 

*/