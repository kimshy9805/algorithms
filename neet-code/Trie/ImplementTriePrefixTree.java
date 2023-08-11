import java.util.HashMap;
import java.util.Map;

public class ImplementTriePrefixTree {
	class Trie {

		private TrieNode root;
		
	    public Trie() {
	        root = new TrieNode('#');
	    }
	    
	    public void insert(String word) {
	        
	    	char[] ar = word.toCharArray();
	    	
	    	TrieNode curr = root;
	    	
	    	for(int i = 0; i < ar.length; i++) {
	    		if (!curr.children.containsKey(ar[i])) {
	    			curr.children.put(ar[i], new TrieNode(ar[i]));
	    		}
	    		curr = curr.children.get(ar[i]);
	    		
	    	}
	    	
	    	curr.isLast = true;
	    	
	    	
	    }
	    
	    public boolean search(String word) {
	        
	    	char[] ar = word.toCharArray();
	    	
	    	TrieNode curr = root;
	    	
	    	for(int i = 0; i < ar.length; i++) {
	    		if (!curr.children.containsKey(ar[i])) {
	    			return false;
	    		}
	    		curr = curr.children.get(ar[i]);
	    		
	    	}
	    	
	    	return curr.isLast;
	    	
	    }
	    
	    public boolean startsWith(String prefix) {
	    	char[] ar = prefix.toCharArray();
	    	
	    	TrieNode curr = root;
	    	
	    	for(int i = 0; i < ar.length; i++) {
	    		if (!curr.children.containsKey(ar[i])) {
	    			return false;
	    		}
	    		curr = curr.children.get(ar[i]);
	    		
	    	}
	    	
	    	return true;
	    }
	}
	
	
	
	class TrieNode {
		char parent;
		Map<Character, TrieNode> children;
		boolean isLast;
		
		TrieNode(char parent) {
			this.parent = parent;
			this.children = new HashMap<>();
			this.isLast = false;
		}
 		
		
		
		
	}
}
