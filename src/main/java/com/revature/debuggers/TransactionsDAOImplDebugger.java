package com.revature.debuggers;

import java.util.ArrayList;
import java.util.List;

import com.revature.models.Transaction;
import com.revature.repository.TransactionsDAO;
import com.revature.repository.TransactionsDAOImpl;
import com.revature.repository.TransactionsDAOPostgresImpl;
import com.revature.services.FundsService;


//1 = deposit, 2 = withdrawal, transfer
public class TransactionsDAOImplDebugger {
	
	public static void main(String args[]) {
		TransactionsDAO td = new TransactionsDAOPostgresImpl();
		td.addTransactionToHistory("113", "115", 5, 3);
		List<Transaction> transacs = td.getAllUserTransactions("113");
		for(Transaction t: transacs) {
			System.out.println(t.getReceiver());
		}
		
		
		
	}
	
}
