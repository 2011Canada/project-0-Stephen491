package com.revature.repository;

import java.util.List;

import com.revature.exceptions.DuplicateEntryException;
import com.revature.models.PendingAccount;
import com.revature.models.UserAccount;

public interface PendingAccountsDAO {
	
	public boolean createPendingAccount(String userID, String firstName, String lastName, String pin) throws DuplicateEntryException;
	public boolean createPendingAccount(UserAccount account) throws DuplicateEntryException;
	public List<PendingAccount> showPendingAccounts(); 
	public boolean removePendingAccount(int id);
	
}
