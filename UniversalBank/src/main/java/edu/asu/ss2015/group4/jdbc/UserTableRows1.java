package edu.asu.ss2015.group4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.asu.ss2015.group4.model.log;

public class UserTableRows1 implements RowMapper<log> {
	public log mapRow(ResultSet resultSet, int line) throws SQLException {
		logGenerator userGenerator = new logGenerator();
		return userGenerator.extractData(resultSet);
	}
}
