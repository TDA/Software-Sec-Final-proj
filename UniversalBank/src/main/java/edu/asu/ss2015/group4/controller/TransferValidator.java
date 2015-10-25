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
	private int Count=0; 
	public static void validateForm(Object info, Errors errors) {

		
		Transactions cinfo = (Transactions) info;
		
		String accountType = cinfo.getAccountType();
		
		if (accountType.isEmpty()) {
			errors.rejectValue("accountType", "NotEmpty.Transactions.accountType", "Select at least one option");
		}

		Pattern p2 = Pattern.compile("([0-9]+([0-9]{1,2})?)");
		
		String amount=cinfo.getAmount();
		Boolean x=amount.matches("^(\\d{1,5}|\\d{0,5}\\.\\d{1,2})$");

		Matcher  am=p2.matcher(amount.subSequence(0, amount.length()));
		if(amount.isEmpty() || am.find()==false || x==false){
			errors.rejectValue("amount", "NotEmpty.Transactions.amount","Please Enter Only digits is invalid");
			
		}

	}
public static void validateForm1(Object info, Errors errors) {

		
		Transactions cinfo = (Transactions) info;
		
		String accountType = cinfo.getAccountType();
		
		if (accountType.isEmpty()) {
			errors.rejectValue("accountType", "NotEmpty.Transactions.accountType", "Select at least one option");
		}

		Pattern p2 = Pattern.compile("([0-9]+([0-9]{1,2})?)");
		
		String amount=cinfo.getAmount();
		Boolean x=amount.matches("^(\\d{1,5}|\\d{0,5}\\.\\d{1,2})$");

		Matcher  am=p2.matcher(amount.subSequence(0, amount.length()));
		if(amount.isEmpty() || am.find()==false || x==false){
			errors.rejectValue("amount", "NotEmpty.Transactions.amount","Please Enter Only digits is invalid");
			
		}
		if( x==true && amount.isEmpty()==false){
			Double b=Double.parseDouble(cinfo.getAmount());
			System.out.println("b"+b+"bal"+cinfo.getBalance());
		if(cinfo.getBalance()<=b){
			errors.rejectValue("amount", "NotEmpty.Transactions.amount","You Dont Have Sufficient Balance");
		}
		}
		

	}
}
