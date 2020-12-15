package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Transaction;
import com.revature.util.BankDatabaseConnection;

public class TransactionsDAOImpl implements TransactionsDAO{
	
Connection conn;
	
	public TransactionsDAOImpl() {
		
		try {
			conn = BankDatabaseConnection.getConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
	public boolean addTransactionToHistory(String giver, String receiver, double amount, int transactionType) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO transactions (giver, receiver, amount, transactiontype) VALUES (\'"+giver+"\', \'"+receiver+"\', \'"+amount
				+"\', \'"+transactionType+"\');";
		try {
			Statement st = conn.createStatement(); 
			return (st.executeUpdate(sql)==1);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Transaction> getAllUserTransactions(String user) {
		String sql = "SELECT * FROM transactions WHERE ( receiver=\'"+user+"\' OR giver=\'"+user+"\');";
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction;
		try {
			Statement st = conn.createStatement();
			ResultSet results = st.executeQuery(sql);
			
			while(results.next()) {
				transaction = new Transaction(results.getDouble("amount"), results.getString("giver"), results.getString("receiver"), 
											results.getInt("transactionType"), results.getInt("id"));
				transactions.add(transaction);
			}
			return transactions;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
		
	}
	
	public List<Transaction> getAllTransactions() {
		String sql = "SELECT * FROM transactions";
		List<Transaction> transactions = new ArrayList<Transaction>();
		ResultSet results;
		Transaction transaction;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			results = st.executeQuery();
			
			while(results.next()) {
				transaction = new Transaction(results.getDouble("amount"), results.getString("giver"), results.getString("receiver"), 
						results.getInt("transactionType"), results.getInt("id"));
				transactions.add(transaction);
			}
			return transactions;
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	
	
}
