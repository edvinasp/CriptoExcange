package com.company;

import com.company.Transaction.Transaction;
import com.company.users.User;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;


public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		int choice;
		List<User> users = new ArrayList<>();

		do {
			System.out.println();
			printMenu();
			choice = sc.nextInt();

			switch (choice) {
				case 1:
					createUser(sc, users);
					break;
				case 2:
					System.out.println("Vartotoju sarasas");
					printUsers(users);
					break;
				case 3:
					checkUser(sc, users);
				case 4:
					transferMoney(sc, users);
				case 5:
					topUpMoney(sc, users);
				case 0:
					break;
				default:
					System.out.println("Bloga ivestis");
			}
		} while (choice != 0);


	}

	private static void topUpMoney(Scanner sc, List<User> users) {
		System.out.println("I kuria saskaita pervesti pinigus?");
		printUsers(users);
		String accountToTransfer = sc.next();
		System.out.println("Kiek pinigu pervesti?");
		double amountToTransfer = sc.nextDouble();

		for (User u : users) {
			if (u.getAccountNumber().equals(accountToTransfer)) {
				u.setBalance(u.getBalance() + amountToTransfer);
			}
		}
	}

	private static void transferMoney(Scanner sc, List<User> users) {
		printUsers(users);
		System.out.println("Iveskite sask numeri is kuri norite padaryti pavedima");
		String accountFrom = sc.next();

		System.out.println("Iveskite sask numeri i kuri norite padaryti pavedima");
		String accountTo = sc.next();

		System.out.println("Iveskite pervedimo suma");

		double amount = sc.nextDouble();

//					users.stream().filter( u -> u.getAccountNumber().equals(accountFrom))
		User from = null;
		User to = null;

		for (User u : users) {
			if (u.getAccountNumber().equals(accountFrom)) {
				from = u;
			}
			if (u.getAccountNumber().equals(accountTo)) {
				to = u;
			}

		}
		users.remove(from);
		users.remove(to);

		Transaction transaction = new Transaction(from, to, amount);

		List<User> transactedUsers = transaction.execute().stream()
				.filter(u -> u != null)
				.collect(Collectors.toList());

		users.addAll(transactedUsers);

		printUsers(users);
	}

	private static void checkUser(Scanner sc, List<User> users) {
		printUsers(users);
		System.out.println("enter which user to delete");

		int deletedUser = sc.nextInt();
		deletedUser++;
		users.remove(deletedUser);
		printUsers(users);
	}

	private static void createUser(Scanner sc, List<User> users) {
		System.out.println("Iveskite savo varda");
		String name = sc.next();

		System.out.println("Iveskite savo turima balansa");
		double balance = sc.nextDouble();

		System.out.println("Iveskite kredito limita");
		int creditLimit = sc.nextInt();

		// create object for user
		User user = new User(name, balance, creditLimit);
		users.add(user);
	}

	public static void printUsers(List<User> users) {
		int i = 0;
		for (User u : users) {
			System.out.println(i + 1 + ". " + u.toString());
			i++;
		}
	}

	public static void printMenu() {
		System.out.println("1. Sukurti vartotoja");
		System.out.println("2. Perziureti ivestus vartotojus");
		System.out.println("3. Istrinti vartotojus");
		System.out.println("4. Pavedimas");
		System.out.println("5. Inesti pinigus");
		System.out.println("*---*---------*-*-*---------");
		System.out.println("0. Baigti darba");
	}

	public static void addMoney() {

	}

}
