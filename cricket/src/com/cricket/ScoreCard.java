package com.cricket;

import java.util.List;

public class ScoreCard {

	private String battingTeam;
	private String fieldingTeam;
	
	public String getBattingTeam() {
		return battingTeam;
	}
	public void setBattingTeam(String battingTeam) {
		this.battingTeam = battingTeam;
	}
	public String getFieldingTeam() {
		return fieldingTeam;
	}
	public void setFieldingTeam(String fieldingTeam) {
		this.fieldingTeam = fieldingTeam;
	}
	
	private int runs;
	private int balls;
	private int overs;
	private int wickets;
	
	
	public int getRuns() {
		return runs;
	}
	public int getBalls() {
		return balls;
	}
	public int getOvers() {
		return overs;
	}
	public int getWickets() {
		return wickets;
	}
	
	public void addRuns(int runs) {
		this.runs += runs;
	}	
	public void addBall() {
		this.balls++;
	}	
	public void addOver() {
		this.overs++;
	}	
	public void addWicket() {
		this.wickets++;
	}
	
	public void printScore() {
		System.out.println(String.format("%d / %d (%d.%d)", getRuns(), getWickets(), (int) getBalls()/6, getBalls()%6));
	}
	
	public void printBattingScoreCard() {
		System.out.println("PLAYERS\t\t\t RUNS\t BALLS\t 4s\t 6s\t SR");
		List<Player> players = Teams.getTeam(battingTeam);
		for (Player player:players) {
			System.out.println(String.format("%s\t %d\t %d\t %d\t %d\t %f", player.getName(), player.getRuns(), player.getBalls(), player.getFours(), player.getSixes(), player.getStrikeRate()));
		}
		System.out.println();
	}
	
	public void printFieldingScoreCard() {
		System.out.println("PLAYERS\t\t\t OVER\t MAIDEN\t RUNS\t WKTS\t ER");
		List<Player> players = Teams.getTeam(fieldingTeam);
		for (Player player:players) {
			System.out.println(String.format("%s\t %d\t %d\t %d\t %d\t %f", player.getName(), player.getBowledBalls()/6, player.getMaidenOvers(), player.getBowledRuns(), player.getWickets(), player.getEconomyRate()));
		}
		System.out.println();
	}
	
	public void resetScoreCard() {
		this.runs = 0;
		this.balls = 0;
		this.wickets = 0;
		this.overs = 0;
	}
	
	
}
