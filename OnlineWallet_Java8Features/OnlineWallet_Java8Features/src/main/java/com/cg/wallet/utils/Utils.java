package com.cg.wallet.utils;

import java.util.ArrayList;
import java.util.HashMap;

import com.cg.wallet.bean.Customer;
import com.cg.wallet.bean.Transaction;

public class Utils {
	
	public static int USER_ID_COUNTER = 1001;
	public static int WALLET_ID_COUNTER = 2001;
	public static int TRANSACTION_ID_COUNTER = 3001;
	
	public static HashMap<Integer, Customer> walletHolders = new HashMap<Integer, Customer>();
	public static ArrayList<Transaction> transactions = new ArrayList<Transaction>();
	
	public static HashMap<Integer, Customer> getCustomers(){
		return walletHolders;
	}
	
	public static ArrayList<Transaction> getTransactions(){
		return transactions;
	}
}
