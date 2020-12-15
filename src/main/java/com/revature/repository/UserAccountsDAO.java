package com.revature.repository;

import com.revature.exceptions.DuplicateEntryException;
import com.revature.exceptions.InvalidAccountException;
import com.revature.models.UserAccount;
import java.util.List;

public interface UserAccountsDAO {
	
	public UserAccount getUserAccount(String userID) throws InvalidAccountException;
	public List<UserAccount> getAllUserAccounts();
	public double getBalance(String userID) throws InvalidAccountException;
	public boolean verifyCredentials(String userID, String pin) throws InvalidAccountException;
	public boolean createAccount(String userID, String firstName, String lastName, String pin)  throws DuplicateEntryException;
	public boolean createAccount(UserAccount account)  throws DuplicateEntryException;
	public boolean withdraw(String userID, double amount) throws InvalidAccountException;
	public boolean withdraw(UserAccount userAccount, double amount) throws InvalidAccountException;
	public boolean deposit(String userID, double amount) throws InvalidAccountException;
	public boolean deposit(UserAccount userAccount, double amount) throws InvalidAccountException;
	public boolean transferFunds(String user1, String user2, double amount) throws InvalidAccountException;
	
}
