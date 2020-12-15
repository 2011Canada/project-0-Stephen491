package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.DuplicateEntryException;
import com.revature.models.PendingAccount;
import com.revature.models.PendingTransfer;
import com.revature.models.UserAccount;
import com.revature.util.BankDatabaseConnection;

public class PendingAccountsDAOImpl implements PendingAccountsDAO{
	Connection conn;
	
	public PendingAccountsDAOImpl() {
		
		try {
			conn = BankDatabaseConnection.getConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}
	


	
	public boolean createPendingAccount(String userID, String firstName, String lastName, String password) throws DuplicateEntryException {

		String sql = "INSERT INTO PendingUserAccounts (username, firstname, lastname, password) VALUES (\'"+userID+"\', \'"+firstName+"\', \'"+lastName
				+"\', \'"+password+"\');";
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

	
	public boolean createPendingAccount(UserAccount account) throws DuplicateEntryException {
		// TODO Auto-generated method stub
		String userid = account.getUserID();
		String lastname = account.getLastName();
		String firstname = account.getFirstName();
		String password = account.getPin();
		return createPendingAccount(userid, firstname, lastname, password);
		
	}
	
	
	public List<PendingAccount> showPendingAccounts() {
		List<PendingAccount> pendingAccounts = new ArrayList<PendingAccount>(); 
		String sql = "SELECT * FROM PendingUserAccounts;";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql); 
			ResultSet results = st.executeQuery();
			
			while(results.next()) {
				PendingAccount pendingAccount = new PendingAccount(); 
				pendingAccount.setUsername(results.getString("username"));
				pendingAccount.setFirstName(results.getString("firstname"));
				pendingAccount.setLastName(results.getString("lastname"));
				pendingAccount.setPassword(results.getString("password"));
				pendingAccount.setPendingAccountID(results.getInt("id"));
				pendingAccounts.add(pendingAccount);
				
			}
			
			return pendingAccounts;
		
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	
	public boolean removePendingAccount(int id) {
		String sql = "DELETE FROM PendingUserAccounts WHERE id = ?";
		try {
			PreparedStatement st = conn.prepareStatement(sql);
			st.setInt(1, id);
			return (st.executeUpdate()==1);
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
}
