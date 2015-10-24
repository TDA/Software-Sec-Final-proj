package edu.asu.ss2015.group4.service;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import edu.asu.ss2015.group4.dto.TransactionDTO;
import edu.asu.ss2015.group4.model.Transactions;

public interface TransactionService {

	public String insertUserTransaction(Transactions transaction)
			throws NoSuchAlgorithmException, FileNotFoundException;

	public void updateTransaction(TransactionDTO transaction, String managerId);

	public List<TransactionDTO> ViewUserInfo(String Username);

	public List<TransactionDTO> ViewUserInfoApprove(String Username);

	public String DebitUser(Transactions transaction);

	public String CreditUser(Transactions transaction);

	public String MerchantPaymentUser(Transactions transaction);

	public String TransferUser(Transactions transaction);

	public String Approve(int i);
	//added by gaurav
	public List<TransactionDTO> DisplayTransactionInfo(String Username);
	public List<TransactionDTO> fetchCriticalTransactions();
	public List<TransactionDTO> viewTransactionByTransactionID(String id);
	
	//added by gaurav
	public List<TransactionDTO> DisplayTransactionInfoToRegularEmployee(String Username);
	public String RegularEmployeeAprroveTransaction(int i,double j, String userName);
	public String RegularEmployeeDeleteTransaction(int i, String userName);
	public String Delete(int i, String userName);


}
