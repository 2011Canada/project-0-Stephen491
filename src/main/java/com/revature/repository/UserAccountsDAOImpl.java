package com.revature.repository;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.List;

import com.revature.exceptions.DuplicateEntryException;
import com.revature.exceptions.InvalidAccountException;
import com.revature.models.UserAccount;
import com.revature.util.BankDatabaseConnection;

public class UserAccountsDAOImpl implements UserAccountsDAO{
	Connection conn;
	
	public UserAccountsDAOImpl() {
		
		try {
			conn = BankDatabaseConnection.getConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	
	

	public UserAccount getUserAccount(String userID) throws InvalidAccountException{
		// TODO Auto-generated method stub
		String sql = "SELECT * FROM UserAccounts WHERE userid = \'"+userID+"\';";
		UserAccount account = null; 
		try {
			Statement st = conn.createStatement(); 
			st.execute(sql);
			ResultSet results = st.getResultSet();
			if(results.next()) {
				account = new UserAccount(results.getString("userid"), results.getString("firstName"), 
						results.getString("lastName"), results.getString("pin"), results.getDouble("balance"), 
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

	
	public double getBalance(String userID) throws InvalidAccountException {
		String sql = "SELECT balance FROM UserAccounts WHERE userid = \'"+userID+"\';";
		try {
			Statement st = conn.createStatement(); 
			st.execute(sql);
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

	
	public boolean verifyCredentials(String userID, String pin) {
		String sql = "SELECT pin FROM UserAccounts WHERE userid = \'"+userID+"\';";
		try {
			Statement st = conn.createStatement(); 
			st.execute(sql);
			ResultSet results = st.getResultSet();
			if(results.next()) {
				if(results.getString("pin").equals(pin)) {
					return true;
				}
			}
			
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
		return false;
	}


	public boolean createAccount(String userID, String firstName, String lastName, String pin) throws DuplicateEntryException{
		String sql = "INSERT INTO UserAccounts VALUES (\'"+userID+"\', \'"+firstName+"\', \'"+lastName
				+"\', \'"+pin+"\', 0, false);";
		try {
			Statement st = conn.createStatement(); 
			return (st.executeUpdate(sql)==1);
			
			
		}
		catch(SQLIntegrityConstraintViolationException e) {
			throw new DuplicateEntryException(); 
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public boolean createAccount(UserAccount account) throws DuplicateEntryException{
		String userID = account.getUserID();
		String firstName = account.getFirstName();
		String lastName = account.getLastName();
		String pin = account.getPin();
		return this.createAccount(userID, firstName, lastName, pin);
	
	}

	
	public List<UserAccount> getAllUserAccounts() {
		// TODO Auto-generated method stub
		return null;
	}

	
	public boolean withdraw(String userID, double amount) throws InvalidAccountException {
		String sql = "SELECT balance FROM UserAccounts WHERE userid = \'"+userID+"\';";
		double balance;
		try {
			Statement st = conn.createStatement(); 
			st.executeQuery(sql);
			ResultSet results = st.getResultSet();
			if(results.next()) {
				balance = results.getDouble("balance");
				balance -=amount;
			
				String sql2 = "UPDATE UserAccounts SET balance = "+balance+"WHERE userid = \'"+userID+"\';";
				return (st.executeUpdate(sql2)==1);
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
		String userID = account.getUserID();
		return this.withdraw(userID, amount);
	}

	public boolean deposit(String userID, double amount) throws InvalidAccountException{
		
		String sql = "SELECT balance FROM UserAccounts WHERE userid = \'"+userID+"\';";
		double balance;
		try {
			Statement st = conn.createStatement(); 
			st.executeQuery(sql);
			ResultSet results = st.getResultSet();
			if(results.next()) {
				balance = results.getDouble("balance");
				balance +=amount;
			
				String sql2 = "UPDATE UserAccounts SET balance = "+balance+"WHERE userid = \'"+userID+"\';";
				return (st.executeUpdate(sql2)==1);
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
		String userID = account.getUserID();
		return this.deposit(userID, amount);
		
	}
	
	public boolean transferFunds(String user1, String user2, double amount) throws InvalidAccountException{
		String sql = "SELECT balance FROM UserAccounts WHERE userid = \'"+user1+"\';";
		double balance1;
		double balance2;
		
		if(user1.equals(user2)) {
			return false;
		}
		
		
		try {
			Statement st = conn.createStatement(); 
			st.executeQuery(sql);
			ResultSet results = st.getResultSet();
			if(results.next()) {
				balance1 = results.getDouble("balance");
				balance1 -=amount;
			
					
				sql = "SELECT balance FROM UserAccounts WHERE userid = \'"+user2+"\';";
				st.executeQuery(sql);
				results = st.getResultSet();
				if(results.next()) {
						balance2 = results.getDouble("balance");
						balance2 +=amount;
						String sql2 = "UPDATE UserAccounts SET balance = "+balance2+"WHERE userid = \'"+user2+"\';";
						sql = "UPDATE UserAccounts SET balance = "+balance1+"WHERE userid = \'"+user1+"\';";
					
						if(st.executeUpdate(sql)==1&&st.executeUpdate(sql2)==1) {
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
