package edu.asu.ss2015.group4.model;

import java.security.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class Transactions {

private int transactionId;
private String transactionType;
private float amount;
private String transactionAccountID;
private String authorizedManagerID;
private Timestamp transactionTime;
private boolean approved;
private Timestamp ApprovedTime;
private String comments;


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
public float getAmount() {
	return amount;
}
public void setAmount(float amount) {
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
public String getTransactionAccountID() {
	return transactionAccountID;
}
public void setTransactionAccountID(String transactionAccountID) {
	this.transactionAccountID = transactionAccountID;
}

}