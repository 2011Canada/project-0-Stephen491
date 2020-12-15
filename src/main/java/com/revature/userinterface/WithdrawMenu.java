package com.revature.userinterface;

import java.util.Scanner;

import com.revature.models.UserAccount;
import com.revature.services.FundsService;

public class WithdrawMenu implements Menu{
	Scanner scanner;
	FundsService fs;
	double amount;
	UserAccount account;
	
	public WithdrawMenu() {
		scanner = new Scanner(System.in);
		fs =  new FundsService();
		amount = 0.0;
		this.account = null; 
	}

	
	public Session display(Session session) {
	// TODO Auto-generated method stub
		System.out.println("Please enter the amount you wish to withdraw from your account.");
		try {
			amount = Double.parseDouble(scanner.nextLine());
			if(amount<0) {
				System.out.println("Please enter a valid option.");
			}
			else {
				account = session.getAccount();
				fs.withdraw(account, amount);
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
