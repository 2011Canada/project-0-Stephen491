package com.revature.userinterface;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.PendingAccount;
import com.revature.services.AccountsService;

public class PendingAccountsMenu implements Menu {
	Scanner scanner;
	AccountsService ac;
	
	public PendingAccountsMenu() {
		scanner = new Scanner(System.in);
		ac = new AccountsService();
	}
	
	
	public Session display(Session session) {
		List<PendingAccount> results;
		String userInput = null;
		int accountid;
		PendingAccount account = null;
		
		results =  ac.showPendingAccounts();
		for(PendingAccount t: results) {
			
			
			System.out.println("Account ID: "+t.getPendingAccountID());
			System.out.println("Username: "+t.getUsername());
			System.out.println("First name: "+t.getFirstName());
			System.out.println("Last name: "+t.getLastName());
			System.out.println("");
			System.out.println("--------------");
		}
		
		System.out.println("Please enter the account ID of the account you wish to approve or reject, or enter a non-digit to exit.");
		
		
		try {	
			accountid= scanner.nextInt();
		}
		catch(InputMismatchException e) {
			return session;
		}
		
		scanner.nextLine();
		
		for(PendingAccount t: results) {
			if(t.getPendingAccountID()==accountid) {
				account = t;
			}
		}
		
		if(account == null) {
			System.out.println("Please enter a valid pending account ID.");
		}
		
		else  {
			System.out.println("Do you wish to approve or reject this account? Enter A to approve, or R to reject, or any other key to return to other pending transfers.");
			userInput = scanner.nextLine();
			if(userInput.toLowerCase().equals("a")) {
				if(ac.createAccount(account.getUsername(), account.getFirstName(), account.getLastName(), account.getPassword())&&ac.removePendingAccount(account.getPendingAccountID())) {
					System.out.println("Account has been approved.");
				} 
			}
			else if(userInput.toLowerCase().equals("r")) {
				if(ac.removePendingAccount(account.getPendingAccountID())) {
					System.out.println("Account has been declined.");
				}
			}
		}
		
		
		
		
		return session;
	}	
	public void resetMenu() {
		
	}
}
