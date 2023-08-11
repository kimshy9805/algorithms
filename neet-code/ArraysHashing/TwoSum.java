import java.util.HashMap;
import java.util.Map;

public class TwoSum {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> hash = new HashMap<>();
		
		
		// Hash가 value와 index 저장 
		for (int i = 0; i < nums.length; i++) {
			hash.put(nums[i], i);
		}
		
		int[] res= new int[2];
		
		for (int i = 0; i < nums.length; i++) {
			int left = target - nums[i];
			
			if (hash.containsKey(left) && hash.get(left) != i) {
				res[0] = i;
				res[1] = hash.get(left);
				break;
			}
		}
		
		return res;
		
		
		
	}

}



