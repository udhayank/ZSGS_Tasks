package com.cricket;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

public class Game {
	
	String tossWonTeam;
	String tossLostTeam;
	int maxOvers = 2; 
	int targetScore;
	
	ScoreCard card = new ScoreCard();
	
	public boolean setMaxOvers(Scanner in) {
		System.out.print("Enter total number of overs (10 overs max): ");		
		int maxOvers = in.nextInt();
		if (maxOvers > 0 && maxOvers <= 10) {
			this.maxOvers = maxOvers;
			return true;
		} else {
			System.out.println("Invalid overs!");
			return false;
		}
	}
	
	public void toss(String team1, String team2) {
		this.tossWonTeam = Math.random() > 0.5 ? team1 : team2;
		System.out.println("Toss won by " + this.tossWonTeam);		 
		this.tossLostTeam = this.tossWonTeam == team1 ? team2 : team1;
	}
	
	public void askForChoice(Scanner in) {
		System.out.println("Choose for: \n1 - Batting \n2 - Fielding");
		System.out.print("Enter your choice: ");		
		int choice = in.nextInt();		
		if (choice == 1) {
			card.setBattingTeam(tossWonTeam);
			card.setFieldingTeam(tossLostTeam);
		} else {
			card.setBattingTeam(tossLostTeam);
			card.setFieldingTeam(tossWonTeam);
		}
	}
	
	public void printGameInstructions() {
		System.out.println("\nGame instructions: \nEnter each response such that, \n\t1-6: runs \n\t-1: out \n\t-2: wide \n\t-3: no ball \n");
		
	}
	
	public void playFirstInnings(Scanner in) {
		
		System.out.println("\nBatting team: " + card.getBattingTeam() + "\tFielding team: " + card.getFieldingTeam());
		
		List<Player> battingPlayers = Teams.getTeam(card.getBattingTeam());
		List<Player> bowlingPlayers = Teams.getTeam(card.getFieldingTeam());
		
		Player striker = battingPlayers.get(0);
		Player nonStriker = battingPlayers.get(1);
		Player bowler = bowlingPlayers.get(0);
		int nextBatsman = 2;
		int nextBowler = 1;
		
		boolean isNoBall = false;
		
		for (int over=1; over<=maxOvers; over++) {
			
			List<String> thisOver = new LinkedList<>();
			boolean isMaiden = true;
			
			for (int ball=1; ball<=6; ball++) {				
				
				System.out.print(String.format("Enter response for over %d ball %d: ", over, ball));
				int response = in.nextInt();				
				
				if (response > 6) {
					System.out.println("Invalid response!");
					ball--;
					continue;
				}
				
				if (!(response == 0 || response == -1)) {
					isMaiden = false;
				}
				
				if (response == -1) { // out
					if (!isNoBall) {
						card.addWicket();
						bowler.addWicket();
						thisOver.add("wkt");
						card.addBall();					
						striker.addRuns(0);
						striker = battingPlayers.get(nextBatsman++);
					} else {
						card.addBall();					
						striker.addRuns(0);
						thisOver.add("0");						
					}
					isNoBall = false;
					
				} else if (response == -2) { // wide
					card.addRuns(1);
					thisOver.add("1wd");
					ball--;
					isNoBall = false;
				} else if (response == -3) { // no ball
					card.addRuns(1);
					thisOver.add("1nb");
					ball--;
					isNoBall = true;
				} else {
					card.addRuns(response);
					card.addBall();
					striker.addRuns(response);
					bowler.addBowledRuns(response);
					thisOver.add(String.valueOf(response));
					if (response == 1 || response == 3) {
						// Swap strikers
						Player temp = striker;
						striker = nonStriker;
						nonStriker = temp;
					}
					isNoBall = false;
				}
				
				if (card.getWickets() > 9) {
					break;
				}
				
				card.printScore();				
				
			}
			
			if (isMaiden) {
				bowler.addMaiden();
			}
			
			if (card.getWickets() > 9) {
				break;
			}
			
			// Swap strikers
			Player temp = striker;
			striker = nonStriker;
			nonStriker = temp;
			
			// change bowler
			bowler = bowlingPlayers.get(nextBowler++);
			
			System.out.println("This over: " + thisOver);
			
		}
		
		this.targetScore = card.getRuns() + 1;
		
		System.out.println("End of first innings.\n");
		System.out.println(card.getBattingTeam() + " stats:");
		card.printBattingScoreCard();
		System.out.println(card.getFieldingTeam() + " stats:");
		card.printFieldingScoreCard();
		
	}
	
