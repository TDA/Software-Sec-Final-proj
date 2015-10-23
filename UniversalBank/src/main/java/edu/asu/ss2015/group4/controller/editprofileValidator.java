	package edu.asu.ss2015.group4.controller;

	import java.util.regex.Matcher;
	import java.util.regex.Pattern;

	import org.springframework.stereotype.Component;
	import org.springframework.validation.Errors;
	import org.springframework.validation.ValidationUtils;

	import edu.asu.ss2015.group4.model.editProfile;



	@Component("ediprofileValidator")
	public class editprofileValidator {


		public boolean supports(Class<?> c) {
			return editProfile.class.isAssignableFrom(c);
		}

			public static void validateForm(Object info, Errors errors) {

				int count = 0;
				int number = 0;
				int ssnNum = 0;

				editProfile cinfo = (editProfile) info;
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "socialSecurityNumber",
						"NotEmpty.editProfile.socialSecurityNumber", "SSN must not be Empty.");

				String userName = cinfo.getUserName();
				
				Pattern p1 = Pattern.compile("[!@#${},%^&*+_.-]");

				
				String ssn = cinfo.getSocialSecurityNumber();
				System.out.println("SSN CHECK: " + ssn);

				for (char c : ssn.toCharArray()) {
					if (Character.isDigit(c)) {
						ssnNum++;
					}
				}
				System.out.println(ssnNum);

				if (ssnNum != 9 || ssn.length() != 9) {
					errors.rejectValue("socialSecurityNumber",
							"lengthOfSocialSecurityNumber.editProfile.socialSecurityNumber", "SSN is invalid");
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

				Pattern p = Pattern.compile("[!@#${},%^&*+_.-]");
				Matcher match = p.matcher(pass.subSequence(0, pass.length()));
				Matcher match_1 = p.matcher(userName.subSequence(0, userName.length()));
				if (pass.length() < 6 || pass.length() > 15 || number <= 0 || count <= 0 || match.find() == false
						|| userName.length() > 10 || userName.length() == 0 || match_1.find() == true) {
					errors.rejectValue("userName", "matchingPassword.editProfile.username",
							"User Name or Password is invalid!");
				}

				
			}
		}
