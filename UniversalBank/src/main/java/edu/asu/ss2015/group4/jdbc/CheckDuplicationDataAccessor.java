package edu.asu.ss2015.group4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import edu.asu.ss2015.group4.dto.CheckDuplicationDTO;

public class CheckDuplicationDataAccessor implements ResultSetExtractor<CheckDuplicationDTO> {

	public CheckDuplicationDTO extractData(ResultSet resultSet) throws SQLException, DataAccessException {

		CheckDuplicationDTO checkDuplicateDTO = new CheckDuplicationDTO();

		checkDuplicateDTO.setUsername(resultSet.getString(1));
		checkDuplicateDTO.setEmail(resultSet.getString(2));
		checkDuplicateDTO.setSsn(resultSet.getString(3));

		return checkDuplicateDTO;
	}
}
