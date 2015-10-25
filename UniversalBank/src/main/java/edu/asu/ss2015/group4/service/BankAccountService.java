package edu.asu.ss2015.group4.service;

import edu.asu.ss2015.group4.model.BankAccount;

public interface BankAccountService {

	void createAccount(BankAccount svgAccount);
	int BankValidate(BankAccount a);
    double BankBalanceValidate(BankAccount account);
    double BankBalancechecking(BankAccount account);
    double BankBalancesaving(BankAccount account);
}
