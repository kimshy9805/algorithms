import java.util.HashMap;
import java.util.Map;

public class SubarraySumsDivisibleByK {
	
	  public int subarraysDivByK(int[] nums, int k) {
		  
		  Map<Integer, Integer> hash = new HashMap<>();
		  
		  
		  hash.put(0, 1);
		  
		  int currSum = 0;
		  int res = 0;
		  for (int i = 0 ; i < nums.length; i++) {
			  currSum += nums[i];
			  
			  int rem = currSum % k;
			  if (rem < 0) {
				  rem += k;
			  }
			  
			  
			  if (hash.containsKey(rem)) {
				  res += hash.get(rem);
			  }
			  
			  hash.put(rem, hash.getOrDefault(rem, 0) + 1);
			  
		  }
		  
		  return res;
		  
		  
	  }

}
