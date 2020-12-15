package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.InvalidAccountException;
import com.revature.util.BankDatabaseConnection;
import com.revature.models.PendingTransfer;

public class PendingTransfersDAOImpl implements PendingTransfersDAO{
	
Connection conn;
	
	public PendingTransfersDAOImpl() {
		
		try {
			conn = BankDatabaseConnection.getConnection();
		}
		catch(SQLException e) {
			e.printStackTrace();
		}
	}

	
	public boolean addTransfer(String giver, String receiver, double amount) throws InvalidAccountException{
		// TODO Auto-generated method stub
		String sqlFindReceiver = "SELECT * FROM UserAccounts WHERE userid=\'"+receiver+"\';";
		String sql = "INSERT INTO PendingTransfers (giver, receiver, amount) VALUES (\'"+giver+"\', \'"+receiver+"\', \'"+amount
				+"\');";
		try {
			Statement stFind = conn.createStatement();
			
			if(!stFind.executeQuery(sqlFindReceiver).next()) {
				throw new InvalidAccountException();
			}
			
			Statement st = conn.createStatement(); 
			return (st.executeUpdate(sql)==1);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean removeTransfer(int transferId) {
		String sql = "DELETE FROM PendingTransfers WHERE id = "+transferId+";";
		
		try {
			Statement st = conn.createStatement(); 
			return (st.executeUpdate(sql)==1);
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public List<PendingTransfer> showTransfers(String userid) {
		List<PendingTransfer> transfers = new ArrayList<PendingTransfer>(); 
		String sql = "SELECT * FROM PendingTransfers WHERE ( receiver = ? OR giver = ? ) ;";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql); 
			
			st.setString(1, userid);
			st.setString(2, userid);
			
			ResultSet results = st.executeQuery();
			
			while(results.next()) {
				PendingTransfer transfer = new PendingTransfer(); 
				transfer.setAmount(results.getDouble("amount"));
				transfer.setSender(results.getString("giver"));
				transfer.setReceiver(results.getString("receiver"));
				transfer.setTransferId(results.getInt("id"));
				transfers.add(transfer);
				
			}
			
			return transfers;
		
		}
		catch(SQLException e) {
			e.printStackTrace();
			return null;
		}
	}
	

		
			
	
	
	
	
	
}
