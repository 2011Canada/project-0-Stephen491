package com.revature.models;

public class UserAccount {
	
	private String username; 
	private String firstName;
	private String lastName;
	private String pin;
	private double balance;
	private boolean isEmployee;
	
	
	
	
	public UserAccount(String username, String firstName, String lastName, String pin, double balance, boolean isEmployee) {
		super();
		this.username = username;
		this.firstName = firstName;
		this.lastName = lastName;
		this.pin = pin;
		this.balance = balance;
		this.isEmployee = isEmployee;
	}



	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	
	
	public String getFirstName() {
		return firstName;
	}



	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}



	public String getLastName() {
		return lastName;
	}



	public void setLastName(String lastName) {
		this.lastName = lastName;
	}



	public boolean isEmployee() {
		return isEmployee;
	}



	public void setEmployee(boolean isEmployee) {
		this.isEmployee = isEmployee;
	}



	public String getPin() {
		return pin;
	}
	public void setPin(String pin) {
		this.pin = pin;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	
	
	
	
	
	
	
	
}
