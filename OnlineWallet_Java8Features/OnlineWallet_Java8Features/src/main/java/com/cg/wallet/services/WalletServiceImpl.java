package com.cg.wallet.services;

import java.time.LocalDate;
import java.util.List;
import java.util.function.Predicate;

import com.cg.wallet.bean.Customer;
import com.cg.wallet.bean.Transaction;
import com.cg.wallet.daos.WalletDAO;
import com.cg.wallet.daos.WalletDAOImpl;
import com.cg.wallet.exception.InsufficientBalanceException;
import com.cg.wallet.exception.InvalidCredentialsException;

public class WalletServiceImpl implements WalletService {
	
	WalletDAO walletDAO = new WalletDAOImpl();

	@Override
	public boolean validateFields(String firstName, String lastName, String mobileNumber, String email, int pin,
			int confirmPin) {
		
		if(firstName != null && lastName != null && mobileNumber != null && email != null && pin != 0 && confirmPin != 0) {
			
			if(mobileNumber.matches("[0-9]{10}") && email.matches("[A-Za-z0-9]+@[a-z]+.[a-z]+") && Integer.toString(pin).matches("[0-9]{4}") && pin == confirmPin) {
				return true;
			}
		}
		
		try {
			throw new InvalidCredentialsException("Invalid Credentials!");
		}
		catch(InvalidCredentialsException e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public Customer createWalletHolder(Customer walletHolder) {
		
		return walletDAO.addWalletHolder(walletHolder);
	}

	@Override
	public Customer getWalletHolder(int userID, int walletID, int pin) {
		
		Customer walletHolder = walletDAO.findWalletHolder(userID);
		
		if(walletHolder != null) {
			if(walletHolder.getWallet().getWalletID() == walletID && walletHolder.getWallet().getPin() == pin) {
				return walletHolder;
			}
		}
		return null;
	}

	@Override
	synchronized public Customer addAmount(Customer walletHolder, int amount) {
		
		int balance = walletHolder.getWallet().getBalance();
			
		walletHolder.getWallet().setBalance(balance + amount);
												  
		Transaction transaction = new Transaction("      Deposit     ", LocalDate.now(), amount, walletHolder);
		walletDAO.addTransaction(transaction);
			
		return walletDAO.updateWallet(walletHolder);
	}

	@Override
	public boolean amountValidity(int amount) {
		
		Predicate<Integer> check = (n) -> {
			try {
				if(n > 0) {
					return true;
				}
				else {
					throw new InvalidCredentialsException("Invalid Amount!");
				}
			}
			catch(InvalidCredentialsException e) {
				System.out.println(e);
				return false;
			}
		};
		
		return check.test(amount);
	}

	@Override
	public int getBalance(int userID) {
		
		return walletDAO.findBalance(userID);
	}

	@Override
	public boolean recipientValidity(int userID, int walletID) {
		
		Customer walletHolder = walletDAO.findWalletHolder(userID);
		
		try {
			if(walletHolder != null && walletHolder.getWallet().getWalletID() == walletID) {
				return true;
			}
			else {
				throw new InvalidCredentialsException("Invalid Recipient Credentials!");
			}
		}
		catch(InvalidCredentialsException e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	synchronized public void transferFunds(Customer sender, int recipientUserID, int amount) {
		
		Customer recipient = walletDAO.findWalletHolder(recipientUserID); 
		
		int senderBalance = sender.getWallet().getBalance();
		int recipientBalance = recipient.getWallet().getBalance();
		
		sender.getWallet().setBalance(senderBalance - amount);
		recipient.getWallet().setBalance(recipientBalance + amount);
		
		walletDAO.updateWallet(sender);
		walletDAO.updateWallet(recipient);
		
		Transaction transaction1 = new Transaction("Amount Transferred", LocalDate.now(), amount, sender);
		walletDAO.addTransaction(transaction1);
		
		Transaction transaction2 = new Transaction(" Amount Received  ", LocalDate.now(), amount, recipient);
		walletDAO.addTransaction(transaction2);
	}

	@Override
	public boolean balanceValidity(Customer walletHolder, int amount) {
		
		try {
			if(walletHolder.getWallet().getBalance() < amount) {
				throw new InsufficientBalanceException("Insufficient Balance for given Amount!");
			}
			else {
				return true;
			}
		}
		catch(InsufficientBalanceException e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	synchronized public Customer withdrawAmount(Customer walletHolder, int amount) {
		
		int balance = walletHolder.getWallet().getBalance();
		
		walletHolder.getWallet().setBalance(balance - amount);
		 										  
		Transaction transaction = new Transaction("     Withdraw     ", LocalDate.now(), amount, walletHolder);
		walletDAO.addTransaction(transaction);
		
		return walletDAO.updateWallet(walletHolder);
	}

	@Override
	public List<Transaction> getLastTenTransaction(Customer walletHolder) {
		
		return walletDAO.findLastTenTransaction(walletHolder.getUserID());
	}
	
	@Override
	public boolean dateValidity(String date) {
		
		try {
			if(date.matches("[0-9]{4}-[0-9]{2}-[0-9]{2}")) {
				return true;
			}
			else {
				throw new InvalidCredentialsException("Invalid Date!");
			}
		}
		catch(InvalidCredentialsException e) {
			System.out.println(e);
			return false;
		}
	}

	@Override
	public List<Transaction> getTransactionOnDate(Customer walletHolder, String date) {
		
		return walletDAO.findTransactionOnDate(walletHolder.getUserID(), date);
	}
}