package com.revature.userinterface;

import java.util.Scanner;

public class InitialMenu implements Menu{
	Scanner scanner;
	String choice;
	boolean validInput;
	
	public InitialMenu() {
		scanner = new Scanner(System.in);
		validInput = false;
		
	}
	
	public Session display(Session currentSession) {
		
		System.out.println("Welcome to the Superduper Awesome Bank! Please enter 1 to sign in and 2 to register for an account.");
		while(!validInput) {
			choice = scanner.nextLine();
			if(choice.equals("1")) {
				validInput = true;
				resetMenu();
				currentSession.setInitOption(1);
				return currentSession;
			}
			else if(choice.equals("2")) {
				validInput = true;
				resetMenu();
				currentSession.setInitOption(2);
				return currentSession;
			}
			else {
				System.out.println("Please enter in a valid input. Please enter 1 to sign in and 2 to register for an account.");
			}
			
		}
		resetMenu();
		return currentSession;
		
	}
	
	public void resetMenu() {
		validInput = false;
		choice = null;
	}
	
}
