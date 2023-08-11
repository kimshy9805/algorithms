import java.util.HashSet;
import java.util.Set;

public class LongestConsecutiveSequence {
	
    public int longestConsecutive(int[] nums) {
    	Set<Integer> set = new HashSet<>();
    	
    	// 1 Convert into Set 
    	for (int num: nums) {
    		set.add(num);
    	}
    	
    	int res = 0;
    	
    	
    	// 2 check if its start element of seq
    	for (int i = 0; i < nums.length; i++) {
    		// this is not starting 
    		if (set.contains(nums[i] - 1)) continue;
    		
    		
    		int curr = nums[i];
    		int cnt = 0;
    		while(set.contains(curr)) {
    			curr += 1;
    			cnt += 1;
    		}
    		
    		res = Math.max(cnt, res);
    		
    	}
    	
    	return res;
    	
    	
    }
}



/*

	0 3 7 2 5 8 4 6 0 1
	
	0 -> 2
	1 -> 1
	2 -> 1
	3 -> 1
	4 -> 1
	5 -> 1
	6 -> 1
	7 -> 1
	8 -> 1



*/