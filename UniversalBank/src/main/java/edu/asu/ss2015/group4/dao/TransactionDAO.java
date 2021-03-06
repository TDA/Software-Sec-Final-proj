package edu.asu.ss2015.group4.dao;

import java.util.List;

import edu.asu.ss2015.group4.dto.TransactionDTO;
import edu.asu.ss2015.group4.dto.UserRequestsDTO;
import edu.asu.ss2015.group4.model.Transactions;

public interface TransactionDAO {

	public List<TransactionDTO> view(String Username);

	public List<TransactionDTO> viewCondition(String Username);

	public void MerchantPayment(Transactions transaction);

	public void Transfer(Transactions transaction);

	public void Debit(Transactions transaction);

	public void Credit(Transactions transaction);

	void approval(int a);

	public List<TransactionDTO> fetchCriticalTransaction(String userName);

	public void updateTransaction(TransactionDTO transaction, String managerId);

	public List<TransactionDTO> viewTransactionByTransactionID(String id);

	// added by gaurav
	public List<TransactionDTO> viewTransactionForDeletion(String Username);

	public void deleteTransaction(int a, String userName);

	public List<TransactionDTO> viewTransactionToRegularEmployee(String Username);

	public String approveTransactionRegularEmployee(int a, double b, String userName);

	public void denyTransactionRegularEmployee(int a, String userName);

	public void modifyTransaction(int a, double b, String userName);

	public List<UserRequestsDTO> viewAcoountDeletionRequestToRegularEmployee(String Username);

	public void approveUserReq(int a, String userName);

}
