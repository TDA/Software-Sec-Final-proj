package edu.asu.ss2015.group4.dao;

import java.util.List;

import edu.asu.ss2015.group4.dto.TransactionDTO;
import edu.asu.ss2015.group4.model.*;
public interface TransactionDAO {

		public List <TransactionDTO>view (String Username);
		public List <TransactionDTO>viewCondition (String Username);
		public void MerchantPayment(Transactions transaction);
		public void Transfer(Transactions transaction);
		public void Debit(Transactions transaction);
		public void Credit(Transactions transaction);
		void approval(int a);
}
