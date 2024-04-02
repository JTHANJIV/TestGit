package com.cg.wallet.bean;

public class Wallet {
	
	private int walletID;
	private int pin;
	private int balance;
	
	public Wallet(int walletID, int pin, int balance) {
		super();
		this.walletID = walletID;
		this.pin = pin;
		this.balance = balance;
	}
	
	public Wallet(int pin) {
		super();
		this.pin = pin;
	}

	public Wallet() {
		super();
	}

	public int getWalletID() {
		return walletID;
	}

	public void setWalletID(int walletID) {
		this.walletID = walletID;
	}

	public int getPin() {
		return pin;
	}

	public void setPin(int pin) {
		this.pin = pin;
	}

	public int getBalance() {
		return balance;
	}

	public void setBalance(int balance) {
		this.balance = balance;
	}

	@Override
	public String toString() {
		return "Wallet [walletID=" + walletID + ", pin=" + pin + ", balance=" + balance + "]";
	}
}
