package com.revature.userinterface;

import java.util.Scanner;

import com.revature.services.AccountsService;

public class AccountCreationMenu implements Menu{
	Scanner scanner;
	boolean validInput = false;
	String username;
	String firstName;
	String lastName;
	String password;
	AccountsService accountService;
	
	public AccountCreationMenu() {
		scanner = new Scanner(System.in);
		accountService = new AccountsService();
	}
	
	public Session display(Session session) {
		while(!validInput) {
			System.out.println("Please enter a username.");
			username = scanner.nextLine();
			if(accountService.checkUsernameTaken(username)) {
				System.out.println("Username is taken, please try again.");
			}
			else {
				validInput = true;
			}
		}
		validInput = false;
		while(!validInput) {
			System.out.println("Please enter your first name.");
			firstName = scanner.nextLine();
			if(firstName.length()>30) {
				System.out.println("That first name is too long.");
				continue;
			}
			validInput = true;
		}
		validInput = false;
		while(!validInput) {
			System.out.println("Please enter your last name.");
			lastName = scanner.nextLine();
			if(lastName.length()>30) {
				System.out.println("That last name is too long.");
				continue;
			}
			validInput = true;
		}
		validInput = false;
		while(!validInput) {
			System.out.println("Please enter your password.");
			password = scanner.nextLine();
			if(password.length()<8) {
				System.out.println("That password is too short.");
				continue;
			}
			validInput = true;
		}
		
		//send to account creation service
		accountService.createPendingAccount(username, firstName, lastName, password);
		resetMenu();
		return session;
		
	}
	
	public void resetMenu() {
		validInput = false;
		username = null;
		firstName = null;
		lastName= null;
		password = null;
		
	}
}
