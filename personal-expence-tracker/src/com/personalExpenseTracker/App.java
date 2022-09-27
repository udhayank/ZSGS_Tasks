package com.personalExpenseTracker;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;

public class App {
	
	private DataBaseModule db;
	
	public App() throws SQLException {
		db = new DataBaseModule();
	}

	public static void main(String[] args) {
		
		Scanner in = new Scanner(System.in);
		
		try {
			
			App app = new App();
			
			app.printCurrentStats();
			app.printMainMenu(in, app);
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		}	
		
		in.close();

	}
	
	public void printMainMenu(Scanner in, App app) throws SQLException {
		
		int choice = 0;
		System.out.println("\n-------------------------");
		System.out.println("1 - Add expense \n2 - Add income \n3 - Show stats \n4 - Show records");
		while (true) {
			System.out.print("Enter choice: ");
			choice = in.nextInt();
			if (choice >=1 && choice <= 4) {
				break;
			} else {
				System.out.println("Invalid choice!");
			}
		}
		
		switch (choice) {
			case (1):
				app.addExpense(in, app);
				break;
			
			case (2):
				app.addIncome(in, app);
				break;
			
			case (3):
				app.printStatsMenu(in, app);
				break;
			
			case(4):
				app.printRecordsMenu(in, app);
				break;		
		}
		
	}
	
	public void printCurrentStats() throws SQLException {
		
		int last30DaysIncome = db.getLast30DaysIncome();
		int last30DaysExpense = db.getLast30DaysExpense();
		
		int currBalance = db.getTotalIncome() - db.getTotalExpense();
		
		System.out.println("---------------------------------");
		System.out.println("30 days income: " + last30DaysIncome);
		System.out.println("30 days expense: " + last30DaysExpense);
		System.out.println("Current balance: " + currBalance);
		System.out.println("---------------------------------");	
		
	}
	
	public void printCategoryStats() throws SQLException {
		
		String query = "SELECT categories.category_name, SUM(expense.amount) from expense INNER JOIN categories ON categories.id = expense.category\r\n"
				+ "GROUP BY category_name;";
		ResultSet result = db.getFromDB(query);
		System.out.println("\n-----------------------------");
		System.out.println("CATEGORY  \tAMOUNT");
		System.out.println("-----------------------------");
		while (result.next()) {
			System.out.println(result.getString(1) + "\t-  " + result.getInt(2));
		}
		
	}
	
	public void printStatsMenu(Scanner in, App app) throws SQLException {
		
		System.out.println("\n---------------------------------");
		System.out.println("1 - Quick stats");
		System.out.println("2 - Category wise expense");
		System.out.println("3 - Main menu");
		System.out.println();
		int choice = 0;
		while (true) {
			System.out.print("Enter choice: ");
			choice = in.nextInt();
			if (choice >= 1 && choice <=3) {
				break;
			} else {
				System.out.println("Invalid choice!");
			}
		}
		
		switch (choice) {
		case (1):
			app.printCurrentStats();
			app.printMainMenu(in, app);
			break;
			
		case (2):
			app.printCategoryStats();
			app.printMainMenu(in, app);
			break;
		
		case (3):
			app.printMainMenu(in, app);
			break;
		}
		
	}
	
	public void printRecordsMenu(Scanner in, App app) throws SQLException {
		
		System.out.println("\n-------------------------");
		System.out.println("1 - Expense records");
		System.out.println("2 - Income records");
		System.out.println("3 - Main menu");
		int choice = 0;
		while (true) {
			System.out.print("Enter choice: ");
			choice = in.nextInt();
			if (choice >= 1 && choice <= 3) {
				break;
			} else {
				System.out.println("Invalid choice!");
			}
		}
		
		if (choice == 1) {
			app.printExpenseRecordsMenu(in, app);
		} else if (choice == 2) {
			app.printIncomeRecordsMenu(in, app);
		} else if (choice == 3) {
			app.printMainMenu(in, app);
		}
		
	}
	
