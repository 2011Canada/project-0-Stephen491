package com.revature.repository;

import java.util.List;

import com.revature.models.Transaction;

public interface TransactionsDAO {
	public boolean addTransactionToHistory(String giver, String receiver, double amount, int transactionType);
	public List<Transaction> getAllUserTransactions(String user);
	public List<Transaction> getAllTransactions();
}
