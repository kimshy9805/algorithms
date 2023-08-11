import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class FindKPairsWithSmallestSums {
	
	
    public List<List<Integer>> kSmallestPairs(int[] nums1, int[] nums2, int k) {
        
		//In the minHeap array, 0th element refers to the curr element in nums1 and 1st element refers to curr element in nums2 
        PriorityQueue<int[]> minHeap = new PriorityQueue<>((a,b) -> (a[0] + a[1]) - (b[0] + b[1]));
        
		// The 2nd element in the minHeap is the index of nums2, the corresponding element of nums2 is in index1 of minHeap
        for(int i=0; i < nums1.length && i < k; i++)
            minHeap.add(new int[]{nums1[i], nums2[0], 0});
        
        List<List<Integer>> result = new ArrayList<>();

        while(!minHeap.isEmpty() && k-- > 0) {
            int[] curr = minHeap.poll();
            result.add(List.of(curr[0], curr[1]));
            int nums2Idx = curr[2];
            if(nums2Idx < nums2.length - 1)
                minHeap.add(new int[]{curr[0], nums2[nums2Idx + 1], nums2Idx + 1});
        }
        return result;
    }

}


/*
	Store the smallest k pair of elements in a minHeap and then poll it out and store the same in the final result.
Intuitively we can think of O(k^2 log k) solution, which will be to store each combination of k elements of nums1 and k elements of nums2 in the minHeap and then poll first k elements from the minHeap and add in the final result.
We can do better than that. We can store combinations of k elements from nums1 and nums2[0] (the first element of nums2) in O(k log k) time. (We could have done the other way as well, storing nums1[0] and all nums2 combination, but the overall time complexity will still be the same).
Now we poll out each element from the minHeap and at the same time we add the pair of the next nums2[] element and the curr polled element of nums1 in the minHeap until we get k elements OR the minHeap is empty.
For getting the next nums2 element we can store the curr index of nums2 in the minHeap when we are storing the nums1 and nums2 pair.
This step will again take O(k log k) time. Hence our overall time complexity reduces to O(2*(k log k)) which is effectively O(k log k).



*/