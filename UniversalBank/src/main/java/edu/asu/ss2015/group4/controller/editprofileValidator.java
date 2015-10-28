package edu.asu.ss2015.group4.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;

import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.model.editProfile;

@Component("ediprofileValidator")
public class editprofileValidator {

	public boolean supports(Class<?> c) {
		return editProfile.class.isAssignableFrom(c);
	}

	public static void validateForm(Object info, Errors errors) {
		UserInformation cinfo = (UserInformation) info;
		if (cinfo.getPhoneNumber() == null) {
			errors.rejectValue("phoneNumber", "lengthOfPhoneNumber.UserInformation.phoneNumber",
					"Phone number is invalid");
		}

		String pNumber = cinfo.getPhoneNumber();
		int phNum = 0;
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
		Pattern p2 = Pattern.compile("[!@#${}%^&*+_.-]");
		Matcher match_add = p2.matcher(add.subSequence(0, add.length()));
		if ((add.length()) > 50 || match_add.find() == true) {
			errors.rejectValue("address", "lengthOfAddress.UserInformation.address", "Address is invalid");
		}

		String sex = cinfo.getSex();
		if (sex == null || sex.isEmpty()) {
			errors.rejectValue("sex", "NotEmpty.UserInformation.sex", "Select at least one option");
		}

	}
}
