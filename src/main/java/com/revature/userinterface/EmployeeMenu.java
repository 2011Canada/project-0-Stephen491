package com.revature.userinterface;

import java.util.Scanner;

public class EmployeeMenu implements Menu{
		Scanner scanner;
		String optionStr;
		int option;
		boolean validInput;
		
		public EmployeeMenu() {
			scanner = new Scanner(System.in);
			validInput = false;
		}
		
		public Session display(Session session){
			while(!validInput) {
				System.out.println("Please enter 1 to handle pending accounts, 2 to view bank accounts, 3 to view transactions, and 4 to logout.");
				try {
					option = Integer.parseInt(scanner.nextLine());
					if(option<1||option>5) {
						System.out.println("Please enter a valid option.");
					}
					else {
						validInput = true;
						session.setEmployeeOption(option);
					}
				}
				catch(NumberFormatException e) {
					System.out.println("Please enter a valid option.");
				}
			}
			return session;
		}
		
		public void resetMenu() {
			optionStr = null;
			validInput = false;
			option = 0;
		} 
		
		
	}

