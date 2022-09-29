package calculator;

import java.util.LinkedList;
import java.util.List;
import java.util.Stack;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Calculator3 {
	
	Stack<Character> stack = new Stack<>(); 

	public double solve(String expression) throws Exception {
		
		for (char ch:expression.toCharArray()) {
			
			if (ch == ')') {
				String currExp = "";
				while (stack.peek() != '(') {
					currExp = stack.pop() + currExp;
				}
				stack.pop();
				String result = String.valueOf(solveExpression(currExp));
				for (char c:result.toCharArray()) {
					stack.push(c);
				}
			} else {
				stack.push(ch);
			}
			
		}
		
		String currExp = "";
		while (!stack.isEmpty()) {
			currExp = stack.pop() + currExp;
		}
		
		return solveExpression(currExp);
		
	}
	
	
	// Solve expressions without parenthesis
	public double solveExpression(String expression) throws Exception {
		
		System.out.println(expression);
		expression = solveUsingRegex(expression);		
		System.out.println(expression);
		
		List<Double> nums = new LinkedList<>();
		List<Character> operators = new LinkedList<>();
		
		String currNum = "";
		for (char ch:expression.toCharArray()) {
			if (Character.isDigit(ch) || ch=='.') {
				currNum += ch;				
			} else {
				if (currNum.length() != 0) {
					nums.add(Double.valueOf(currNum));
				}
				operators.add(ch);
				currNum = "";
			}
		}
		
		if (currNum.length() != 0) {
			nums.add(Double.valueOf(currNum));
		}
		
		// handling initial - or +
		if (nums.size() == operators.size()) {
			if (operators.get(0) == '-') {
				nums.set(0, nums.get(0)*(-1));
				operators.remove(0);
			} else if(operators.get(0) == '+') {
				operators.remove(0);
			} else {
				throw new Exception("Syntax error!");
			}
		}
		
		System.out.println(nums);
		System.out.println(operators);
		
		
		while (operators.indexOf('^') != -1) {
			int index = operators.indexOf('^');
			double num1 = nums.get(index);
			double num2 = nums.get(index+1);
			double result = Math.pow(num1, num2);
			nums.remove(index+1);
			nums.set(index, result);
			operators.remove(index);
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
			int index = operators.indexOf('*');
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
	
	public String solveUsingRegex(String expression) {
		
		// remove all spaces
		expression = Pattern.compile(" +").matcher(expression).replaceAll("");
		
		String[] funcList = {"sin","cos","tan","ln","log"};
		
		for (String func:funcList) {
			
			Pattern regexPattern = Pattern.compile("(?<="+func+")([0-9]+.?[0-9]*)");
			Matcher matcher = regexPattern.matcher(expression);
					
			while (matcher.find()) {
				System.out.println("found");
				double num = Double.parseDouble(matcher.group());
				switch (func) {
				case "sin":
					num = Math.sin(num);
					break;
					
				case "cos":
					num = Math.cos(num);
					break;
					
				case "tan":
					num = Math.tan(num);
					break;
					
				case "ln":
					num = Math.log(num);
					break;
					
				case "log":
					num = Math.log10(num);
					break;

				default:
					break;
				}
				expression = Pattern.compile(func+"[0-9]+.?[0-9]*").matcher(expression).replaceFirst(String.valueOf(num));
			}
						
		}
		
		return expression;
	}
	
}
