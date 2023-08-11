import java.util.Stack;

public class ValidateStackSequences {
	
	
	
    public boolean validateStackSequences(int[] pushed, int[] popped) {
        Stack<Integer> st = new Stack<>();
        
        int j = 0;
        
        for (int i = 0; i < pushed.length; i++) {
        	st.push(pushed[i]);
        	
        	while(!st.isEmpty() && st.peek() == popped[j]) {
        		st.pop();
        		j++;
        	}
        }
    	
    	if (st.size() > 0) {
    		return false;
    	}
    	
    	return true;
    	
    }
    
    
}
