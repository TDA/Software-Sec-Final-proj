package edu.asu.ss2015.group4.dao;

import java.util.List;

/**
 * @author schandramouli
 *
 */

import edu.asu.ss2015.group4.model.Account;

public interface AccountDAO {
		public void insert(Account account);
		public void update(Account account);
		public void update(List<Account> accounts); 
		public void delete(long accountId);
		public Account find(long accountId);
		public List<Account> find(List<Long> accountIds);
		public List<Account> find(String ownerName);
}
