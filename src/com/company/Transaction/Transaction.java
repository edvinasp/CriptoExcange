package com.company.Transaction;

import com.company.users.User;

import java.util.ArrayList;
import java.util.List;

public class Transaction {
	private User from;
	private User to;
	private double amount;

	public Transaction(User from, User to, double amount) {
		this.from = from;
		this.to = to;
		this.amount = amount;
	}

	private boolean isValid() {
		return to != null && from != null && from.getBalance() + from.getCreditLimit() >= amount;
	}

	public List<User> execute() {
		List<User> transactedUsers = new ArrayList<>();

		if (isValid()) {
			if (from.getBalance() - amount < 0) {
				double balance = from.getBalance();
				from.setBalance(0);
				from.setCreditLimit(from.getCreditLimit() + (balance - amount));
			} else {
				from.setBalance(from.getBalance() - amount);
			}
			to.setBalance(to.getBalance() + amount);

		}
		else {
			System.out.println("Error: Transakcija nevalidi");
		}
		transactedUsers.add(from);
		transactedUsers.add(to);

		return transactedUsers;
	}

}

