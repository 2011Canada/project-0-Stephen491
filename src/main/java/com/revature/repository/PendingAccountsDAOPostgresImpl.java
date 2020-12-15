package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.DuplicateEntryException;
import com.revature.models.PendingAccount;
import com.revature.models.UserAccount;
import com.revature.util.BankDatabaseConnectionPostgres;

public class PendingAccountsDAOPostgresImpl implements PendingAccountsDAO{
	Connection conn;
	BankDatabaseConnectionPostgres cf ;
	
	public PendingAccountsDAOPostgresImpl() {
		cf = BankDatabaseConnectionPostgres.getConnectionFactory();
		conn = cf.getConnection();
	}
	
	
	
	public boolean createPendingAccount(String username, String firstName, String lastName, String password) throws DuplicateEntryException {

		String sql = "INSERT INTO bank.pendingaccounts (username, firstname, lastname, password) VALUES (?, ?, ?, ?);";
		try {
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setString(1, username);
			st.setString(2, firstName);
			st.setString(3, lastName);
			st.setString(4, password);
			return (st.executeUpdate()==1);
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
		String userid = account.getUsername();
		String lastname = account.getLastName();
		String firstname = account.getFirstName();
		String password = account.getPin();
		return createPendingAccount(userid, firstname, lastname, password);
		
	}
	
	
	public List<PendingAccount> showPendingAccounts() {
		List<PendingAccount> pendingAccounts = new ArrayList<PendingAccount>(); 
		String sql = "SELECT * FROM bank.pendingaccounts;";
		
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
		String sql = "DELETE FROM bank.pendingaccounts WHERE id = ?";
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
