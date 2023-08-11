
public class RemoveNthNodeFromEndOfList {
    public ListNode removeNthFromEnd(ListNode head, int n) {
        
        if (head == null || head.next == null) return null;
    
        ListNode preHead = new ListNode(0);
        preHead.next = head;
        
        ListNode left = preHead;
        
        ListNode right = preHead;
        
        
        
        while(n-- > 0) {
        	right = right.next;
        }
        
        while(right.next != null) {
        	left = left.next;
        	right = right.next;
        }
        
        
        left.next = left.next.next;
        
        return preHead.next; 
        
    	
    }
    
	public class ListNode {
	    int val;
	    ListNode next;
	    ListNode() {}
	    ListNode(int val) { this.val = val; }
	    ListNode(int val, ListNode next) { this.val = val; this.next = next; }
	}
}
