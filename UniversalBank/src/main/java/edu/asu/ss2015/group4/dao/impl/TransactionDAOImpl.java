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


public  class TransactionDAOImpl implements TransactionDAO{
	@Autowired
	DataSource dataSource;

	public void insert(Transactions transac) {

		String registerUserQuery = "INSERT iwnto transactions" + "(TransactionID,TransactionType,Amount,TransactionStartUser,TransactionStartAccountID,TransactionEndUser,TransactionEndAccountID,AuthorizedManagerID,TransactionTime) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		System.out.println(transac.getTransactionType());
		JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
		jdbcTemplateForTransaction.update(registerUserQuery,
				new Object[] {transac.getTransactionId(),transac.getTransactionType(),transac.getAmount(),transac.getTransactionStartUser(),transac.getTransactionStartAccountID(),transac.getTransactionEndUser(),transac.getTransactionEndAccountID(),transac.getAuthorizedManagerID(),transac.getTransactionTime()});
		
	}

	public List<TransactionDTO> view(String username) {
		List<TransactionDTO> customerInformationToDisplay = new ArrayList<TransactionDTO>();
		String retrieveDetailsQuery = "SELECT * from transactions where TransactionStartUser=(Select AccountNumber from users where username=?)" ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] {username},new TransactionTableRows());
		return customerInformationToDisplay;
	}

	public void Debit(Transactions transac) {
		String registerUserQuery = "INSERT into transactions" + "(TransactionID,TransactionType,Amount,TransactionStartUser,TransactionStartAccountID,TransactionEndUser,TransactionEndAccountID,AuthorizedManagerID,TransactionTime) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
		jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] {transac.getTransactionId(),"Debit",transac.getAmount(),"W/D ATM",transac.getTransactionStartAccountID(),transac.getTransactionEndUser(),transac.getTransactionEndAccountID(),transac.getAuthorizedManagerID(),transac.getTransactionTime()});
	}
	
	public void Credit(Transactions transac) {
		String registerUserQuery = "INSERT into transactions" + "(TransactionID,TransactionType,Amount,TransactionStartUser,TransactionStartAccountID,TransactionEndUser,TransactionEndAccountID,AuthorizedManagerID,TransactionTime) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
		jdbcTemplateForTransaction.update(registerUserQuery,
				new Object[] {transac.getTransactionId(),"Credit",transac.getAmount(),"Deposit at ATM",transac.getTransactionStartAccountID(),transac.getTransactionEndUser(),transac.getTransactionEndAccountID(),transac.getAuthorizedManagerID(),transac.getTransactionTime()});
	
}
	
	public void MerchantPayment(Transactions mtransac) {
		ModelAndView modelAndView = new ModelAndView();
	
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			System.out.println(loggedInUser);
		
		String registerUserQuery = "INSERT into transactions" + "(TransactionID,TransactionType,Amount,TransactionStartUser,TransactionStartAccountID,TransactionEndUser,TransactionEndAccountID,AuthorizedManagerID,TransactionTime) "
				+ "VALUES (?,?,?,?,?,?,?,?,?)";
		
		JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
					jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] {mtransac.getTransactionId(),"MerchantInitiatedPayment",mtransac.getAmount(),mtransac.getTransactionStartUser(),mtransac.getTransactionStartAccountID(),mtransac.getTransactionEndUser(),loggedInUser,mtransac.getAuthorizedManagerID(),mtransac.getTransactionTime()});
		
	}
	
}

	
	}

