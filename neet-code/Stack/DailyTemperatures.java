import java.util.Stack;

public class DailyTemperatures {
	
    public int[] dailyTemperatures(int[] temperatures) {
    	Stack<Temp> st = new Stack<>(); //monotonically decreasing stack
    	
    	int[] res = new int[temperatures.length];
    	for (int i = 0; i < temperatures.length; i++) {
    		// current temp이 st peek 보다 warmer하면 pop & update res 
    		while(!st.isEmpty() && st.peek().temp < temperatures[i]) {
    			Temp temp = st.pop();
    			res[temp.idx] = i - temp.idx;
    		}
    		
    		// 더 작다면 단순히 push 
    		Temp t = new Temp(temperatures[i], i);
    		st.push(t);
    	}
    	
    	return res;
    	
    }
    
    public class Temp {
    	int temp;
    	int idx;
    	
    	Temp(int temp, int idx) {
    		this.temp = temp;
    		this.idx = idx;
    	}
    	
    	
    }
}
