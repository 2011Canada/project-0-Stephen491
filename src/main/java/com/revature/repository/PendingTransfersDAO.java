package com.revature.repository;

import java.util.List;

import com.revature.exceptions.InvalidAccountException;
import com.revature.models.PendingTransfer;

public interface PendingTransfersDAO {
	public boolean addTransfer(String giver, String receiver, double amount) throws InvalidAccountException;
	public boolean removeTransfer(int transferId);
	public List<PendingTransfer> showTransfers(String userid);
}
