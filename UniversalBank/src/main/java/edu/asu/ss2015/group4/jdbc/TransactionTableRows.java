package edu.asu.ss2015.group4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;
import org.springframework.jdbc.core.RowMapper;

import edu.asu.ss2015.group4.dto.TransactionDTO;

public class TransactionTableRows implements RowMapper<TransactionDTO> {
	public TransactionDTO mapRow(ResultSet resultSet, int line) throws SQLException {
		Transactioninsert Transacinsert  = new Transactioninsert();
		return Transacinsert.extractData(resultSet);
	}
}
