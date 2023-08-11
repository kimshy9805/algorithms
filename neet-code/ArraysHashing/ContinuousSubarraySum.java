import java.util.HashMap;
import java.util.Map;

public class ContinuousSubarraySum {
	
	
	
	
	public boolean checkSubarraySum(int[] nums, int k) {
	       
		Map<Integer, Integer> hash = new HashMap<>();
		
		
		hash.put(0, -1);
		int currSum = 0;
		
		for (int i = 0; i < nums.length; i++) {
			currSum += nums[i];
			
			int rem = currSum % k;
			
			if (hash.containsKey(rem)) {
				if (i - hash.get(rem) >= 2) {
					return true;
				}
			
			} else {
				hash.put(rem, i);
			}
	
		}
		
		return false;
		
		
		
		
		
	}
}
