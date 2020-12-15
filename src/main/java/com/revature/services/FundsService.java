package com.revature.services;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.InvalidAccountException;
import com.revature.models.PendingTransfer;
import com.revature.models.Transaction;
import com.revature.models.UserAccount;
import com.revature.repository.PendingTransfersDAO;
import com.revature.repository.PendingTransfersDAOPostgresImpl;
import com.revature.repository.TransactionsDAO;
import com.revature.repository.TransactionsDAOPostgresImpl;
import com.revature.repository.UserAccountsDAO;
import com.revature.repository.UserAccountsDAOPostgresImpl;

public class FundsService {
	UserAccount account;
	UserAccountsDAO userAccountDAO;
	TransactionsDAO transactionsDAO;
	PendingTransfersDAO pendingTransfersDAO;
	public static Logger transactionLogger = LogManager.getLogger("com.revature.transactions"); 
	
	
	public FundsService() {
		userAccountDAO = new UserAccountsDAOPostgresImpl();
		transactionsDAO = new TransactionsDAOPostgresImpl(); 
		pendingTransfersDAO = new PendingTransfersDAOPostgresImpl();
	}
	
	public void withdraw(UserAccount account, double amount) {
		double balance = 0;
		try {
			balance = userAccountDAO.getBalance(account.getUsername());
			
			
			if(amount>balance) {
				System.out.println("Amount to withdraw exceeds your balance.");
			}
			else {
				if(userAccountDAO.withdraw(account.getUsername(), amount)&&transactionsDAO.addTransactionToHistory(account.getUsername(), null, amount, 2)) {
					System.out.println("$"+amount+" has been successfully withdrawn from your account.");
					
					//make a call to transactionsdao to insert into transaction history
				}
			}
		}
		catch(InvalidAccountException e) {
			System.out.println(e);
			return ;
		}
			
	}
	
	
	
	public void deposit(UserAccount account, double amount) {
		try {
			if(userAccountDAO.deposit(account.getUsername(), amount)&&transactionsDAO.addTransactionToHistory(null, account.getUsername(), amount, 1)) {
				System.out.println("$"+amount+" has been successfully deposited to your account.");
				
			}
		}
		catch(InvalidAccountException e) {
			System.out.println(e);
			return ;
		}
	}
	
	public void getBalance(UserAccount account) {
		double balance = 0;
		try {
			balance = userAccountDAO.getBalance(account.getUsername());
			System.out.println("Your current balance is $"+balance+",");
		}
		catch(InvalidAccountException e) {
			System.out.println(e);
			return ;
		}
		
		
	}
	
	public void getBalance(String username) {
		double balance = 0;
		try {
			balance = userAccountDAO.getBalance(username);
			System.out.println("Your current balance is $"+balance+",");
		}
		catch(InvalidAccountException e) {
			System.out.println(e);
			return ;
		}
	}
	public void transferFunds(PendingTransfer pendingTransfer) {
		try {
			if(userAccountDAO.transferFunds(pendingTransfer.getSender(), pendingTransfer.getReceiver(), pendingTransfer.getAmount())&&transactionsDAO.addTransactionToHistory(pendingTransfer.getSender(), pendingTransfer.getReceiver(), pendingTransfer.getAmount(), 3)&&pendingTransfersDAO.removeTransfer(pendingTransfer.getTransferId())) {
				
				System.out.println("Funds were successfully transferred.");
				transactionLogger.info("$"+pendingTransfer.getAmount()+" was transferred from "+pendingTransfer.getSender()+" to "+pendingTransfer.getReceiver()+".");
			}
		}
		catch(InvalidAccountException e) {
			System.out.println(e);
		}
		
	
	}
	
	public void addToPendingTransfers(String giver, String receiver, double amount) {
		try {
			if(pendingTransfersDAO.addTransfer(giver, receiver, amount)) {
				System.out.println("You are now transferring funds to "+receiver+".");
			}
		}
		catch(InvalidAccountException e) {
			System.out.println(e);
		}
	}
	
	public void cancelTransfer(int id) {
		if(pendingTransfersDAO.removeTransfer(id)) {
			System.out.println("Transfer has been cancelled.");
		}
	}
		
	
	
	public void showTransactionHistory(String user) {

		List<Transaction> results = new ArrayList<Transaction>();
		results = transactionsDAO.getAllUserTransactions(user);
		String sender = null;
		String receiver = null;
		
		
		for(Transaction t: results) {
			String transactionType = null;
			
			switch (t.getTransactionType()) {
			case 1: 
				transactionType = "Deposit";
				break;
			case 2: 
				transactionType = "Withdrawal";
				break;
			case 3:
				transactionType = "Transfer";
				receiver = t.getReceiver();
				sender = t.getGiver();
				break;
			}
		
			System.out.println("Transaction ID: "+t.getTransactionId());
			System.out.println("Amount: "+t.getAmount());
			System.out.print("Operation: "+ transactionType);
			if(t.getTransactionType()==3) {
				System.out.print(" from "+sender+" to "+receiver);
			}
			System.out.println("");
			System.out.println("--------------");
			
		}
		
		
	}
	
	
	public List<PendingTransfer> showTransfers(String user) {

		return pendingTransfersDAO.showTransfers(user);

		
	}
	
	public List<Transaction> getAllTransactions() {
		return transactionsDAO.getAllTransactions();
	}
	
}
