package edu.asu.ss2015.group4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import edu.asu.ss2015.group4.dto.UserInformationDTO;

public class UserGenerator implements ResultSetExtractor<UserInformationDTO> {

	public UserInformationDTO extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		UserInformationDTO userInfoDTO = new UserInformationDTO();
		userInfoDTO.setUserName(resultSet.getString(1));
		userInfoDTO.setFirstName(resultSet.getString(2));
		userInfoDTO.setLastName(resultSet.getString(3));
		userInfoDTO.setAccountType(resultSet.getString(4));
		userInfoDTO.setEmailAddress(resultSet.getString(5));
		userInfoDTO.setSupervisorName(resultSet.getString(6));
		return userInfoDTO;
	}
}
