import java.util.Stack;

public class MinStack {
	Stack<Integer> st;
	Stack<Integer> minSt;
	
	public MinStack() {
		this.st = new Stack<>();
		this.minSt = new Stack<>();
	}
	    
    public void push(int val) {
    	if (minSt.isEmpty()) {
    		minSt.push(val);
    	} else {
    		if (minSt.peek() >= val) {
    			minSt.push(val);
    		}
    	}
    	
        st.push(val);
    }
    
    public void pop() {
        int val = st.pop();
        if (val == minSt.peek()) {
        	minSt.pop();
        }
        
    }
    
    public int top() {
        return st.peek();
    }
    
    public int getMin() {
        return minSt.peek();
    }
}


/**
 * Your MinStack object will be instantiated and called as such:
 * MinStack obj = new MinStack();
 * obj.push(val);
 * obj.pop();
 * int param_3 = obj.top();
 * int param_4 = obj.getMin();
 */