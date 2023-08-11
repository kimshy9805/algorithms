
public class SearchInRotatedSortedArray {
	
    public int search(int[] nums, int target) {
    	int l = 0;
    	int r = nums.length - 1;
    	
    	
    	
    	while(l < r) {
    		int mid = l + (r-l)/2;
    		
    		if (nums[mid] == target) {
    			return mid;
    		}
    		
    		// check if left is increasing order
    		if (nums[l] <= nums[mid]) {
    			// check if target is within the range 
    			if (target >= nums[l] && target < nums[mid]) { // notice nums[l] <= target considering the case l == 0
    				r = mid - 1;
    			} else {
    				l = mid + 1;
    			}
    		} else {
    			if (target > nums[mid] && target <= nums[r]) { // notice nums[r] >= target considering the case r = nums.length-1
    				l = mid + 1;
    			} else {
    				r = mid - 1;
    			}
    		}
    	}
    	
    	
    	return nums[l] == target ? l : -1;
    	
    	
    	
    }
}
