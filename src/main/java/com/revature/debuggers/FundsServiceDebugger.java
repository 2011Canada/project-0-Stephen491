package com.revature.debuggers;

import com.revature.exceptions.InvalidAccountException;
import com.revature.models.UserAccount;
import com.revature.repository.PendingTransfersDAO;
import com.revature.repository.PendingTransfersDAOImpl;
import com.revature.repository.UserAccountsDAO;
import com.revature.repository.UserAccountsDAOImpl;
import com.revature.services.FundsService;

public class FundsServiceDebugger {
	
	
	public static void main(String args[]) {
		
		FundsService fs = new FundsService(); 
		UserAccountsDAO dao = new UserAccountsDAOImpl(); 
		PendingTransfersDAO dao2 = new PendingTransfersDAOImpl();
		UserAccount testAccount;
		
		try {
			testAccount = dao.getUserAccount("113");
			System.out.println(dao2.removeTransfer(1));
			
		
			
			
			
		}
		catch(InvalidAccountException e) {
			System.out.println(e);
		}
		
		
		
		
		
		
	}
	
	
	
	
	
	
}
