package com.cg.wallet.bean;

import java.time.LocalDate;

public class Transaction {

	private int transactionID;
	private String transactionType;
	private LocalDate transactionDate;
	private int amount;
	private Customer user;
	
	public Transaction(int transactionID, String transactionType, LocalDate transactionDate, int amount, Customer user) {
		
		super();
		this.transactionID = transactionID;
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.user = user;
	}
	
	public Transaction(String transactionType, LocalDate transactionDate, int amount, Customer user) {
		
		super();
		this.transactionType = transactionType;
		this.transactionDate = transactionDate;
		this.amount = amount;
		this.user = user;
	}

	public Transaction() {
		super();
	}

	public int getTransactionID() {
		return transactionID;
	}

	public void setTransactionID(int transactionID) {
		this.transactionID = transactionID;
	}

	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public LocalDate getTransactionDate() {
		return transactionDate;
	}

	public void setTransactionDate(LocalDate transactionDate) {
		this.transactionDate = transactionDate;
	}

	public int getAmount() {
		return amount;
	}

	public void setAmount(int amount) {
		this.amount = amount;
	}

	public Customer getUser() {
		return user;
	}

	public void setUser(Customer user) {
		this.user = user;
	}

	@Override
	public String toString() {
		return "TransactionID: " + transactionID + " | TransactionType: " + transactionType
				+ " | TransactionDate: " + transactionDate + " | Amount=" + amount + " | UserID: " + user.getUserID();
	}
}
