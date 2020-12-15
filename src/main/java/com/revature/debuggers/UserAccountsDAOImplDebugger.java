package com.revature.debuggers;

import com.revature.exceptions.DuplicateEntryException;
import com.revature.exceptions.InvalidAccountException;
import com.revature.models.UserAccount;
import com.revature.repository.UserAccountsDAO;
import com.revature.repository.UserAccountsDAOImpl;
import com.revature.repository.UserAccountsDAOPostgresImpl;

public class UserAccountsDAOImplDebugger {
	
	public static void main(String args[]) {
		UserAccountsDAO userAccountsDAO = new UserAccountsDAOPostgresImpl(); 
		UserAccount testAccount;
		try {
		
			
			System.out.println(userAccountsDAO.verifyCredentials("113", "password123"));
			
			
			
		}
		catch(InvalidAccountException e) {
			System.out.println(e);
		}
		//System.out.println(testAccount.getFirstName());
		
		//System.out.println(userAccountsDAO.verifyCredentials("111", "password"));
		try {
			System.out.println(userAccountsDAO.createAccount("113", "Jane", "Doe", "password123"));
		}
		catch(DuplicateEntryException e) {
			System.out.println(e);
		}
		
		
		//System.out.println(userAccountsDAO.withdraw("111", 1));
		//System.out.println(userAccountsDAO.withdraw(testAccount, 2));
	}
	
}
