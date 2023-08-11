package ClassicalDP;

public class KConcatenationMaximumSum {
	
	
	
    public int kConcatenationMaxSum(int[] arr, int k) {
    	if (k == 1) return (int)kadane(arr);
    	
    	
    	long mod = 1000000007;
    	
    	long total = 0;
    	int [] twice = new int[arr.length * 2];
    	
    	for (int i = 0; i < arr.length; i++) {
    		total += arr[i];
    		twice[i] = twice[i + arr.length] = arr[i];
     	}
    	
    	if (total < 0) {
    		return (int) (kadane(twice) % mod);
    	}
    	
    	return (int) ((kadane(twice) + total * (k - 2l)) % mod);
    	
    }
    
    
    public long kadane(int[] nums){
        long maxSum = 0, currSum = 0;
        for(int i = 0; i<nums.length; ++i){
            currSum = Math.max(nums[i], currSum + nums[i]);
            maxSum = Math.max(currSum, maxSum);
        }
        return maxSum;
    }
}
