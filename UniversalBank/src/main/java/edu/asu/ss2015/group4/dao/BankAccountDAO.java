package edu.asu.ss2015.group4.dao;

import java.util.List;

import edu.asu.ss2015.group4.model.BankAccount;

public interface BankAccountDAO {
	public void insert(BankAccount account);

	public void update(BankAccount account);

	public void update(List<BankAccount> accounts);

	public void delete(long accountId);

	public BankAccount find(long accountId);

	public List<BankAccount> find(List<Long> accountIds);

	public List<BankAccount> find(String ownerName);
}
