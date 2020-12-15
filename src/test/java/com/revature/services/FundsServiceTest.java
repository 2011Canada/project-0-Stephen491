package com.revature.services;

import org.junit.Test;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.PrintStream;


import com.revature.exceptions.InvalidAccountException;
import com.revature.models.UserAccount;
import com.revature.repository.UserAccountsDAO;
import com.revature.repository.UserAccountsDAOPostgresImpl;




public class FundsServiceTest {
	private static FundsService fs = new FundsService(); 
	private UserAccountsDAO ad = new UserAccountsDAOPostgresImpl();
	
	
	@Test
	public void testNegativeWithdrawal() {
		try {
		  PrintStream oldout = System.out;
		  OutputStream os = new ByteArrayOutputStream();
		  double balance = ad.getBalance("testAccount");
		  UserAccount account = ad.getUserAccount("testAccount");
		  System.setOut(new PrintStream(os));
		  String error = "Amount to withdraw exceeds your balance.";
		  fs.withdraw(account, balance+1);
		  String outputReceived = os.toString().trim();
		  assertEquals(error, outputReceived);
		  System.setOut(oldout);
		  
		}
		catch(InvalidAccountException e) {
			e.printStackTrace();
		}
		
		
		
		
	}
	
}
