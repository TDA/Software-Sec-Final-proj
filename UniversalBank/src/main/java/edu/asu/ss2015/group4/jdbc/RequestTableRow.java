package edu.asu.ss2015.group4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.asu.ss2015.group4.dto.UserRequestsDTO;

public class RequestTableRow implements RowMapper<UserRequestsDTO> {
	public UserRequestsDTO mapRow(ResultSet resultSet, int line) throws SQLException {
		RequestGenerator userGenerator = new RequestGenerator();
		return userGenerator.extractData(resultSet);
	}
}