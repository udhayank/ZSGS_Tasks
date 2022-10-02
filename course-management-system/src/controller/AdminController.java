package controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import model.Course;
import model.Student;
import model.Tutor;

public class AdminController {
	
	private Statement statement;
	
	public AdminController() throws SQLException {
		
		Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/courseManagementDB", "root", "password");
		statement = connection.createStatement();
		
	}

	public boolean isValidCredentials(String password) {
		if (password.equals("password")) {
			return true;
		}
		return false;
	}
	
	public void addCourse(Course course) throws SQLException {
		
		String query = String.format("INSERT INTO course (courseName, department, semester, startTime, endTime) VALUES (\"%s\", %d, %d, \"%s\", \"%s\")"
				, course.getCourseName(), course.getDepartment(), course.getSemester(), course.getStartTime().toString(), course.getEndTime().toString());
		
		statement.executeUpdate(query);
		
	}

	public void printCourseList() throws SQLException {
		
		String query = "SELECT courseName, deptName, semester, startTime, endTime FROM course INNER JOIN department ON course.department = department.id;";
		ResultSet result = statement.executeQuery(query);
		
		while (result.next()) {			
			for (int i=0; i<5; i++) {
				System.out.print(result.getString(i+1));
				System.out.print("\t");
				if (i==1 || i==2) {
					System.out.print("\t");
				}
			}
			System.out.println();			
		}
	}
	
	public void printStudentList() throws SQLException {
		String query = "SELECT name, deptName, semester, credits FROM student INNER JOIN department ON student.department = department.id;";
		ResultSet result = statement.executeQuery(query);
		
		while (result.next()) {			
			for (int i=0; i<4; i++) {
				System.out.print(result.getString(i+1));
				System.out.print("\t");
				if (i==0 || i==1 || i==2) {
					System.out.print("\t");
				}
			}
			System.out.println();			
		}
	}

	public void addStudent(Student student) throws SQLException {
		
		String query = String.format("INSERT INTO student (name, semester, department, email) VALUES (\"%s\", %d, %d, \"%s\");"
							, student.getName(), student.getSemester(), student.getDepartment(), student.getEmail());
		statement.executeUpdate(query);
	}

	public void addTutor(Tutor tutor) throws SQLException {
		
		String query = String.format("INSERT INTO tutor (name, department, email) VALUES (\"%s\", %d, \"%s\");"
				, tutor.getName(), tutor.getDepartment(), tutor.getEmail());
		statement.executeUpdate(query);
		
	}

	public void printTutorList() throws SQLException {
		
		String query = "SELECT tutor.id, name, deptName, email FROM tutor INNER JOIN department ON tutor.department = department.id;";
		ResultSet result = statement.executeQuery(query);
		
		while (result.next()) {			
			for (int i=0; i<4; i++) {
				System.out.print(result.getString(i+1));
				System.out.print("\t");
				if (i==0 || i==1) {
					System.out.print("\t");
				}
			}
			System.out.println();			
		}
		
	}

	
}
