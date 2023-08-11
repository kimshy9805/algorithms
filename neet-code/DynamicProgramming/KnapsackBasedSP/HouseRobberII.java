package KnapsackBasedSP;

import java.util.ArrayList;
import java.util.List;

public class HouseRobberII {
	
    public int rob(int[] nums) {
    	
    	if(nums.length == 0)    return 0;
        if(nums.length == 1)    return nums[0];
        
        List<Integer> house1 = new ArrayList<>();
        List<Integer> house2 = new ArrayList<>();
        
        for(int i = 0; i < nums.length-1; i++)
            house1.add(nums[i]);
        
        for(int i = 1; i < nums.length; i++)
            house2.add(nums[i]);
        
        
        return Math.max(helper(house1), helper(house2));
    }
    
    public int helper(List<Integer> house)
    {
        if(house.size() == 0)   return 0;
        if(house.size() == 1)   return house.get(0);
        
        int[] maxSum = new int[house.size()];
        maxSum[0] = house.get(0);
        maxSum[1] = Math.max(house.get(0), house.get(1));
        
        for(int i = 2; i < house.size(); i++)
            maxSum[i] = Math.max(maxSum[i-1], maxSum[i-2] + house.get(i));
        
        return maxSum[maxSum.length-1];
    }
    
    
    
    
    
    private int dfs(int ind, int[] nums, int amount, boolean isRob) {
    	if (ind == nums.length) {
    		return amount;
    	}
    	
    	if (isRob) {
    		return dfs(ind+1, nums, amount, false); // skip
    	}
    	
    	
    	return Math.max(dfs(ind+1, nums, amount + nums[ind], true), dfs(ind+1, nums, amount, false));
    	
    }
    
    
    
}
