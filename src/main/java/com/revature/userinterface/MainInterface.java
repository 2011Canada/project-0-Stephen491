package com.revature.userinterface;

import com.revature.models.UserAccount;


public class MainInterface {
	public static void main(String args[]) {
		
		UserAccount account;
		Menu initMenu = new InitialMenu();
		SignInMenu loginMenu = new SignInMenu();
		Menu registrationMenu = new AccountCreationMenu(); 
		Menu bankOptionsMenu = new BankOptionsMenu();
		Menu depositMenu;
		Menu withdrawMenu;
		Menu balanceMenu;
		Menu transactionsMenu;
		Menu transferMenu;
		Menu pendingTransfersMenu;
		Menu employeeMenu = new EmployeeMenu();
		Menu pendingAccountsMenu; 
		Menu employeeAccessAccountMenu;
		Menu allTransactionsMenu;
		int initChoice;
		int validLogin = 0;
		int bankOptionsChoice = 0;
		int employeeOptionsChoice = 0;
		Session currentSession = new Session();
		
		
		while(true) {
			currentSession = initMenu.display(currentSession); 
			initChoice = currentSession.getInitOption();
			if(initChoice==1) {
				loginMenu.display(currentSession);
				validLogin = currentSession.getLoginType();
				if(validLogin==1) {
					account = loginMenu.getAccount();
					//show options
					while(initChoice==1) {
					bankOptionsMenu.display(currentSession);
					bankOptionsChoice = currentSession.getBankOption();
						if(bankOptionsChoice==1) {
							depositMenu = new DepositMenu();
							depositMenu.display(currentSession);
							currentSession.SetBankOption(0);
							bankOptionsMenu.resetMenu();
							continue;
							//deposit
						}
						else if(bankOptionsChoice==2) {
							withdrawMenu = new WithdrawMenu();
							withdrawMenu.display(currentSession);
							currentSession.SetBankOption(0);
							bankOptionsMenu.resetMenu();
							continue;
							//withdraw
						}
						else if(bankOptionsChoice==3) {
							balanceMenu = new BalanceMenu();
							balanceMenu.display(currentSession);
							currentSession.SetBankOption(0);
							bankOptionsMenu.resetMenu();
							continue;
						}
						else if(bankOptionsChoice==4) {
							//show all the user's transactions
							transactionsMenu = new TransactionsMenu();
							transactionsMenu.display(currentSession);
							currentSession.SetBankOption(0);
							bankOptionsMenu.resetMenu();
							continue;
						}
						else if(bankOptionsChoice==5) {
							transferMenu = new TransferMenu(); 
							transferMenu.display(currentSession);
							currentSession.SetBankOption(0);
							bankOptionsMenu.resetMenu();
							continue;
						
					
						}
						else if(bankOptionsChoice==6) {
							pendingTransfersMenu = new PendingTransfersMenu(); 
							pendingTransfersMenu.display(currentSession);
							currentSession.SetBankOption(0);
							bankOptionsMenu.resetMenu();
							continue;
							
						}
						else if(bankOptionsChoice==7) {
							//logout
							initChoice = 0;
							bankOptionsMenu.resetMenu();
							loginMenu.resetMenu();
							initMenu.resetMenu();
							currentSession.logout();
							
						}
					
					}
					
				}//if validLogin is 1 or regular user
				else if(validLogin==2) {
					//show employee options
					account = loginMenu.getAccount();
					while(initChoice==1) {
						employeeMenu.display(currentSession);
						employeeOptionsChoice = currentSession.getEmployeeOption();
						
						if(employeeOptionsChoice==1) {
							pendingAccountsMenu = new PendingAccountsMenu();
							pendingAccountsMenu.display(currentSession);
							currentSession.setEmployeeOption(0);
							employeeMenu.resetMenu();
							//show pending accounts menu
							
						}
						else if(employeeOptionsChoice==2) {
							employeeAccessAccountMenu = new EmployeeAccessAccountMenu();
							employeeAccessAccountMenu.display(currentSession);
							currentSession.setEmployeeOption(0);
							employeeMenu.resetMenu();
							//show accounts menu
							
							
						}
						else if(employeeOptionsChoice==3) {
							allTransactionsMenu = new AllTransactionsMenu();
							allTransactionsMenu.display(currentSession);
							currentSession.setEmployeeOption(0);
							employeeMenu.resetMenu();
							//show all transactions
						}
						else if(employeeOptionsChoice==4) {
							//logout
							initChoice = 0;
							bankOptionsMenu.resetMenu();
							loginMenu.resetMenu();
							initMenu.resetMenu();
							currentSession.logout();
						}
					}
				}//if user is employee
			}//initChoice is 1 or sign in
			
			else if(initChoice==2) {
				//show registration menu
				registrationMenu.display(currentSession);
				initChoice = 0;
			}
			
		}//end while true
	}//end main method
}
