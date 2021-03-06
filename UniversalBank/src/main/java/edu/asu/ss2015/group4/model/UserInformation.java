package edu.asu.ss2015.group4.model;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.NotEmpty;

public class UserInformation {
	@NotEmpty(message = "Please enter a user name.")
	private String userName;
	@NotEmpty(message = "Please enter a valid password.")
	private String password;
	@NotEmpty(message = "Please re-enter password.")
	private String confirmPassword;
	@NotEmpty(message = "Please enter your first name.")
	private String firstName;
	@NotEmpty(message = "Please enter your last name.")
	private String lastName;
	@NotNull(message = "Select atleast an account type.")
	private String accountType;
	@Email(message = "Invalid email address.")
	@NotEmpty(message = "Please enter your email.")
	private String emailAddress;
	@Size(min = 9, max = 9, message = "Enter a valid SSN Number")
	private String socialSecurityNumber;
	private boolean enabled;
	private boolean userLocked;
	private boolean piiAccess;
	private boolean userAccountExpired = true;
	private String otp = "0";
	private String otpValidity = "0";
	@Size(min = 10, max = 10, message = "Enter a valid Phone Number")
	private String phoneNumber;
	@NotEmpty(message = "Please enter a valid address.")
	private String address;
	@NotNull(message = "Select atleast an account type.")
	private String sex;

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isPiiAccess() {
		return piiAccess;
	}

	public void setPiiAccess(boolean piiAccess) {
		this.piiAccess = piiAccess;
	}

	public boolean isUserLocked() {
		return userLocked;
	}

	public void setUserLocked(boolean userLocked) {
		this.userLocked = userLocked;
	}

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

	public String getConfirmPassword() {
		return confirmPassword;
	}

	public void setConfirmPassword(String confirmPassword) {
		this.confirmPassword = confirmPassword;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String email) {
		this.emailAddress = email;
	}

	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

	public String getSocialSecurityNumber() {
		return socialSecurityNumber;
	}

	public void setSocialSecurityNumber(String socialSecurityNumber) {
		this.socialSecurityNumber = socialSecurityNumber;
	}

	public boolean isEnabled() {
		return enabled;
	}

	public void setEnabled(boolean enabled) {
		this.enabled = enabled;
	}

	public boolean isUserAccountExpired() {
		return userAccountExpired;
	}

	public void setUserAccountExpired(boolean userAccountExpired) {
		this.userAccountExpired = userAccountExpired;
	}

	public String getOTP() {
		return otp;
	}

	public void setOTP(String otp) {
		this.otp = otp;
	}

	public String getOtpValidity() {
		return otpValidity;
	}

	public void setOtpValidity(String otpValidity) {
		this.otpValidity = otpValidity;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

}
