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
import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.jdbc.TransactionTableRows;
import edu.asu.ss2015.group4.jdbc.UserTableRows;
import edu.asu.ss2015.group4.model.Transactions;

public class TransactionDAOImpl implements TransactionDAO {
	@Autowired
	DataSource dataSource;

	

	public List<TransactionDTO> view(String username) {
		System.out.println("view:"+username);
		List<TransactionDTO> customerInformationToDisplay = new ArrayList<TransactionDTO>();
		String retrieveDetailsQuery = "SELECT * from transactions where TransactionAccountID=(Select AccountID from accounts where username=?)" ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] {username},new TransactionTableRows());
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
			
		if(Float.parseFloat(transac.getAmount())>=10000.0){	
			String registerUserQuery = "INSERT into transactions"
					+ "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved"
					+ ",ApprovalTime,Comments,critical_transactions) "
					+ "VALUES (?,?,(select AccountID from accounts where AccountType= ?),?,?,?,?,?,?)";
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] {"Debit", transac.getAmount(),transac.getAccountType(),
							transac.getAuthorizedManagerID(), transac.getTransactionTime(), transac.isApproved(),
							transac.getApprovedTime(), "Withdraw from ATM" ,1});
		}
		if(Float.parseFloat(transac.getAmount())<=10000.0){	
			String registerUserQuery = "INSERT into transactions"
					+ "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,"
					+ "ApprovalTime,Comments,critical_transactions) "
					+ "VALUES (?,?,(select AccountID from accounts where AccountType= ?),?,?,?,?,?,?)";
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] {"Debit", transac.getAmount(),transac.getAccountType(),
							transac.getAuthorizedManagerID(), transac.getTransactionTime(), transac.isApproved(),
							transac.getApprovedTime(), "Withdraw from ATM" ,0});
		}
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
			if(Float.parseFloat(transac.getAmount())>=10000.0){	
			String registerUserQuery = "INSERT into transactions"
					+ "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,"
					+ "ApprovalTime,Comments,critical_transactions) "
					+ "VALUES (?,?,(select AccountID from accounts where AccountType= ?),?,?,?,?,?,?)";
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] { "Credit", transac.getAmount(), transac.getAccountType(),
							transac.getAuthorizedManagerID(), transac.getTransactionTime(), transac.isApproved(),
							transac.getApprovedTime(), "Deposit at branch" ,1});
		}
			else if(Float.parseFloat(transac.getAmount())<=10000.0){	
				String registerUserQuery = "INSERT into transactions"
						+ "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime,Comments,critical_transactions) "
						+ "VALUES (?,?,(select AccountID from accounts where AccountType= ?),?,?,?,?,?,?)";
				JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
				jdbcTemplateForTransaction.update(registerUserQuery,
						new Object[] { "Credit", transac.getAmount(), transac.getAccountType(),
								transac.getAuthorizedManagerID(), transac.getTransactionTime(), transac.isApproved(),
								transac.getApprovedTime(), "Deposit at branch",0 });
			}
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
					+ "(TransactionID,TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime,Comments,critical_transactions) "
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

	
	public void Transfer(Transactions transac) {
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			System.out.println(loggedInUser+"in transfer"+transac.getAccountType());
			System.out.println(transac.getAmount());
			if(Float.parseFloat(transac.getAmount())>=10000.0){
			String registerUserQuery = "INSERT into transactions"
					+ "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime"
					+ ",Comments,critical_transactions) "
					+ "VALUES (?,?,"+"(select AccountID from accounts where AccountType= ?),?,?,?,?,?,?)";
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] {  "Debit", transac.getAmount(),transac.getAccountType(),
							transac.getAuthorizedManagerID(), transac.getTransactionTime(), transac.isApproved(),
							transac.getApprovedTime(),"User transfer Debit" ,1});
			String registerUserQuery1 = "INSERT into transactions"
					+ "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime"
					+ ",Comments,critical_transactions) "
					+ "VALUES (?,?,?,?,?,?,?,?,?)";
			JdbcTemplate jdbcTemplateForTransaction1 = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction1.update(registerUserQuery1,
					new Object[] {"Credit", transac.getAmount(),
							transac.getTransactiontoAccountID(), transac.getAuthorizedManagerID(),
							transac.getTransactionTime(), transac.isApproved(), transac.getApprovedTime(),
							"User transfer Credit" ,1});
			}
			else if (Float.parseFloat(transac.getAmount())<=10000.0){
				String registerUserQuery = "INSERT into transactions"
						+ "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime"
						+ ",Comments,critical_transactions) "
						+ "VALUES (?,?,"+"(select AccountID from accounts where AccountType= ?),?,?,?,?,?,?)";
				JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
				jdbcTemplateForTransaction.update(registerUserQuery,
						new Object[] {  "Debit", transac.getAmount(),transac.getAccountType(),
								transac.getAuthorizedManagerID(), transac.getTransactionTime(), transac.isApproved(),
								transac.getApprovedTime(),"User transfer Debit" ,0});
				String registerUserQuery1 = "INSERT into transactions"
						+ "(TransactionType,Amount,TransactionAccountID,AuthorizedManagerID,TransactionTime,Approved,ApprovalTime"
						+ ",Comments,critical_transactions) "
						+ "VALUES (?,?,?,?,?,?,?,?,?)";
				JdbcTemplate jdbcTemplateForTransaction1 = new JdbcTemplate(dataSource);
				jdbcTemplateForTransaction1.update(registerUserQuery1,
						new Object[] {"Credit", transac.getAmount(),
								transac.getTransactiontoAccountID(), transac.getAuthorizedManagerID(),
								transac.getTransactionTime(), transac.isApproved(), transac.getApprovedTime(),
								"User transfer Credit" ,0});
				}
			
		}
	}
	
	public void approval(int a ) {
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
				System.out.println(loggedInUser);
			String registerUserQuery = "Update transactions  SET AuthoriseBank=? where transactionID=?";
			System.out.println("updatedapprove"+a);
			JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] {1,a});
				}
			}

	public List<TransactionDTO> viewCondition(String Username) {
			System.out.println("view:"+Username);
			List<TransactionDTO> customerInformationToDisplay = new ArrayList<TransactionDTO>();
			String retrieveDetailsQuery = "SELECT * from transactions where TransactionAccountID=(Select AccountID from accounts where username=?) and AuthoriseBank=?" ;
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] {Username,0},new TransactionTableRows());
			return customerInformationToDisplay;
		}
		}