	// Second innings
	
	public void playSecondInnings(Scanner in) {
		
		// Swap batting team
		String tempTeam = card.getBattingTeam();
		card.setBattingTeam(card.getFieldingTeam());
		card.setFieldingTeam(tempTeam);
		
		card.resetScoreCard();
		
		System.out.println("\nBatting team: " + card.getBattingTeam() + "\tFielding team: " + card.getFieldingTeam());
		
		List<Player> battingPlayers = Teams.getTeam(card.getBattingTeam());
		List<Player> bowlingPlayers = Teams.getTeam(card.getFieldingTeam());
		
		Player striker = battingPlayers.get(0);
		Player nonStriker = battingPlayers.get(1);
		Player bowler = bowlingPlayers.get(0);
		int nextBatsman = 2;
		int nextBowler = 1;
		
		for (int over=1; over<=maxOvers; over++) {
			
			List<String> thisOver = new LinkedList<>();
			boolean isMaiden = true;
			
			for (int ball=1; ball<=6; ball++) {				
				
				System.out.print(String.format("Enter response for over %d ball %d: ", over, ball));
				int response = in.nextInt();				
				
				if (response > 6) {
					System.out.println("Invalid response!");
					ball--;
					continue;
				}
				
				if (!(response == 0 || response == -1)) {
					isMaiden = false;
				}
				
				if (response == -1) { // out
					card.addWicket();
					card.addBall();
					bowler.addWicket();
					striker.addRuns(0);
					thisOver.add("wkt");
					striker = battingPlayers.get(nextBatsman++);
				} else if (response == -2) { // wide
					card.addRuns(1);
					thisOver.add("1wd");
					ball--;
				} else if (response == -3) { // no ball
					card.addRuns(1);
					thisOver.add("1nb");
					ball--;
				} else {
					card.addRuns(response);
					card.addBall();
					striker.addRuns(response);
					bowler.addBowledRuns(response);
					thisOver.add(String.valueOf(response));
					if (response == 1 || response == 3) {
						// Swap strikers
						Player temp = striker;
						striker = nonStriker;
						nonStriker = temp;
					}
				}
				
				if (card.getWickets() > 9 || card.getRuns() >= targetScore) {
					break;
				}
				
				card.printScore();				
				
			}
			
			if (isMaiden) {
				bowler.addMaiden();
			}
			
			if (card.getWickets() > 9 || card.getRuns() >= targetScore) {
				break;
			}
			
			// Swap strikers
			Player temp = striker;
			striker = nonStriker;
			nonStriker = temp;
			
			// change bowler
			bowler = bowlingPlayers.get(nextBowler++);
			
			System.out.println("This over: " + thisOver);
			
		}
		
		String wonTeam = null;
		if (card.getRuns() >= targetScore) {
			wonTeam = card.getBattingTeam();
		} else if (card.getRuns() < targetScore-1) {
			wonTeam = card.getFieldingTeam();
		}
		
		System.out.println("End of match!\n");		
		System.out.println(card.getBattingTeam() + "stats:");
		card.printBattingScoreCard();
		System.out.println(card.getFieldingTeam() + "stats:");
		card.printFieldingScoreCard();
		
		if (wonTeam == null) {
			System.out.println("Match draw!");
		} else if (wonTeam == card.getBattingTeam()) {
			System.out.println(wonTeam + " won the match by " + (10-card.getWickets()) + " wickets");
		} else if (wonTeam == card.getFieldingTeam()) {
			System.out.println(wonTeam + " won the match by " + (targetScore - card.getRuns() - 1) + " runs");
		}
		
		
	}
		
	
}
