package com.revature.repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.revature.exceptions.InvalidAccountException;
import com.revature.util.BankDatabaseConnectionPostgres;
import com.revature.models.PendingTransfer;

public class PendingTransfersDAOPostgresImpl implements PendingTransfersDAO{
	
	Connection conn;
	BankDatabaseConnectionPostgres cf ;
	
	public PendingTransfersDAOPostgresImpl() {
		cf = BankDatabaseConnectionPostgres.getConnectionFactory();
		conn = cf.getConnection();
	}

	
	public boolean addTransfer(String sender, String receiver, double amount) throws InvalidAccountException{
		// TODO Auto-generated method stub
		String sqlFindReceiver = "SELECT * FROM bank.useraccounts WHERE username = ?;";
		String sql = "INSERT INTO bank.pendingtransfers (sender, receiver, amount) VALUES (?, ?, ?);";
		try {
			PreparedStatement stFind = conn.prepareStatement(sqlFindReceiver);
			stFind.setString(1, receiver);
			if(!stFind.executeQuery().next()) {
				throw new InvalidAccountException();
			}
			
			PreparedStatement st = conn.prepareStatement(sql);
			st.setString(1, sender);
			st.setString(2, receiver);
			st.setDouble(3, amount);
			return (st.executeUpdate()==1);
			
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
		
	}
	
	public boolean removeTransfer(int transferId) {
		String sql = "DELETE FROM bank.pendingtransfers WHERE id = ?;";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql); 
			st.setInt(1, transferId);
			return (st.executeUpdate()==1);
		}
		catch(SQLException e) {
			e.printStackTrace();
			return false;
		}
	}
	
	
	public List<PendingTransfer> showTransfers(String userid) {
		List<PendingTransfer> transfers = new ArrayList<PendingTransfer>(); 
		String sql = "SELECT * FROM bank.pendingtransfers WHERE ( receiver = ? OR sender = ? ) ;";
		
		try {
			PreparedStatement st = conn.prepareStatement(sql); 
			
			st.setString(1, userid);
			st.setString(2, userid);
			
			ResultSet results = st.executeQuery();
			
			while(results.next()) {
				PendingTransfer transfer = new PendingTransfer(); 
				transfer.setAmount(results.getDouble("amount"));
				transfer.setSender(results.getString("sender"));
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
