import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {

	
    public int subarraySum(int[] nums, int k) {
    	
    	Map<Integer, Integer> hash = new HashMap<>();
    	
    	
    	hash.put(0, 1);
    	int res = 0;
    	int currSum = 0;
    	for (int i = 0; i < nums.length; i++) {
    		currSum += nums[i];
    		
    		if (hash.containsKey(currSum - k)) {
    			res += hash.get(currSum - k);
    		}
    		
    		hash.put(currSum, hash.getOrDefault(currSum, 0) + 1);
    	}
    	
    	
    	return res;
    	
    }
}
