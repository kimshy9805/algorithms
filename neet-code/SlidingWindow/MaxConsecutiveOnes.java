
public class MaxConsecutiveOnes {
	public int longestOnes(int[] nums, int k) {
        int ones = 0;
        int res =0;
        int left = 0;
       
        for (int right=0; right < nums.length; right++) {
        	if (nums[right] == 1) ones++;
        	
        	while(left < nums.length && right - left + 1 - ones > k) {
        		if (nums[left] == 1) ones--;
        		left++;
        	}
        	
        	res = Math.max(res, right - left + 1);
        }
	   
	   return res;
	   
    }
}
