package com.cricket;

import java.util.Scanner;

public class App {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
		Game game = new Game();
		while(!game.setMaxOvers(scanner)) {}		
		game.toss("India", "Australia");
		game.askForChoice(scanner);
		game.printGameInstructions();
		game.playFirstInnings(scanner);
		game.playSecondInnings(scanner);
		
		scanner.close();

	}

}
