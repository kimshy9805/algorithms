import java.util.HashMap;
import java.util.Map;

public class DS {

	
	
	class LRUCache {
		private int capacity;
		private Map<Integer, Node> cache;
		
		private Node least;
		private Node most;
		
	    public LRUCache(int capacity) {
	        this.capacity = capacity;
	        
	        cache = new HashMap<>();
	        
	        least = new Node(0, 0);
	        most = new Node(0, 0);
	    	
	        // update least and most pointers
	        least.next = most;
	        most.next = least;
	    }
	    
	    public int get(int key) {
	        if (cache.containsKey(key)) {
	        	// update corresponding node to most 
	        	Node node = cache.get(key);
	        	
	        	removeNode(node);
	        	insertNode(node);
	        	
	        	return cache.get(key).val;
	        }
	    	
	    	return -1;
	    	
	    	
	    }
	    
	    public void put(int key, int value) {
	    	if (cache.containsKey(key)) {
	    		removeNode(cache.get(key));
	    		cache.remove(key);
	    	}
	    	
	    	
	        cache.put(key, new Node(key, value));
	        insertNode(cache.get(key));
	    	
	        if (cache.size() > capacity) {
	        	Node nodeToBeDeleted = least.next;
	        	
	        	removeNode(nodeToBeDeleted);
	        	cache.remove(nodeToBeDeleted.key);
	        }

	    	
	    }
	    
	    private void removeNode(Node node) {
	    	Node prev = node.prev;
	    	Node next = node.next;
	    	
	    	prev.next = next;
	    	next.next = prev;
	    	
	    }
	    
	    private void insertNode(Node node) {
	    	Node prev = this.most.prev;
	    	Node next = this.most;
	    	
	    	// update current node with node before most
	    	prev.next = node;
	    	node.prev = prev;
	    	
	    	// update right and node
	    	node.next = next;
	    	next.prev = node;
	    	
	    }
	    
	}

	
	public class Node {
		int key;
		int val;
		
		Node prev;
		Node next;
		
		public Node(int key, int val) {
			this.key = key;
			this.val = val;
		}
		
		
		
	}
	
	
}
