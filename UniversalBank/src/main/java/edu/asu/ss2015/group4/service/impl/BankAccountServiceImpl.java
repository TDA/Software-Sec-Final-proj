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

	public int BankValidate(BankAccount account,String AccountType) {

		int a = accountDAO.Validate(account,AccountType);
		return a;
	}

	@Override
	public double BankBalanceValidate(BankAccount account) {
		double a = accountDAO.ValidateBalance(account);
		return a;
	}

	@Override
	public double BankBalancechecking(BankAccount account) {

		return accountDAO.CheckingBalance(account);
	}

	@Override
	public double BankBalancesaving(BankAccount account) {
		// TODO Auto-generated method stub
		return accountDAO.SavingBalance(account);
	}

	@Override
	public double BankBalanceValidateForRegularEmployee(BankAccount account, int i) {
		double a = accountDAO.ValidateBalanceForRegularEmployee(account, i);
		return a;
	}
}
