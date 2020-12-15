package com.revature.models;

public class Transaction {
	double amount;
	String giver;
	String receiver;
	int transactionType;
	int transactionId;
	
	
	
	public Transaction() {
		amount = 0;
		giver = null;
		receiver = null;
		transactionType = 0;
		transactionId = 0;
		
	}
	public Transaction(double amount, String giver, String receiver, int transactionType, int transactionId) {
		super();
		this.amount = amount;
		this.giver = giver;
		this.receiver = receiver;
		this.transactionType = transactionType;
		this.transactionId = transactionId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getGiver() {
		return giver;
	}
	public void setGiver(String giver) {
		this.giver = giver;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public int getTransactionType() {
		return transactionType;
	}
	public void setTransactionType(int transactionType) {
		this.transactionType = transactionType;
	}
	public int getTransactionId() {
		return transactionId;
	}
	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	} 
	
	
	
}
