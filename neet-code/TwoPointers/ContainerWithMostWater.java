
public class ContainerWithMostWater {
    public int maxArea(int[] height) {
    	
    	int i = 0;
    	int j = height.length - 1;
    	
    	int res = 0;
    	while(i < j) {
    		int h = Math.min(height[i], height[j]);
    		
    		res = Math.max(res, h * (j - i));
    		
    		
    		if (height[i] < height[j]) {
    			i++;
    		} else {
    			j--;
    		}
    		
    	}
    	
    	return res;
    	
    }
}
