package edu.asu.ss2015.group4.dao.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import edu.asu.ss2015.group4.dao.BankAccountDAO;
import edu.asu.ss2015.group4.model.AccountLoginAttempts;
import edu.asu.ss2015.group4.model.BankAccount;

/* adding a sample comment */
public class BankAccountDAOImpl implements BankAccountDAO {
	@Autowired
	DataSource dataSource;

	@Override
	public void insert(BankAccount account) {
		String registerUserQuery = "INSERT into accounts" + "(AccountID, username, AccountType,"
				+ " Balance, CreationTime) VALUES (?,?,?,?,?)";

		JdbcTemplate jdbcTemplateForAccount = new JdbcTemplate(dataSource);
		jdbcTemplateForAccount.update(registerUserQuery, new Object[] { account.getId(), account.getUserName(),
				account.getAccountType(), account.getBalance(), account.getCreationTime() });
	}

	@Override
	public void updateBalance(BankAccount account) {

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

	@Override
	public int Validate(BankAccount a) {
		System.out.println("countinvalidate"+a.getId());

		String sql="SELECT COUNT(*) from accounts where AccountID=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count=0;
		 count = jdbcTemplate.queryForObject(sql, new Object[] { a.getId()}, Integer.class);
		System.out.println("count"+count);
		return count;
	}
	@Override
	public double ValidateBalance(BankAccount a) {
		Double count=0.0;
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
		System.out.println("countinvalidate1"+a.getId());
		String sql="SELECT Balance from accounts where username=? AND AccountType=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		
		 count = jdbcTemplate.queryForObject(sql, new Object[] {loggedInUser,a.getAccountType() }, Double.class);
		System.out.println("count"+count);
		}
		return count;
	}
}
