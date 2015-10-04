package edu.asu.ss2015.group4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.asu.ss2015.group4.dto.UserInformationDTO;

public class UserTableRows implements RowMapper<UserInformationDTO> {
	public UserInformationDTO mapRow(ResultSet resultSet, int line) throws SQLException {
		UserGenerator userGenerator = new UserGenerator();
		return userGenerator.extractData(resultSet);
	}
}
