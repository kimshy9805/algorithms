import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.PriorityQueue;

public class DP {

	
	
    public int lengthOfLIS(int[] nums) {
        
    	int[] dp = new int[nums.length];
    	
    	int res = 0;
    	for (int i = 0; i < nums.length; i++) {
    		dp[i] = 1;
    		for (int j = 0; j < i; j++) {
    			if (nums[i] > nums[j]) {
    				dp[i] = Math.max(dp[i], dp[j] + 1);
    			}
    		}
    		
    		res = Math.max(res, dp[i]);
    	}
    	
    	return res;
    	
    }
    
    public int findNumberOfLIS(int[] nums) {
     
    	int[] dp = new int[nums.length];
    	int[] count = new int[nums.length];
    	
     	int longest = 0;
    	int res = 0;
    	
    	
    	for (int i = 0 ; i < nums.length; i++) {
    		dp[i] = 1;
    		count[i] = 1;
    		
    		for (int j = 0; j < i; j++) {
    			if (nums[i] > nums[j]) {
    				if (dp[i] == dp[j] + 1) {
    					count[i] += count[j];
    				} else if (dp[i] < dp[j] + 1) {
    					count[i] = count[j];
    					dp[i] = Math.max(dp[i], dp[j] + 1);
    				}
    			}
    		}
    		
    		if (longest < dp[i]) {
    			res = count[i];
    			longest = dp[i];
    		} else if (longest == dp[i]) {
    			res += count[i];
    		}
    	}
    	
    	return res;
    	
    	
    }
    
    
    public int minimumMountainRemovals(int[] nums) {
        
    	int[] left = new int[nums.length];
    	int[] right = new int[nums.length];
    	
    	
    	// fill up left lis
    	for (int i = 0 ; i < nums.length; i++) {
    		left[i] = 1;
    		for (int j = 0; j < i; j++) {
    			if (nums[i] > nums[j]) {
    				left[i] = Math.max(left[i], left[j] + 1);
    			}
    		}
    		
    	}
    	
    	// fill up right lis
    	for (int i = nums.length-1; i >= 0; i--) {
    		right[i] = 1;
    		for (int j = nums.length-1; j > i; j--) {
    			if (nums[i] > nums[j]) {
    				right[i] = Math.max(right[i], right[j] + 1);
    			}
    		}
    	}
    	
    	int res = Integer.MAX_VALUE;
    	for (int i = 0; i < nums.length; i++) {
    		if (left[i] == 1 || right[i] == 1) continue;
    		
    		res = Math.min(res, nums.length - (left[i] + right[i] - 1));
    	}
    	
    	
    	return res;
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
    
    
    
    public int maxSubArray(int[] nums) {
        int[] dp = new int[nums.length];
        
        dp[0] = nums[0];
    	
    	int res = dp[0];
        for (int i = 1; i< nums.length; i++) {
        	dp[i] = nums[i];
        	if (dp[i-1] + nums[i] > 0) {
        		dp[i] = Math.max(dp[i-1] + nums[i], dp[i]);
        	}
        	res = Math.max(res, dp[i]);
        }
    	
    	return res;
    	
    }
    
    
    
    
    
    
    public int longestCommonSubsequence(String text1, String text2) {
        
    	
    	int[][] dp = new int[text1.length()+1][text2.length()+1];
    	
    	
    	for (int i = text1.length()-1; i >= 0; i--) {
    		for (int j = text2.length()-1; j >= 0; j--) {
    			if (text1.charAt(i) == text2.charAt(j)) {
    				dp[i][j] = dp[i+1][j+1] + 1;
    			} else {
    				dp[i][j] = Math.max(dp[i+1][j], dp[i][j+1]);
    			}
    		}
    	}
    	
    	return dp[0][0];
    	
    	
    	
    	
    	
    }
    
    
    
    
    
    
    
    

    public static int findMaximumGreatness(List<Integer> arr) {
    	
    	
    	// freq hash
    	Map<Integer, Integer> hash = new HashMap<>();
    	
    	for (Integer val: arr) {
    		hash.put(val, hash.getOrDefault(val, 0) + 1);
    	}
    	
    	PriorityQueue<Pair> pq = new PriorityQueue<>((Pair p1, Pair p2) -> p1.val - p2.val);
    	for (Map.Entry<Integer, Integer> entry: hash.entrySet()) {
    		pq.add(new Pair(entry.getKey(), entry.getValue()));
    	}
    	
    	
    	// sort 
    	Collections.sort(arr);
    	int res = 0;
    	
    	// iterate sort 
    	for (int i = 0; i < arr.size(); i++) {
    		
        	PriorityQueue<Pair> temp = new PriorityQueue<>((Pair p1, Pair p2) -> p1.val - p2.val);
    		while(!pq.isEmpty()) {
    			Pair pair = pq.poll();
    			
    			// less or equal
    			if (pair.val <= arr.get(i)) {
    				temp.add(pair);
    			}
    			// greater 
    			else {
    				res++;
    				pair.cnt--;
    				if (pair.cnt > 0) temp.add(pair);
    				break;
    			}
    		}
    		// update temp to pq
    		pq.addAll(temp);
    		
    	}
    	
    	return res;

    }
    
    
    
    static class Pair {
    	int val;
    	int cnt;
    	
    	Pair(int val, int cnt) {
    		this.val = val;
    		this.cnt = cnt;
    	}
    }
    
    
    public static int reductionCost(List<Integer> num) {
    	
    	PriorityQueue<Integer> pq = new PriorityQueue<>();

    	pq.addAll(num);
    	int res = 0;
    	
    	while(pq.size() > 1) {
    		Integer first = pq.poll();
    		Integer second = pq.poll();
    		
    		pq.add(first + second);
    		res += first + second;
    	}
    	
    	return res;
    	
    	
    	
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
}
