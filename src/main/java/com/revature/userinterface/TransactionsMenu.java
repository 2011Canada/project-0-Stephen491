package com.revature.userinterface;

import java.util.Scanner;

import com.revature.services.FundsService;

public class TransactionsMenu implements Menu{
	Scanner scanner;
	FundsService fs;
	
	
	public TransactionsMenu() {
		scanner = new Scanner(System.in);
		fs = new FundsService();
	}
	
	
	public Session display(Session session) {
		fs.showTransactionHistory(session.getAccount().getUserID());
		return session;
	}	
	public void resetMenu() {
		return ;
	}
	
	
}
