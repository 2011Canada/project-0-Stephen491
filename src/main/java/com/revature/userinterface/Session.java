package com.revature.userinterface;

import com.revature.models.UserAccount;

public class Session {
	UserAccount account;
	String username;
	String firstName;
	String lastName;
	boolean isEmployee; 
	boolean isLoggedIn;
	int initOption;
	int loginType;
	int bankOption;
	int employeeOption;
	
	public Session() {
		account = null;
		username = null;
		firstName = null;
		lastName = null;
		isEmployee = false;
		isLoggedIn = false;
		initOption = 0;
		bankOption = 0;
	}
	
	
	public void setAccount(UserAccount account) {
		this.account = account;
		username = account.getUsername();
		firstName = account.getFirstName();
		lastName = account.getLastName();
	}
	
	public void login() {
		isLoggedIn = true;
	}
	public void logout() {
		account = null;
		username = null;
		firstName = null;
		lastName = null; 
		isEmployee = false;
		isLoggedIn = false;
		initOption = 0;
		loginType = 0;
		bankOption = 0;
	}
	
	public int getEmployeeOption() {
		return employeeOption;
	}


	public void setEmployeeOption(int employeeOption) {
		this.employeeOption = employeeOption;
	}


	public UserAccount getAccount() {
		return account;
	}
	
	public boolean isLoggedIn() {
		return isLoggedIn;
	}
	public boolean isLoggedOut() {
		return !isLoggedIn;
	}
	public void setInitOption(int option) {
		initOption = option;
	}
	public int getInitOption() {
		return initOption;
	}
	public void setLoginType(int type) {
		this.loginType = type;
	}
	public int getLoginType() {
		return loginType;
	}
	 public void SetBankOption(int option) {
		 bankOption = option;
	 }
	public int getBankOption() {
		return bankOption;
	}
}
