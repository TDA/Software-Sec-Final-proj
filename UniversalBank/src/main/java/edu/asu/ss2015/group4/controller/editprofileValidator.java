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

				//int count = 0;
				//int number = 0;
				int ssnNum = 0;

				editProfile cinfo = (editProfile) info;
				ValidationUtils.rejectIfEmptyOrWhitespace(errors, "socialSecurityNumber",
						"NotEmpty.editProfile.socialSecurityNumber", "SSN must not be Empty.");

				//String userName = cinfo.getUserName();
				
				//Pattern p1 = Pattern.compile("[!@#${},%^&*+_.-]");

				
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

				
			}
		}
