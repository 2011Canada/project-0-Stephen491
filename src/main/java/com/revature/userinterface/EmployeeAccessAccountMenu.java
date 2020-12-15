package com.revature.userinterface;


import java.util.Scanner;

import com.revature.models.UserAccount;
import com.revature.services.AccountsService;

public class EmployeeAccessAccountMenu implements Menu {
	AccountsService ac; 
	Scanner scanner;
	
	
	public EmployeeAccessAccountMenu() {
		scanner = new Scanner(System.in);
		ac = new AccountsService();
	}
	public Session display(Session session) {
		
		UserAccount account;
		String username;
		
		System.out.println("Please enter the username of the account you wish to access.");
		username = scanner.nextLine();
		
		account = ac.getAccount(username);
		if(account!= null) {
			//System.out.println("Account ID: "+t.getPendingAccountID());
			System.out.println("Username: "+account.getUsername());
			System.out.println("First name: "+account.getFirstName());
			System.out.println("Last name: "+account.getLastName());
			System.out.println("Balance: "+account.getBalance());
			System.out.println("");
			System.out.println("--------------");
		}
		
		return session;
	} 
	public void resetMenu() {
		
	} 
	
}
