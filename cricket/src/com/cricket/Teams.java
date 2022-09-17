package com.cricket;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;

public class Teams {
	
	private static HashMap<String, List<Player>> teams = new HashMap<>();
	
	static {
		
		String teamName = "India";
		String[] playerNames = {"KL Rahul", "Rishabh Pant", "Virat Kohli", "Suryakumar Yadav", "Deepak Hooda", "Dinesh Karthik", "Axar Patel", "Ravichandran Ashwin", "Bhuvneshwar Kumar", "Deepak Chahar", "Arshdeep Singh"};
		List<Player> players = new LinkedList<>();
		for (String name:playerNames) {
			players.add(new Player(name));
		}
		Teams.addTeam(teamName, players);
		
		teamName = "Australia";
		playerNames = new String[] {"Aaron Finch", "Pat Cummins", "Ashton Agar", "Tim David", "Josh Hazlewood", "Josh Inglis", "Mitchell Marsh", "Glenn Maxwell", "Kane Richardson", "Steve Smith", "Mitchell Starc"};
		players = new LinkedList<>();
		for (String name:playerNames) {
			players.add(new Player(name));
		}
		Teams.addTeam(teamName, players);
		
	}

	public static boolean addTeam(String name, List<Player> players) {
		if (players.size() == 11) {
			if (teams.put(name, players) == null) {
//				System.out.println("New team added!");
				return true;
			}
			System.out.println("Team replaced!");
			return true;
		}
		System.err.println("Not valid player list!");
		return false;
	}
	
	public static boolean replacePlayer(String teamName, Player oldPlayer, Player newPlayer) {
		if (teams.containsKey(teamName)) {
			List<Player> players = teams.get(teamName);			
			int playerIndex = searchPlayer(teamName, oldPlayer.getName());
			if (playerIndex < 0) {
				System.err.println("Player not found!");
				return false;
			}
			players.set(playerIndex, newPlayer);
			System.out.println("Player replaced successfully!");
			return true;
		}
		System.err.println("Team not found!");
		return false;
	}
	
	public static int searchPlayer(String teamName, String name) {
		List<Player> team = teams.get(teamName);
		for (int i=0; i<team.size(); i++) {
			if (name == team.get(i).getName()) {
				return i;
			}
		}
		return -1;
	}
	
	public static List<Player> getTeam(String name) {
		return teams.get(name);
	}
	
	public static String[] getTeamsList() {
		String[] teamsList = new String[teams.size()];
		int i = 0;
		for (String team:teams.keySet()) {
			teamsList[i++] = team;			
		}
		return teamsList;
	}

}
