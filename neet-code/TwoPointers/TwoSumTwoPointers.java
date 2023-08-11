import java.util.Arrays;

public class TwoSumTwoPointers {

	
	 public int[] twoSum(int[] nums, int target) {
		 
		 Pair[] temp = new Pair [nums.length]; 
		 for (int i = 0 ; i < nums.length; i++) {
			 temp[i] = new Pair(i, nums[i]);
		 }
		 
	     Arrays.sort(temp); // sort entire array based on val
		 
		 
		 int i = 0;
		 int j = nums.length-1;
		 
		 while(i < j) { // two pointer approach.
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
	 
	 
	 
	 
	 
	 
}
