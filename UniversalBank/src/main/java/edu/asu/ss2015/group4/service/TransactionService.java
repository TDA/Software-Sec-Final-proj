package edu.asu.ss2015.group4.service;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import edu.asu.ss2015.group4.dto.TransactionDTO;
import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.model.*;

public interface TransactionService {

	public String insertUserTransaction(Transactions transaction) ;
	public List<TransactionDTO> ViewUserInfo(String Username);
	public String DebitUser(Transactions transaction) ;
	public String CreditUser(Transactions transaction) ;

}
