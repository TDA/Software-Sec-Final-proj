package edu.asu.ss2015.group4.dto;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

import edu.asu.ss2015.group4.model.Transactions;

public class TransactionDTO {

	private int transactionID;
	private String transactionType;
	private String amount;
	private String transactionAccountID;
	private String totransactionAccountID;
	private String authorizedManagerID;
	private Timestamp transactionTime;
	private boolean approved;
	private Timestamp ApprovedTime;
	private String comments;
	private int AuthoriseBank;
	private int criticalTransactions;

	
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
	public String getAmount() {
		return amount;
	}
	public void setAmount(String amount) {
		this.amount = amount;
	}
	public String getTransactionAccountID() {
		return transactionAccountID;
	}
	public void setTransactionAccountID(String transactionAccountID) {
		this.transactionAccountID = transactionAccountID;
	}
	public String getTotransactionAccountID() {
		return totransactionAccountID;
	}
	public void setTotransactionAccountID(String totransactionAccountID) {
		this.totransactionAccountID = totransactionAccountID;
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
	public int getAuthoriseBank() {
		return AuthoriseBank;
	}
	public void setAuthoriseBank(int authoriseBank) {
		AuthoriseBank = authoriseBank;
	}
	public int getCriticalTransactions() {
		return criticalTransactions;
	}
	public void setCriticalTransactions(int criticalTransactions) {
		this.criticalTransactions = criticalTransactions;
	}
	
}