import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Sum3 {
    public List<List<Integer>> threeSum(int[] nums) {
    	Arrays.sort(nums);
    	
    	List<List<Integer>> res = new ArrayList<>();
    	
    	for (int i = 0 ; i < nums.length; i++) {
    		if (i > 0 && nums[i] == nums[i-1]) {
    			continue;
    		}
    		
    		int k = i + 1;
    		int j = nums.length - 1;
    		while(k < j) {
    			int sum = nums[i] + nums[k] + nums[j];
    			
    			if (sum < 0) {
    				k++;
    			} else if (sum > 0) {
    				j--;
    			} else {
    				res.add(Arrays.asList(nums[i], nums[k], nums[j]));
    				k++;
    				while (nums[k] == nums[k-1] && k < j) {
    					k++;
    				}
    			}
    		}
    	}
    	
    	return res;
    	
    }
}
