
public class TwoSum2InputArrayIsSorted {
	
    public int[] twoSum(int[] numbers, int target) {
    	
    	int[] res = new int [2];
    	
    	
    	int i = 0;
    	int j = numbers.length - 1;
    	
    	
    	while(i < j) {
    		if (numbers[i] + numbers[j] == target) {
    			break;
    		}
    		
    		if (numbers[i] + numbers[j] < target) {
    			i++;
    			continue;
    		}
    		
    		j--;
    		
    	}
    	
    	
    	return new int [] {i+1, j+1};
    }
}
