package edu.asu.ss2015.group4.service;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import edu.asu.ss2015.group4.dto.TransactionDTO;
import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.model.*;

public interface TransactionService {

	public String insertUserTransaction(Transactions transaction) throws NoSuchAlgorithmException, FileNotFoundException;
	public List<TransactionDTO> ViewUserInfo(String Username);
	public List<TransactionDTO> ViewUserInfoApprove(String Username);
	public String DebitUser(Transactions transaction) ;
	public String CreditUser(Transactions transaction) ;
	public String MerchantPaymentUser(Transactions transaction);
	public String TransferUser(Transactions transaction);
	public String Approve(int i);


}