	private void printExpenseRecordsMenu(Scanner in, App app) throws SQLException {
		
		int choice = 0;
		System.out.println("\n-------------------------");
		System.out.println("\t1 - all expense records");
		System.out.println("\t2 - last 7 days expense records");
		System.out.println("\t3 - last 30 days expense records");
		System.out.println("\t4 - custom range expense records");
		System.out.println();
		
		while (true) {
			System.out.print("Enter choice: ");
			choice = in.nextInt();
			if (choice >= 1 && choice <= 4) {
				break;
			} else {
				System.out.println("Invalid choice!");
			}
		}
		
		switch (choice) {
		case (1):
			app.printAllExpense();
			break;
		
		case (2):
			app.printLast7DaysExpense();
			break;
			
		case (3):
			app.printLast30DaysExpense();
			break;
			
		case (4):
			
			System.out.println("\n-------------------------");
			System.out.print("Enter from date in (yyyy-mm-dd) format: ");
			String from = in.next();
			System.out.print("Enter to date int (yyyy-mm-dd) format: ");
			String to = in.next();
			
			if(!app.printCustomRangeExpense(from, to)){
				app.printExpenseRecordsMenu(in, app);
			} else {
				break;
			}
			break;
		}
		
		app.printMainMenu(in, app);
		
	}
	
	private void printIncomeRecordsMenu(Scanner in, App app) throws SQLException {
		
		int choice = 0;
		System.out.println("\n-------------------------");
		System.out.println("\t1 - all income records");
		System.out.println("\t2 - last 7 days income records");
		System.out.println("\t3 - last 30 days income records");
		System.out.println("\t4 - custom range income records");
		System.out.println();
		
		while (true) {
			System.out.print("Enter choice: ");
			choice = in.nextInt();
			if (choice >= 1 && choice <= 4) {
				break;
			} else {
				System.out.println("Invalid choice!");
			}
		}
		
		switch (choice) {
		case (1):
			app.printAllIncome();
			break;
		
		case (2):
			app.printLast7DaysIncome();
		break;
		
		case (3):
			app.printLast30DaysIncome();
		break;
		
		case (4):
			
			System.out.println("\n-------------------------");
			System.out.print("Enter from date in (yyyy-mm-dd) format: ");
			String from = in.next();
			System.out.print("Enter to date int (yyyy-mm-dd) format: ");
			String to = in.next();
		
			if(!app.printCustomRangeIncome(from, to)){
				app.printExpenseRecordsMenu(in, app);
			} else {
				break;
			}
		break;
		}
		
		app.printMainMenu(in, app);
		
	}
	
	public void printAllExpense() throws SQLException {		
		
		String query = "SELECT date_of_expense, amount, category_name, note FROM expense INNER JOIN categories ON (expense.category = categories.id);";
		ResultSet result = db.getFromDB(query);
		printResult(result, EntryType.EXPENSE);
		
	}
	
	public void printAllIncome() throws SQLException {		
		
		String query = "SELECT date_of_income, amount, note FROM income;";
		ResultSet result = db.getFromDB(query);
		printResult(result, EntryType.INCOME);
		
	}
	
	public void printLast30DaysExpense() throws SQLException {		
		printLastNDaysEntry(30, EntryType.EXPENSE);		
	}
	
	public void printLast30DaysIncome() throws SQLException {
		printLastNDaysEntry(30, EntryType.INCOME);
	}
	
	public void printLast7DaysExpense() throws SQLException {		
		printLastNDaysEntry(7, EntryType.EXPENSE);		
	}
	
	public void printLast7DaysIncome() throws SQLException {
		printLastNDaysEntry(7, EntryType.INCOME);
	}
	
	public boolean printCustomRangeExpense(String from, String to) throws SQLException {
		return printCustomRangeEntry(from, to, EntryType.EXPENSE);
	}
	
	public boolean printCustomRangeIncome(String from, String to) throws SQLException {
		return printCustomRangeEntry(from, to, EntryType.INCOME);
	}
	
