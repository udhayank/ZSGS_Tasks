package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentController {
	
	private Statement statement;
	
	public StudentController() throws SQLException {
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/courseManagementDB", "root", "password");
		statement = connection.createStatement();
		
	}

	public boolean isValidCredentials(int rollNo, String password) {
		
		try {
			
			String query = "SELECT password FROM student WHERE (rollNo = "+ rollNo +")";
			ResultSet result = statement.executeQuery(query);
			if (result.next()) {
				if (password.equals(result.getString(1))) {
					return true;
				}
			}
			
		} catch (SQLException e) {
			return false;
		}
		
		return false;
	}

	public void addCourse(int rollNo, int courseId) throws SQLException {
		
		String query = "INSERT INTO studenttocourse (student, course) VALUES ("+ rollNo + "," + courseId + ")";
		statement.executeUpdate(query);
		
	}

	public void printMyCourses(int rollNo) throws SQLException {
		
		String query = "SELECT course.id, courseName, course.semester, startTime, endTime FROM studenttocourse "
				+ "INNER JOIN course ON studenttocourse.course = course.id "
				+ "WHERE (student = "+ rollNo +")";
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

	public void deleteCourse(int rollNo, int courseId) throws SQLException {
		
		String query = "SELECT id FROM studenttocourse WHERE (course = "+ courseId +")";
		ResultSet r = statement.executeQuery(query);
		r.next();
		int id = r.getInt(1);
		
		query = "DELETE * FROM studenttocourse WHERE (id = "+ id +")";
		statement.executeUpdate(query);
		
	}

}
