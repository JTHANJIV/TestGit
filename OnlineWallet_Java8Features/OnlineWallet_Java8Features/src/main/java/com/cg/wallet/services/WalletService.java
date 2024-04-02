package com.cg.wallet.services;

import java.util.List;

import com.cg.wallet.bean.Customer;
import com.cg.wallet.bean.Transaction;

public interface WalletService {

	boolean validateFields(String firstName, String lastName, String mobileNumber, String email, int pin, int confirmPin);
	
	Customer createWalletHolder(Customer walletHolder);
	
	Customer getWalletHolder(int userID, int walletID, int pin);
	
	Customer addAmount(Customer walletHolder, int amount);
	
	boolean amountValidity(int amount);
	
	int getBalance(int userID);
	
	boolean recipientValidity(int userID, int walletID);
	
	void transferFunds(Customer walletHolder, int recipientUserID, int amount);
	
	boolean balanceValidity(Customer walletHolder, int amount);
	
	Customer withdrawAmount(Customer walletHolder, int amount);
	
	List<Transaction> getLastTenTransaction(Customer walletHolder);
	
	boolean dateValidity(String date);
	
	List<Transaction> getTransactionOnDate(Customer walletHolder, String date);
	
}
