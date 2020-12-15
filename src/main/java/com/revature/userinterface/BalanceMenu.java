package com.revature.userinterface;

import com.revature.services.FundsService;

public class BalanceMenu implements Menu{
	FundsService fs;
	double balance;
	
	public BalanceMenu() {
		fs = new FundsService();
		balance = 0;
	}
	
	

	public Session display(Session session) {
		fs.getBalance(session.getAccount());
		
		return session;
	}

	
	public void resetMenu() {
		// TODO Auto-generated method stub
		
	}
	
}
