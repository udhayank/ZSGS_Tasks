package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;


public class DataBaseController {
	
	private Statement statement;
	
	public DataBaseController() throws SQLException {
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/courseManagementDB", "root", "password");
		statement = connection.createStatement();
		
	}
	
	public void printDeptList() throws SQLException {
		
		ResultSet result = statement.executeQuery("SELECT * FROM department;");
		printResult(result, 2);
		
	}
	
	public void printResult(ResultSet result, int noOfCol) throws SQLException {
		
		while (result.next()) {
			
			for (int i=0; i<noOfCol; i++) {
				System.out.print(result.getString(i+1));
				System.out.print("\t");
			}
			System.out.println();
			
		}
		
	}

	public int getNoOfDepartment() throws SQLException {
		ResultSet result = statement.executeQuery("SELECT count(id) FROM department;");
		result.next();
		return result.getInt(1);
	}

	public void printDeptCourse(int department) throws SQLException {
		
		String query = "SELECT id, courseName, semester, startTime, endTime FROM course WHERE (department = "+ department +")";
		ResultSet result = statement.executeQuery(query);
		
		while (result.next()) {
			for (int i=0; i<5; i++) {
				System.out.print(result.getString(i+1));
				System.out.print("\t");
				if (i==0 || i==2) {
					System.out.print("\t");					
				}
			}
			System.out.println();
		}
		
	}
	

	
}
