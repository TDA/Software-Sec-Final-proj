package edu.asu.ss2015.group4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import edu.asu.ss2015.group4.dto.UserRequestsDTO;

public class RequestGenerator implements ResultSetExtractor<UserRequestsDTO> {

	public UserRequestsDTO extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		UserRequestsDTO userInfoDTO = new UserRequestsDTO();
		userInfoDTO.setRequestBy(resultSet.getString(2));
		userInfoDTO.setRequestType(resultSet.getString(3));
		userInfoDTO.setApprovedBy(resultSet.getString(4));
		return userInfoDTO;
	}
}