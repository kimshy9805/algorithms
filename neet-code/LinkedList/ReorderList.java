
public class ReorderList {
	
    public void reorderList(ListNode head) {
        ListNode slow = head;
        ListNode fast = head.next;
        
        while(fast != null && fast.next != null) {
        	slow = slow.next;
        	fast = fast.next.next;
        }
    	
    	ListNode second = slow.next; // half
    	ListNode prev = slow.next= null; //  
    	
    	// Reverse second half 
    	while(second != null) {
    		ListNode next = second.next;
    		second.next = prev;
    		prev= second;
    		second = next;
    	}
    	
    	ListNode first = head;
    	second = prev;
    	
    	while(second != null) {
    		ListNode tmp1 = first.next;
    		ListNode tmp2 = second.next;
    		
    		first.next = second;
    		second.next = tmp1;
    		first = tmp1;
    		second = tmp2;
    	}
    	
    }
    
    
    
	public class ListNode {
	    int val;
	    ListNode next;
	    ListNode() {}
	    ListNode(int val) { this.val = val; }
	    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}
