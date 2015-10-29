package edu.asu.ss2015.group4.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;

public class editProfile {

	@Size(min = 10, max = 10, message = "Enter a valid Phone Number")
	private String phoneNumber;
	@NotEmpty(message = "Please enter a valid address.")
	private String address;
	@NotNull(message = "Select atleast an account type.")
	private String sex;

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

}
