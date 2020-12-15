package com.revature.exceptions;

public class DuplicateEntryException extends Throwable{
	String message = "That account already exists";
	
	public String toString() {
		return message;
	}
	
		
}
