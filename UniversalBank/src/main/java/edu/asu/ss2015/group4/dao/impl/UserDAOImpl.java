package edu.asu.ss2015.group4.dao.impl;

import java.io.FileNotFoundException;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import edu.asu.ss2015.group4.dao.UserDAO;
import edu.asu.ss2015.group4.dto.CheckDuplicationDTO;
import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.dto.UserRequestsDTO;
import edu.asu.ss2015.group4.jdbc.CheckDuplicationMapper;
import edu.asu.ss2015.group4.jdbc.RequestTableRow;
import edu.asu.ss2015.group4.jdbc.UserTableRows;
import edu.asu.ss2015.group4.model.AccountLoginAttempts;
import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.model.editProfile;

public class UserDAOImpl implements UserDAO {
	@Autowired
	DataSource dataSource;

	private static final String SQL_USERS_UPDATE_LOCKED = "UPDATE USERS SET userLocked = ? WHERE username = ?";
	private static final String SQL_USERS_COUNT = "SELECT count(*) FROM USERS WHERE username = ?";
	private static final int MAX_ATTEMPTS = 3;

	private static final String SQL_USER_ATTEMPTS_GET = "SELECT * FROM USER_ATTEMPTS WHERE username = ?";
	private static final String SQL_USER_ATTEMPTS_INSERT = "INSERT INTO USER_ATTEMPTS (USERNAME, ATTEMPTS, LASTMODIFIED) VALUES(?,?,?)";
	private static final String SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS = "UPDATE USER_ATTEMPTS SET attempts = attempts + 1, lastmodified = ? WHERE username = ?";
	private static final String SQL_USER_ATTEMPTS_RESET_ATTEMPTS = "UPDATE USER_ATTEMPTS SET attempts = 0, lastmodified = now() WHERE username = ?";

