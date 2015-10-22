package edu.asu.ss2015.group4.model;

import java.sql.Timestamp;
import java.util.Date;

/**
 * @author schandramouli
 */
public class BankAccount {
	private String id;
	private String userName;
	private String accountType;
	private double balance = 0.0;
	private Timestamp creationTime;

	public BankAccount() {
	}

	public BankAccount(String id) {
		Date date = new Date();
		this.creationTime = new Timestamp(date.getTime());
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public double getBalance() {
		return balance;
	}

	public void setBalance(double balance) {
		this.balance = balance;
	}

	public Date getCreationTime() {
		return creationTime;
	}

	public void setCreationTime(Timestamp creationTime) {
		this.creationTime = creationTime;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
