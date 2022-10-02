package view;

import java.sql.SQLException;
import java.util.Scanner;

import controller.DataBaseController;
import controller.StudentController;

public class StudentView {

	Scanner in;
	StudentController studentController;
	DataBaseController db;
	
	public StudentView() throws SQLException {
		in = new Scanner(System.in);
		studentController = new StudentController();
		db = new DataBaseController();
	}
	
	public void studentLogin() throws SQLException {
		System.out.println("\n-----------------------------------\n");
		int rollNo;
		while (true) {
			System.out.print("\nEnter roll number: ");
			rollNo = in.nextInt();
			System.out.print("Enter password: ");
			String password = in.next();		
			
			if (studentController.isValidCredentials(rollNo, password)) {				
				break;
			} else {
				System.out.println("Invalid credentials!");
			}
		}
		printMenu(rollNo);
	}

	public void printMenu(int rollNo) throws SQLException {
		
		System.out.println("\n-----------------------------------\n");
		System.out.println("1 - Enroll course");
		System.out.println("2 - Show my course list");
		System.out.println("3 - Exit course");
		System.out.println("4 - Sign out");
		
		int choice;
		while (true) {
			System.out.print("\nEnter choice: ");
			choice = in.nextInt();
			if (choice >= 1 && choice <= 4) {
				break;
			} else {
				System.out.println("Invalid choice!");
			}
		}
		
		switch (choice) {
		case 1:
			enrollCourse(rollNo);
			printMenu(rollNo);
			break;
			
		case 2:
			showMyCourseList(rollNo);
			printMenu(rollNo);
			break;
			
		case 3:
			showMyCourseList(rollNo);
			exitCourse(rollNo);
			break;
			
		case 4:
			new MainMenu().printMainMenu();
			break;			
		}
		
	}
	
	
	private void enrollCourse(int rollNo) throws SQLException {
		
		System.out.println("\n-----------------------------------\n");
		System.out.println("Choose department:");
		db.printDeptList();
		
		int choice;
		while (true) {
			System.out.println("\nEnter choice:");
			choice = in.nextInt();
			if (choice >= 1 && choice <= db.getNoOfDepartment()) {
				break;
			} else {
				System.out.println("Invalid choice!");
			}
		}
		
		chooseCourse(rollNo, choice);
		
	}
	
	private void showMyCourseList(int rollNo) throws SQLException {
		
		System.out.println("\nCourse id \tCourse name \tSemester \tStart time \tEnd time");
		studentController.printMyCourses(rollNo);
		
	}
	
	private void exitCourse(int rollNo) {
		
		System.out.print("\nEnter course id: ");
		int courseId = in.nextInt();
		studentController.deleteCourse(rollNo, courseId);
		
	}
	
	private void chooseCourse(int rollNo, int department) throws SQLException {
		
		System.out.println("Available courses:");
		
		System.out.println("\nCourse id \tCourse name \tSemester \tStart time \tEnd time");
		db.printDeptCourse(department);
		
		System.out.print("\nChoose course: ");
		int choice = in.nextInt();
		
		studentController.addCourse(rollNo, choice);
		System.out.println("Course added successfully!");
		
	}
	
}
