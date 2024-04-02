package com.cg.wallet.daos;

import java.util.List;

import com.cg.wallet.bean.Customer;
import com.cg.wallet.bean.Transaction;

public interface WalletDAO {

	Customer addWalletHolder(Customer walletHolder);
	
	Customer findWalletHolder(int userID);
	
	Customer updateWallet(Customer walletHolder);
	
	int findBalance(int userID);
	
	void addTransaction(Transaction transaction);
	
	List<Transaction> findLastTenTransaction(int userID);
	
	List<Transaction> findTransactionOnDate(int userID, String date);
}
