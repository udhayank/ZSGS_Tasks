package com.personalExpenseTracker;

import java.sql.SQLException;

public class Test {

	public static void main(String[] args) throws SQLException {
		
		DataBaseModule db = new DataBaseModule();
		
		db.addExpense(120, 1, "2022-09-09", "lunch");
		
		
		

	}

}
