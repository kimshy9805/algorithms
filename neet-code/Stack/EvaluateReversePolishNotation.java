import java.util.Stack;

public class EvaluateReversePolishNotation {

	public int evalRPN(String[] tokens) {
		Stack<String> st = new Stack<>();

		for (String token : tokens) {
			if (!isOperand(token)) {
				st.push(token);
			} else {
				int y = Integer.parseInt(st.pop());
				int x = Integer.parseInt(st.pop());

				int newToken = -1;

				if (token.equals("+")) {
					newToken = x + y;
				} else if (token.equals("-")) {
					newToken = x - y;
				} else if (token.equals("*")) {
					newToken = x * y;
				} else {
					newToken = x / y;
				}

				st.push(String.valueOf(newToken));
			}
		}

		return Integer.parseInt(st.peek());

	}

	public boolean isOperand(String str) {
		if (str.equals("+") || str.equals("-") || str.equals("*") || str.equals("/")) {
			return true;
		}

		return false;
	}
}
