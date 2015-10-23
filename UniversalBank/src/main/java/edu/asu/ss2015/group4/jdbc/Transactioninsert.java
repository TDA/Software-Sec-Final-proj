package edu.asu.ss2015.group4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import edu.asu.ss2015.group4.dto.TransactionDTO;

public class Transactioninsert implements ResultSetExtractor<TransactionDTO> {

	public TransactionDTO extractData(ResultSet resultSet) throws SQLException, DataAccessException {

		TransactionDTO transacDTO = new TransactionDTO();
		transacDTO.setTransactionID(resultSet.getInt(1));
		transacDTO.setTransactionType(resultSet.getString(2));
		transacDTO.setAmount(resultSet.getString(3));
		transacDTO.setTransactionAccountID(resultSet.getString(4));
		transacDTO.setAuthorizedManagerID(resultSet.getString(5));
		transacDTO.setTransactionTime(resultSet.getTimestamp(6));
		transacDTO.setApproved(resultSet.getBoolean(7));
		transacDTO.setApprovedTime(resultSet.getTimestamp(8));
		transacDTO.setComments(resultSet.getString(9));
		transacDTO.setAuthoriseBank(resultSet.getInt(10));
		return transacDTO;

	}
}

