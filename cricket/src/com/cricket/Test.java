package com.cricket;

import java.util.Arrays;
import java.util.Scanner;

public class Test {

	public static void main(String[] args) {
		
		Scanner scanner = new Scanner(System.in);
		
//		System.out.println(Arrays.toString(Teams.getTeamsList()));
//		System.out.println(Teams.getTeam("India"));
		
//		ScoreCard card = new ScoreCard();
//		card.setBattingTeam("India");
//		card.printBattingScoreCard();
//		
//		card.setFieldingTeam("Australia");
//		card.printFieldingScoreCard();
		
//		card.printScore();
//		System.out.println(Teams.getTeam("India").get(0).getName() + "a");
		
		Game game = new Game();
		game.toss("India", "Australia");
		game.askForChoice(scanner);
		game.playFirstInnings(scanner);
		
		scanner.close();
			
	}

}
