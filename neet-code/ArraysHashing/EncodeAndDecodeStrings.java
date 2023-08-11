import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class EncodeAndDecodeStrings {
	

	public static void main(String[] args) {
		List<String> strs = Arrays.asList("line", "code", "love");
		System.out.println(encode(strs));
		
		System.out.println(decode(encode(strs)));
	}
	
	
	
    /*
     * @param strs: a list of strings
     * @return: encodes a list of strings to a single string.
     */
    public static String encode(List<String> strs) {
    	List<Integer> counter = new ArrayList<>();
    	
    	for (String str: strs) {
    		int cnt = str.length();
    		
    		counter.add(cnt);
    		
    	}
    	
    	String encoded = "";
    	
    	for (int i = 0; i < counter.size(); i++) {
    		encoded = encoded + String.valueOf(counter.get(i)) + "#" + strs.get(i);
    	}
    	
    	return encoded;
    	
    	
    }

    /*
     * @param str: A string
     * @return: dcodes a single string to a list of strings
     */
    public static List<String> decode(String str) {
    	List<String> res = new ArrayList<>();
    	
    	int i = 0;
    	while(i < str.length()) {
    		int j = i;
    		
    		while(str.charAt(j) != '#') j++;
    		
    		int length = Integer.valueOf(str.substring(i, j));
    		i = j + 1 + length;
    		res.add(str.substring(j+1, i));
    		
    		
    	}
    	
    	return res;
    }
    
    
    
    
}


/*
	[Neet, Code]
	
	Add number of characters with delimeter 
	
	4#Neet4#Code 


	When decode
	look at the number and encode the next x number of character after delimeter 




*/