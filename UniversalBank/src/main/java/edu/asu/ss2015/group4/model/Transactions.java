package edu.asu.ss2015.group4.model;

import java.security.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class Transactions {
@NotEmpty(message = "id cannot be empty")
private int customer_id;
@NotEmpty(message = "id cannot be empty")
private int merchant_id;
@NotEmpty(message = "Amount cant be zero or negative ")
private float amount;
@NotEmpty(message = "id cannot be empty")
private int transaction_id;
@NotEmpty(message = "id cannot be empty")
private String transaction_type;
private Timestamp timestamp;


public int getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(int customer_id) {
	this.customer_id = customer_id;
}
public int getMerchant_id() {
	return merchant_id;
}
public void setMerchant_id(int merchant_id) {
	this.merchant_id = merchant_id;
}
public float getAmount() {
	return amount;
}
public void setAmount(int amount) {
	this.amount = amount;
}
public int getTransaction_id() {
	return transaction_id;
}
public void setTransaction_id(int transaction_id) {
	this.transaction_id = transaction_id;
}
public String getTransaction_type() {
	return transaction_type;
}
public void setTransaction_type(String transaction_type) {
	this.transaction_type = transaction_type;
}
public Timestamp getTimestamp() {
	return timestamp;
}
public void setTimestamp(Timestamp timestamp) {
	this.timestamp = timestamp;
}

}
