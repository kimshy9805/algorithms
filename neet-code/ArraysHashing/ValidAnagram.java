import java.util.Arrays;

public class ValidAnagram {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	
    public boolean isAnagram(String s, String t) {
    	if (s.length() != t.length()) return false;
    	
    	int [] freq = new int [26];
    	
    	
    	for (int i = 0 ; i < s.length(); i++) {
    		freq[s.charAt(i) - 'a']++;
    		freq[t.charAt(i) - 'a']--;
    	}
    	
    	for (int n : freq) if (n != 0) return false;
    	
    	return true;
    	
    }
    
    public boolean isAnagram1(String s, String t) {
    	char[] charAr1 = s.toCharArray();
    	char[] charAr2 = t.toCharArray();
    	
    	Arrays.sort(charAr1);
    	Arrays.sort(charAr2);
    	
    	if (charAr1.length != charAr2.length) return false;
    	
    	for (int i = 0; i < charAr1.length; i++) {
    		if (charAr1[i] != charAr2[i]) return false;
    	}
    	
    	return true;
    	
    }

}
