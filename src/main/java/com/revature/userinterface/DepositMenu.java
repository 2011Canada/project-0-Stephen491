package com.revature.userinterface;

import java.util.Scanner;

import com.revature.models.UserAccount;
import com.revature.services.FundsService;

public class DepositMenu implements Menu{
	Scanner scanner;
	Double amount;
	FundsService fs;
	UserAccount account;
	
	public DepositMenu() {
		scanner = new Scanner(System.in);
		fs =  new FundsService();
		amount = 0.0;
		account = null;
	}
	
	
	public Session display(Session session) {
		// TODO Auto-generated method stub
		System.out.println("Please enter the amount you wish to deposit into your account.");
		try {
			amount = Double.parseDouble(scanner.nextLine());
			if(amount<0) {
				System.out.println("Please enter a valid option.");
			}
			else {
				//deposit
				account = session.getAccount();
				fs.deposit(account, amount);
			}
		}
		catch(NumberFormatException e) {
			System.out.println("Please enter a valid option.");
		}
		return session;
	}

	
	public void resetMenu() {
		// TODO Auto-generated method stub
		amount = 0.0;
		
	}

}
