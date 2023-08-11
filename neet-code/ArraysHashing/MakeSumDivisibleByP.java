import java.util.HashMap;
import java.util.Map;

public class MakeSumDivisibleByP {
	
	
	
	class Solution {
	    /*
	        [3, 1, 10, 2], p = 6
	        sum = 3 + 1 + 10 + 2 = 16
	        16 % 6 = 4, we find the shrortest subarray with remainder = 4;
	        
	        We use a hashmap with rem at that point as key, and idx as value.
	        Initially len = n = 4
	        
	        At idx -1,  rem = 0;    Map = {0: -1}
	        
	        At idx 0,   rem = 3;    we check if ((3 - 4) % 6 + 6) % 6 = 5 exists as Key? No.    Map = {0: -1, 3: 0}
	        
	        At idx 1,   rem = 4,    we check if ((4 - 4) % 6 + 6) % 6 = 0 exists as Key? Yes.   Map = {0: -1, 3: 0, 4: 1}
	                                len = min(4, idx - map[0]) = min(4, 1 - (-1)) = 2
	                                
	        At idx 2,   rem = 2     we check if ((2 - 4) % 6 + 6) % 6 = 4 exists as Key? Yes.   Map = {0: -1, 3: 0, 4: 1, 2: 2}
	                                len = min(2, idx - map[4]) = min(2, 2 - (1)) = 1
	                                
	        At idx 3,   rem = 4;    we check if ((4 - 4) % 6 + 6) % 6 = 0 exists as Key? Yes.   Map = {0: -1, 3: 0, 4: 3, 2: 2} (also update map)
	                                len = min(1, idx - map[0]) = min(1, 3 - (-1)) = 1
	                                
	        return len = 1
	    */
	    public int minSubarray(int[] nums, int p) {
	        
	        long sum = 0;
	        for(int num : nums) {
	            sum = (sum + num) % p; // For large numbers using p as mod
	        }
	        
	        if(sum % p == 0) {
	            return 0;
	        }
	        
	        int rem = ((int)sum % p + p) % p;      // The remainder to remove
	        Map<Integer, Integer> map = new HashMap<>();
	        int n = nums.length;
	        int len = n;
	        long pSum = 0;
	        
	        map.put(0, -1);
	        for(int i = 0; i < n; i ++) {
	            
	            pSum = (pSum + nums[i]) % p;    // For large numbers using p as mod
	            int currRem = ((int)pSum % p + p) % p;
	            map.put(currRem, i);
	            
	            int remToSearch = ((currRem - rem) % p + p) % p;
	            if(map.containsKey(remToSearch)) {
	                len = Math.min(len, i - map.get(remToSearch));
	            }
	            
	        }
	        
	        if(len == n) {
	            return -1;
	        }
	        return len;
	    }
	    
	    
	    public int minSubarray1(int[] nums, int p) {
	    	long sum = 0;
	    	for (int num: nums) {
	    		sum = (sum + num) % p;
	    	}
	    	
	    	if (sum%p == 0) { // total sum % p 가 0이라면 굳이 remove할것도 없음. 
	    		return 0;
	    	}
	    	
	    	int remToRemove = ((int)sum % p + p) % p; // in case, it gets too large. 이 나머지 remToRemove를 없애야지 remaining sum이 %p == 0
	    	
	    	Map<Integer, Integer> hash = new HashMap<>();
	    	int res = nums.length;
	    	
	    	long currSum = 0;
	    	
	    	hash.put(0, -1);
	    	
	    	for (int i = 0; i < nums.length; i++) {
	    		currSum = (currSum + nums[i]) % p;
	    		int currRem = ((int) currSum % p + p) % p;
	    		hash.put(currRem, i);
	    		
	    		int remToSearch = ((currRem - remToRemove) % p + p) % p;
	    		if (hash.containsKey(remToSearch)) {
	    			res = Math.min(res, i - hash.get(remToSearch));
	    		}
	    		
	    	}
	    	
	    	if (res == nums.length) {
	    		return - 1;
	    	}
	    	
	    	return res;
	    	
	    }
	}
}
