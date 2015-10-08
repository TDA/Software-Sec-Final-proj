package edu.asu.ss2015.group4.dto;

import java.sql.Timestamp;

import org.hibernate.validator.constraints.NotEmpty;

public class TransactionDTO {
	
	private int customer_id;

	private int merchant_id;
	
	private float amount;
	
	private int transaction_id;
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
	public void setAmount(float f) {
		this.amount = f;
	}
	public int getTransaction_id() {
		return transaction_id;
	}
	public void setTransaction_id(int transaction_id) {
		this.transaction_id = transaction_id;
	}

	public Timestamp getTimestamp() {
		return timestamp;
	}
	public void setTimestamp( Timestamp timestamp) {
		this.timestamp = timestamp;
	}
	

}
