
package edu.asu.ss2015.group4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import edu.asu.ss2015.group4.dto.TransactionDTO;
import edu.asu.ss2015.group4.dto.UserInformationDTO;

public class Transactioninsert implements ResultSetExtractor<TransactionDTO> {

	public TransactionDTO extractData(ResultSet resultSet) throws SQLException, DataAccessException {

		TransactionDTO transacDTO = new TransactionDTO();

		transacDTO.setTransactionID(resultSet.getInt(1));
		transacDTO.setTransactionType(resultSet.getString(2));
		transacDTO.setAmount(resultSet.getFloat(3));
		transacDTO.setTransactionStartUser(resultSet.getString(4));
		transacDTO.setTransactionStartAccountID(resultSet.getString(5));
		transacDTO.setTransactionEndUser(resultSet.getString(6));
		transacDTO.setTransactionEndAccountID(resultSet.getString(7));
		transacDTO.setAuthorizedManagerID(resultSet.getString(8));
		transacDTO.setTransactionTime(resultSet.getTimestamp(9));
		transacDTO.setApproved(false);
		return transacDTO;

		
	}
}
