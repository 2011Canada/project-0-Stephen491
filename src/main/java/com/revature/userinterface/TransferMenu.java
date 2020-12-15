package com.revature.userinterface;

import java.util.Scanner;

import com.revature.services.FundsService;

public class TransferMenu implements Menu {
	String user1;
	String user2;
	double amount;
	Scanner scanner;
	FundsService fs;
	
	public TransferMenu() {
		scanner = new Scanner(System.in);
		fs = new FundsService();
	}
	
	
	public Session display(Session session) {
		user1 = session.getAccount().getUsername();
		
		System.out.println("Please enter the username of the user you wish to transfer funds to.");
		user2 = scanner.nextLine();
		System.out.println("Please enter the amount you wish to transfer.");
		amount = scanner.nextDouble();
		
		fs.addToPendingTransfers(user1, user2, amount);
		
		return session;
	}
	
	public void resetMenu() {
		user1 = null;
		user2 = null;
		amount = 0;
		
	}
	
	
	
	
}
