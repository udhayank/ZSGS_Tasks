package view;

import java.sql.SQLException;
import java.time.LocalTime;
import java.util.Scanner;

import controller.AdminController;
import controller.DataBaseController;
import model.Course;
import model.Student;
import model.Tutor;

public class AdminView {
	
	Scanner in;
	AdminController adminController;
	DataBaseController db;
	
	public AdminView() throws SQLException {
		in = new Scanner(System.in);
		adminController = new AdminController();
		db = new DataBaseController();
	}
	
	public void adminLogin() throws SQLException {
		System.out.println("\n-----------------------------------\n");
		System.out.println("Hello admin!");
		while (true) {
			System.out.print("\nEnter admin password: ");
			String password = in.next();		
			
			if (adminController.isValidCredentials(password)) {				
				break;
			} else {
				System.out.println("Invalid credentials!");
			}
		}
		printMenu();
	}

	public void printMenu() throws SQLException {
		System.out.println("\n-----------------------------------\n");
		System.out.println("1 - view course list");
		System.out.println("2 - add course");
		System.out.println("3 - view student list");
		System.out.println("4 - add student");
		System.out.println("5 - view tutor list");
		System.out.println("6 - add tutor");
		System.out.println("7 - sign out");
		
		int choice = 0;
		while(true) {
			System.out.println("\nEnter your choice: ");
			choice = in.nextInt();
			if (choice >= 1 && choice <= 7) {
				break;
			} else {
				System.out.println("Invalid choice!");
			}
		}
		
		switch (choice) {
		case 1:
			printCourseList();
			printMenu();
			break;
			
		case 2:
			addCourse();
			printMenu();
			break;
			
		case 3:
			printStudentList();
			printMenu();
			break;
			
		case 4:
			addStudent();
			printMenu();
			break;
			
		case 5:
			printTutorList();
			printMenu();
			break;
			
		case 6:
			addTutor();
			printMenu();
			break;
			
		case 7:
			System.out.println("\n-----------------------------------\n");
			new MainMenu().printMainMenu();
			break;
			
		}
	}

	public void printCourseList() throws SQLException {
		System.out.println("\n----------------------------------------------------------------------------");
		System.out.println("Course name \t\tDepartment \tSemester \tStart \t\tEnd");
		System.out.println("----------------------------------------------------------------------------");
		adminController.printCourseList();		
	}

	public void addCourse() throws SQLException {
		
		String courseName = inputCourseName();		
		int department = inputDepartment();
		int semester = inputSemester();
		LocalTime startTime = inputStartTime();
		LocalTime endTime = inputEndTime();
		
		Course course = new Course(courseName);
		course.setDepartment(department);
		course.setSemester(semester);
		course.setStartTime(startTime);
		course.setEndTime(endTime);
		
		adminController.addCourse(course);
		
	}
	
	private void printStudentList() throws SQLException {		
		System.out.println("\n----------------------------------------------------------------------------");
		System.out.println("Student name \tDepartment \tSemester \tCredits");
		System.out.println("----------------------------------------------------------------------------");
		adminController.printStudentList();			
	}
	
	public void addStudent() throws SQLException {
		String studentName = inputStudentName();
		int semester = inputSemester();
		int department = inputDepartment();
		String email = inputEmail();
		
		Student student = new Student(studentName);
		student.setSemester(semester);
		student.setDepartment(department);
		student.setEmail(email);
		
		adminController.addStudent(student);
	}
	
	private void printTutorList() throws SQLException {
		System.out.println("\n----------------------------------------------------------------------------");
		System.out.println("Tutor ID \tTutor name \tDepartment \tEmail");
		System.out.println("----------------------------------------------------------------------------");
		adminController.printTutorList();			
	}
	
	private void addTutor() throws SQLException {
		String name = inputTutorName();
		int department = inputDepartment();
		String email = inputEmail();
		
		Tutor tutor = new Tutor(name);
		tutor.setEmail(email);
		tutor.setDepartment(department);
		
		adminController.addTutor(tutor);
	}
	
	

	private String inputEmail() {
		String email;
		System.out.print("Enter email: ");
		in.nextLine();
		email = in.nextLine();
		return email;
	}

	private String inputTutorName() {
		System.out.println("\n-----------------------------------\n");
		System.out.print("Enter tutor name: ");
		in.nextLine();
		return in.nextLine();
	}
	
	private String inputStudentName() {
		System.out.println("\n-----------------------------------\n");
		System.out.print("Enter student name: ");
		in.nextLine();
		return in.nextLine();
	}

	private String inputCourseName() {
		System.out.println("\n-----------------------------------\n");
		System.out.print("Enter course name: ");
		in.nextLine();
		return in.nextLine();
	}
	
	private int inputDepartment() throws SQLException {
		int department;
		System.out.println("\nDepartments:");
		db.printDeptList();
		while (true) {
			System.out.print("\nChoose department: ");
			department = in.nextInt();
			if  (department >= 1 && department <= db.getNoOfDepartment()) {
				break;
			}
		}
		return department;
	}
	
	private int inputSemester() {
		int semester;
		System.out.println("\nChoose semester: ");
		System.out.println("1 - odd");
		System.out.println("2 - even");
		while (true) {
			System.out.print("Enter choise: ");
			semester = in.nextInt();
			if  (semester >= 1 && semester <= 2) {
				break;
			}
		}
		return semester;
	}
	
	private LocalTime inputStartTime() {
		LocalTime startTime;
		System.out.print("Enter start time: ");
		String time = in.next();
		startTime = LocalTime.of( Integer.valueOf(time.split(":")[0]) , Integer.valueOf(time.split(":")[1]) );
		return startTime;
	}
	
	private LocalTime inputEndTime() {
		LocalTime endTime;
		System.out.print("Enter end time: ");
		String time = in.next();
		endTime = LocalTime.of( Integer.valueOf(time.split(":")[0]) , Integer.valueOf(time.split(":")[1]) );
		return endTime;
	}
	
}
