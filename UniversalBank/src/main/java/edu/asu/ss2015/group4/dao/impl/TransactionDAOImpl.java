package edu.asu.ss2015.group4.dao.impl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.asu.ss2015.group4.dao.TransactionDAO;
import edu.asu.ss2015.group4.dao.UserDAO;
import edu.asu.ss2015.group4.dto.CheckDuplicationDTO;
import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.jdbc.CheckDuplicationMapper;
import edu.asu.ss2015.group4.jdbc.UserTableRows;
import edu.asu.ss2015.group4.model.Account;
import edu.asu.ss2015.group4.model.Transactions;
import edu.asu.ss2015.group4.model.UserInformation;

public  class TransactionDAOImpl implements TransactionDAO{
	@Autowired
	DataSource dataSource;

	public void insert(Transactions transac) {

		String registerUserQuery = "INSERT into transactions" + "(Transaction_id,Customer_id,Merchant_id,Amount,Time) "
				+ "VALUES (?,?,?,?)";
		

		JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
		
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] {transac.getTransaction_id(),transac.getCustomer_id(),transac.getTimestamp(),transac.getMerchant_id()
							,transac.getAmount()});
		
	}


	public void view(Transactions transac) {
		// TODO Auto-generated method stub
		
		String registerUserQuery = "Select * from  transactions" + "where transaction_id=?";
		
		JdbcTemplate jdbcTemplateForTransaction = new JdbcTemplate(dataSource);
		
			jdbcTemplateForTransaction.update(registerUserQuery,
					new Object[] {transac.getTransaction_id()});
		
	}
		
	}

