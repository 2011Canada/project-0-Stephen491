package com.revature.userinterface;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

import com.revature.models.PendingTransfer;
import com.revature.services.FundsService;

public class PendingTransfersMenu implements Menu{
	Scanner scanner;
	FundsService fs;
	int transferid;
	PendingTransfer transfer;
	
	public PendingTransfersMenu() {
		scanner = new Scanner(System.in);
		fs = new FundsService();
	}
	
	
	public Session display(Session session) {
		List<PendingTransfer> results;
		PendingTransfer transfer = null;
		String userInput = null;
		
		results =  fs.showTransfers(session.getAccount().getUsername());
		for(PendingTransfer t: results) {
			
			
			System.out.println("Transfer ID: "+t.getTransferId());
			System.out.println("Amount: "+t.getAmount());
			System.out.print("from "+t.getSender()+" to "+t.getReceiver());
			System.out.println("");
			System.out.println("--------------");
			
		}
		
		System.out.println("Please enter the transfer ID of the transfer you wish to accept or cancel, or enter a non-digit to exit.");
		
		try {	
			transferid = scanner.nextInt();
		}
		catch(InputMismatchException e) {
			return session;
		}
		
		scanner.nextLine();
		
		for(PendingTransfer t: results) {
			if(t.getTransferId()==transferid) {
				transfer = t;
			}
		}
		
		if(transfer == null) {
			System.out.println("Please enter a valid transfer ID.");
		}
		else if(session.getAccount().getUsername().equals(transfer.getSender())) {
			System.out.println("Do you wish to cancel this transfer? Enter C to cancel, or any other key to return to other pending transfers.");
			userInput = scanner.nextLine();
			if(userInput.toLowerCase().equals("c")) {
				fs.cancelTransfer(transfer.getTransferId());
				System.out.println("Transfer has successfully been cancelled");
			}
		}
		
		else if(session.getAccount().getUsername().equals(transfer.getReceiver())) {
			System.out.println("Do you wish to accept this transfer? Enter A to accept, D to decline, or any other key to return to other pending transfers.");
			userInput = scanner.nextLine();
			if(userInput.toLowerCase().equals("d")) {
				fs.cancelTransfer(transfer.getTransferId());
				System.out.println("Transfer has successfully been cancelled");
			}
			else if(userInput.toLowerCase().equals("a")) {
				fs.transferFunds(transfer);
			}
		}
		
		
		return session;
	}	
	public void resetMenu() {
		
	}
}
