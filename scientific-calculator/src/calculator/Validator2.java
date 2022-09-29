package calculator;

import java.util.Stack;
import java.util.regex.*;

public class Validator2 {
	
	public boolean isValidExpression(String expression) {
		
		expression = Pattern.compile(" ").matcher(expression).replaceAll("");
		
		String pattern = "([0-9]|[\\+\\-\\*\\/().^]|(sin|cos|tan|log|ln|sqrt)\\([0-9.(log|sin|cos|tan|ln|sqrt)]+\\))*";
		Pattern regexPattern = Pattern.compile(pattern);
		
		Matcher matcher = regexPattern.matcher(expression);
		
		if (!matcher.matches()) {
			return false;
		}
		
		if (Pattern.compile("(\\)(?=[0-9|a-z|.|\\(]))|([0-9|.](?=\\())+").matcher(expression).find()) {
			return false;
		}
		
		
		// validate parenthesis
		Stack<Character> stack = new Stack<>(); 
		for (char ch:expression.toCharArray()) {
			
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

//public boolean isValidExpression(String expression) {
//		
//		// validate characters
//		Set<Character> reference = new HashSet<>();
//		String ref = "1234567890()+-*/ ";
//		for (char ch:ref.toCharArray()) {
//			reference.add(ch);
//		}
//				
//		// validate parenthesis
//		Stack<Character> stack = new Stack<>(); 
//		for (char ch:expression.toCharArray()) {
//			
//			if (!reference.contains(ch)) {
//				return false;
//			}
//			
//			if (ch == ')') {
//				if (stack.isEmpty() || stack.pop() != '(') {
//					return false;
//				}
//			} else if (ch == '(') {
//				stack.push(ch);
//			}
//		}
//		
//		if (!stack.isEmpty()) {
//			return false;
//		}
//		
//		return true;
//	}
//	
}
