package calculator;

import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class Validator {

public boolean isValidExpression(String expression) {
		
		// validate characters
		Set<Character> reference = new HashSet<>();
		String ref = "1234567890()+-*/ ";
		for (char ch:ref.toCharArray()) {
			reference.add(ch);
		}
				
		// validate parenthesis
		Stack<Character> stack = new Stack<>(); 
		for (char ch:expression.toCharArray()) {
			
			if (!reference.contains(ch)) {
				return false;
			}
			
			if (ch == ')') {
				if (stack.isEmpty() || stack.pop() != '(') {
					return false;
				}
			} else if (ch == '(') {
				stack.push(ch);
			}
		}
		
		if (!stack.isEmpty()) {
			return false;
		}
		
		return true;
	}
	
}
