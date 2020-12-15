package com.revature.userinterface;

import java.util.Scanner;

public class BankOptionsMenu implements Menu {
	Scanner scanner;
	String optionStr;
	int option;
	boolean validInput;
	
	public BankOptionsMenu() {
		scanner = new Scanner(System.in);
		validInput = false;
	}
	
	public Session display(Session session){
		while(!validInput) {
			System.out.println("Please enter 1 to deposit, 2 to withdraw, 3 to see your balance, 4 to see all your transactions, 5 to transfer funds, and 6 to see pending funds, and 7 to logout.");
			try {
				option = Integer.parseInt(scanner.nextLine());
				if(option<1||option>7) {
					System.out.println("Please enter a valid option.");
				}
				else {
					validInput = true;
					session.SetBankOption(option);
				}
			}
			catch(NumberFormatException e) {
				System.out.println("Please enter a valid option.");
			}
		}
		return session;
	}
	
	public void resetMenu() {
		validInput = false;
		option = 0;
	} 
	
	
}
