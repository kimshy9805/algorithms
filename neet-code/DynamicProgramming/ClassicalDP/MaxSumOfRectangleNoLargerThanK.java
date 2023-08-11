package ClassicalDP;

import java.util.TreeSet;

public class MaxSumOfRectangleNoLargerThanK {
    public int maxSumSubmatrix(int[][] matrix, int k) {
        int res = Integer.MIN_VALUE;
        
        
        for (int left = 0; left < matrix[0].length; left++) {
        	int[] runningRows = new int [matrix.length];
        	
        	
        	for (int right =left; right < matrix[0].length; right++) {
        		// update running Rows Sum
        		for (int row = 0; row < matrix.length; row++) {
        			runningRows[row] += matrix[row][right];
        		}

        		// cs - k 보다 작은 값은 potential candidate이므로 res update
        		// kadane's algo approach
        		TreeSet<Integer> set = new TreeSet<>();
        		
        		set.add(0);
        		int cs = 0;
        		
        		for (int val: runningRows) {
        			cs += val;
        			
        			Integer target = set.ceiling(cs - k); // cs -k ceiling은 이것보다 작은것도 포
        			
        			if (target != null) {
        				res = Math.max(res, cs - target); // cs- target이 결국 k의 range만큼의 sum 의미하기때문
        			}
        			set.add(cs);
        		}
        	}
        }
        
        
    	return res;
    	
    	
    }
    
    
    
    
}
