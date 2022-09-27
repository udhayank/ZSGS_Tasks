package com.personalExpenseTracker;

import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Connection;
import java.sql.Statement;
import java.time.LocalDate;

public class DataBaseModule {
	
	private Statement statement;
	
	public DataBaseModule() throws SQLException {
		
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/personalExpenceTrackerDB", "root", "password");
		statement = connection.createStatement();
		System.out.println("Database connected successsfully!");
		
	}
	
	public boolean addExpense(int amount, int category, String date, String note) {
		
		String query = String.format("INSERT INTO expense (amount, category, date_of_expense, note) VALUES (%d, %d, \"%s\", \"%s\")", amount, category, date, note); 
		
		try {
			statement.executeUpdate(query);
			return true;
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean addIncome(int amount, String date, String note) {
		
		String query = String.format("INSERT INTO income (amount, date_of_income, note) VALUES (%d, \"%s\", \"%s\")", amount, date, note); 
		
		try {
			statement.executeUpdate(query);
			return true;
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean deleteExpene(int id) {
		
		String query = String.format("DELETE FROM expense WHERE id = %d", id);
		
		try {
			statement.executeQuery(query);
			return true;
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean deleteIncome(int id) {
		
		String query = String.format("DELETE FROM income WHERE id = %d", id);
		
		try {
			statement.executeQuery(query);
			return true;
		} catch (SQLException e) {			
			e.printStackTrace();
			return false;
		}
		
	}
	
	public ResultSet getFromDB(String query) throws SQLException {		
		
		ResultSet result = statement.executeQuery(query);
		return result;
		
	}
	
	public int getTotalExpense() throws SQLException {		
		ResultSet result = statement.executeQuery("SELECT SUM(amount) FROM expense;");
		result.next();
		return result.getInt(1);		
	}
	
	public int getTotalIncome() throws SQLException {		
		ResultSet result = statement.executeQuery("SELECT SUM(amount) FROM income;");	
		result.next();
		return result.getInt(1);		
	}
	
	public int getLast30DaysExpense() throws SQLException {
		
		String from = LocalDate.now().minusDays(30).toString();
		String to = LocalDate.now().toString();
		
		String query = String.format("SELECT SUM(amount) FROM expense WHERE (date_of_expense BETWEEN \"%s\" AND \"%s\");", from, to);
		ResultSet result = statement.executeQuery(query);	
		result.next();
		return result.getInt(1);
		
	}
	
	public int getLast30DaysIncome() throws SQLException {
		
		String from = LocalDate.now().minusDays(30).toString();
		String to = LocalDate.now().toString();
		
		String query = String.format("SELECT SUM(amount) FROM income WHERE (date_of_income BETWEEN \"%s\" AND \"%s\");", from, to);
		ResultSet result = statement.executeQuery(query);
		result.next();
		return result.getInt(1);
		
	}
	
	public ResultSet getCategories() throws SQLException {
		
		String query = "SELECT * FROM categories;";
		ResultSet result = statement.executeQuery(query);
		return result;
		
	}
	
}
