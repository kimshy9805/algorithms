import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GroupAnagrams {
	
	
	
    public List<List<String>> groupAnagrams(String[] strs) {
    	Map<String, List<String>> hash = new HashMap<>();
    	
    	
    	for (String str: strs) {
    		char [] charAr = str.toCharArray();
    		Arrays.sort(charAr);
    		
    		String sortedStr = new String(charAr);
    		
    		if (!hash.containsKey(sortedStr)) {
    			hash.put(sortedStr, new ArrayList<>());
    			hash.get(sortedStr).add(str);
    			continue;
    		}
    		
    		hash.get(sortedStr).add(str);
    		
    		System.out.println(hash.get(sortedStr));
    		
    	}
    	
    	List<List<String>> res = new ArrayList<>();
    	
    	int cnt = 0;
    	for (Map.Entry<String, List<String>> entry: hash.entrySet()) {
    		res.add(entry.getValue());
    	}
    	
    	
    	return res;
    }
    
}



/*
	eat, tea, tan, ate, nat, bat 
	
	
	sort each str
	aet, aet, ant, aet, ant, abt
	
	
	Hash 
	<sorted String, List<String>> 
	aet -> eat, tea, ate 
	tan -> tan
	
	Iterate Hash -> put inside List 
	return List

*/