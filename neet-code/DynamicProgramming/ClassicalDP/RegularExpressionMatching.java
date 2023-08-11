package ClassicalDP;

public class RegularExpressionMatching {
	
	
	
    public boolean isMatch(String s, String p) {
        
    	
    	return dfs(0, 0, s, p);
    	
    }
    
    private boolean dfs(int i, int j, String s, String p) {
    	
    	// base case 1 
    	if (i >= s.length() && j >= p.length()) return true;
    	
    	// base case 2 
    	if (j >= p.length()) return false;
    	
    	
    	// 같은 char 혹은 p가 . 인 경
    	boolean match = i < s.length() && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
    	
    	// star 확인
    	if (j+1 < p.length() && p.charAt(j+1) == '*') {
    		return dfs(i, j + 2, s, p) || (match && dfs(i+1, j, s ,p));
    	} else {
    		if (match) {
    			dfs(i+1, j+1, s, p);
    		}
    	}
    	
    	
    	return false;
    	
    }
    
    
}
