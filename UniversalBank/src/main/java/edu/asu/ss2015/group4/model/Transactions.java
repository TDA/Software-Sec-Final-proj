package edu.asu.ss2015.group4.model;

import java.security.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class Transactions {

private int transactionId;
private String transactionType;
@NotEmpty(message = "Please select a value")
private String AccountType;
@NotEmpty(message = "Please enter a value")
private String amount;
private String FromTransactionAccountID;
private String ToTransactionAccountID;
private String authorizedManagerID;
private Timestamp transactionTime;
private boolean approved;
private Timestamp ApprovedTime;
private String comments;
private int Authorisebank;
private int criticalTransactions;
private int count;
private Double balance;


	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
	}
public String getAccountType() {
	return AccountType;
}
public void setAccountType(String AccountType) {
	this.AccountType = AccountType;
}
	public String getTransactionType() {
		return transactionType;
	}

	public void setTransactionType(String transactionType) {
		this.transactionType = transactionType;
	}

	public String getAmount() {
		return amount;
	}

	public void setAmount(String amount) {
		this.amount = amount;
	}

	public String getAuthorizedManagerID() {
		return authorizedManagerID;
	}

	public void setAuthorizedManagerID(String authorizedManagerID) {
		this.authorizedManagerID = authorizedManagerID;
	}

	public Timestamp getTransactionTime() {
		return transactionTime;
	}

	public void setTransactionTime(Timestamp transactionTime) {
		this.transactionTime = transactionTime;
	}

	public boolean isApproved() {
		return approved;
	}

	public void setApproved(boolean approved) {
		this.approved = approved;
	}

	public Timestamp getApprovedTime() {
		return ApprovedTime;
	}

	public void setApprovedTime(Timestamp approvedTime) {
		ApprovedTime = approvedTime;
	}

	public String getComments() {
		return comments;
	}

	public void setComments(String comments) {
		this.comments = comments;
	}

	public int getAuthorisebank() {
		return Authorisebank;
	}

	public void setAuthorisebank(int authorisebank) {
		Authorisebank = authorisebank;
}
public int getCriticalTransactions() {
	return criticalTransactions;
}
public void setCriticalTransactions(int criticalTransactions) {
	this.criticalTransactions = criticalTransactions;
	}

public String getToTransactionAccountID() {
	return ToTransactionAccountID;
}

public void setToTransactionAccountID(String toTransactionAccountID) {
	ToTransactionAccountID = toTransactionAccountID;
}

public String getFromTransactionAccountID() {
	return FromTransactionAccountID;
}

public void setFromTransactionAccountID(String fromTransactionAccountID) {
	FromTransactionAccountID = fromTransactionAccountID;
}

public int getCount() {
	return count;
}

public void setCount(int count) {
	this.count = count;
}

public Double getBalance() {
	return balance;
}

public void setBalance(Double balance) {
	this.balance = balance;
}

}

