package edu.asu.ss2015.group4.model;

import javax.validation.constraints.NotNull;

public class modifyIntUsers {
	@NotNull(message = "Select atleast an account type.")
	private String accountType;
	public String getAccountType() {
		return accountType;
	}

	public void setAccountType(String accountType) {
		this.accountType = accountType;
	}

}
