package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;

import java.util.List;

import com.revature.exceptions.DuplicateEntryException;
import com.revature.exceptions.InvalidAccountException;
import com.revature.models.UserAccount;
import com.revature.util.BankDatabaseConnectionPostgres;

public class UserAccountsDAOPostgresImpl implements UserAccountsDAO{
	Connection conn;
	BankDatabaseConnectionPostgres cf ;
	public UserAccountsDAOPostgresImpl() {
		cf = BankDatabaseConnectionPostgres.getConnectionFactory();
		conn = cf.getConnection();
	}
	
	
	
	public UserAccount getUserAccount(String username) throws InvalidAccountException{
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM bank.useraccounts WHERE username= ?;";
		UserAccount account = null; 
		try {
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1, username);
			st.executeQuery();
			ResultSet results = st.getResultSet();
			if(results.next()) {
				account = new UserAccount(results.getString("username"), results.getString("firstName"), 
						results.getString("lastName"), results.getString("password"), results.getDouble("balance"), 
						results.getBoolean("isemployee"));
			}
			else {
				throw new InvalidAccountException();
			}
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return account;
	}

	public double getBalance(String username) throws InvalidAccountException {
		String sql = "SELECT balance FROM bank.useraccounts WHERE username = ?;";
		try {
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1, username);
			st.executeQuery();
			ResultSet results = st.getResultSet();
			if(results.next()) {
				return results.getDouble("balance");
			}
			else {
				throw new InvalidAccountException();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return 0;
		}
		
	}

	
	public boolean verifyCredentials(String username, String password) {
		String sql = "SELECT password FROM bank.useraccounts WHERE username = ?;";
		try {
			
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1,  username);
			st.executeQuery();
			ResultSet results = st.getResultSet();
			if(results.next()) {
				if(results.getString("password").equals(password)) {
					return true;
				}
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}

	
	public boolean createAccount(String username, String firstName, String lastName, String password) throws DuplicateEntryException{
		String sql = "INSERT INTO bank.useraccounts (username, firstname, lastname, password, balance, isemployee) VALUES (?, ?, ?, ?, 0, false);";
		try {
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1,  username);
			st.setString(2, firstName);
			st.setString(3,  lastName);
			st.setString(4, password);
			return (st.executeUpdate()==1);
			
			
		}
		catch(SQLIntegrityConstraintViolationException e) {
			throw new DuplicateEntryException(); 
		}
		catch(SQLException e) {
			if(e.getSQLState().equals("23505")) {
				throw new DuplicateEntryException();
			}
			e.printStackTrace();
			return false;
		}
		
	}
	
	
	public boolean createAccount(UserAccount account) throws DuplicateEntryException{
		String userID = account.getUsername();
		String firstName = account.getFirstName();
		String lastName = account.getLastName();
		String pin = account.getPin();
		return this.createAccount(userID, firstName, lastName, pin);
	
	}

	
	public List<UserAccount> getAllUserAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean withdraw(String username, double amount) throws InvalidAccountException {
		String sql = "SELECT balance FROM bank.useraccounts WHERE username = ? ;";
		double balance;
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,  username);
			st.executeQuery();
			ResultSet results = st.getResultSet();
			if(results.next()) {
				balance = results.getDouble("balance");
				balance -=amount;
				
			
				String sql2 = "UPDATE bank.useraccounts SET balance = ? WHERE username = ?;";
				PreparedStatement st2 = conn.prepareStatement(sql2);
				st2.setDouble(1, balance);
				st2.setString(2, username);
				return (st2.executeUpdate()==1);
			}
			else {
				throw new InvalidAccountException(); 
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	public boolean withdraw(UserAccount account, double amount) throws InvalidAccountException{
		String userID = account.getUsername();
		return this.withdraw(userID, amount);
	}

	public boolean deposit(String username, double amount) throws InvalidAccountException{
		
		String sql = "SELECT balance FROM bank.useraccounts WHERE username = ?;";
		double balance;
		try {
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1,  username);
			st.executeQuery();
			ResultSet results = st.getResultSet();
			if(results.next()) {
				balance = results.getDouble("balance");
				balance +=amount;
			
				String sql2 = "UPDATE bank.useraccounts SET balance = ? WHERE username = ?;";
				PreparedStatement st2 = conn.prepareStatement(sql2);
				st2.setDouble(1,  balance);
				st2.setString(2, username);
				return (st2.executeUpdate()==1);
			}
			else {
				throw new InvalidAccountException();
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
	}
	
	public boolean deposit(UserAccount account, double amount) throws InvalidAccountException{
		String userID = account.getUsername();
		return this.deposit(userID, amount);
		
	}
	
	public boolean transferFunds(String user1, String user2, double amount) throws InvalidAccountException{
		String sql = "SELECT balance FROM bank.useraccounts WHERE username = ?;"; //user1
		String sql2 = null;
		double balance1;
		double balance2;
		
		if(user1.equals(user2)) {
			return false;
		}
		
		
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1,  user1);
			st.executeQuery();
			ResultSet results = st.getResultSet();
			if(results.next()) {
				balance1 = results.getDouble("balance");
				balance1 -=amount;
			
					
				sql2 = "SELECT balance FROM bank.useraccounts WHERE username = ?;";
				PreparedStatement st2 = conn.prepareStatement(sql2);
				st2.setString(1,  user2);
				st2.executeQuery();
				results = st2.getResultSet();
				if(results.next()) {
						
					
						balance2 = results.getDouble("balance");
						balance2 +=amount;
						String sql3 = "UPDATE bank.useraccounts SET balance = ? WHERE username = ? ;";
						PreparedStatement st3 = conn.prepareStatement(sql3);
						st3.setDouble(1, balance2);
						st3.setString(2,  user2);
						
						String sql4 = "UPDATE bank.useraccounts SET balance = ? WHERE username = ? ;";
						PreparedStatement st4 = conn.prepareStatement(sql4);
						st4.setDouble(1, balance1);
						st4.setString(2,  user1);
						
						
						
						
						if(st4.executeUpdate()==1&&st3.executeUpdate()==1) {
							return true;
						}
						else {
							return false;
						}

				}
				else {
					throw new InvalidAccountException();
				}
			}
			else {
				throw new InvalidAccountException(); 
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			
			return false;
		}
		
		
	}

	
	
}
