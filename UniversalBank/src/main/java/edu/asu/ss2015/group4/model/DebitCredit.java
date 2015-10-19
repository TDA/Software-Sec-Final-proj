package edu.asu.ss2015.group4.model;

import javax.validation.constraints.DecimalMin;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class DebitCredit {
@NotEmpty(message = "Please enter your CustomerID")
private String customer_id;
private String merchant_id;
@NotEmpty(message="enter a number")
private String amount;
private float balance;
private String transaction_type;



public String getCustomer_id() {
	return customer_id;
}
public void setCustomer_id(String customer_id) {
	this.customer_id = customer_id;
}

public String getAmount() {
	return amount;
}
public void setAmount(String amount) {
	this.amount = amount;
}
public float getBalance() {
	return balance;
}
public void setBalance(float balance) {
	this.balance = balance;
}


}
