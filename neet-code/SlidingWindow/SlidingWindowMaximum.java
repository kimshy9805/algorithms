import java.util.Deque;
import java.util.LinkedList;

public class SlidingWindowMaximum {
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
}
