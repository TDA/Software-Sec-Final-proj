package edu.asu.ss2015.group4.dao.impl;



import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;


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

		String registerUserQuery = "INSERT into transactions" + "(Customer_id,Merchant_id,Amount,Transaction_Type) "
				+ "VALUES (?,?,?,?)";
		

		JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
		
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] {transac.getCustomer_id(),transac.getMerchant_id()
							,transac.getAmount(),transac.getTransaction_type()});
		
	}

	public void view(Transactions transaction) {
		// TODO Auto-generated method stub
		
	}
/*
	public List<TransactionDTO> retrieveDetails(String customer_id) {
		List<TransactionDTO> customerInformationToDisplay = new ArrayList<TransactionDTO>();
		String retrieveDetailsQuery = "SELECT * where Customr_id=?" ;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] { customer_id},new TransactionTableRows());
		return customerInformationToDisplay;
	}
	

*/
	
	
	}

