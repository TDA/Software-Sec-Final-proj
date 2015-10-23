package edu.asu.ss2015.group4.service.impl;

import org.springframework.beans.factory.annotation.Autowired;

import edu.asu.ss2015.group4.dao.BankAccountDAO;
import edu.asu.ss2015.group4.model.BankAccount;
import edu.asu.ss2015.group4.service.BankAccountService;

public class BankAccountServiceImpl implements BankAccountService {

	@Autowired
	BankAccountDAO accountDAO;

	@Override
	public void createAccount(BankAccount account) {
		accountDAO.insert(account);
	}

}