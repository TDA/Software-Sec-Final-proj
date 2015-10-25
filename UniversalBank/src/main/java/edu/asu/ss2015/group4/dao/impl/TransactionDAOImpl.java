package edu.asu.ss2015.group4.dao.impl;

import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.ss2015.group4.dao.TransactionDAO;
import edu.asu.ss2015.group4.dto.TransactionDTO;
import edu.asu.ss2015.group4.jdbc.TransactionTableRows;
import edu.asu.ss2015.group4.model.Transactions;
import edu.asu.ss2015.group4.service.UserService;

public class TransactionDAOImpl implements TransactionDAO {
	@Autowired
	DataSource dataSource;
	UserService userService;
	public List<TransactionDTO> view(String username) {
		System.out.println("view:" + username);
		List<TransactionDTO> customerInformationToDisplay = new ArrayList<TransactionDTO>();
		String retrieveDetailsQuery = "SELECT * from transactions where (FromTransactionAccountID IN (Select AccountID from accounts where username=? ) OR (ToTransactionAccountID IN (Select AccountID from accounts where username=? )))";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] { username, username },
				new TransactionTableRows());
		return customerInformationToDisplay;
	}

	public List<TransactionDTO> viewTransactionByTransactionID(String id) {
		System.out.println("view:" + id);
		List<TransactionDTO> customerInformationToDisplay = new ArrayList<TransactionDTO>();
		String retrieveDetailsQuery = "SELECT * from transactions where TransactionID=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] { id },
				new TransactionTableRows());
		return customerInformationToDisplay;
	}

	public void Debit(Transactions transac) {

		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			System.out.println(loggedInUser);

			int criticalTransaction = 0;
			if (Float.parseFloat(transac.getAmount()) >= 10000.0)
				criticalTransaction = 1;

			String registerUserQuery = "INSERT into transactions"
					+ "(TransactionType,Amount,FromTransactionAccountId,AuthorizedManagerID,TransactionTime,Approved"
					+ ",ApprovalTime,Comments,critical_transactions) "
					+ "VALUES (?,?,(select AccountID from accounts where AccountType= ?),?,?,?,?,?,?)";
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] { "Debit", transac.getAmount(), transac.getAccountType(),
							transac.getAuthorizedManagerID(), transac.getTransactionTime(), transac.isApproved(),
							transac.getApprovedTime(), "Withdraw from ATM", criticalTransaction });
			// Bottom is removed in Rajat's branch please verify
			// if it is correct or not
			// if (Float.parseFloat(transac.getAmount()) <=
			// 10000.0) {
			// String registerUserQuery = "INSERT into
			// transactions"
			// +
			// "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,"
			// + "ApprovalTime,Comments,critical_transactions) "
			// + "VALUES (?,?,(select AccountID from accounts
			// where AccountType= ?),?,?,?,?,?,?)";
			// JdbcTemplate jdbcTemplateForTransaction = new
			// JdbcTemplate(dataSource);
			// jdbcTemplateForTransaction.update(registerUserQuery,
			// new Object[] { "Debit", transac.getAmount(),
			// transac.getAccountType(),
			// transac.getAuthorizedManagerID(),
			// transac.getTransactionTime(),
			// transac.isApproved(),
			// transac.getApprovedTime(), "Withdraw from ATM", 0
			// });
			// }

		}
	}

	public void Credit(Transactions transac) {

		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			System.out.println(loggedInUser);

			int criticalTransaction = 0;
			if (Float.parseFloat(transac.getAmount()) >= 10000.0)
				criticalTransaction = 1;

			String registerUserQuery = "INSERT into transactions"
					+ "(TransactionType,Amount,ToTransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,"
					+ "ApprovalTime,Comments,critical_transactions) "
					+ "VALUES (?,?,(select AccountID from accounts where AccountType= ? and username = ?),?,?,?,?,?,?)";
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] { "Credit", transac.getAmount(), transac.getAccountType(), loggedInUser,
							transac.getAuthorizedManagerID(), transac.getTransactionTime(), transac.isApproved(),
							transac.getApprovedTime(), "Deposit at branch", criticalTransaction });
			
		}
	}

	public void MerchantPayment(Transactions transac) {
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			System.out.println(loggedInUser);

			String registerUserQuery = "INSERT into transactions"
					+ "(TransactionID,TransactionType,Amount,FromTransactionAccountId,ToTransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime,Comments,critical_transactions) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] { transac.getTransactionId(), "Debit", transac.getAmount(),
							transac.getFromTransactionAccountID(), transac.getToTransactionAccountID(),
							transac.getAuthorizedManagerID(), transac.getTransactionTime(), transac.isApproved(),
							transac.getApprovedTime(), "Merchant initiated transaction" });
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] { transac.getTransactionId(), "Credit", transac.getAmount(), loggedInUser,
							transac.getAuthorizedManagerID(), transac.getTransactionTime(), transac.isApproved(),
							transac.getApprovedTime(),
							"Merchant getting credited for merchant initiated transaction" });

		}

	}

	@Override
	public void Transfer(Transactions transac) {
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			System.out.println(loggedInUser + "in transfer" + transac.getAccountType());
			System.out.println(transac.getAmount());

			int criticalTransaction = 0;
			if (Float.parseFloat(transac.getAmount()) >= 10000.0)
				criticalTransaction = 1;

			String registerUserQuery = "INSERT into transactions"
					+ "(TransactionType,Amount,FromTransactionAccountId,ToTransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime"
					+ ",Comments,critical_transactions) " + "VALUES (?,?,"
					+ "(select AccountID from accounts where username=? AND AccountType=? ),?,?,?,?,?,?,?)";
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] { "UserTransfer", transac.getAmount(), loggedInUser, transac.getAccountType(),
							transac.getToTransactionAccountID(), transac.getAuthorizedManagerID(),
							transac.getTransactionTime(), transac.isApproved(), transac.getApprovedTime(),
							"User transfer Debit", criticalTransaction });

			// String registerUserQuery1 = "INSERT into transactions"
			// +
			// "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime"
			// + ",Comments,critical_transactions) " + "VALUES
			// (?,?,?,?,?,?,?,?,?)";
			// JdbcTemplate jdbcTemplateForTransaction1 = new
			// JdbcTemplate(dataSource);
			// jdbcTemplateForTransaction1.update(registerUserQuery1,
			// new Object[] { "Credit", transac.getAmount(),
			// transac.getTransactiontoAccountID(),
			// transac.getAuthorizedManagerID(), transac.getTransactionTime(),
			// transac.isApproved(),
			// transac.getApprovedTime(), "User transfer Credit", 1 });
			// } else if (Float.parseFloat(transac.getAmount()) <= 10000.0) {
			// String registerUserQuery = "INSERT into transactions"
			// +
			// "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime"
			// + ",Comments,critical_transactions) " + "VALUES (?,?,"
			// + "(select AccountID from accounts where AccountType=
			// ?),?,?,?,?,?,?)";
			// JdbcTemplate jdbcTemplateForTransaction = new
			// JdbcTemplate(dataSource);
			// jdbcTemplateForTransaction.update(registerUserQuery,
			// new Object[] { "Debit", transac.getAmount(),
			// transac.getAccountType(),
			// transac.getAuthorizedManagerID(), transac.getTransactionTime(),
			// transac.isApproved(),
			// transac.getApprovedTime(), "User transfer Debit", 0 });
			// String registerUserQuery1 = "INSERT into transactions"
			// +
			// "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime"
			// + ",Comments,critical_transactions) " + "VALUES
			// (?,?,?,?,?,?,?,?,?)";
			// JdbcTemplate jdbcTemplateForTransaction1 = new
			// JdbcTemplate(dataSource);
			// jdbcTemplateForTransaction1.update(registerUserQuery1,
			// new Object[] { "Credit", transac.getAmount(),
			// transac.getTransactiontoAccountID(),
			// transac.getAuthorizedManagerID(), transac.getTransactionTime(),
			// transac.isApproved(),
			// transac.getApprovedTime(), "User transfer Credit", 0 });
			// }

		}
	}

	@Override
	public void approval(int a) {
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			System.out.println(loggedInUser);
			String registerUserQuery = "Update transactions  SET AuthoriseBank=? where transactionID=?";
			System.out.println("updatedapprove" + a);
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery, new Object[] { 1, a });
		}
	}

	@Override
	public List<TransactionDTO> viewCondition(String Username) {
		System.out.println("view:" + Username);
		List<TransactionDTO> customerInformationToDisplay = new ArrayList<TransactionDTO>();
		String retrieveDetailsQuery = "SELECT * from transactions where (FromTransactionAccountID IN (Select AccountID from accounts where username=?) OR ToTransactionAccountID IN (Select AccountID from accounts where username=?))) and AuthoriseBank=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] { Username, Username, 0 },
				new TransactionTableRows());

		return customerInformationToDisplay;
	}

	@Override
	public List<TransactionDTO> fetchCriticalTransaction() {
		List<TransactionDTO> criticalTransactions = new ArrayList<TransactionDTO>();
		String retrieveDetailsQuery = "SELECT * from transactions where Critical_transactions=1 and Approved=0";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		criticalTransactions = jdbcTemplate.query(retrieveDetailsQuery, new TransactionTableRows());
		return criticalTransactions;
	}

	// added by Gaurav
	@Override
	public List<TransactionDTO> viewTransactionForDeletion(String Username) {
		System.out.println("view:" + Username);
		System.out.println("called from transaction deletion");
		List<TransactionDTO> transactionToDisplay = new ArrayList<TransactionDTO>();
		String retrieveDetailsQuery = "SELECT * from transactions where Approved=? and IsDeleted=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		transactionToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] { 0, 0 },
				new TransactionTableRows());
		return transactionToDisplay;
	}

	// added by Gaurav
	@Override
	public void deleteTransaction(int a, String userName) {
		System.out.println("GOING TO UPDATE TRANS TABLE AFTER DELETION" + a);
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			System.out.println("transactionid" + a);
			String registerUserQuery = "Update transactions SET Approved=-1, IsDeleted=? , AuthorizedManagerID=? where transactionID=?";
			System.out.println("updatedapprove" + a);
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery, new Object[] { 1, userName, a });
		}
	}

	@Override
	public void updateTransaction(TransactionDTO transaction, String managerId) {
		String updatTrans = "UPDATE transactions SET  Approved=1, AuthorizedManagerID=? where transactionID=? and TransactionAccountID=?";
		JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
		jdbcTemplateForTransaction.update(updatTrans,
				new Object[] { managerId, transaction.getTransactionID(), transaction.getTransactionAccountID() });

	}

	// added by Gaurav
	@Override
	public List<TransactionDTO> viewTransactionToRegularEmployee(String Username) {
		System.out.println("view:" + Username);
		System.out.println("called from transaction deletion");
		List<TransactionDTO> transactionToDisplay = new ArrayList<TransactionDTO>();
		String retrieveDetailsQuery = "SELECT * from transactions where Critical_transactions=? and Approved=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		transactionToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] { 0, 0 },
				new TransactionTableRows());
		return transactionToDisplay;
	}

	// added by Gaurav
	@Override
	public void approveTransactionRegularEmployee(int a,double b, String userName) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			System.out.println("transactionid" + a);
			String registerUserQuery = "UPDATE transactions SET  Approved=1, AuthorizedManagerID=? where transactionID=?";
			System.out.println("updatedapprove" + a);
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery, new Object[] { userName, a });
			
			//update balance
			String registerUserQuery2 = "UPDATE accounts SET  balance= balance + ? where AccountID = ( Select ToTransactionAccountID from transactions where TransactionID = ? and TransactionType In ('UserTransfer','Credit'))";
			System.out.println("updatedapprove" + a);
			JdbcTemplate jdbcTemplateForTransaction2= new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction2.update(registerUserQuery2, new Object[] {b,a});
			
			
			String registerUserQuery3 = "UPDATE accounts SET  balance= balance - ? where AccountID = ( Select FromTransactionAccountID from transactions where TransactionID = ? and TransactionType In ('UserTransfer','Debit'))";
			System.out.println("updatedapprove" + a);
			JdbcTemplate jdbcTemplateForTransaction3= new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction3.update(registerUserQuery3, new Object[] {b,a});
		}
	}

	// added by Gaurav
	@Override
	public void denyTransactionRegularEmployee(int a, String userName) {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			System.out.println("transactionid" + a);
			String registerUserQuery = "UPDATE transactions SET  Approved=-1, AuthorizedManagerID=? where transactionID=?";
			System.out.println("updatedapprove" + a);
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery, new Object[] { userName, a });
		}
	}

}
