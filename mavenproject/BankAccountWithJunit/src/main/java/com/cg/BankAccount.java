package com.cg;

public class BankAccount {
private double balance;

public BankAccount() {
	
	this.balance = 0.0;
}

public double getBalance() {
	return balance;
}
public void deposit(double amount) {
	if(amount<=0) {
		throw new IllegalArgumentException("Deposit should be positive.");
	}
	balance=balance+amount;
}
public void withdraw(double amount) {
	if(amount<=0) {
		throw new IllegalArgumentException("Withdraw should be positive.");
	}
	if(amount>balance) {
		throw new IllegalArgumentException("Insufficient funds.");
	}
	balance=balance-amount;
}
}
