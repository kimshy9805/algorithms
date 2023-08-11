
public class LinkedListCycle {
    public boolean hasCycle(ListNode head) {
        
    	// Easy way is Set 
    	
    	
    	
    	// Tortoise and Hare
    	
    	ListNode preHead = new ListNode();
    	preHead.next = head;
    	
    	ListNode curr = head;
    	ListNode next = curr.next;
    	
    	while(next != null && next.next != null) {
    		if (curr.val == next.val) {
    			return true;
    		}
    		
    		curr = curr.next;
    		next = next.next.next;
    	}
    	
    	return false;
    	
    	
    	
    }
    
	public class ListNode {
	    int val;
	    ListNode next;
	    ListNode() {}
	    ListNode(int val) { this.val = val; }
	    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}
