package com.revature.models;

public class PendingTransfer {
	double amount;
	String sender;
	String receiver;
	int transferId;
	
	
	
	public PendingTransfer() {
		amount = 0;
		sender = null;
		receiver = null;
		transferId = 0;
	}
	public PendingTransfer(double amount, String sender, String receiver, int transactionType, int transactionId) {
		super();
		this.amount = amount;
		this.sender = sender;
		this.receiver = receiver;
		this.transferId = transactionId;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getSender() {
		return sender;
	}
	public void setSender(String sender) {
		this.sender = sender;
	}
	public String getReceiver() {
		return receiver;
	}
	public void setReceiver(String receiver) {
		this.receiver = receiver;
	}
	public int getTransferId() {
		return transferId;
	}
	public void setTransferId(int transferId) {
		this.transferId = transferId;
	} 
	
	
	
}
