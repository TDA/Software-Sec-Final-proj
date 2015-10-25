package edu.asu.ss2015.group4.controller;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;

import edu.asu.ss2015.group4.model.BankAccount;
import edu.asu.ss2015.group4.model.Transactions;
import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.service.BankAccountService;


@Component("TransferValidator1")
public class transfervalidator1 {
	

	public boolean supports(Class<?> c) {
		return Transactions.class.isAssignableFrom(c);
	}

	public static  void validateForm(Object info, Errors errors) {
		
		
		int i;
		Transactions cinfo = (Transactions) info;
		
		Pattern p = Pattern.compile("[!@#${},%^&*+_.-]");
		Pattern p1 = Pattern.compile("[a-z]");
		
		String accountType = cinfo.getAccountType();
		
		if (accountType.isEmpty()) {
			errors.rejectValue("accountType", "NotEmpty.Transactions.accountType", "Select at least one option");
		}
		if (cinfo.getCount()==0){
		errors.rejectValue("ToTransactionAccountID","NotEmpty.Transactions.amount", " AccountId not Registered" ); 
		}
		String transactiontoAccountID = cinfo.getToTransactionAccountID();
		 System.out.println("in validator"+transactiontoAccountID);
		Matcher match_fn = p.matcher(transactiontoAccountID.subSequence(0, transactiontoAccountID.length()));
		Matcher match_fn1 = p1.matcher(transactiontoAccountID.subSequence(0, transactiontoAccountID.length()));
		if ((transactiontoAccountID.length()) > 10 || match_fn1.find() == true ||match_fn.find() == true ||transactiontoAccountID.isEmpty()==true) {
			System.out.println("herein validate");
			errors.rejectValue("ToTransactionAccountID","NotEmpty.Transactions.amount", " Shouldnt be empty or more than 10 digits");
		}
		Pattern p2 = Pattern.compile("[0-9]+");
		System.out.println("hereamount"+cinfo.getAmount());
		String amount=cinfo.getAmount();
		Boolean x=amount.matches("^(\\d{1,5}|\\d{0,5}\\.\\d{1,2})$");
		System.out.println(x);
		//Matcher  am=p2.matcher(amount.subSequence(0, amount.length()));
		if(amount.isEmpty() || x==false ){
			errors.rejectValue("amount", "NotEmpty.Transactions.amount","Please Enter Only digits is invalid");
			
		}
		
		
		if(cinfo.getCount()==1 && x==true && amount.isEmpty()==false){
			Double b=Double.parseDouble(cinfo.getAmount());
		if(cinfo.getBalance()<=b){
			errors.rejectValue("amount", "NotEmpty.Transactions.amount","You Dont Have Sufficient Balance");
		}
		}
		

	}
}
