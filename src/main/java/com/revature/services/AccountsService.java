package com.revature.services;

import java.util.List;

import com.revature.exceptions.DuplicateEntryException;
import com.revature.exceptions.InvalidAccountException;
import com.revature.models.PendingAccount;
import com.revature.models.UserAccount;
import com.revature.repository.PendingAccountsDAO;
import com.revature.repository.PendingAccountsDAOPostgresImpl;
import com.revature.repository.UserAccountsDAO;
import com.revature.repository.UserAccountsDAOPostgresImpl;

public class AccountsService {
	UserAccount account;
	UserAccountsDAO userAccountDAO ;
	PendingAccountsDAO pendingAccountDAO;
	
	public AccountsService() {
		userAccountDAO = new UserAccountsDAOPostgresImpl();
		pendingAccountDAO = new PendingAccountsDAOPostgresImpl(); 
	}
	public boolean createAccount(String userId, String firstName, String lastName, String password) {
		try {
			return userAccountDAO.createAccount(userId, firstName, lastName, password);
		}
		catch(DuplicateEntryException e) {
			System.out.println(e);
			return false;
		}
		
	}
	
	
	
	public List<UserAccount> getAllUserAccounts( ) {
		return userAccountDAO.getAllUserAccounts();
	}
	
	
	public UserAccount getAccount(String username) {
		try {
			return userAccountDAO.getUserAccount(username);
		}
		catch(InvalidAccountException e) {
			System.out.println("Invalid Account");
			return null;
		}
	}
	public boolean createPendingAccount(String userId, String firstName, String lastName, String password) {
		try {
			return pendingAccountDAO.createPendingAccount(userId, firstName, lastName, password);
		}
		catch(DuplicateEntryException e) {
			System.out.println(e);
			return false;
		}
	}
	
	public boolean checkUsernameTaken(String userId) {
		try {
			if(!(userAccountDAO.getUserAccount(userId)==null)) {
				return true;
			}
			else {
				return false;
			}
		}
		catch(InvalidAccountException e) {
			return false;
		}
		
	}
	
	
	public boolean removePendingAccount(int id) {
		return pendingAccountDAO.removePendingAccount(id);
			
	}
	
	
	public List<PendingAccount> showPendingAccounts()
	{
		return pendingAccountDAO.showPendingAccounts();
	}	

}
