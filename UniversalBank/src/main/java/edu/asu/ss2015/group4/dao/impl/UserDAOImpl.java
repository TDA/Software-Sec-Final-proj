package edu.asu.ss2015.group4.dao.impl;

import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.asu.ss2015.group4.dao.UserDAO;
import edu.asu.ss2015.group4.dto.CheckDuplicationDTO;
import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.jdbc.CheckDuplicationMapper;
import edu.asu.ss2015.group4.jdbc.UserTableRows;
import edu.asu.ss2015.group4.model.UserInformation;

public class UserDAOImpl implements UserDAO {
	@Autowired
	DataSource dataSource;

	public String registerExternalUser(UserInformation userInfo) throws FileNotFoundException {

		String registerUserQuery = "INSERT into users" + "(username, password, firstname,"
				+ " lastname, AccountType, enabled, email, SSN) VALUES (?,?,?,?,?,?,?,?)";
		String insertIntoUserRolesTable = "INSERT into user_roles (username, role) " + "VALUES (?,?)";

		JdbcTemplate jdbcTemplateForExternalUser = new JdbcTemplate(dataSource);
		JdbcTemplate jdbcTemplateForUserRoles = new JdbcTemplate(dataSource);
		BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
		String hash = encoder.encode(userInfo.getPassword());

		List<CheckDuplicationDTO> list1 = new ArrayList<CheckDuplicationDTO>();
		list1 = checkDuplicateExternalUser(userInfo.getUserName(), userInfo.getEmailAddress(),
				userInfo.getSocialSecurityNumber());

		if (list1.size() != 0) {
			return "UserName, Email or SSN is already used!";
		} else {
			jdbcTemplateForExternalUser.update(registerUserQuery,
					new Object[] { userInfo.getUserName(), hash, userInfo.getFirstName(), userInfo.getLastName(),
							userInfo.getAccountType(), userInfo.isEnabled(), userInfo.getEmailAddress(),
							userInfo.getSocialSecurityNumber() });

			String user_role = "";
			switch (userInfo.getAccountType()) {
			case "Individual":
				user_role = "ROLE_CUSTOMER";
				break;
			case "Merchant":
				user_role = "ROLE_MERCHANT";
				break;
			case "Clerk":
				user_role = "ROLE_EMPLOYEE";
				break;
			case "Manager":
				user_role = "ROLE_MANAGER";
				break;
			default:
				user_role = "ROLE_INVALID";
			}
			jdbcTemplateForUserRoles.update(insertIntoUserRolesTable,
					new Object[] { userInfo.getUserName(), user_role });
		}

		return "Registration Completed! <br/> Please check you email for account approval notification!";
	}

	// Method for checking duplicate details
	public List<CheckDuplicationDTO> checkDuplicateExternalUser(String username, String email, String SSN) {
		List<CheckDuplicationDTO> duplicateCheckDetails = new ArrayList<CheckDuplicationDTO>();
		String getDuplicateDetailsQuery = "SELECT users.username, users.email, users.SSN from users where users.username=? OR users.email=? OR users.SSN=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		duplicateCheckDetails = jdbcTemplate.query(getDuplicateDetailsQuery, new Object[] { username, email, SSN },
				new CheckDuplicationMapper());

		return duplicateCheckDetails;
	}

	public List<UserInformationDTO> retrieveUserDetails(String username) {
		List<UserInformationDTO> customerInformationToDisplay = new ArrayList<UserInformationDTO>();
		String retrieveDetailsQuery = "SELECT users.username, users.firstname, users.lastname, "
				+ "users.AccountType, users.email " + "from users where users.username=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] { username },
				new UserTableRows());
		return customerInformationToDisplay;
	}

}
