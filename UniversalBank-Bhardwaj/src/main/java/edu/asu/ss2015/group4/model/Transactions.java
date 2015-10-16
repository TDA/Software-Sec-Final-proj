package edu.asu.ss2015.group4.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

public class Transactions {


private int customer_id;
private int merchant_id;
@NotNull(message="enter a number")
private float amount;
private float balance;
private String transaction_type;



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
public float getBalance() {
	return balance;
}
public void setBalance(float balance) {
	this.balance = balance;
}
public String getTransaction_type() {
	return transaction_type;
}
public void setTransaction_type(String transaction_type) {
	this.transaction_type = transaction_type;
}


}
