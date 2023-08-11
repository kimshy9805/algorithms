import java.util.Arrays;

public class PermutationInString {
	
    public boolean checkInclusion(String s1, String s2) {
    	if (s1.length() > s2.length()) {
    		return false;
    	}
    	
    	
    	int left = 0;
    	int right = s1.length() - 1;
    	
    	
    	int[] freq = getFreq(s1);
    	int[] freq1 = getFreq(s2.substring(0, right));
    	
    	
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
    
    public int[] getFreq(String str) {
    	int[] freq = new int [26];
    	
    	for (int i = 0; i < str.length(); i++) {
    		freq[str.charAt(i) - 'a']++;
    	}
    	
    	return freq;
    }

}
