package edu.asu.ss2015.group4.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import edu.asu.ss2015.group4.model.UserInformation;

@Component("signUpFormValidator")
public class SignUpFormValidator {
	public boolean supports(Class<?> c) {
		return UserInformation.class.isAssignableFrom(c);
	}

	public static void validateForm(Object info, Errors errors) {

		int count = 0;
		int number = 0;
		int ssnNum = 0;
		int phNum = 0;

		UserInformation cinfo = (UserInformation) info;
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "socialSecurityNumber",
				"NotEmpty.UserInformation.socialSecurityNumber", "SSN must not be Empty.");

		String userName = cinfo.getUserName();
		String firstName = cinfo.getFirstName();
		Pattern p1 = Pattern.compile("[!@#${},%^&*+_.-]");
		Matcher match_fn = p1.matcher(firstName.subSequence(0, firstName.length()));
		if ((firstName.length()) > 15 || match_fn.find() == true) {
			errors.rejectValue("f_name", "lengthOfFirst.UserInformation.firstName", "First name is invalid");
		}

		String lastName = cinfo.getLastName();
		Matcher match_ln = p1.matcher(lastName.subSequence(0, lastName.length()));
		if ((lastName.length()) > 15 || match_ln.find() == true) {
			errors.rejectValue("l_name", "lengthOfLast.UserInformation.lastName", "Last name is invalid");
		}

		String accountType = cinfo.getAccountType();
		if (accountType.isEmpty()) {
			errors.rejectValue("accountType", "NotEmpty.UserInformation.accountType", "Select at least one option");
		}

		String ssn = cinfo.getSocialSecurityNumber();

		for (char c : ssn.toCharArray()) {
			if (Character.isDigit(c)) {
				ssnNum++;
			}
		}

		if (ssnNum != 9 || ssn.length() != 9) {
			errors.rejectValue("socialSecurityNumber",
					"lengthOfSocialSecurityNumber.UserInformation.socialSecurityNumber", "SSN is invalid");
		}

		String pass = cinfo.getPassword();
		for (char c : pass.toCharArray()) {
			if (Character.isDigit(c)) {
				number++;
			}
		}
		for (char c : pass.toCharArray()) {
			if (Character.isUpperCase(c)) {
				count++;
			}
		}

		if (cinfo.getPhoneNumber() == null) {
			errors.rejectValue("phoneNumber", "lengthOfPhoneNumber.UserInformation.phoneNumber",
					"Phone number is invalid");
		}

		String pNumber = cinfo.getPhoneNumber();
		for (char c : pNumber.toCharArray()) {
			if (Character.isDigit(c)) {
				phNum++;
			}
		}
		if (phNum != 10 || pNumber.length() != 10) {
			errors.rejectValue("phoneNumber", "lengthOfPhoneNumber.UserInformation.phoneNumber",
					"Phone number is invalid");
		}

		String add = cinfo.getAddress();
		Matcher match_add = p1.matcher(add.subSequence(0, add.length()));
		if ((add.length()) > 50 || match_add.find() == true) {
			errors.rejectValue("address", "lengthOfAddress.UserInformation.address", "Address is invalid");
		}

		Pattern p = Pattern.compile("[!@#${},%^&*+_.-]");
		Matcher match = p.matcher(pass.subSequence(0, pass.length()));
		Matcher match_1 = p.matcher(userName.subSequence(0, userName.length()));
		if (pass.length() < 6 || pass.length() > 15 || number <= 0 || count <= 0 || match.find() == false
				|| userName.length() > 15 || userName.length() == 0 || match_1.find() == true) {
			errors.rejectValue("userName", "matchingPassword.UserInformation.username",
					"User Name or Password is invalid!");
		}

		if (!(cinfo.getPassword()).equals(cinfo.getConfirmPassword())) {
			errors.rejectValue("password", "matchingPassword.UserInformation.password",
					"Password and Confirm Password Not match.");
		}

		String sex = cinfo.getSex();
		if (sex == null || sex.isEmpty()) {
			errors.rejectValue("sex", "NotEmpty.UserInformation.sex", "Select at least one option");
		}
	}
}
