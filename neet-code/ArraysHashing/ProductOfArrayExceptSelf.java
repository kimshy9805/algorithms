import java.util.HashMap;
import java.util.Map;

public class ProductOfArrayExceptSelf {

	
    public int[] productExceptSelf(int[] nums) {
    	int pref = 1;
    	Map<Integer, Integer> hash = new HashMap<>();
    	
    	// compute suffix 
    	for (int i = nums.length-1; i >= 0; i--) {
    		hash.put(i, hash.getOrDefault(i+1, 1) * nums[i]);
    	}
    	
    	
    	int[] res = new int[nums.length];
    	
    	for (int i = 0 ; i < nums.length; i++) {
    		res[i] = pref * hash.getOrDefault(i + 1, 1);
    		pref *= nums[i];
    	}
    	
    	return res;
    }
}
