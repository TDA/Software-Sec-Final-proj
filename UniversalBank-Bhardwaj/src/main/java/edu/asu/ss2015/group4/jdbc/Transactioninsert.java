
package edu.asu.ss2015.group4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import edu.asu.ss2015.group4.dto.TransactionDTO;

public class Transactioninsert implements ResultSetExtractor<TransactionDTO> {

	public TransactionDTO extractData(ResultSet resultSet) throws SQLException, DataAccessException {

		TransactionDTO transacDTO = new TransactionDTO();

		transacDTO.setAmount(resultSet.getFloat(3));
		transacDTO.setCustomer_id(resultSet.getInt(1));
		transacDTO.setMerchant_id(resultSet.getInt(2));
		transacDTO.setTransaction_type(resultSet.getString(4));
		transacDTO.setBalance(resultSet.getInt(5));
		return transacDTO;

		
	}
}