	private boolean printCustomRangeEntry(String from, String to, EntryType entryType) throws SQLException {
		
		try {
			from = LocalDate.parse(from).toString();
			to = LocalDate.parse(to).toString();			
		} catch (DateTimeParseException e) {
			System.out.println("Not valid date");
			return false;
		}
		
		String query = "";
		
		if (entryType == EntryType.EXPENSE) {
			query = "SELECT date_of_"+ entryType.name().toLowerCase() +", amount, category_name, note FROM "+ entryType.name().toLowerCase() +" INNER JOIN categories ON "+ entryType.name().toLowerCase() +".category = categories.id "
					+ String.format("WHERE (date_of_"+ entryType.name().toLowerCase() +" BETWEEN \"%s\" AND \"%s\");", from, to);
		} else if (entryType == EntryType.INCOME) {
			query = "SELECT date_of_"+ entryType.name().toLowerCase() +", amount, note FROM "+ entryType.name().toLowerCase() 
					+ String.format(" WHERE (date_of_"+ entryType.name().toLowerCase() +" BETWEEN \"%s\" AND \"%s\");", from, to);
		}
		
		ResultSet result = db.getFromDB(query);
		
		printResult(result, entryType);
		
		return true;
		
	}
	
	
	private void printLastNDaysEntry(int n, EntryType entryType) throws SQLException {
		
		String toDate = LocalDate.now().toString();
		String fromDate = LocalDate.now().minusDays(n).toString();
		
		String query = "";
		
		if (entryType == EntryType.EXPENSE) {
			query = "SELECT date_of_"+ entryType.name().toLowerCase() +", amount, category_name, note FROM "+ entryType.name().toLowerCase() +" INNER JOIN categories ON "+ entryType.name().toLowerCase() +".category = categories.id "
					+ String.format("WHERE (date_of_"+ entryType.name().toLowerCase() +" BETWEEN \"%s\" AND \"%s\");", fromDate, toDate);			
		} else if (entryType == EntryType.INCOME) {
			query = "SELECT date_of_"+ entryType.name().toLowerCase() +", amount, note FROM "+ entryType.name().toLowerCase() 
					+ String.format(" WHERE (date_of_"+ entryType.name().toLowerCase() +" BETWEEN \"%s\" AND \"%s\");", fromDate, toDate);
		}

											
		ResultSet result = db.getFromDB(query);
		
		printResult(result, entryType);
		
	}
	
	private void printResult(ResultSet result, EntryType entryType) throws SQLException {
		if (entryType == EntryType.EXPENSE) {
			System.out.println("DATE \t\tAMOUNT \tCATEGORY \t\tNOTE");
			System.out.println("----------------------------------------------------");
			
			while (result.next()) {
				System.out.println(result.getString(1) + "\t" + result.getString(2) + "\t" + result.getString(3) + "\t\t" + result.getString(4) );
			}
		} else if (entryType == EntryType.INCOME) {
			System.out.println("DATE \t\tAMOUNT \tNOTE");
			System.out.println("----------------------------------------");
			
			while (result.next()) {
				System.out.println(result.getString(1) + "\t" + result.getString(2) + "\t" + result.getString(3) );
			}
		}
		System.out.println();
	}
	
	public void addExpense(Scanner in, App app) throws SQLException {
		
		int amount = -1;
		while (true) {
			System.out.print("Enter amount: ");
			amount = in.nextInt();
			if (amount > 0) {
				break;
			} else {
				System.out.println("Invalid amount!");
			}
		}		
		
		System.out.println("Select category:");
		printCategories();
		int category = 0;
		while (true) {
			System.out.print("Enter choice: ");
			category = in.nextInt();
			if (category >=1 && category <= 4) {
				break;
			} else {
				System.out.println("Invalid category!");
			}
		}
		
		String date = "";
		while (true) {
			System.out.print("Enter date in (yyyy-mm-dd) format (enter today for current date): ");
			String input = in.next();
			if (input.equals("today")) {
				date = LocalDate.now().toString();
				System.out.println(date);
			} else {
				date = input;
			}
			
			System.out.print("Enter note (optional): ");
			String note = in.next();
			
			// Store in database
			if (db.addExpense(amount, category, date, note)) {
				System.out.println("Data added successfully!");
				break;
			} else {
				System.out.println("Invalid date!");
			}
		}
		
		app.printCurrentStats();
		app.printMainMenu(in, app);
		
	}
	
	public void addIncome(Scanner in, App app) throws SQLException {
		
		int amount = -1;
		while (true) {
			System.out.print("Enter amount: ");
			amount = in.nextInt();
			if (amount > 0) {
				break;
			} else {
				System.out.println("Invalid amount!");
			}
		}
		
		String date = "";
		while (true) {
			System.out.print("Enter date in (yyyy-mm-dd) format (enter today for current date): ");
			String input = in.next();
			if (input.equals("today")) {
				date = LocalDate.now().toString();
				System.out.println(date);
			} else {
				date = input;
			}
			
			System.out.print("Enter note (optional): ");
			String note = in.next();
			
			// Store in database
			if (db.addIncome(amount, date, note)) {
				System.out.println("Data added successfully!");
				break;
			} else {
				System.out.println("Invalid date!");
			}
		}
		
		app.printCurrentStats();
		app.printMainMenu(in, app);
		
	}
	
	private void printCategories() throws SQLException {
		ResultSet result = db.getCategories();
		while (result.next()){
			System.out.println("\t" + result.getInt(1) + " - " + result.getString(2));
		}
	}

}

enum EntryType {
	EXPENSE, INCOME
}
