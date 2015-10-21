package edu.asu.ss2015.group4.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import edu.asu.ss2015.group4.model.Transactions;
import edu.asu.ss2015.group4.model.UserInformation;



@Component("TransferValidator")
public class TransferValidator {

	public boolean supports(Class<?> c) {
		return Transactions.class.isAssignableFrom(c);
	}

	public static void validateForm(Object info, Errors errors) {

		int count = 0;
		int number = 0;
		int ssnNum = 0;

		Transactions cinfo = (Transactions) info;
		
		 String transactionAccountID = cinfo.getTransactionAccountID();
		 System.out.println("in validator"+transactionAccountID);
		Pattern p1 = Pattern.compile("[^0-9]");
		Matcher match_fn = p1.matcher(transactionAccountID.subSequence(0, transactionAccountID.length()));
		if ((transactionAccountID.length()) > 10 || match_fn.find() == true) {
			System.out.println("herein validate");
			errors.rejectValue("transactionAccountID", "NotEmpty.Transactions.transactionAccountID", " is invalid");
		}
		

		Pattern p2 = Pattern.compile("([0-9]+([0-9]{1,2})?)");
		
		String amount=cinfo.getAmount();
		Matcher  am=p2.matcher(amount.subSequence(0, amount.length()));
		if(amount.isEmpty() || am.find()==false ){
			errors.rejectValue("amount", "NotEmpty.Transactions.amount","Please Enter Only digits is invalid");
			
		}

	}
}
