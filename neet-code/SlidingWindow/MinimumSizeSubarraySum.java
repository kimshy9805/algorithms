
public class MinimumSizeSubarraySum {
    public int minSubArrayLen(int target, int[] nums) {
    	
    	int res = Integer.MAX_VALUE;
    	int left = 0;
    	int currSum = 0;
    	for (int right = 0 ; right < nums.length; right++) {
    		currSum += nums[right];
    		
    		while(left < nums.length && currSum >= target) {
    			res = Math.min(res,  right - left + 1);
    			currSum -= nums[left];
    			left++;
    			
    		}
    	}
    	
    	return res == Integer.MAX_VALUE ? 0 : res;
    	
    }
}
