package KnapsackBasedSP;

public class DoubleKeysKeyboard {

	
    public int minSteps(int n) {
    	return dfs(0, "", "A", n);
    }
	
	
    private int dfs(int cnt, String stored, String curr, int n) {
    	if (curr.length() >= n) {
    		return curr.length() > n ? Integer.MAX_VALUE : cnt;
    	}
    	
    	
    	if (stored.isEmpty()) {
    		return dfs(cnt+1, curr, curr, n); // perform copy
    	}
    	
    	if (stored.length() == curr.length()) {
    		return dfs(cnt+1, stored, curr+stored, n); // perform paste
    	}
    	
    	return Math.min(dfs(cnt+1, curr, curr,n), dfs(cnt+1, stored, curr+stored, n)); 
    	
    }
}
