import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.Deque;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Stack;

public class Test {
	
	public static void main(String[] args) {
		System.out.println("asdf");
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	class Pair implements Comparator<Pair>{
		int val;
		int ts;
		int n;
		
		Pair(int val, int ts, int n) {
			this.val = val;
			this.ts = ts;
			this.n = n;
		}

		@Override
		public int compare(Pair o1, Pair o2) {
			if (o1.val > o2.val) return 1;
			else if (o1.val < o2.val) return -1;
			else {
				if (o1.ts > o2.ts) return 1;
				else if (o1.ts < o2.ts) return -1;
				else return 0;
			}
		}
		
	}
	
	
	
	class TimeMap {
		Map<String, List<Pair>> hash;
 		
	    public TimeMap() {
	    	hash = new HashMap<>();
	    }
	    
	    public void set(String key, String value, int timestamp) {
	        if (!hash.containsKey(key)) {
	        	hash.put(key, new ArrayList<>());
	        	hash.get(key).add(new Pair(value, timestamp));
	        	return;
	        }
	    	
	        hash.get(key).add(new Pair(value, timestamp));
	    	
	    	
	    }
	    
	    public String get(String key, int timestamp) {
	        if (!hash.containsKey(key)) {
	        	return null;
	        }
	    	
	    	List<Pair> pairs = hash.get(key);
	    	
	    	
	    	int left = 0;
	    	int right = pairs.size() - 1;
	    	
	    	while(left < right) {
	    		int mid = (right - left)/2 + left;
	    		
	    		if (timestamp <= pairs.get(mid).timestamp) {
	    			right = mid;
	    		} else {
	    			left = mid + 1;
	    		}
	    	}
	    	
	        return pairs.get(left).val;
	    	
	    }
	    
	    
	    public class Pair {
	    	String val;
	    	int timestamp;
	    	
	    	Pair(String val, int timestamp) {
	    		this.val = val;
	    		this.timestamp = timestamp;
	    	}
	    }
	}
	

	
	
	
	
	
	
    public ListNode swapPairs(ListNode head) {
        if (head == null || head.next == null) return null;
        
        
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        ListNode temp = preHead;
        
        while(temp.next != null && temp.next.next != null) {
        	ListNode prev =temp.next;
        	ListNode curr = temp.next.next;
        	
        	prev.next = curr.next;
        	curr.next = prev;
        	temp = prev;
        	
        }
        
        return preHead.next;
    	
    	
    	
    }
	
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        
    	ListNode preHead = new ListNode(0);
    	
    	ListNode curr = preHead;
    	int carry = 0;
    	
    	while(l1 != null || l2 != null || carry == 1) {
    		int sum = 0;
    		
    		if(l1 != null) {
    			sum += l1.val;
    			l1 =l1.next;
    		}
    		
    		if(l2 != null) {
    			sum += l2.val;
    			l2 = l2.next;
    		}
    		
    		sum += carry;
    		carry = sum/10;
    		
    		ListNode node = new ListNode(sum%10);
    		curr.next= node;
    		curr = curr.next;
    		
    	}
    	
    	return preHead.next;
    	
    }
	
	
	
	
	
    public ListNode removeNthFromEnd(ListNode head, int n) {
    	ListNode preHead = new ListNode(0);
    	
    	preHead.next = head;
    	
    	
    	ListNode right = preHead;
    	ListNode left = preHead;
    	
    	while(n-- > 0) {
    		right = right.next;
    	}
    	
    	while(right.next != null) {
    		right = right.next;
    		left = left.next;
    	}
    	
    	left.next = left.next.next;
    	
    	return preHead.next;
    	
    	
    }
	
	
    public void reorderList(ListNode head) {
        // use slow, fast to find out half
    	ListNode slow = head;
    	ListNode fast = head.next;
    	
    	while(fast != null && fast.next != null) {
    		slow = slow.next;
    		fast = fast.next.next;
    	}
    	
    	ListNode second = slow.next;
    	ListNode prev = null;
    	slow.next = null;
    	
    	while(second != null) {
    		ListNode next = second.next;
    		second.next = prev;
    		prev = second;
    		second = next;
    	}
    	
    	// prev => tail
    	second = prev;
    	
    	// construct
    	ListNode first = head;
    	while(first != null) {
    		ListNode nFirst = first.next;
    		ListNode nSecond = second.next;
    		
    		first.next = second;
    		second.next = nFirst;
    		
    		first = nFirst;
    		second = nSecond;
    		
    	}
    	
    	
    	
    }
	
	
    public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
    	ListNode preHead = new ListNode();
    	ListNode tail = preHead;
    	
    	
    	while(l1 != null && l2 != null) {
    		if (l1.val < l2.val) {
    			tail.next = l1;
    			l1 = l1.next;
    		} else {
    			tail.next = l2;
    			l2 = l2.next;
    		}
    		tail = tail.next;
    	}
    	
    	if (l1 != null) {
    		tail.next = l1;
    	} else if (l2 != null) {
    		tail.next = l2;
    	}
    	
