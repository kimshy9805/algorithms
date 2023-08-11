import java.util.HashMap;
import java.util.Map;

public class ContainsDuplicate {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	
	public static boolean containsDuplicate(int[] nums) {
		Map<Integer, Integer> freq = new HashMap<>();
		
		for (int i = 0; i < nums.length; i++) {
			if (freq.containsKey(nums[i])) {
				return true;
			}
			freq.put(nums[i], 1);
		}
		
		return false;
		
		
	}
	

}
