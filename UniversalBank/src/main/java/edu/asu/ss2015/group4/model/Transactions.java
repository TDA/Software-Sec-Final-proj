package edu.asu.ss2015.group4.model;

import java.security.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class Transactions {

	private int transactionId;
	private String transactionType;

	@NotEmpty(message = "Please enter some amount")
	private String amount;
	@NotEmpty(message = "Please enter your AccountId")
	private String transactionAccountID;

	private String authorizedManagerID;
	private Timestamp transactionTime;
	private boolean approved;
	private Timestamp ApprovedTime;
	private String Comments;
	private int Authorisebank;

	public int getTransactionId() {
		return transactionId;
	}

	public void setTransactionId(int transactionId) {
		this.transactionId = transactionId;
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
		return Comments;
	}

	public void setComments(String comments) {
		this.Comments = comments;
	}

	public String getTransactionAccountID() {
		return transactionAccountID;
	}

	public void setTransactionAccountID(String transactionAccountID) {
		this.transactionAccountID = transactionAccountID;
	}

	public int getAuthorisebank() {
		return Authorisebank;
	}

	public void setAuthorisebank(int authorisebank) {
		Authorisebank = authorisebank;
	}

}
