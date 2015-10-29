package edu.asu.ss2015.group4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.ResultSetExtractor;

import edu.asu.ss2015.group4.dto.EditInfoDTO;

public class EditInfoGenerator implements ResultSetExtractor<EditInfoDTO> {
	public EditInfoDTO extractData(ResultSet resultSet) throws SQLException, DataAccessException {
		EditInfoDTO editInfoDTO = new EditInfoDTO();
		editInfoDTO.setPhoneNumber(resultSet.getString(1));
		editInfoDTO.setAddress(resultSet.getString(2));
		editInfoDTO.setSex(resultSet.getString(3));

		return editInfoDTO;
	}
}
