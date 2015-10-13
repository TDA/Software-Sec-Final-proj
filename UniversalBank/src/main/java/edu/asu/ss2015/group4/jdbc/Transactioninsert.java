
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

		transacDTO.setAmount(resultSet.getFloat(4));
		transacDTO.setCustomer_id(resultSet.getInt(2));
		transacDTO.setMerchant_id(resultSet.getInt(3));
		transacDTO.setTransaction_id(resultSet.getInt(1));
		transacDTO.setTimestamp(resultSet.getTimestamp(5));
		return transacDTO;

		
	}
}
