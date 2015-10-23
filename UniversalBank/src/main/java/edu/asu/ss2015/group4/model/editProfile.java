package edu.asu.ss2015.group4.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class editProfile {
	@NotEmpty(message = "Please enter a user name.")
	private String userName;
	@NotEmpty(message = "Please enter a valid password.")
	private String password;
	@Email(message = "Invalid email address.")
	@NotEmpty(message = "Please enter your email.")
	private String emailAddress;
	@Size(min = 9, max = 9, message = "Enter a valid SSN Number")
	private String socialSecurityNumber;

	

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String email) {
		this.emailAddress = email;
	}

	

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	}