	public String registerExternalUser(UserInformation userInfo) throws FileNotFoundException {

		String registerUserQuery = "INSERT into users" + "(username, password, firstname,"
				+ " lastname, AccountType, enabled, userLocked, userAccountExpired,  email, SSN) VALUES (?,?,?,?,?,?,?,?,?,?)";
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
							userInfo.getAccountType(), userInfo.isEnabled(), userInfo.isUserLocked(),
							userInfo.isUserAccountExpired(), userInfo.getEmailAddress(),
							userInfo.getSocialSecurityNumber() });

			String user_role = "";
			switch (userInfo.getAccountType()) {
			case "Individual":
				user_role = "ROLE_INDIVIDUAL";
				break;
			case "Merchant":
				user_role = "ROLE_MERCHANT";
				break;
			case "Clerk":
				user_role = "ROLE_CLERK";
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
				+ "users.AccountType, users.email, users.SupervisorName " + "from users where users.username=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] { username },
				new UserTableRows());
		return customerInformationToDisplay;
	}

	public List<UserInformationDTO> retrieveDisabledExternalUserAccounts() {
		List<UserInformationDTO> customerInformationToDisplay = new ArrayList<UserInformationDTO>();
		String retrieveDetailsQuery = "SELECT users.username, users.firstname, users.lastname, "
				+ "users.AccountType, users.email, users.supervisorname "
				+ "from users where (users.AccountType='Individual' OR users.AccountType='Merchant') AND users.enabled=false";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new UserTableRows());
		return customerInformationToDisplay;
	}

	public boolean enableExternalUserAccount(String username) {
		String sql = "UPDATE users set enabled = true where enabled = false and username =  ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int status = jdbcTemplate.update(sql, new Object[] { username });
		if (status == 1) {
			return true;
		}
		return false;
	}

	public boolean unlockExternalUserAccount(String username) {
		String sql = "UPDATE users set userLocked = true where userLocked = false and username =  ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int status = jdbcTemplate.update(sql, new Object[] { username });
		if (status == 1) {
			return true;
		}
		return false;
	}

	public String EditUser(String username, editProfile userInfo) throws FileNotFoundException {
		String registerUserQuery = "INSERT into edit_info (username,emailID,SSN) VALUES (?,?,?)";
		JdbcTemplate jdbcTemplateForExternalUser = new JdbcTemplate(dataSource);
		jdbcTemplateForExternalUser.update(registerUserQuery,
				new Object[] { username, userInfo.getEmailAddress(), userInfo.getSocialSecurityNumber() });
		return "Request received! <br/> Please check you email for account approval notification!";
	}

	public void addEditInfoRequest(String requestType, String requestBy, String approveBy) {
		String registerUserQuery = "INSERT into user_requests (requestBy,requstType,approvedBy) VALUES (?,?,?)";
		JdbcTemplate jdbcTemplateForExternalUser = new JdbcTemplate(dataSource);
		jdbcTemplateForExternalUser.update(registerUserQuery, new Object[] { requestBy, requestType, approveBy });
	}

	@Override
	public void updateFailAttempts(String username) {

		AccountLoginAttempts user = getUserAttempts(username);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		if (user == null) {
			if (isUserExists(username)) {
				// if no record, insert a new
				jdbcTemplate.update(SQL_USER_ATTEMPTS_INSERT, new Object[] { username, 1, new Date() });
			}
		} else {

			if (isUserExists(username)) {
				// update attempts count, +1
				jdbcTemplate.update(SQL_USER_ATTEMPTS_UPDATE_ATTEMPTS, new Object[] { new Date(), username });
			}

			if (user.getAttempts() + 1 >= MAX_ATTEMPTS) {
				// locked user
				jdbcTemplate.update(SQL_USERS_UPDATE_LOCKED, new Object[] { false, username });
				// throw exception
				throw new LockedException("User Account is locked!");
			}

		}

	}

	public DataSource getDataSource() {
		return dataSource;
	}

	public void setDataSource(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	@Override
	public AccountLoginAttempts getUserAttempts(String username) {
		try {
			JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
			AccountLoginAttempts userAttempts = jdbcTemplate.queryForObject(SQL_USER_ATTEMPTS_GET,
					new Object[] { username }, new RowMapper<AccountLoginAttempts>() {
						public AccountLoginAttempts mapRow(ResultSet rs, int rowNum) throws SQLException {
							AccountLoginAttempts user = new AccountLoginAttempts();
							user.setId(rs.getInt("id"));
							user.setUsername(rs.getString("username"));
							user.setAttempts(rs.getInt("attempts"));
							user.setLastModified(rs.getDate("lastModified"));
							return user;
						}

					});
			return userAttempts;

		} catch (EmptyResultDataAccessException e) {
			return null;
		}

	}

	@Override
	public void resetFailAttempts(String username) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		// jdbcTemplate.update(SQL_USER_ATTEMPTS_RESET_ATTEMPTS, new Object[] {
		// username });
	}

	private boolean isUserExists(String username) {

		boolean result = false;
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int count = jdbcTemplate.queryForObject(SQL_USERS_COUNT, new Object[] { username }, Integer.class);
		if (count > 0) {
			result = true;
		}
		return result;
	}

	@Override
	public List<UserInformationDTO> fetchAllRegularEmployees() {
		List<UserInformationDTO> regulareEmployees = new ArrayList<UserInformationDTO>();
		String retrieveDetailsQuery = "SELECT * from users where users.AccountType like 'Clerk'";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		regulareEmployees = jdbcTemplate.query(retrieveDetailsQuery, new UserTableRows());
		return regulareEmployees;
	}

	@Override
	public void assignSupervisor(String userName, String employeeName) {
		String sql = "UPDATE users set SupervisorName = ? where username =  ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, new Object[] { employeeName, userName });
	}

	@Override
	public List<UserRequestsDTO> getAllRequests() {
		List<UserRequestsDTO> requests = new ArrayList<UserRequestsDTO>();
		String retrieveDetailsQuery = "SELECT * from user_requests where user_requests.Approved=1 and user_requests.Completed=0";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		requests = jdbcTemplate.query(retrieveDetailsQuery, new RequestTableRow());
		return requests;
	}

	public boolean deleteAccount(String username) {

		String sql = "UPDATE users set enabled = false, userAccountExpired=false, userLocked=false where enabled = true  and username =  ?";
		String sql2 = "UPDATE user_requests set completed = true, approvedtime=now() where approved = true  and requestby =  ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int status = jdbcTemplate.update(sql, new Object[] { username });
		int status2 = jdbcTemplate.update(sql2, new Object[] { username });
		if (status == 1 && status2 == 1) {
			return true;
		}
		return false;
	}

}
