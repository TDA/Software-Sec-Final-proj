package edu.asu.ss2015.group4.jdbc;

import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.jdbc.core.RowMapper;

import edu.asu.ss2015.group4.dto.EditInfoDTO;

public class EditInfoTableRows implements RowMapper<EditInfoDTO> {
	public EditInfoDTO mapRow(ResultSet resultSet, int line) throws SQLException {
		EditInfoGenerator userGenerator = new EditInfoGenerator();
		return userGenerator.extractData(resultSet);
	}
}
