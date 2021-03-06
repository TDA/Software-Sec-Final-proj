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
		userInfoDTO.setSocialSecurityNumber(resultSet.getString(6));
		userInfoDTO.setSupervisorName(resultSet.getString(7));
		userInfoDTO.setOTP(resultSet.getString(8));
		userInfoDTO.setOtpValidity(resultSet.getString(9));
		userInfoDTO.setPhoneNumber(resultSet.getString(10));
		userInfoDTO.setAddress(resultSet.getString(11));
		userInfoDTO.setSex(resultSet.getString(12));
		return userInfoDTO;
	}
}
