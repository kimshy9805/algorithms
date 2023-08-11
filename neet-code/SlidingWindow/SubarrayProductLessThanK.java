
public class SubarrayProductLessThanK {
		
	
	
    public int numSubarrayProductLessThanK(int[] nums, int k) {
        if(k <= 1) return 0; // if k <= 1, no way we can make product of subarray to be less than 1

    	int res = 0;
    	int left = 0;
    	int right = 1;
    	
    	int currProd = nums[0];
    	if (currProd < k) res++;

    	while(right < nums.length) {
    		currProd *= nums[right];
    		
    		while(currProd >= k) {
    			currProd /= nums[left];
    			left++;
    		}
    		
    		res += right - left + 1; // 이 부분이 trick인듯. 
    		right++;
    	}
    	
    	return res;
    	
	    
	}
}


/*
	
	After including A[right] there are 2 Cases Possible:
	
	CASE 1: product is less than k
	It means that I can be part of previous Subarrays (right-left) and also can start a subarray from me(+1).
	So in total Subarrays increase count by (right-left+1)
	
	CASE 2:Product will became greater than k
	Start relasing element from left till product will become less than k.
	Now same logic as of Case 1.
	
	
	so if(k<=1) return 0.


*/