package com.cg.wallet.bean;

public class Customer {
	
	private int userID;
	private String firstName;
	private String lastName;
	private String mobileNumber;
	private String email;
	private Wallet wallet;
	
	public Customer(int userID, String firstName, String lastName, String mobileNumber, String email,
			Wallet wallet) {
		
		super();
		this.userID = userID;
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.wallet = wallet;
	}
	
	public Customer(String firstName, String lastName, String mobileNumber, String email, Wallet wallet) {
		super();
		this.firstName = firstName;
		this.lastName = lastName;
		this.mobileNumber = mobileNumber;
		this.email = email;
		this.wallet = wallet;
	}

	public Customer() {
		super();
	}

	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getMobileNumber() {
		return mobileNumber;
	}

	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Wallet getWallet() {
		return wallet;
	}

	public void setWallet(Wallet wallet) {
		this.wallet = wallet;
	}

	@Override
	public String toString() {
		return "WalletHolder [userID=" + userID + ", firstName=" + firstName + ", lastName=" + lastName
				+ ", mobileNumber=" + mobileNumber + ", email=" + email + ", wallet=" + wallet + "]";
	}
}
