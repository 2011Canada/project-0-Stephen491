package com.revature.services;

public class ActionStatus {
	boolean hasIssue;
	boolean duplicateEntry;
	boolean withdrawTooMuch;
	boolean invalidAccount;
	boolean unauthorized;
	
	
	
	public ActionStatus(boolean hasIssue, boolean duplicateEntry, boolean withdrawTooMuch,
			boolean invalidAccount, boolean unauthorized) {
		super();
		this.hasIssue = hasIssue;
		this.duplicateEntry = duplicateEntry;
		this.withdrawTooMuch = withdrawTooMuch;
		this.invalidAccount = invalidAccount;
		this.unauthorized = unauthorized;
	}
	public boolean isHasIssue() {
		return hasIssue;
	}
	public void setHasIssue(boolean hasIssue) {
		this.hasIssue = hasIssue;
	}
	public boolean isDuplicateEntry() {
		return duplicateEntry;
	}
	public void setDuplicateEntry(boolean duplicateEntry) {
		this.duplicateEntry = duplicateEntry;
	}
	public boolean isWithdrawTooMuch() {
		return withdrawTooMuch;
	}
	public void setWithdrawTooMuch(boolean withdrawTooMuch) {
		this.withdrawTooMuch = withdrawTooMuch;
	}
	public boolean isInvalidAccount() {
		return invalidAccount;
	}
	public void setInvalidAccount(boolean invalidAccount) {
		this.invalidAccount = invalidAccount;
	}
	
	public boolean isUnauthorized() {
		return unauthorized;
	}
	
	
	
	
}
