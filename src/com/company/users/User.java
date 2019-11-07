package com.company.users;

import java.util.Random;

public class User {

	private String name;
	private double balance;
	private String accountNumber;
	private double creditLimit;


	public double getCreditLimit() {
		return creditLimit;
	}

	public void setCreditLimit(double creditLimit) {
		this.creditLimit = creditLimit;
	}

	public String getAccountNumber() {
		return accountNumber;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public User(String name, double balance, double creditLimit) {
		this.name = name;
		this.balance = balance;
		this.creditLimit = creditLimit;
		Random random = new Random();
		this.accountNumber = "LT" + (random.nextInt(99999 - 10000) + 10000);

	}
	
	@Override
	public String toString() {
		return "User{" +
				"name='" + name + '\'' +
				", balance=" + balance +
				", accountNumber='" + accountNumber + '\'' +
				'}';
	}
}
