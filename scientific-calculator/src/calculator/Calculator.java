package calculator;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Set;
import java.util.Stack;

public class Calculator {
	
	public String solve(List<String> expressionList) {
		
		for (int i=0; i<expressionList.size(); i++) {
			
			String expression = expressionList.get(i);
			
			if (isExpression(expression)) {
				
//				System.out.println(expression);
			
				if (expression.charAt(0) == '(') {
					
					List<String> list = new LinkedList<>() ;
					
					String currNum = "";
					
					for (int index=0; index<expression.length(); index++) {
						
						char ch = expression.charAt(index);
						
						if (ch == '(') {
							int[] pair =  solveParanthesis(expression, index);
							list.add(expression.substring(pair[0]+1, pair[1]));
							index = pair[1];
						} else if (Character.isDigit(ch)) {
							currNum += ch;
						} else {
							if (currNum.length() != 0) {
								list.add(currNum);			
							}
							currNum = "";
							list.add(""+ch);
						}
						
					}
					
					if (currNum.length() != 0) {
						list.add(currNum);			
					}
					
					System.out.println(list);
					solve(list);
					
				} else {
					expressionList.set(i, String.valueOf(solveExpression(expression)));
				}
				
			} else {
				
				
				
			}
			
//			System.out.println(expressionList);
			
		}
		
		return "";
	}
	
	public boolean isExpression(String expression) {
		
		if (expression.length() <= 1) {
			return false;
		}
		
		try {
			Double.parseDouble(expression);
		} catch (NumberFormatException e) {
			return true;
		}
		
		return false;
		
	}
	
//	public double solve(String expression) {
//		
//		List<String> list = new LinkedList<>() ;
//		
//		String currNum = "";
//		
//		for (int index=0; index<expression.length(); index++) {
//			
//			char ch = expression.charAt(index);
//			
//			if (ch == '(') {
//				int[] pair =  solveParanthesis(expression, index);
//				list.add(expression.substring(pair[0]+1, pair[1]));
//				index = pair[1];
//			} else if (Character.isDigit(ch)) {
//				currNum += ch;
//			} else {
//				if (currNum.length() != 0) {
//					list.add(currNum);			
//				}
//				currNum = "";
//				list.add(""+ch);
//			}
//			
//		}
//		
//		if (currNum.length() != 0) {
//			list.add(currNum);			
//		}
//		
//		System.out.println(list);
//		
//		return 0.0;
//		
//	}
//	
	public int[] solveParanthesis(String expression, int startIndex) {
		
		Stack<Character> paraStack = new Stack<>();
		Stack<Integer> indexStack = new Stack<>();
		Stack<int[]> paraPairs = new Stack<>();
		
		for (int index=startIndex; index<expression.length(); index++) {
			
			char ch = expression.charAt(index);
			
			if (ch == '(') {
				paraStack.push(ch);
				indexStack.push(index);
			} else if (ch == ')') {
				paraStack.pop();
				paraPairs.push(new int[] {indexStack.pop(), index});
			}
			
//			System.out.println(paraStack);
//			System.out.println(paraStack.size() == 0);
						
			if (paraStack.size() == 0) {
				break;
			}
			
		}
		
		return paraPairs.pop();
		
	}

	public double solveExpression(String expression) {
		
		System.out.println(expression);
		
		List<Double> nums = new LinkedList<>();
		List<Character> operators = new LinkedList<>();
		
		String currNum = "";
		for (char ch:expression.toCharArray()) {
			if (Character.isDigit(ch) || ch=='.') {
				currNum += ch;				
			} else {
				nums.add(Double.valueOf(currNum));
				operators.add(ch);
				currNum = "";
			}
		}
		
		if (currNum.length() != 0) {
			nums.add(Double.valueOf(currNum));
		}
		
		
		while (operators.indexOf('/') != -1) {
			int index = operators.indexOf('/');
			double num1 = nums.get(index);
			double num2 = nums.get(index+1);
			double result = num1/num2;
			nums.remove(index+1);
			nums.set(index, result);
			operators.remove(index);
		}
		
		while (operators.indexOf('*') != -1) {
			int index = operators.indexOf('/');
			double num1 = nums.get(index);
			double num2 = nums.get(index+1);
			double result = num1*num2;
			nums.remove(index+1);
			nums.set(index, result);
			operators.remove(index);
		}
		
		for (int i=0; i<operators.size(); i++) {
			
			char operator = operators.get(i);
			
			switch (operator) {
			case '+':
				nums.set(0, nums.get(0) + nums.get(1));
				nums.remove(1);
				break;

			
			case '-':
				nums.set(0, nums.get(0) - nums.get(1));
				nums.remove(1);
				break;				
				
			}
			
		}
		
		return nums.get(0);
	}
	
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
