package com.cg.wallet.daos;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.cg.wallet.bean.Customer;
import com.cg.wallet.bean.Transaction;
import com.cg.wallet.utils.Utils;

public class WalletDAOImpl implements WalletDAO {

	@Override
	public Customer addWalletHolder(Customer walletHolder) {
		
		//adding new Customer to the walletHolders collection
		
		walletHolder.setUserID(Utils.USER_ID_COUNTER++);
		walletHolder.getWallet().setWalletID(Utils.WALLET_ID_COUNTER++);
		walletHolder.getWallet().setBalance(0);
		
		Utils.walletHolders.put(walletHolder.getUserID(), walletHolder);
		
		return walletHolder;
	}

	@Override
	public Customer findWalletHolder(int userID) {
		
		//using stream to retrieve the Customer
		
		return Utils.getCustomers().values().stream().filter(c -> c.getUserID() == userID).findFirst().orElse(null);
		
	}

	@Override
	public Customer updateWallet(Customer walletHolder) {
		
		//updating the Customer
		
		Utils.walletHolders.put(walletHolder.getUserID(), walletHolder);
		
		Customer customer = Utils.getCustomers().values().stream().filter(c -> c.getUserID() == walletHolder.getUserID()).findAny().orElse(null); 			
		return customer;
	}

	@Override
	public int findBalance(int userID) {
		
		//finding balance of an user using streams
		
		int balance = Utils.getCustomers().values().stream().filter(c -> c.getUserID() == userID).map(c -> c.getWallet().getBalance()).findAny().orElse(0);
		
		return balance;
	}

	@Override
	public void addTransaction(Transaction transaction) {
		
		//adding new transaction to the transactions collection
		
		transaction.setTransactionID(Utils.TRANSACTION_ID_COUNTER++);
		
		Utils.transactions.add(transaction);
	}

	@Override
	public List<Transaction> findLastTenTransaction(int userID) {
		
		//finding the last ten transactions of an user using streams
		
		List<Transaction> userTransactions = Utils.getTransactions().stream().filter(t -> t.getUser().getUserID() == userID).collect(Collectors.toList());
		List<Transaction> lastTenTransactions = new ArrayList<Transaction>();
		
		int n = userTransactions.size();
		int count = 0;
		
		for(int i = n-1; i >= 0; i--) {
			
			lastTenTransactions.add(userTransactions.get(i));
			count = count + 1;
			
			if(count == 10) {
				break;
			}	
			
		}
		return lastTenTransactions;
	}

	@Override
	public List<Transaction> findTransactionOnDate(int userID, String date) {
		
		//finding the transactions of an user on a specified date
		
		List<Transaction> result = Utils.getTransactions().stream().filter(t -> t.getTransactionDate().toString().equals(date) && t.getUser().getUserID() == userID).collect(Collectors.toList());
		return result;
		
	}

}
