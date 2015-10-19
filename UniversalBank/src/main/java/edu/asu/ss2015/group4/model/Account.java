package edu.asu.ss2015.group4.model;

import java.util.Date;

import org.hibernate.validator.constraints.NotEmpty;

/**
 * @author schandramouli
 *
 */
public class Account {
	@NotEmpty(message = "id cannot be empty")
	private long id;
	@NotEmpty(message = "owner name cannot be empty")
	private String ownerName; 
	private double balance; 
	@NotEmpty(message = "access time cannot be empty")
	private Date accessTime;
	@NotEmpty(message = "ssn cannot be empty")
	private String socialSecurityNumber;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getOwnerName() {
		return ownerName;
	}

	public void setOwnerName(String ownerName) {
		this.ownerName = ownerName;
	}

	public Double getBalance() {
		return balance;
	}

	public void setBalance(Double balance) {
		this.balance = balance;
	}

	public Date getAccessTime() {
		return accessTime;
	}

	public void setAccessTime(Date accessTime) {
		this.accessTime = accessTime;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}
}
