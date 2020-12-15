package com.revature.userinterface;

import java.util.List;

import com.revature.models.Transaction;
import com.revature.services.FundsService;

public class AllTransactionsMenu implements Menu{
	FundsService fs;
	
	
	public AllTransactionsMenu() {
		fs = new FundsService();
	}
	
	public Session display(Session session) {
		List<Transaction> transactions = fs.getAllTransactions();
		
		for(Transaction t: transactions) {
			System.out.println("Transaction ID: "+t.getTransactionId());
			System.out.println("Amount: "+t.getAmount());
			System.out.print("Operation: "+ t.getTransactionType());
			if(t.getTransactionType()==3) {
				System.out.print(" from "+t.getGiver()+" to "+t.getReceiver());
			}
			System.out.println("");
			System.out.println("--------------");
		}
		
		return session;
	}
	public void resetMenu() {
		
	}
}
