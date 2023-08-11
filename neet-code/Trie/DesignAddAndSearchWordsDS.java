import java.util.HashMap;
import java.util.Map;

public class DesignAddAndSearchWordsDS {

	
	
	
	class WordDictionary {
		
		TrieNode root;
		

	    public WordDictionary() {
	        this.root = null;
	    }
	    
	    public void addWord(String word) {
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
	    	return search(word, 0, root);
	    }
	    
	    public boolean search(String word, int i, TrieNode node) {
	    	if (i == word.length()) {
	    		return node.isLast;
	    	}
	    	
	    	char c = word.charAt(i);
	    	
	    	if (c == '.') { // c == . 이라면 다음 layer를 확인. 원래는 for each char at word인데 recursion으로 대체
	    		for (Character character : node.children.keySet()) {
	    			if (search(word, i + 1, node.children.get(character))) return true;
	    		}
	    	} else {
	    		if (node.children.containsKey(c)) {
	    			return search (word, i + 1, node.children.get(c));
	    		}
	    	}
	    	
	    	return false;
	    	
	    }
	    	
	}

	
	
	
	
	
	public class TrieNode {
		char parent;
		Map<Character, TrieNode> children;
		boolean isLast;
		
		public TrieNode(char parent) {
			this.parent = parent;
			this.children = new HashMap<>();
			this.isLast = false;
		}
		
		
		
		
		
	}
	
	
	
}
