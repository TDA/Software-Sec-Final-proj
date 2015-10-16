package edu.asu.ss2015.group4.dto;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class TransactionDTO {
	
	private int transactionID;
	private String transactionType;
	private float amount;
	private String transactionStartUser;
	private String transactionStartAccountID;
	private String transactionEndUser;
	private String transactionEndAccountID;
	private String authorizedManagerID;
	private Timestamp transactionTime;
	private boolean approved;


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
	public float getAmount() {
		return amount;
	}
	public void setAmount(float amount) {
		this.amount = amount;
	}
	public String getTransactionStartUser() {
		return transactionStartUser;
	}
	public void setTransactionStartUser(String transactionStartUser) {
		this.transactionStartUser = transactionStartUser;
	}
	public String getTransactionStartAccountID() {
		return transactionStartAccountID;
	}
	public void setTransactionStartAccountID(String transactionStartAccountID) {
		this.transactionStartAccountID = transactionStartAccountID;
	}
	public String getTransactionEndUser() {
		return transactionEndUser;
	}
	public void setTransactionEndUser(String transactionEndUser) {
		this.transactionEndUser = transactionEndUser;
	}
	public String getTransactionEndAccountID() {
		return transactionEndAccountID;
	}
	public void setTransactionEndAccountID(String transactionEndAccountID) {
		this.transactionEndAccountID = transactionEndAccountID;
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
	

}
