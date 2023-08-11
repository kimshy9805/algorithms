import java.util.Stack;

public class RemoveKDigits {

	
	
    public String removeKdigits(String num, int k) {
        Stack<Character> st = new Stack<>();
        
        char[] ar = num.toCharArray();
    	
    	// Iterate num and if peek > current element, better to replace
    	for (int i = 0 ; i < ar.length; i++) {
    		while(!st.isEmpty() && k > 0 && st.peek() > ar[i]) {
    			st.pop();
    			k--;
    		}
    		st.push(ar[i]);
    	}
    	
    	
    	// in case k > 0 pop until k = 0
    	while(k > 0 && !st.isEmpty()) {
    		k--;
    		st.pop();
    	}
    	
    	
    	// form string
    	StringBuilder sb = new StringBuilder();
    	
    	
    	// remove leading zeros
    	while(!st.isEmpty()) {
    		sb.append(st.pop());
    	}
    	
    	sb = sb.reverse();
    	
		// remove leading zeros
		while (sb.toString().startsWith("0")) {
			sb.deleteCharAt(0);

		}
		return sb.length() == 0 ? "0" : sb.toString();

    }
}
