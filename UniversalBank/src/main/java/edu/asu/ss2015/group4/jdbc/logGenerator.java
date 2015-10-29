package edu.asu.ss2015.group4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.model.log;

public class logGenerator implements ResultSetExtractor<log> {

	public log extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		log userInfoDTO = new log();
		userInfoDTO.setid(resultSet.getString(1));
		userInfoDTO.settime(resultSet.getDate(2));
		userInfoDTO.setcontent(resultSet.getString(3));
		
		return userInfoDTO;
	}
}
