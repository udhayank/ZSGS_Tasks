package view;

import java.sql.SQLException;
import java.util.Scanner;

public class MainMenu {
	
	Scanner in;
	
	public MainMenu() {
		in = new Scanner(System.in);
	}
	
	public void printMainMenu() throws SQLException {
		
		System.out.println("1 - Admin login");
		System.out.println("2 - Student login");
		System.out.println("3 - Tutor login");
		
		int choice = 0;
		while (true) {
			System.out.print("\nEnter your choice: ");
			choice = in.nextInt();
			if (choice >= 1 && choice <= 3) {
				break;
			} else {
				System.out.println("Invalid Choice!");
			}			
		}
		
		switch (choice) {
		case 1:
			AdminView adminView = new AdminView();
			adminView.adminLogin();
			break;

		case 2:
			StudentView studentView = new StudentView();
			studentView.studentLogin();
			break;
			
		case 3:
			
			break;
			
				}
		
	}
	
	public void init() throws SQLException {
		System.out.println("-----------------------------------");
		System.out.println("Welcome to Course Management System");
		System.out.println("-----------------------------------\n");
		printMainMenu();
	}

	public static void main(String[] args) throws SQLException {
		
		MainMenu menu = new MainMenu();
		menu.init();
		
	}
	
}
