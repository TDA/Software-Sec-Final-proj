package edu.asu.ss2015.group4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.asu.ss2015.group4.dto.CheckDuplicationDTO;

public class CheckDuplicationMapper implements RowMapper<CheckDuplicationDTO> {
	public CheckDuplicationDTO mapRow(ResultSet resultSet, int line) throws SQLException {
		CheckDuplicationDataAccessor duplicateDataAccessor = new CheckDuplicationDataAccessor();
		return duplicateDataAccessor.extractData(resultSet);
	}
}
