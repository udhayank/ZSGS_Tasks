package calculator;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		Calculator2 calculator = new Calculator2();
		Validator validator = new Validator();
				
		while (true) {
			System.out.print("Enter expression: ");
			String expression = in.next();
			if (expression == "exit") {
				break;
			}
			if (validator.isValidExpression(expression)) {				
				try {
					System.out.println("The result is: " + calculator.solve(expression));
				} catch (Exception e) {
					System.out.println("Syntax error!");
				}
			} else {
				System.out.println("Syntax error!");
			}
		}
		
		in.close();
		
	}

}
