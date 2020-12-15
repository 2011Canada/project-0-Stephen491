package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.models.Transaction;
import com.revature.util.BankDatabaseConnectionPostgres;

public class TransactionsDAOPostgresImpl implements TransactionsDAO{
	
	Connection conn;
	BankDatabaseConnectionPostgres cf ;
	
	public TransactionsDAOPostgresImpl() {
		cf = BankDatabaseConnectionPostgres.getConnectionFactory();
		conn = cf.getConnection();
	}

	
	public boolean addTransactionToHistory(String sender, String receiver, double amount, int transactionType) {
		// TODO Auto-generated method stub
		String sql = "INSERT INTO bank.transactions (sender, receiver, amount, transactiontype) VALUES (?,?,?,?);";
		try {
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1, sender);
			st.setString(2, receiver);
			st.setDouble(3, amount);
			st.setInt(4, transactionType);
			
			return (st.executeUpdate()==1);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	public List<Transaction> getAllUserTransactions(String user) {
		String sql = "SELECT * FROM bank.transactions WHERE ( receiver= ?  OR sender = ?);";
		List<Transaction> transactions = new ArrayList<Transaction>();
		Transaction transaction;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, user);
			st.setString(2, user);
			ResultSet results = st.executeQuery();
			
			while(results.next()) {
				transaction = new Transaction(results.getDouble("amount"), results.getString("sender"), results.getString("receiver"), 
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
		String sql = "SELECT * FROM bank.transactions";
		List<Transaction> transactions = new ArrayList<Transaction>();
		ResultSet results;
		Transaction transaction;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			results = st.executeQuery();
			
			while(results.next()) {
				transaction = new Transaction(results.getDouble("amount"), results.getString("sender"), results.getString("receiver"), 
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