    	return preHead.next;
    	
    }
	
    public ListNode reverseList(ListNode head) {
        if (head == null) return null;
    	
    	ListNode prev = null;
    	ListNode curr = head;
    	
    	
    	while(curr != null) {
    		ListNode next = curr.next;
    		
    		
    		curr.next = prev;
    		
    		
    		prev = curr;
    		curr =next;
    	}
    	
    	return prev;
    }
	
	
	
	
	
	
	
	
	
	
    public class ListNode {
    	      int val;
    	      ListNode next;
    	      ListNode() {}
    	      ListNode(int val) { this.val = val; }
    	      ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
	
	
	
	
	
	
	
	
	
	
	
    public boolean isInterleave(String s1, String s2, String s3) {
        
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    	
    }
	
	
    public int longestCommonSubsequence(String text1, String text2) {
        
    	
    	int[][] dp = new int[text1.length()][text2.length()];
    	
    	
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
	
	
	
	
	
	
	
	
	
	
    public boolean wordBreak(String s, List<String> wordDict) {
        
    	boolean[] dp = new boolean [s.length()+1];
    	
    	
    	int j = s.length();
    	
    	for (int i = s.length()-1; i >= 0; i--) {
    		if (wordDict.contains(s.substring(i, j))) {
    			dp[i] = dp[j];
    			j = i;
    			continue;
    		}
    	}
    	
    	return dp[0];
    }
	
	
	
	
	
	
	
    public boolean canPartition(int[] nums) {
        
    	int sum = 0;
    	for (int num: nums) {
    		sum += num;
    	}
    	
    	if (sum < 0 || sum%2 != 0) return false;
    	
    	int amount = sum / 2;
    	
    	boolean[][] dp = new boolean [nums.length+1][amount + 1];
    	dp[0][0] = true;
    	
    	for (int i = 1; i <= nums.length; i++) { // accessing nums [i-1]
    		for (int j = 0; j <= amount; j++) {
    			if (j - nums[i-1] >= 0) {
    				dp[i][j] = dp[i-1][j-nums[i-1]] || dp[i-1][j];
    			} else {
    				dp[i][j] = dp[i-1][j];
    			}
    		}
    	}
    	
    	return dp[nums.length][amount];   	
    }
    
	
	
	
	
    public int change2(int amount, int[] coins) {
       int[][] dp = new int [coins.length + 1][amount+1];
       
       
       for (int i = 1; i <= coins.length; i++) {
    	   for (int j = 0; j <= amount; j++) {
    		   if (j == 0) {
    			   dp[i][j] = 1;
    			   continue;
    		   }
    		   
    		   if (j - coins[i-1] >= 0) {
    			   dp[i][j] = dp[i-1][j] + dp[i][j - coins[i-1]];
    		   } else {
    			   dp[i][j] = dp[i-1][j];
    		   }
    	   }
       }
    	
       return dp[coins.length][amount];
    	
    	
    }
	
	
    public int uniquePaths(int m, int n) {
        
    	// m = > # of rows
    	// n => # of cols
    	
    	int[][] dp = new int [m][n];
    	
    	
    	
    	for (int i = 0; i < m; i++) {
    		for (int j = 0; j < n; j++) {
    			if (i == 0 || j == 0) {
    				dp[i][j] = 1;
    				continue;
    			}
    			dp[i][j] = dp[i-1][j] + dp[i][j-1];
    		}
    	}
    
    	return dp[m-1][n-1];
    	
    	
    	
    }
	
	
	
	
	
	
	
	
    public int lengthOfLIS(int[] nums) {
        
    	int[] dp = new int[nums.length];
    	
    	
    	int res = 0;
    	
    	
    	for (int i = 0; i < nums.length; i++) {
    		dp[i] = 1;
     		for (int k = 0; k < i; k++) {
    			if (nums[i] > nums[k]) {
    				dp[i] = Math.max(dp[i], dp[k] + 1);
    			}
    		}
    		
    		res = Math.max(dp[i], res);
    		
    	}
    	
    	return res;
    	
    }
	
	

    public int maxProduct(int[] nums) {
        
    	int[][] dp = new int [nums.length][2];
    	
    	dp[0][0] = nums[0];
    	dp[0][1] = nums[1];
    	
    	
    	int res = dp[0][0];
    	
    	for (int i = 1 ; i < nums.length; i++) {
    		if (nums[i] >= 0) {
    			dp[i][0] = Math.max(dp[i-1][0] * nums[i], nums[i]);
    			dp[i][1] = Math.min(dp[i-1][1] * nums[i], nums[i]);
    		} else {
    			dp[i][0] = Math.max(dp[i-1][1] * nums[i], nums[i]);
    			dp[i][1] = Math.min(dp[i-1][0] * nums[i], nums[i]);
     		}
    		
    		res = Math.max(res, dp[i][0]);
    		
    	}
    	
    	return res;
    	
    	
    	
    	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    public int[] asteroidCollision(int[] ar) {
        Stack<Integer> st = new Stack<>();
        
        
        
        for (int i = 0; i < ar.length; i++) {
        	if (st.isEmpty()) {
        		st.push(ar[i]);
        		continue;
        	}
        	
        	boolean flag = false;
        	// if not empty 
        	while(!st.isEmpty() && 
        			(st.peek() >= 0 && ar[i] < 0) ||
        			(st.peek() < 0 && ar[i] >= 0)) {
        		int a = Math.abs(st.peek()); // from st
        		int b = Math.abs(ar[i]);
        		
        		// if same size, pop one from st
        		if (a == b) {
        			st.pop();
        			// continue next
        			flag = true;
        		} else if (a < b) {
        			st.pop();
        		} else {
        			flag = true;
        			// continue next;
        		}
        		
        		if (flag) {
        			break;
        		}
        	}
        	
        	if (flag) {
        		st.push(ar[i]);
        	}
        	
        	
        }
    	
        int[] res = new int [st.size()];
    	for (int i = res.length - 1; i <= 0; i--) {
			res[i] = st.pop();
		}
		return res;
    	
    	
    }
	
    public String removeKdigits(String num, int k) {
        // convert num -> int[] ar
    	char[] charAr = num.toCharArray();
    	
    	int[] ar = new int[charAr.length];
    	
    	for (int i = 0; i < charAr.length; i++) {
    		ar[i] = Character.getNumericValue(charAr[i]);
    	}
    	
    	Stack<Integer> st = new Stack<>();
    	
    	// iterate each element
    	for (int i = 0; i < ar.length; i++) {
    		// it is always better to replace if st.peek() > ar[i] with k > 0
    		while(!st.isEmpty() && st.peek() > ar[i] && k > 0) {
    			st.pop();
    			k--;
    		}
    		
    		st.push(ar[i]);
    		
    	}
    	
    	// in case k > 0
    	while(k-- > 0 && !st.isEmpty()) {
    		st.pop();
    	}
    	
    	// form string
    	StringBuilder sb = new StringBuilder();
    	
    	
    	// remove leading zeros
    	while(!st.isEmpty()) {
    		sb.append(st.pop());
    	}
    	
    	sb = sb.reverse();
    	
		// remove leading zeros
		while (sb.toString().startsWith("0")) {
			sb.deleteCharAt(0);

		}
		return sb.length() == 0 ? "0" : sb.toString();

    	
    }
	
	
	
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<>();
        
        int j = 0;
        
        for (int i = 0; i < pushed.length; i++) {
        	st.push(pushed[i]);
        	
        	while(!st.isEmpty() && st.peek() == popped[j]) {
        		st.pop();
        		j++;
        	}
        }
    	
    	if (st.size() > 0) {
    		return false;
    	}
    	
    	return true;
    	
    }
	
    public int[] dailyTemperatures(int[] temperatures) {
     
    	int[] res = new int[temperatures.length];
    	Stack<Integer> st = new Stack<>();
    	
    	
    	for (int i = 0; i < temperatures.length; i++) {
    		while(!st.isEmpty() && temperatures[st.peek()] < temperatures[i]) {
    			int pop = st.pop();
    			res[pop] = i -pop;
    		}
    		st.push(i);
    	}
    	
    	return res;
    	
    }
	
	class MinStack {
		Stack<Integer> st = new Stack<>();
		Stack<Integer> minSt = new Stack<>();
 		

	    public MinStack() {
	        
	    }
	    
	    public void push(int val) {
	        st.push(val);
	        if (!minSt.isEmpty() && minSt.peek() >= val) {
	        	minSt.push(val);
	        	return;
	        }
	        if (minSt.isEmpty()) minSt.push(val);
	    }
	    
	    public void pop() {
	        int pop = st.pop();
	    	if (!minSt.isEmpty() && minSt.peek() == pop) {
	    		minSt.pop();
	    	}
	    }
	    
	    public int top() {
	        return st.peek();
	    }
	    
	    public int getMin() {
	        return minSt.peek();
	    }
	}
	
	
	
	
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
    
    	
    	int n = nums1.length;
    	int m = nums2.length;
    	
    	if (n > m) findMedianSortedArrays(nums2, nums1);
    	
    	
    	// nums1 will be always smaller array
    	
    	int total = n + m;
    	int half = (total + 1)/2;
    	
    	int left = 0;
    	int right = nums1.length-1;
    	double result = 0.0;
    	
       	while(left <= right) {
    		int i = left + (right - left)/2; // Smaller Array mid tracking
    		int j = half - i;  // Bigger Array mid tracking
    		
    		int left1 = (i > 0) ? nums1[i-1] : Integer.MIN_VALUE;
    		int right1 = (i < m) ? nums1[i] : Integer.MAX_VALUE;
    		int left2 = (j > 0) ? nums2[j-1] : Integer.MIN_VALUE;
    		int right2 = (j < n) ? nums2[j] : Integer.MAX_VALUE;
    		
    		// partition is correct
    		if (left1 <= right2 && left2 <= right1) {
    	        // even
                if (total % 2 == 0) {
                    result =
                        (Math.max(left1, left2) + Math.min(right1, right2)) /
                        2.0;
                    // odd
                } else {
                    result = Math.max(left1, left2);
                }
                break;
    		}
    		else if (left1 > right2) {
    			right = i -1;
    		} else {
    			left = i + 1;
    		}
    	}
    	
    	return result;
    	
    	
    	
    }
	
    public int minEatingSpeed(int[] piles, int h) {
     
    	int max = 0;
    	for (int pile: piles) {
    		max = Math.max(max, pile);
    	}
    	
    	int left = 1;
    	int right = max;
    	
    	
    	while(left < right) {
    		int mid = (right - left)/2 + left;
    		
    		int hours = 0;
    		for (int pile: piles) {
    			if (pile % mid == 0) {
    				hours += pile/mid;
    			} else {
    				hours += pile/mid + 1;
    			}
    			
    		}
    		
    		// update bs based on hours
    		if (hours <= h) { // if less than h		
    			// update bs. We can do better
    			right = mid;
    		} else {
    			left = mid + 1;
    		}
    	}
    	
    	
    	return left;
    	
    }
	
	
    public int findDuplicate(int[] nums) {
    	Arrays.sort(nums);
    	
    	for(int i = 0; i < nums.length; i++) {
    		int target = nums[i];
    		
    		int left = 0;
    		int right = nums.length-1;
    		
    		while(left < right) {
    			int mid = (right - left)/2 + left;
    			
    			if (target <= nums[mid]) {
    				right = mid;
    			} else {
    				left = mid + 1;
    			}
    		}
    		if (nums[left] == nums[left+1]) {
    			return nums[left];
    		}
    	}
    	
    	return 0;
    	
    }
	
	
	
    public int findPeakElement(int[] nums) {
        
    	int left = 0;
    	int right = nums.length-1;
    	
    	while(left < right) {
    		int mid = (right -left)/2 + left;
    		
    		if (nums[mid] < nums[mid+1]) {
    			left = mid +1;
    		} else {
    			right = mid;
    		}
    	}
    	
    	return left;
    	
    }
	
	
    public List<Integer> findClosestElements(int[] ar, int k, int x) {
        
    	int left = 0;
    	int right = ar.length-1;
    	
    	List<Integer> res = new ArrayList<>();
    	
    	while(left < right) {
    		
    		int mid = (right - left)/2 + left;
    		
    		
    		if (x <= ar[mid]) {
    			right = mid;
    		} else {
    			left = mid + 1;
    		}
    	}
    	
		int i = left - 1, j = left;
		while (k-- > 0) {
			if (i < 0) {
				res.add(ar[j++]);
			} else if (j > ar.length - 1) {
				res.add(ar[i--]);
			} else if (x - ar[i] <= ar[j] - x) {
				res.add(ar[i--]);
			} else {
				res.add(ar[j++]);
			}
		}
    	Collections.sort(res);
    	return res;
    	
    }
	
	
	
	
	
	
	
	
	
    public int search(int[] nums, int target) {
        
    	int left = 0;
    	int right = nums.length - 1;
    	
    	while(left < right) {
    		int mid = (right - left)/2 + left;
    		
    		if (nums[mid] == target) return mid;
    		
    		
    		if (nums[left] < nums[mid]) { // found ascending section
    			if (nums[left] <= target && target < nums[mid]) {
    				right = mid - 1;
    			} else {
    				left = mid + 1;
    			}
    		} else {
    			if (nums[mid] < target && target < nums[right]) {
    				left = mid + 1;
    			} else {
    				right = mid - 1;
    			}
    		}
    	}
    	
    	return nums[left] == target ? left : -1;
    	
    	
    }
	
	
	
	
    public int findMin(int[] nums) {
        
    	int left = 0;
    	int right = nums.length - 1;
    	
    	while(left < right) {
    		int mid = (right - left)/2 + left;
    		
    		if (nums[mid] > nums[right]) {
    			left = mid + 1;
    		} else {
    			right = mid;
    		}
    	}
    	
    	return nums[left];
    	
    	
    	
    	
    }
	
    public int search1(int[] nums, int target) {
        int left = 0;
        int right = nums.length-1;
        
        
        while(left < right) {
        	int mid = (right-left)/2 + left;
        	
        	
        	if (target == nums[mid]) return mid;
        	
        	if (target < nums[mid]) {
        		right = mid - 1;
        	} else {
        		left = mid + 1;
        	}
        }
    	
        return -1;
    	
    }
	
	
	
	
	
	
	
    public int[] maxSlidingWindow(int[] nums, int k) {
    	
    	int[] ans = new int[nums.length - k + 1];
    	
    	int left = 0;
    	Deque<Integer> q = new LinkedList<>();
    	
    	for (int right=0; right < nums.length; right++) {
    		// remove if first element is no longer in window
    		if (!q.isEmpty() && q.peekFirst() < right - k + 1) q.pollFirst();
    		
    		// queue last element가 현재 position element보다 작으면 remove decreasing order 
            while (!q.isEmpty() && nums[right] > nums[q.peekLast()]) q.pollLast();
            
            q.offer(right);
            
            // update ans if the size of window is at least k
            if (right >= k - 1) ans[left++] = nums[q.peekFirst()];
    		
    	}
    	
    	return ans;
    	
    }
	
	
	
	
    public String minWindow1(String s, String t) {
     
    	Map<Character, Integer> hashT = new HashMap<>();
    	
    	
    	int matchingFreq = 0;
    	String res = "";
    	int left = 0;
    	
    	for (int right = 0; right < s.length(); right++) {
    		char c = s.charAt(right);
    		
    		if (hashT.containsKey(c)) {
    			hashT.put(c, hashT.get(c) - 1);  // update hashT
    			if (hashT.get(c) == 0) matchingFreq++;
    		}
    		
    		while(left < s.length() && matchingFreq == hashT.size()) {
    			// Update res
    			res = s.substring(left, right+1);
    			
    			if (hashT.containsKey(s.charAt(left))) {
    				hashT.put(s.charAt(left), hashT.get(s.charAt(left)) + 1);
    				if (hashT.get(s.charAt(left)) > 0) matchingFreq--;
    			}
    			left++;
    		}
    	}
    	
    	
    	
    	return res;
    	
    	
    }
	
	
	
    public int numSubarrayProductLessThanK(int[] nums, int k) {
     
    	int res = 0;
    	int left = 0;
    	int right = 0;
    	
    	int currProd = 1;
    	
    	while(right < nums.length) {
    		currProd *= nums[right];
    		
    		while(left < nums.length && currProd >= k) {
    			currProd /= nums[left];
    			left++;
    		}
    		
    		res += right - left + 1;
    		
    	}
    	
    	return res;
    	
    }
	
	
    public boolean checkInclusion1(String s1, String s2) {
        
    	
    	
    	int[] freq= getFreqArray(s1);
    	int[] freq1 = getFreqArray(s2.substring(0, s1.length()-1));
    	
    	int left = 0;
    	int right = s1.length() - 1;
    	
    	while(right < s2.length()) {
    		freq1[s2.charAt(right) - 'a']++;
    		
    		if (Arrays.equals(freq, freq1)) {
    			return true;
    		}
    		
    		freq1[s2.charAt(left) - 'a']--;
    		left++;
    		right++;
    	}
    	
    	return false;
    	
    	
    }
    
    private int[] getFreqArray(String str) {
    	int[] freq= new int[26];
    	
    	
    	for (char c: str.toCharArray()) {
    		freq[c - 'a']++;
    	}
    	
    	return freq;
    	
    }
    
    
	
    public int characterReplacement2(String s, int k) {
     
    	Map<Character, Integer> hash = new HashMap<>();

    	int res = 0;
    	int left = 0;
    	
    	int mostRepeating = 0;
    	
    	for (int right = 0; right < s.length(); right++) {
    		char c = s.charAt(right);
    		
    		hash.put(c, hash.getOrDefault(c, 0) + 1);
    		
    		mostRepeating = Math.max(mostRepeating, hash.get(c));
    		
    		while (left < s.length() && right - left + 1 - mostRepeating > k) {
    			hash.put(s.charAt(left), hash.get(s.charAt(left)) - 1);
    			left++;
    		}
    		
    		res = Math.max(res, right - left + 1);
    		
    	}
    	
    	return res;
    	
    	
    }
	
	
	
    public int lengthOfLongestSubstring2(String s) {
        
    	
    	Map<Character, Integer> hash = new HashMap<>();
    	
    	
    	int left = 0;
    	int res = 0;
    	for (int right = 0; right < s.length(); right++) {
    		char c = s.charAt(right);
    		
    		// update hash
    		hash.put(c, hash.getOrDefault(c, 0) + 1);
    		
    		
    		// expand until?
    		while(left < s.length() && hash.get(c) > 1) {
    			hash.put(s.charAt(left), hash.get(s.charAt(left)) - 1);
    			left++;
    		}
    		
    		res = Math.max(res, right - left + 1);
    		
    	}
    	
    	return res;
    	
    }
	
	
	
	
	
    public int maxProfit2(int[] prices) {
        int res = 0;
        
        int j = 1;
        int i = 0;
        while(j < prices.length) {
        	if (prices[i] < prices[j]) {
        		i = j;
        		j++;
        		continue;
        	}
        	
        	res = Math.max(res, prices[j] - prices[i]);
        	j++;
        }
    	
    	return res;
    	
    }
	
	
	
    public int minSubArrayLen2(int target, int[] nums) {
    	int res = Integer.MAX_VALUE;
    	int currSum = 0;
    	
    	int left = 0;
    	for (int right = 0; right < nums.length; right++) {
    		currSum += nums[right];
    		
    		while(left < nums.length && currSum >= target) {
    			res = Math.min(res, right - left + 1);
    			currSum -= nums[left++];
    		}
    	}
    	
    	return res == Integer.MAX_VALUE ? 0 : res;
    	
    	
    }
	
	
    public int countSubstrings(String s) {
        
    	int res = 0;
    	
    	for (int i = 0; i < s.length(); i++) {
    		// odd
    		int left = i;
    		int right = i;
    		
    		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
    			res++;
    			left++;
    			right--;
    		}
    		
    		left = i - 1;
    		right = i;
    		
    		// even
    		while(left >= 0 && right < s.length() && s.charAt(left) == s.charAt(right)) {
    			res++;
    			left++;
    			right--;
    		}
    		
    		
    	}
    	
    	
    	
    	return res;
    	
    }
	
	
    public int longestOnes2(int[] nums, int k) {
        
    	int res = 0;
    	
    	int ones = 0;
    	
    	int left = 0;
    	for (int right = 0; right < nums.length; right++) { // O(n)
    		if (nums[right] == 1) ones++;
    		
    		// expand windows until
    		while(left < nums.length && right - left + 1 - ones > k) {
    			if (nums[left] == 1) ones--;
    			left++;
    		}
    		
    		
    		// update res
    		res = Math.max(res, right - left + 1);
    		
    	}
    	
    	return res;
    	
    	
    }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    public int trap(int[] height) {
        int maxL = 0;
        int maxR = 0;
        int l = 0;
        int r = height.length-1;
    	
        
        int res = 0;
    	for (int i = 0; i < height.length; i++) {
    		// update maxL
    		l = i - 1;
    		maxL = 0;
    		while(l >= 0) {
    			maxL = Math.max(maxL, height[l]);
    			l--;
    		}
    		
    		// update maxR
    		r = i + 1;
    		maxR = 0;
    		while(r < height.length) {
    			maxR = Math.max(maxR, height[r]);
    			r++;
    		}
    		
    		
    		// update res
    		res += Math.min(maxL, maxR) - height[i] >= 0 ? Math.min(maxL, maxR) - height[i] : 0; 
    		
    		
    	}
    	
    	return res;
    	
    }
	
	
	
    public int maxArea2(int[] height) {
     
    	int i = 0;
    	int j = height.length - 1;
    	
    	int res = 0;
    	
    	while(i < j) {
    		res = Math.max(res, (j - i) * Math.min(height[i], height[j]));
    		
    		if (height[i] < height[j]) {
    			i++;
    		} else {
    			j--;
    		}
    	}
    	
    	
    	return res;
    	
    }
	
	
    public List<List<Integer>> threeSum2(int[] nums) {
        List<List<Integer>> res = new ArrayList<>();
        
        Arrays.sort(nums);
        
        
        for (int i = 0; i < nums.length; i++) {
        	if (i > 0 && nums[i] == nums[i-1]) {
        		continue;
        	}
        	
        	
        	int k = i + 1;
        	int j = nums.length-1;
        	
        	
        	while(k < j) {
        		int sum = nums[i] + nums[k] + nums[j];
        		
        		if (sum < 0) {
        			k++;
        		} else if (sum > 0) {
        			j--;
        		}
        		
        		
        		if (sum == 0) {
        			res.add(List.of(nums[i], nums[k], nums[j]));
        			k++;
        			while(k < j && nums[k] == nums[k-1]) {
        				k++;
        			}
        		}
        		
        	}
        }
    	
        return res;
    	
    	
    }
	
	
	
	

    public boolean isPalindrome2(String s) {
        int i = 0;
        int j = s.length() - 1;
        
        
        while(i < j ) {
        	if (!Character.isLetterOrDigit(s.charAt(i))) {
        		i++;
        		continue;
        	}
        	if (!Character.isLetterOrDigit(s.charAt(j))) {
        		j--;
        		continue;
        	}
        	
        	if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
        		return false;
        	}
        	i++;
        	j--;
        	
        }
        
    	return true;
    	
    }
	
	
    public int[] twoSumII(int[] numbers, int target) {
    	int[] res = new int[2];    	
    	
        int i = 0;
        int j = numbers.length-1;
        
        while(i < j) {
        	int sum = numbers[i] + numbers[j];
        	
        	if (sum == target) {
        		res[0] = i + 1;
        		res[1] = j + 1;
        		break;
        	}
        	
        	if (sum < target) {
        		i++;
        	} else {
        		j--;
        	}
        }
    	
        return res;
    	
    	
    }
	
	
	
    public static boolean checkSubarraySum(int[] nums, int k) {
    	Map<Integer, Integer> hash = new HashMap<>();
    	
    	hash.put(0, -1);
    	
    	int currSum = 0;
    	for (int i = 0; i < nums.length; i++) {
    		currSum += nums[i];
    		
    		int rem = currSum % k;
    		
    		
    		if (hash.containsKey(rem)) {
    			System.out.println(rem);
    			if (i - hash.get(rem) >= 2) {
    				System.out.println(i + " " + hash.get(rem));
    				return true;
    			}
    		} else {
    			hash.put(rem, i);
    		}
    		
    		
    		
    	}
    	
    	return false;
    	
    	
    	
    	
    }
	
	
	
	
	
    public int longestConsecutive1(int[] nums) {
     
    	Set<Integer> set = new HashSet<>();
    	
    	for (int num: nums) {
    		set.add(num);
    	}
    	
    	
    	int res = 0;
    	for (int i=0; i < nums.length; i++) {
    		// ar[i] - 1 exsits in set
    		if (set.contains(nums[i] - 1)) {
    			continue;
    		}
    		
    		int temp = 0;
    		int curr = nums[i];
    		while(set.contains(curr)) {
    			temp++;
    			curr++;
    		}
    		
    		res = Math.max(res, temp);
    		
    	}
    	
    	
    	return res;
    	
    	
    }
	
    public int subarraysDivByK(int[] nums, int k) {
    	Map<Integer, Integer> hash = new HashMap<>();
    	
    	
    	int res = 0;
    	int currSum = 0;
    	
    	hash.put(0, 1);
    	
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

    public int firstUniqChar(String s) {
        
    	Map<Character, Integer> hash = new HashMap<>();
    	
    	
    	for (char c: s.toCharArray()) {
    		hash.put(c, hash.getOrDefault(c, 0) + 1);
    	}
    	
    	int res = -1;
    	for (int i = 0; i < s.length(); i++) {
    		if (hash.get(s.charAt(i)) == 1) {
    			res = i;
    			break;
    		}
    	}
    	
    	return res;
    	
    }	
	
	
	 public int[] intersection(int[] nums1, int[] nums2) {
		 
		 
		 Set<Integer> set = new HashSet<>();
		 
		 for (int num: nums1) {
			 set.add(num);
		 }
		 
		 // set = {4, 7, 6, 9}
		 
		 
		 
		 Set<Integer> resSet = new HashSet<>();
		 
		 for (int num: nums2) {
			 if (set.contains(num)) {
				 resSet.add(num);
			 }
		 }
		 
		 int[] res = new int [resSet.size()];
		 
		 int i = 0;
		 for (int num: resSet) {
			 res[i++] = num;
		 }
		 return res;
		 
	 }	
	
	
	
	
	
	
	
	
	
	
	
	 public List<List<String>> groupAnagrams(String[] strs) {
	        
		 List<List<String>> res = new ArrayList<>();
		 
		 Map<String, List<String>> hash = new HashMap<>();
		 
		 
		 for (String str: strs) {
			 char[] charAr = str.toCharArray();
			 Arrays.sort(charAr);
			 String sortedStr = new String(charAr);
			 
			 if (!hash.containsKey(sortedStr)) {
				 // add in our key list 
				 hash.put(sortedStr, new ArrayList<>());
				 hash.get(sortedStr).add(str);
			 } else {
				 hash.get(sortedStr).add(str);
			 }
		 }
		 
		 
		 for (Map.Entry<String, List<String>> entry: hash.entrySet()) {
			 res.add(entry.getValue());
		 }
		 
		 
		 return res;
		 
	 }
	
	
	
	
	
	
    public int[] twoSum3(int[] nums, int target) {
        Map<Integer, Integer> hash = new HashMap<>();
        
        
        for (int i = 0; i < nums.length; i++) {
        	hash.put(nums[i], i); // val and index 
        }
    	
    	
    	for (int i = 0 ; i < nums.length; i++) {
    		int left = target - nums[i];
    		
    		if (hash.containsKey(left) && hash.get(left) != i) {
    			return new int[] {hash.get(left), i};
    		}
    	}
    	
    	return new int[] {1, 2};
    	
    	
    }
	
	
	
	 public int[] twoSum1(int[] nums, int target) {
		 
		 Pair[] temp = new Pair [nums.length]; 
		 for (int i = 0 ; i < nums.length; i++) {
			 temp[i] = new Pair(i, nums[i]);
		 }
		 
	     Arrays.sort(temp);
		 
		 
		 int i = 0;
		 int j = nums.length-1;
		 
		 while(i < j) {
			 if (temp[i].val + temp[j].val == target) {
				 return new int[] {temp[i].idx, temp[j].idx};
			 }
			 
			 if (temp[i].val + temp[j].val > target) {
				 j--;
			 } else {
				 i++;
			 }
		 }
		 
		 
		 return new int[] {temp[i].idx, temp[j].idx};
		 
		 
	 }
	
	
	
	 class Pair implements Comparable<Pair>{
		 int idx;
		 int val;
		 
		 Pair(int idx, int val) {
			 this.idx = idx;
			 this.val = val;
		 }
		 
		@Override
		public int compareTo(Pair p1) {
			 if (p1.val < val) return 1;
			 else return -1;
		}
	 }
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
    public int[] dailyTemperatures(int[] temperatures) {
        
    	
    	Stack<Integer> st = new Stack<>();
    	
    	int[] res = new int[temperatures.length];
    	
    	for (int i = 0; i < temperatures.length; i++) {
    		while(!st.isEmpty() && st.peek() < temperatures[i]) {
    			int index = st.pop();
    			res[index] = i - index;
    		}
    		
    		st.push(i);
    		
    	}
    	
    	return res;
    	
    	
    	
    }
    
    
    
    
    public int longestConsecutive(int[] nums) {
    	Set<Integer> set = new HashSet<>();
    	
    	for (int num: nums) {
    		set.add(num);
    	}
    	
    	int res = 0;
    	
    	for (int i = 0 ; i < nums.length; i++) {
    		if (!set.contains(nums[i] - 1)) continue;
    		
    		int cnt = 0;
    		int curr = nums[i];
    		while(set.contains(curr)) {
    			cnt++;
    			curr++;
    		}
    		
    		res = Math.max(res, cnt);
    		
    	}
    	
    	
    	return res;
    	
    }
	
	
    public int maxArea(int[] height) {
    	int i = 0;
    	int j = height.length;
    	
    	
    	int res = 0;
    	
    	while(i < j) {
    		if (height[i] >= height[j]) {
    			Math.max(res, height[i] * (j - i));
    			j--;
    			continue;
    		} else {
    			Math.max(res, height[j] * (j - i));
    			i++;
    			continue;
    		}
    	}
    	
    	return res;
    	
    }
	
    public List<List<Integer>> threeSum(int[] nums) {
    	
    	Arrays.sort(nums);
    	
    	List<List<Integer>> res = new ArrayList<>();
    	
    	for (int i = 0; i < nums.length; i++) {
     		if (i > 0 && nums[i] == nums[i-1]) {
    			continue;
    		}
     		
    		int k = i + 1;
    		int j = nums.length-1;
    		
    		while(k < j) {
    			int sum = nums[i] + nums[k] + nums[j];
    			
    			if (sum > 0) {
    				j--;
    				continue;
    			}
    			if (sum < 0) {
    				k++;
    				continue;
    			}
    			
    			// Update res
    			List<Integer> temp = List.of(nums[i], nums[j], nums[k]);
    			res.add(temp);
    			k++;
    			while (nums[k] == nums[k-1] && k < j) {
					k++;
				}
    			
    		}
    	}
    	
    	return res;
    	
    }
	
	
    public int[] twoSum(int[] numbers, int target) {
    	int i = 0;
    	int j = numbers.length - 1;
    	
    	
    	while(i < j) {
    		int sum = numbers[i] + numbers[j];
    		
    		if (sum > target) {
    			j--;
    			continue;
    		}
    		
    		if (sum < target) {
    			i++;
    			continue;
    		}
    		
    		if (sum == target) {
    			
    			return new int [] {i + 1, j + 1};
    		}
    	}
    	
    	return new int[] { i + 1, j + 1};
    	
    	
    	
    }
	
    public boolean isPalindrome(String s) {
        
    	int i = 0;
    	int j = s.length() - 1;
    	
    	
    	while(i < j) {
    		if (!Character.isLetterOrDigit(s.charAt(i))) {
    			i++;
    			continue;
    		}
    		
    		if (!Character.isLetterOrDigit(s.charAt(j))) {
    			j--;
    			continue;
    		}
    		
    		if (Character.toLowerCase(s.charAt(i)) != Character.toLowerCase(s.charAt(j))) {
    			return false;
    		}
    	}
    	
    	return true;
    	
    	
    }
	
    public String minWindow(String s, String t) {
        
    	Map<Character, Integer> hash = new HashMap<>();
    	
    	for (char c: t.toCharArray()) {
    		hash.put(c, hash.getOrDefault(c, 0) + 1);
    	}
    	
    	int cnt = 0;
    	int left = 0;
    	String winString = "";
    	int res = Integer.MAX_VALUE;
    	for (int right = 0 ; right < s.length(); right++) {
    		char c = s.charAt(right);
    		if (hash.containsKey(c)) {
    			hash.put(c, hash.get(c) - 1);
    			if (hash.get(c) == 0) {
    				cnt++;
    			}
    		}
    		
    		
    		while(cnt == hash.size()) {
    			if (res > right - left + 1 ) {
    				res = right - left + 1;
    				winString = s.substring(left, right + 1);
    			}
    			
    			if (hash.containsKey(s.charAt(left))) {
    				if (hash.get(s.charAt(left)) == 0) cnt--;
    				hash.put(s.charAt(left), hash.get(s.charAt(left)) + 1);
    			}
    			left++;
    		}
    		
    	}
    	
    	return winString;
    	
    	
    }
    
	
	
	
	
	
    public int minSubArrayLen(int target, int[] nums) {
    	
    	int res = Integer.MAX_VALUE;
    	int left = 0;
    	int currSum = 0;
    	for (int right = 0 ; right < nums.length; right++) {
    		currSum += nums[right];
    		
    		while(left < nums.length && currSum >= target) {
    			res = Math.min(res,  right - left + 1);
    			currSum -= nums[left];
    			left--;
    			
    		}
    	}
    	
    	return res == Integer.MAX_VALUE ? 0 : res;
    	
    }
	
	
   public int longestOnes(int[] nums, int k) {
        int ones = 0;
        int res =0;
        int left = 0;
       
        for (int right=0; right < nums.length; right++) {
        	if (nums[right] == 1) ones++;
        	
        	while(left < nums.length && right - left + 1 - ones > k) {
        		if (nums[left] == 1) ones--;
        		left++;
        	}
        	
        	res = Math.max(res, right - left + 1);
        }
	   
	   return res;
	   
    }
	
	

	
    public boolean checkInclusion(String s1, String s2) {
        Map<Character, Integer> hash1 = new HashMap<>(); // tracking s1
        Map<Character, Integer> hash2 = new HashMap<>(); // tracking s2
    	
        
        // update hash1 s1
        for (int i = 0; i < s1.length(); i++) {
        	hash1.put(s1.charAt(i), hash1.getOrDefault(s1.charAt(i), 0) + 1);
        }
        
    	// apply sliding window
        int left = 0;
        int right = s1.length() - 1;
        
        while(right < s2.length()) {
        	hash2.put(s2.charAt(right), hash2.getOrDefault(s2.charAt(right), 0) + 1);
        	
        	if (isSame(hash1, hash2)) {
        		return true;
        	}
        	
        	hash2.put(s2.charAt(left), hash2.getOrDefault(s2.charAt(left), 0) - 1);
        	left++;
        	right++;
        }
        
        return false;
        	
    	
    	
    }
    
    
    private boolean isSame(Map<Character, Integer> hash1, Map<Character, Integer> hash2) {
    	if (hash1.size() != hash2.size()) return false;
    	
    	for (Map.Entry<Character, Integer> entry: hash1.entrySet()) {
    		char key = entry.getKey();
    		int val = entry.getValue();
    		
    		if (hash1.get(key) == hash2.get(key)) continue;
    		return false;
    	}
    	
    	return true;
    	
    }
    
	
    public int countVowelSubstrings(String word) {
    	Map<Character, Integer> hash = new HashMap<>();
    	
    	
    	char[] ar = word.toCharArray();
    	
    	int res = 0;
    	
    	// iterate until size - 5
    	for (int i = 0; i <= ar.length - 5; i++) {
    		// check if char is vowel
    		// consonant -> clear hash iterate next
    		if (isVowel(ar[i])) {
    			hash = new HashMap<>();
    			hash.put(ar[i], hash.getOrDefault(ar[i], 0) + 1);
        		
        		// iterate rest of characters
        		for (int j = i + 1; j < ar.length; j++) {
        			if (!isVowel(ar[j])) {
            			hash.clear();
            			break;
            		}
        			
            		hash.put(ar[j], hash.getOrDefault(ar[j], 0) + 1);
        			
        			res += isVowelSubstring(hash) ? 1 : 0;
        		}
    		}
    	
    	}
    	
    	return res;
    	
    }
    
    private boolean isVowelSubstring(Map<Character, Integer> hash) {
    	if (hash.getOrDefault('a', 0) > 0 
    			&& hash.getOrDefault('e', 0) > 0 
    			&& hash.getOrDefault('i', 0) > 0 
    			&& hash.getOrDefault('o', 0) > 0 
    			&& hash.getOrDefault('u', 0) > 0) {
    		return true;
    	}
    	
    	return false;
    }
    
    private boolean isVowel(Character key) {
		if (key == 'a' || key == 'e' || key == 'i' || key == 'o' || key == 'u') {
			return true;
		}
		
		return false;

    }
	
    public int maxProfit(int[] prices) {
    	
    	
    	int res = 0;
    	int left = 0;
    	int right = 1;
    	
    	while(right < prices.length) {
    		if (prices[right] > prices[left]) {
    			res = Math.max(res, prices[right] - prices[left]);
    			right++;
    		} else {
    			left = right;
    			right++;
    		}
    	}
    	
    	return res;
    	
    }
	
    public int lengthOfLongestSubstring(String s) {
        Map<Character, Integer> hash = new HashMap<>();
        
        char[] charAr = s.toCharArray();
        
        int left = 0;
        int res = 0;
        
        for (int right = 0; right < charAr.length; right++) {
        	hash.put(charAr[right], hash.getOrDefault(charAr[right], 0) + 1);
        	
        	while(left < charAr.length && hash.get(charAr[right]) > 1) {
        		hash.put(charAr[left], hash.get(charAr[left]) -1);
        		left++;
        	}
        	
        	res = Math.max(res, right - left + 1);
        }
    	
    	return res;
    	
    }
	
    public int characterReplacement(String s, int k) {
    	Map<Character, Integer> hash = new HashMap<>();
    	
    	char[] charAr = s.toCharArray();
    	int maxRepeating = 0;
    	int left = 0;
    	int res = 0;
    	
    	for (int right = 0; right < charAr.length; right++) {
    		// update hash
    		hash.put(charAr[right], hash.getOrDefault(charAr[right], 0) + 1);
    		
    		// update maxRepeating
    		maxRepeating = Math.max(maxRepeating, hash.get(charAr[right]));
    		
    		// expand window until
    		while(left < charAr.length && right - left + 1 - maxRepeating > k) {
    			hash.put(charAr[left], hash.get(charAr[left]) - 1);    			
    			left++;
    			
    			
    		}
    		// update res
    		res = Math.max(res, right - left + 1);
    		
    	}
    	
    	return res;
    	
    	
    }
}
