package com.revature.exceptions;

public class InvalidAccountException extends Throwable {
	String message = "Account was not found, please try again";
	
	public String toString() {
		return message;
	}
}
