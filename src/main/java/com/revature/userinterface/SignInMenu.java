package com.revature.userinterface;

import java.util.Scanner;

import com.revature.services.LoginService;
import com.revature.models.UserAccount;
import com.revature.services.AccountsService;
import com.revature.services.ActionStatus;

public class SignInMenu implements Menu{
	Scanner scanner;
	LoginService loginService;
	AccountsService accountService;
	String username;
	String password;
	ActionStatus status;
	
	public SignInMenu() {
		scanner = new Scanner(System.in);
		loginService = new LoginService();
		accountService = new AccountsService();
	}
	
	
	
	public Session display(Session session) {
		
		System.out.println("Please enter your username."); 
		username = scanner.nextLine();
		System.out.println("Please enter your password.");
		password = scanner.nextLine();
		
		status = loginService.verifyCredentials(username, password);
		if(status.isHasIssue()) {
			if(status.isUnauthorized()) {
				System.out.println("Incorrect password.");
			}
			else if(status.isInvalidAccount()) {
				System.out.println("There is no account for that username.");
			}
			
			
		}
		else {
			//correct login, determine if is employee or not
			
			session.login();
			session.setAccount(loginService.getUserAccount(username));
			//if not employee set login type to 1
			if(!session.getAccount().isEmployee()) {
				session.setLoginType(1);
			}
			else {
				session.setLoginType(2);
			}
		
		}
		
		
		
		return session;
	}
	
	public UserAccount getAccount() {
		UserAccount account = loginService.getUserAccount(username);
		resetMenu();
		return account;
		
	};
	
	
	public void resetMenu() {
		username = null;
		password = null;
		status = null;
	}
}
