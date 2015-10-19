package edu.asu.ss2015.group4.dao;

import java.util.List;

import edu.asu.ss2015.group4.dto.TransactionDTO;
import edu.asu.ss2015.group4.model.*;
public interface TransactionDAO {

		public void insert(Transactions transaction);
		public List <TransactionDTO>view (String Username);
		public void MerchantPayment(Transactions transaction);
		public void Transfer(Transactions transaction);
		public void Debit(Transactions transaction);
		public void Credit(Transactions transaction);
}
