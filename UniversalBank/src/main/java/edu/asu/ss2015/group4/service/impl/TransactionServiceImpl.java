package edu.asu.ss2015.group4.service.impl;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;


import edu.asu.ss2015.group4.dao.TransactionDAO;
import edu.asu.ss2015.group4.dto.TransactionDTO;
import edu.asu.ss2015.group4.model.Transactions;
import edu.asu.ss2015.group4.service.TransactionService;


public class TransactionServiceImpl 
 implements TransactionService {
	@Autowired
	TransactionDAO transac ;
	
	
	public String insertUserTransaction(Transactions transaction)
			throws NoSuchAlgorithmException, FileNotFoundException {
		// TODO Auto-generated method stub
		
		return "null";
	}
	
	public List<TransactionDTO> ViewUserInfo(String Username) {
		// TODO Auto-generated method stub
		return transac.view(Username);
	}
	public String DebitUser(Transactions transaction) {
		// TODO Auto-generated method stub
		transac.Debit(transaction);
		return "successful";
	}
	public String CreditUser(Transactions transaction) {
		// TODO Auto-generated method stub
		transac.Credit(transaction);
		return "successful";
	}
	
	public String MerchantPaymentUser(Transactions transaction) {
		// TODO Auto-generated method stub
		transac.MerchantPayment(transaction);
		return "successful";
	}
	
	public String TransferUser(Transactions transaction) {
		// TODO Auto-generated method stub
		transac.Transfer(transaction);
		return "successful";
	}

	

	@Override
	public String Approve(int i) {
	transac.approval(i);
	return "approved";
	}

	@Override
	public List<TransactionDTO> ViewUserInfoApprove(String Username) {
		// TODO Auto-generated method stub
		return transac.viewCondition(Username);
	}
	
}
