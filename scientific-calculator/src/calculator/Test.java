package calculator;

public class Test {

	public static void main(String[] args) throws Exception {
		
		Validator2 val = new Validator2();
		Calculator3 calculator = new Calculator3();
		String expression = "sin(2.5)^(2*2)";
		
		if (val.isValidExpression(expression)) {
			System.out.println(calculator.solve(expression)); 		
		} else {
			System.out.println("Syntax error!");
		}

	}

}
