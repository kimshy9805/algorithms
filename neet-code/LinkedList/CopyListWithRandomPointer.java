import java.util.HashMap;
import java.util.Map;

public class CopyListWithRandomPointer {
	 public Node copyRandomList(Node head) {
	     
		 Node curr = head;
		 
		 Map<Node, Node> hash = new HashMap<>();
		 
		 
		 // 1st pass: Iterate entire ll and fill up hash
		 while(curr != null) {
			 hash.put(curr, new Node(curr.val));
			 curr = curr.next;
		 }
		 
		 // 2ed pass: link them all
		 curr = head;
		 while(curr != null) {
			 hash.get(curr).next = hash.get(curr.next);
			 hash.get(curr).random = hash.get(curr.random);
			 curr = curr.next;
		 }
		 
		 return hash.get(head);
		 
		 
	 }
	 
	 class Node {
		    int val;
		    Node next;
		    Node random;

		    public Node(int val) {
		        this.val = val;
		        this.next = null;
		        this.random = null;
		    }
	 }
}
