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

public class TransactionDAOImpl implements TransactionDAO {
	@Autowired
	DataSource dataSource;

	public void insert(Transactions transac) {

		String registerUserQuery = "INSERT into transactions"
				+ "(TransactionID,TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime,Comments) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		System.out.println(transac.getTransactionType());
		JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
		jdbcTemplateForTransaction.update(registerUserQuery,
				new Object[] { transac.getTransactionId(), "Inserting", transac.getAmount(),
						transac.getTransactionAccountID(), transac.getAuthorizedManagerID(),
						transac.getTransactionTime(), transac.isApproved(), transac.getApprovedTime(),
						transac.getComments() });

	}

	public List<TransactionDTO> view(String username) {
		System.out.println("view:" + username);
		List<TransactionDTO> customerInformationToDisplay = new ArrayList<TransactionDTO>();
		String retrieveDetailsQuery = "SELECT * from transactions where TransactionAccountID=(Select AccountID from accounts where username=?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] { username },
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

			String registerUserQuery = "INSERT into transactions"
					+ "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime,Comments) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] { "Debit", transac.getAmount(), transac.getTransactionAccountID(),
							transac.getAuthorizedManagerID(), transac.getTransactionTime(), transac.isApproved(),
							transac.getApprovedTime(), "Withdraw from ATM" });
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

			String registerUserQuery = "INSERT into transactions"
					+ "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime,Comments) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] { "Credit", transac.getAmount(), transac.getTransactionAccountID(),
							transac.getAuthorizedManagerID(), transac.getTransactionTime(), transac.isApproved(),
							transac.getApprovedTime(), "Deposit at branch" });
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
					+ "(TransactionID,TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime,Comments) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] { transac.getTransactionId(), "Debit", transac.getAmount(),
							transac.getTransactionAccountID(), transac.getAuthorizedManagerID(),
							transac.getTransactionTime(), transac.isApproved(), transac.getApprovedTime(),
							"Merchant initiated transaction" });
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
			System.out.println(loggedInUser);

			String registerUserQuery = "INSERT into transactions"
					+ "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime,Comments) "
					+ "VALUES (?,?,?,?,?,?,?,?)";
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] { "Debit", transac.getAmount(), transac.getTransactionAccountID(),
							transac.getAuthorizedManagerID(), transac.getTransactionTime(), transac.isApproved(),
							transac.getApprovedTime(), "User transfer Debit" });
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] { "Credit", transac.getAmount(), transac.getTransactionAccountID(),
							transac.getAuthorizedManagerID(), transac.getTransactionTime(), transac.isApproved(),
							transac.getApprovedTime(), "User transfer Credit" });

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
			String registerUserQuery = "Update transactions  SET Authorise_bank=? where transactionID=?";
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery, new Object[] { 1, a });
		}
	}

	@Override
	public List<TransactionDTO> viewCondition(String Username) {

		List<TransactionDTO> customerInformationToDisplay = new ArrayList<TransactionDTO>();
		String retrieveDetailsQuery = "SELECT * from transactions where TransactionAccountID=(Select AccountID from accounts where username=?) and Authorise_bank=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] { Username, 0 },
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
		String retrieveDetailsQuery = "SELECT * from transactions where Authorise_bank=? and IsDeleted=?";
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
			String registerUserQuery = "Update transactions SET IsDeleted=? , AuthorizedManagerID=? where transactionID=?";
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
	public void approveTransactionRegularEmployee(int a, String userName) {
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
