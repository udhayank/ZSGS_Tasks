package fourte;

import java.util.ArrayList;
import java.util.List;

public class FourteSolver4 {
	
	char[] elements = {0,0,0,0,'+','-','*','/'};
//	char[] operators = {'+','-','*','/'};	
	char[] operators = {'*','/','+','-'};	
	

	public static void main(String[] args) {
		
//		int[] nums = {1,2,3,4};
//		int target = 10;
//		int[] nums = {5,3,6,7};
//		int target = 48;
//		int[] nums = {3,6,4,5};
//		int target = 72;
//		int[] nums = {8,4,2,5};
//		int target = 81;
		int[] nums = {6,8,9,2};
		int target = 80;
		
		FourteSolver4 solver = new FourteSolver4();
		
		solver.solve(nums, target);
		
//		System.out.println(solver.isValid("1234", 10));
		
		
	}
	
	public void solve(int[] nums, int target) {
		
		for (int i=0; i<nums.length; i++) {
			elements[i] = (char) ('0' + nums[i]);
		}		
		
		String combination = "";
		
		for (int i=0; i<4; i++) {
			
			combination += elements[i];
			if (addElement(combination, 1, target)) {
				return;
			}			
			combination = "";
			
		}
			
	}
	
	public boolean addElement(String combination, int numCount, int target) {
		
//		System.out.println(combination);
		
		if (numCount >= 4) {
			if (isValid(combination, target)) {
				return true;
			} 
			return false;
		}
		
		if (Character.isDigit(combination.charAt(combination.length()-1))) {
			
			for (int i=0; i<4; i++) {
				
				if ( combination.contains("" + elements[i])) {
					continue;
				}				
												
				combination += elements[i];
				if (addElement(combination, numCount+1, target)) {
					return true;
				}				
											
				combination = combination.substring(0, combination.length()-1);
			}
			
			for (int i=0; i<4; i++) {
								
				combination += operators[i];
				if (addElement(combination, numCount, target)) {
					return true;
				}				
				combination = combination.substring(0, combination.length()-1);
				
			}
			
		} else {
			
			for (int i=0; i<4; i++) {
				if ( combination.contains("" + elements[i])) {
					continue;
				}
				
				combination += elements[i];
				if (addElement(combination, numCount+1, target)) {
					return true;
				}				
				combination = combination.substring(0, combination.length()-1);
				
			}
			
		}
		
		return false;
		
	}
	
	public boolean isValid(String combination, int target) {
		
		List<Integer> parsedNums = new ArrayList<>();
		List<Character> parsedChar = new ArrayList<>();	
		
		
		String currNum = "";
//		System.out.println(combination);
		
		for (char ch:combination.toCharArray()) {
			
			if (Character.isDigit(ch)) {
				currNum += ch;
			} else {
				parsedChar.add(ch);
				parsedNums.add(Integer.valueOf(currNum));
				currNum = "";
			}			
			
		}
		
		if (currNum.length() != 0) {
			parsedNums.add(Integer.valueOf(currNum));
		}
		
		while (parsedChar.indexOf('/') != -1) {
			int index = parsedChar.indexOf('/');
			int num1 = parsedNums.get(index);
			int num2 = parsedNums.get(index+1);
			int res = num1;
			if (num1 % num2 == 0) {
				res = num1 / num2;
			} else {
				return false;
			}
			parsedChar.remove(index);
			parsedNums.remove(index + 1);
			parsedNums.set(index, res);			
		}
		
		while (parsedChar.indexOf('*') != -1) {
			int index = parsedChar.indexOf('*');
			int num1 = parsedNums.get(index);
			int num2 = parsedNums.get(index+1);
			int res = num1 * num2;
			
			parsedChar.remove(index);
			parsedNums.remove(index + 1);
			parsedNums.set(index, res);			
		}
		
		int index = 0;
		
		for (char ch:parsedChar) {
			int num1 = parsedNums.get(index);
			int num2 = parsedNums.get(index+1);
			
			switch (ch) {
			
			case ('+'):
				parsedNums.set(index+1, num1+num2);
				break;
			
			case ('-'):
				parsedNums.set(index+1, num1-num2);
				break;
			
			case ('*'):
				parsedNums.set(index+1, num1*num2);
				break;
			
			case ('/'):
				if (num1%num2 == 0) {
					parsedNums.set(index+1, num1/num2);					
				} else {
					return false;
				}
				break;
			
			}
			
			index++;
		}
		
		if (parsedNums.get(index) == target) {
			System.out.println(combination);
			return true;
		}
		
		return false;
		
	}


}
