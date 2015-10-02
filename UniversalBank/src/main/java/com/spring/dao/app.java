package com.spring.dao;

public class app {
	private int transaction_ID;
	private String customer_User_name;
	private String transaction_Type;
	private int access_level;
	
	public app() {
	
	}

	@Override
	public String toString() {
		return "app [transaction_ID=" + transaction_ID + ", customer_User_name=" + customer_User_name
				+ ", transaction_Type=" + transaction_Type + ", access_level=" + access_level + "]";
	}

	public int getTransaction_ID() {
		return transaction_ID;
	}

	public void setTransaction_ID(int transaction_ID) {
		this.transaction_ID = transaction_ID;
	}

	public String getCustomer_User_name() {
		return customer_User_name;
	}

	public void setCustomer_User_name(String customer_User_name) {
		this.customer_User_name = customer_User_name;
	}

	public String getTransaction_Type() {
		return transaction_Type;
	}

	public void setTransaction_Type(String transaction_Type) {
		this.transaction_Type = transaction_Type;
	}

	public int getAccess_level() {
		return access_level;
	}

	public void setAccess_level(int access_level) {
		this.access_level = access_level;
	}
	
	
}
