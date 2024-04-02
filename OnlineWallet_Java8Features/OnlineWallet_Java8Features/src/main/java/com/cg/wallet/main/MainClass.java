package com.cg.wallet.main;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;
import java.util.function.Supplier;

import com.cg.wallet.bean.Customer;
import com.cg.wallet.bean.Transaction;
import com.cg.wallet.bean.Wallet;
import com.cg.wallet.services.WalletService;
import com.cg.wallet.services.WalletServiceImpl;

public class MainClass {

	public static void main(String[] args) {
		
		//WalletService walletService = new WalletServiceImpl();
		
		Supplier<WalletService> ws = WalletServiceImpl :: new;
		WalletService walletService = ws.get();
		
		//MainClass obj = new MainClass();
		
		Supplier<MainClass> mc = MainClass :: new;
		MainClass obj = mc.get();
		
		Customer walletHolder;
		Wallet wallet;
		
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
		
			System.out.println("\n-------------------------Welcome to Payment Wallet Application------------------------");
			
			int option;
			
			do {
				
				System.out.println("\nChoose an option:");
				System.out.println("\n1. Register");
				System.out.println("2. Login");
				System.out.println("3. Exit");
				
				System.out.println("\nEnter your option (1/2/3): ");
				
				option = Integer.parseInt(br.readLine());
				
				switch(option) {
				
				case 1:
					System.out.println("\nEnter your First Name: ");
					String firstName = br.readLine();
					
					System.out.println("\nEnter your Last Name: ");
					String lastName = br.readLine();
					
					System.out.println("\nEnter your Mobile Number: ");
					String mobileNumber = br.readLine();
					
					System.out.println("\nEnter your Email ID: ");
					String email = br.readLine();
				
					System.out.println("\nEnter 4 Digit Pin: ");
					int pin = Integer.parseInt(br.readLine());
					
					System.out.println("\nConfirm Pin: ");
					int confirmPin = Integer.parseInt(br.readLine());
					
					boolean valid = walletService.validateFields(firstName, lastName, mobileNumber, email, pin, confirmPin);
					
					if(valid) {
						wallet = new Wallet(pin);
						walletHolder = new Customer(firstName, lastName, mobileNumber, email, wallet);
						
						walletHolder = walletService.createWalletHolder(walletHolder);
						
						System.out.println("\nNew Wallet Created!");
						System.out.println("\nYour User ID   : " + walletHolder.getUserID());
						System.out.println("Your Wallet ID : " + walletHolder.getWallet().getWalletID());
						
						obj.menu(walletHolder);
					}
					
					break;
					
				case 2:
					
					System.out.println("\nEnter your User ID: ");
					int userID = Integer.parseInt(br.readLine());
					
					System.out.println("\nEnter your Wallet ID: ");
					int walletID = Integer.parseInt(br.readLine());
					
					System.out.println("\nEnter your Wallet Pin: ");
					int walletpin = Integer.parseInt(br.readLine());
					
					walletHolder = walletService.getWalletHolder(userID, walletID, walletpin);
					
					if(walletHolder == null) {
						System.out.println("\nInvalid Login Credentials!");
					}
					else {
						System.out.println("\nUser Logged In Successfully! Welcome " + walletHolder.getFirstName());
						obj.menu(walletHolder);
					}
					
					break;
					
				case 3:
					System.out.println("\nExited from Payment wallet Application!");
					break;
					
				default:
					System.out.println("\nInvalid option!");
					break;
				}
			}while(option != 3);
		}
		catch (IOException e) {
			
			e.printStackTrace();
		}
	}
	
	void menu(Customer walletHolder) {
		
		WalletService walletService = new WalletServiceImpl();
		
		int optionMenu;
		
		try {
			BufferedReader br=new BufferedReader(new InputStreamReader(System.in));
			
			do {
			
				System.out.println("\n--------------Menu--------------");
				System.out.println("\n1. Add Amount to Wallet");
				System.out.println("2. View Wallet Account Balance");
				System.out.println("3. Tranfer Funds");
				System.out.println("4. Withdraw Money");
				System.out.println("5. View Last 10 Transactions");
				System.out.println("6. View Transactions of a Date");
				System.out.println("7. Logout");
				
				System.out.println("\nEnter your option: ");
			
				optionMenu = Integer.parseInt(br.readLine());
			
				switch(optionMenu) {
				
				case 1:
					
					System.out.println("Your Current Balance: " + walletHolder.getWallet().getBalance());
					
					System.out.println("\nEnter the Amount to Deposit: ");
					int amount = Integer.parseInt(br.readLine());
					
					if(walletService.amountValidity(amount)) {
						
						walletHolder = walletService.addAmount(walletHolder, amount);
						
						System.out.println("\nYour Updated Balance: " + walletHolder.getWallet().getBalance());
					}
					
					
					break;
					
				case 2:
					
					System.out.println("\nYour Wallet Balance: " + walletService.getBalance(walletHolder.getUserID()));
					
					break;
				
				case 3:
					
					System.out.println("\nEnter Recipients' Wallet ID: ");
					int recipientWalletID = Integer.parseInt(br.readLine());
					
					System.out.println("Enter Recipients' User ID: ");
					int recipientUserID = Integer.parseInt(br.readLine());
					
					System.out.println("\nYour Current Balance: " + walletHolder.getWallet().getBalance());
					
					System.out.println("\nEnter Amount to Transfer: ");
					int transferAmount = Integer.parseInt(br.readLine());
					
					if(walletService.recipientValidity(recipientUserID, recipientWalletID)) {
						
						if(walletService.balanceValidity(walletHolder, transferAmount) == true && walletService.amountValidity(transferAmount) == true) {
						
							walletService.transferFunds(walletHolder, recipientUserID, transferAmount);
							
							System.out.println("\nAmount Tranferred Successfully");
							System.out.println("\nYour Updated Balance: " + walletHolder.getWallet().getBalance());
						}
					}
					
					break;
					
				case 4:
					
					System.out.println("\nEnter Amount to Withdraw: ");
					int withdrawAmount = Integer.parseInt(br.readLine());
					
					if(walletService.amountValidity(withdrawAmount)) {
					
						if(walletService.balanceValidity(walletHolder, withdrawAmount)) {
							
							walletHolder = walletService.withdrawAmount(walletHolder, withdrawAmount);
							System.out.println("\nAmount Withdraw Successful");
							System.out.println("\nAmount Withdrawn    : " + withdrawAmount);
							System.out.println("Your Updated Balance: " + walletHolder.getWallet().getBalance());
						}
					}
					break;
				
				case 5:
					
					List<Transaction> lastTenTransactions = walletService.getLastTenTransaction(walletHolder);
					
					if(lastTenTransactions.isEmpty()) {
						
						System.out.println("\nNo Transactions");
					}
					else {
						System.out.println("\n--------------------------------------------Last Ten Transaction---------------------------------------------\n");
						
						lastTenTransactions.forEach(t -> System.out.println(t));
						
					}
					
					break;
					
				case 6:
					
					System.out.println("\nEnter the Date in YYYY-MM-DD format: ");;
					String date = br.readLine();
					
					if(walletService.dateValidity(date)) {
					
						List<Transaction> transactionsOnDate = walletService.getTransactionOnDate(walletHolder, date);
						
						if(transactionsOnDate.isEmpty()) {
							
							System.out.println("\nNo Transactions on Specified Date");
						}
						else {
							
							System.out.println("\n---------------------------------------Transactions on Given Date----------------------------------------\n");
							
							transactionsOnDate.forEach(t -> System.out.println(t));
							
						}
					}
					break;
					
				case 7:
					System.out.println("\nUser Logged Out from Payment wallet Application!");
					break;
					
				default:
					System.out.println("Invalid Option!");
					break;
				}
			
			}while(optionMenu != 7);
		} 
		catch (NumberFormatException | IOException e) {
			
			e.printStackTrace();
		}
	}
}
