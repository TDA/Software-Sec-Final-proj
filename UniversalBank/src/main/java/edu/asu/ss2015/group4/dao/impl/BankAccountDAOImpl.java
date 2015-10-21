package edu.asu.ss2015.group4.dao.impl;

import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;

import edu.asu.ss2015.group4.dao.BankAccountDAO;
import edu.asu.ss2015.group4.model.BankAccount;

public class BankAccountDAOImpl implements BankAccountDAO {
	@Autowired
	DataSource dataSource;

	@Override
	public void insert(BankAccount account) {
		String registerUserQuery = "INSERT into account" + "(AccountID, username, AccountType,"
				+ " Balance, CreationTime) VALUES (?,?,?,?,?)";

		JdbcTemplate jdbcTemplateForAccount = new JdbcTemplate(dataSource);
		jdbcTemplateForAccount.update(registerUserQuery, new Object[] { account.getId(), account.getUserName(),
				account.getAccountType(), account.getBalance(), account.getCreationTime() });
	}

	@Override
	public void update(BankAccount account) {
		// TODO Auto-generated method stub

	}

	@Override
	public void update(List<BankAccount> accounts) {
		// TODO Auto-generated method stub

	}

	@Override
	public void delete(long accountId) {
		// TODO Auto-generated method stub

	}

	@Override
	public BankAccount find(long accountId) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankAccount> find(List<Long> accountIds) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<BankAccount> find(String ownerName) {
		// TODO Auto-generated method stub
		return null;
	}

}
