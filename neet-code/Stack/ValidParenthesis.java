import java.util.Stack;

public class ValidParenthesis {
	
	
    public boolean isValid(String s) {
    	Stack<Character> st = new Stack<>();
    	
    	for (int i = 0; i < s.length(); i++) {
    		if (isOpen(s.charAt(i))) {
    			st.push(s.charAt(i));
    			continue;
    		}
    		
    		char c = s.charAt(i);
    		if (st.isEmpty()) return false;
    		if (c == '}' && st.peek() == '{') {
    			st.pop();
    			continue;
    		} else if (c == ']' && st.peek() == '[') {
    			st.pop();
    			continue;
    		} else if (c == ')' && st.peek() == '(') {
    			st.pop();
    			continue;
    		}
    		
    		return false;
    		
    	}
    	
    	return st.size() > 0 ? false: true;
    	
    	
    	
    	
    	
    }
    
    public boolean isOpen(Character c) {
    	if (c == '(' || c == '[' || c == '{') {
    		return true;
    	} 
    	return false;
    }
}
