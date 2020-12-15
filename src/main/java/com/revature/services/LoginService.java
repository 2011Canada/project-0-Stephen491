package com.revature.services;

import com.revature.exceptions.InvalidAccountException;
import com.revature.models.UserAccount;
import com.revature.repository.UserAccountsDAO;
import com.revature.repository.UserAccountsDAOPostgresImpl;

public class LoginService {
	
	UserAccountsDAO userAccountDAO ;
	
	public LoginService() {
		userAccountDAO = new UserAccountsDAOPostgresImpl();
	}
	
	public ActionStatus verifyCredentials(String userId, String password) {
		
		try {
			if(userAccountDAO.verifyCredentials(userId, password)) {
				return new ActionStatus(false, false, false, false, false);
			}
			else {
				return new ActionStatus(true, false, false, false, true);
			}
		}
		catch(InvalidAccountException e) {
			return new ActionStatus(true, false, false, true, false);
		}
		
	}
	
	
	public UserAccount getUserAccount(String userid) {
		
		try {
			return userAccountDAO.getUserAccount(userid);
			
		}
		catch(InvalidAccountException e) {
			return null;
		}
	}
	
	
	
}
