package ClassicalDP;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class LargestDivisibleSubset {
	
	static List<Integer> ans ;
	
    public List<Integer> largestDivisibleSubset(int[] nums) {
        
    	Arrays.sort(nums);
    	
    	int[] dp = new int[nums.length];
    	dfs(0, 1, nums, new ArrayList<>());
    	
    	return ans;
    	
    }
    
    private void dfs (int i, int prev, int[] nums, List<Integer> currList) {
    	if (i >= nums.length) {
    		if (currList.size() >= ans.size()) {
    			ans.clear();
    			ans.addAll(currList);
    		}
    		return;
    	}
    	
    	
    	// add 
    	if (nums[i] % prev ==0 || prev % nums[i] == 0) {
    		currList.add(nums[i]);
    		
    		dfs(i+1, nums[i], nums, currList);
    		
    		// remove to check skip action
    		currList.remove(currList.size()-1);
    		
    	}
    	
    	
    	// skip
    	dfs(i+1, prev, nums, currList);
    	
    	
    }
    
    
}
