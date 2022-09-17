package com.cricket;

public class Player {

	private String name;

	public Player(String name) {
		if (name.length() > 30) {
			name = name.substring(0,31);
		}
		if (name.length() < 30) {
			for (int i=name.length(); i<21; i++) {
				name += "\s";
			}			
		}
		this.name = name;
	}

	public String getName() {
		return name;
	}

	private int[] battingStats = new int[4]; // runs, balls, fours, sixes

	public void addRuns(int runs) {
		this.battingStats[0] += runs;
		this.battingStats[1] += 1;
		if (runs == 4) {
			this.battingStats[2]++;
		} else if (runs == 6) {
			this.battingStats[3]++;
		}
	}

	public int getRuns() {
		return this.battingStats[0];
	}

	public int getBalls() {
		return this.battingStats[1];
	}

	public int getFours() {
		return battingStats[2];
	}

	public int getSixes() {
		return battingStats[3];
	}

	public double getStrikeRate() {
		if (getRuns() == 0) return 0;
		return (getRuns() / (double) getBalls()) * 100;
	}

	private int[] bowlingStats = new int[4]; // balls, runs, maidenOvers, wickets

	public void addBowledRuns(int runs) {
		this.bowlingStats[1] += runs;
		this.bowlingStats[0]++;
	}

	public void addWicket() {
		this.bowlingStats[3]++;
		this.bowlingStats[0]++;
	}
	
	public void addMaiden() {
		this.bowlingStats[2]++;
	}

	public int getBowledBalls() {
		return bowlingStats[0];
	}

	public int getBowledRuns() {
		return bowlingStats[1];
	}

	public int getMaidenOvers() {
		return bowlingStats[2];
	}

	public int getWickets() {
		return bowlingStats[3];
	}

	public double getEconomyRate() {
		if (getBowledBalls() == 0) return 0;
		return (getBowledRuns() / (getBowledBalls()) / 6.0);
	}

}
