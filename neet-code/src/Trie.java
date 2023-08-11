import java.util.HashMap;
import java.util.Map;

public class Trie {
	
	private Node root;

    public void addWord(String word) {
     
    	char[] ar = word.toCharArray();
    	Node curr = root;
    	
    	for (int i = 0; i < ar.length; i++) {
    		if (!curr.children.containsKey(ar[i])) {
    			curr.children.put(ar[i], new Node(ar[i]));
    		}
    		
    		curr = curr.children.get(ar[i]);
    		
    	}
    	
    	curr.isLast = true;
    	
    }
    
    public boolean search(String word) {
        char[] ar = word.toCharArray();
        Node curr = root;
        
        for (int i = 0; i < ar.length; i++) {
        	if (!curr.children.containsKey(ar[i])) {
        		return false;
        	}
        	
        	curr = curr.children.get(ar[i]);
        }
    	
    	return true;
    	
    	
    }
	
	
	
	public class Node {
		char parent;
		Map<Character, Node> children;
		boolean isLast;
		
		public Node(char parent) {
			this.parent = parent;
			this.children = new HashMap<>();
			this.isLast = false;
		}
		
	}
}
