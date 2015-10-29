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
import edu.asu.ss2015.group4.dto.EditInfoDTO;
import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.dto.UserRequestsDTO;
import edu.asu.ss2015.group4.jdbc.CheckDuplicationMapper;
import edu.asu.ss2015.group4.jdbc.EditInfoTableRows;
import edu.asu.ss2015.group4.jdbc.RequestTableRow;
import edu.asu.ss2015.group4.jdbc.UserTableRows;
import edu.asu.ss2015.group4.jdbc.UserTableRows1;
import edu.asu.ss2015.group4.model.AccountLoginAttempts;
import edu.asu.ss2015.group4.model.OTPGenerator;
import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.model.editProfile;
import edu.asu.ss2015.group4.model.log;

public class UserDAOImpl implements UserDAO {
	@Autowired
	DataSource dataSource;

	private static final String SQL_USERS_UPDATE_LOCKED = "UPDATE USERS SET userLocked = ? WHERE username = ?";
	private static final String SQL_USERS_COUNT = "SELECT count(*) FROM USERS WHERE username = ?";
	private static final int MAX_ATTEMPTS = 3;
	private static final String checkUnlocking = "select userLocked from users where username =?";
	private static final String SQL_User_Attpts_GET = "SELECT * FROM User_Attpts WHERE username = ?";
	private static final String SQL_User_Attpts_INSERT = "INSERT INTO User_Attpts (USERNAME, ATTEMPTS, LASTMODIFIED) VALUES(?,?,?)";
	private static final String SQL_User_Attpts_UPDATE_ATTEMPTS = "UPDATE User_Attpts SET attempts = attempts + 1, lastmodified = now() WHERE username = ?";
	private static final String SQL_User_Attpts_RESET_ATTEMPTS = "UPDATE User_Attpts SET attempts = 0, lastmodified = now() WHERE username = ?";

	public String registerExternalUser(UserInformation userInfo) throws FileNotFoundException {

		String registerUserQuery = "INSERT into users" + "(username, password, firstname,"
				+ " lastname, AccountType, enabled, userLocked, userAccountExpired,  email, SSN, piiAccess,otp, otpValidity, phonenumber, address, sex) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
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
							userInfo.getSocialSecurityNumber(), userInfo.isPiiAccess(), userInfo.getOTP(),
							userInfo.getOtpValidity(), userInfo.getPhoneNumber(), userInfo.getAddress(),
							userInfo.getSex() });

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
			case "Gov":
				user_role = "ROLE_Gov";
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

	public void insertOTP(String otp, String otpValidity, String username) {
		String insertQuery = "UPDATE users SET otp = ?, otpValidity = ? WHERE username = ?";
		JdbcTemplate jdbcTemplateForOTP = new JdbcTemplate(dataSource);
		List<CheckDuplicationDTO> list1 = new ArrayList<CheckDuplicationDTO>();
		list1 = checkDuplicateOTP(otp);
		OTPGenerator OTP = new OTPGenerator();
		while (list1.size() != 0) {
			int pwd = OTP.generateOTP();
			list1 = new ArrayList<CheckDuplicationDTO>();
			list1 = checkDuplicateOTP(Integer.toString(pwd));
		}

		jdbcTemplateForOTP.update(insertQuery, new Object[] { otp, otpValidity, username });

	}

	// Method for checking duplicate details
	public List<CheckDuplicationDTO> checkDuplicateExternalUser(String username, String email, String SSN) {
		List<CheckDuplicationDTO> duplicateCheckDetails = new ArrayList<CheckDuplicationDTO>();
		String getDuplicateDetailsQuery = "SELECT users.username, users.email, users.SSN, users.otp from users where users.username=? OR users.email=? OR users.SSN=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		duplicateCheckDetails = jdbcTemplate.query(getDuplicateDetailsQuery, new Object[] { username, email, SSN },
				new CheckDuplicationMapper());

		return duplicateCheckDetails;
	}

	public List<CheckDuplicationDTO> checkDuplicateOTP(String otp) {
		List<CheckDuplicationDTO> duplicateCheckDetails = new ArrayList<CheckDuplicationDTO>();
		String getDuplicateDetailsQuery = "SELECT users.otp from users where users.otp=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		duplicateCheckDetails = jdbcTemplate.query(getDuplicateDetailsQuery, new Object[] { otp },
				new CheckDuplicationMapper());

		return duplicateCheckDetails;
	}

	public List<UserInformationDTO> retrieveUserDetails(String username) {
		List<UserInformationDTO> customerInformationToDisplay = new ArrayList<UserInformationDTO>();
		String retrieveDetailsQuery = "SELECT users.username, users.firstname, users.lastname, "
				+ "users.AccountType, users.email, users.SSN, users.SupervisorName, users.otp, users.otpValidity, users.phonenumber, users.address, users.sex "
				+ "from users where users.username=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] { username },
				new UserTableRows());
		return customerInformationToDisplay;
	}

	public List<UserInformationDTO> retrieveDisabledExternalUserAccounts() {
		List<UserInformationDTO> customerInformationToDisplay = new ArrayList<UserInformationDTO>();
		String retrieveDetailsQuery = "SELECT users.username, users.firstname, users.lastname, "
				+ "users.AccountType, users.email, users.SSN, users.supervisorname, users.otp, users.otpValidity, users.phonenumber, users.address, users.sex "
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
		String sql = "UPDATE users set userLocked = 1, userAccountExpired = 1 where userLocked = false and username =  ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int status = jdbcTemplate.update(sql, new Object[] { username });
		if (status == 1) {
			return true;
		}
		return false;
	}

	public String EditUser(String username, editProfile userInfo) throws FileNotFoundException {
		String registerUserQuery = "INSERT into edit_info (username,phonenumber,address,sex) VALUES (?,?,?,?)";
		JdbcTemplate jdbcTemplateForExternalUser = new JdbcTemplate(dataSource);
		jdbcTemplateForExternalUser.update(registerUserQuery,
				new Object[] { username, userInfo.getPhoneNumber(), userInfo.getAddress(), userInfo.getSex() });
		addEditInfoRequest("Edit Profile", username, "");
		return "Request received! <br/> Please check you email for account approval notification!";
	}

	public void addDeleteAccountInfoRequest(String requestType, String requestBy, String approveBy) {
		String registerUserQuery = "INSERT into user_requests (requestBy,requstType,approvedBy) VALUES (?,?,?)";
		JdbcTemplate jdbcTemplateForExternalUser = new JdbcTemplate(dataSource);
		jdbcTemplateForExternalUser.update(registerUserQuery, new Object[] { requestBy, requestType, approveBy });
	}

	public void addEditInfoRequest(String requestType, String requestBy, String approveBy) {
		String registerUserQuery = "INSERT into user_requests (requestBy,requstType,approvedBy,approved) VALUES (?,?,?,?)";
		JdbcTemplate jdbcTemplateForExternalUser = new JdbcTemplate(dataSource);
		jdbcTemplateForExternalUser.update(registerUserQuery, new Object[] { requestBy, requestType, approveBy, 1 });
	}

	@Override
	public void updateFailAttempts(String username) {

		AccountLoginAttempts user = getUserAttempts(username);
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		if (user == null) {
			if (isUserExists(username)) {
				// if no record, insert a new
				jdbcTemplate.update(SQL_User_Attpts_INSERT, new Object[] { username, 1, new Date() });
			}
		} else {

			if (isUserExists(username)) {
				// update attempts count, +1
				jdbcTemplate.update(SQL_User_Attpts_UPDATE_ATTEMPTS, new Object[] { new Date(), username });
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
			AccountLoginAttempts userAttempts = jdbcTemplate.queryForObject(SQL_User_Attpts_GET,
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
		jdbcTemplate.update(SQL_User_Attpts_RESET_ATTEMPTS, new Object[] { username });
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
	public List<UserInformationDTO> fetchAllManagerEmployees() {
		List<UserInformationDTO> regulareEmployees = new ArrayList<UserInformationDTO>();
		String retrieveDetailsQuery = "SELECT * from users where users.AccountType like 'Manager'";
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

	public List<UserInformationDTO> retrieveDelUserDetails(String username) {
		List<UserInformationDTO> customerInformationToDisplay = new ArrayList<UserInformationDTO>();
		String retrieveDetailsQuery = "SELECT username, firstname, lastname, AccountType, email, SSN, SupervisorName, otp, otpvalidity, phonenumber, address, sex "
				+ "from deletedusers where username=?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new Object[] { username },
				new UserTableRows());
		return customerInformationToDisplay;
	}

	public List<UserInformationDTO> retrieveDisabledInternalUserAccounts() {
		List<UserInformationDTO> customerInformationToDisplay = new ArrayList<UserInformationDTO>();
		String retrieveDetailsQuery = "SELECT users.username, users.firstname, users.lastname, "
				+ "users.AccountType, users.email, users.SSN, users.SupervisorName, users.otp, users.otpvalidity, users.phonenumber, users.address, users.sex "
				+ "from users where (users.AccountType='Manager' OR users.AccountType='Clerk') AND users.enabled=false";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new UserTableRows());
		return customerInformationToDisplay;
	}

	public List<UserInformationDTO> retrievePiiUserAccounts() {
		List<UserInformationDTO> customerInformationToDisplay = new ArrayList<UserInformationDTO>();
		String retrieveDetailsQuery = "SELECT users.username, users.firstname, users.lastname, "
				+ "users.AccountType, users.email, users.SSN, users.SupervisorName, users.otp, users.otpvalidity, users.phonenumber, users.address, users.sex "
				+ "from users where (users.AccountType='Individual' OR users.AccountType='Merchant') and userLocked = true";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new UserTableRows());
		return customerInformationToDisplay;
	}

	public List<UserInformationDTO> retrieveIntUserAccounts() {
		List<UserInformationDTO> customerInformationToDisplay = new ArrayList<UserInformationDTO>();
		String retrieveDetailsQuery = "SELECT username, firstname, lastname, AccountType, email, SSN, SupervisorName, otp, otpvalidity, phonenumber, address, sex  from users where (AccountType='Clerk' OR AccountType='Manager') and userLocked = true";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new UserTableRows());
		return customerInformationToDisplay;
	}

	public List<log> retrievelogAccounts() {
		List<log> customerInformationToDisplay = new ArrayList<log>();
		String retrieveDetailsQuery = "SELECT * from LOGS";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new UserTableRows1());
		return customerInformationToDisplay;
	}

	public List<UserInformationDTO> retrieveDelIntUserAccounts() {
		List<UserInformationDTO> customerInformationToDisplay = new ArrayList<UserInformationDTO>();
		String retrieveDetailsQuery = "SELECT username, firstname, lastname, AccountType, email, SSN, SupervisorName, otp, otpvalidity, phonenumber, address, sex  from deletedusers where (AccountType='Clerk' OR AccountType='Manager')";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new UserTableRows());
		return customerInformationToDisplay;
	}

	public List<UserInformationDTO> retrieveAuthPiiUserAccounts() {
		List<UserInformationDTO> customerInformationToDisplay = new ArrayList<UserInformationDTO>();
		String retrieveDetailsQuery = "SELECT users.username, users.firstname, users.lastname, "
				+ "users.AccountType, users.email,users.SSN,users.SupervisorName, users.otp, users.otpvalidity, users.phonenumber, users.address, users.sex "
				+ "from users where (users.AccountType='Individual' OR users.AccountType='Merchant') and piiAccess = true";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new UserTableRows());
		return customerInformationToDisplay;
	}

	public List<UserInformationDTO> retrieveAuthPiiUserAccounts1() {
		List<UserInformationDTO> customerInformationToDisplay = new ArrayList<UserInformationDTO>();
		String retrieveDetailsQuery = "SELECT users.username, users.firstname, users.lastname, "
				+ "users.AccountType, users.email, users.SSN, users.SupervisorName, users.otp, users.otpvalidity, users.phonenumber, users.address, users.sex "
				+ "from users where (users.AccountType='Individual' OR users.AccountType='Merchant') and piiAccess = true";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		customerInformationToDisplay = jdbcTemplate.query(retrieveDetailsQuery, new UserTableRows());
		return customerInformationToDisplay;
	}

	// bug fixed by Indraneel: approval by manager
	public boolean enableInternalUserAccount(String username) {
		String sql = "UPDATE users set enabled = true,userLocked = true where enabled = false and (AccountType = 'Manager' or AccountType = 'Clerk') and username =  ?";
		String sql1 = "UPDATE users set userLocked = true where userLocked = false and (AccountType = 'Manager' or AccountType = 'Clerk') and username =  ?";
		String sql2 = "UPDATE users set userAccountExpired = true where (AccountType = 'Manager' or AccountType = 'Clerk') and username =  ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int status = jdbcTemplate.update(sql, new Object[] { username });
		int status1 = jdbcTemplate.update(sql1, new Object[] { username });
		jdbcTemplate.update(sql2, new Object[] { username });
		if (status == 1) {
			return true;
		}
		if (status1 == 1) {
			return true;
		}
		return false;
	}

	public boolean enablePiiUserAccount(String username) {
		String sql = "UPDATE users set piiAccess = true where username =  ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int status = jdbcTemplate.update(sql, new Object[] { username });
		if (status == 1) {
			return true;
		}
		return false;
	}

	public boolean disablePiiUserAccount(String username) {
		String sql = "UPDATE users set piiAccess = false where username =  ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		int status = jdbcTemplate.update(sql, new Object[] { username });

		if (status == 1) {
			return true;
		}

		return false;
	}

	public boolean deleteInternalUserAccount(String username) {
		String sql = "INSERT into deletedusers SELECT * from users where username =  ?";
		String sql1 = "INSERT into delete_user_roles SELECT * from user_roles where username =  ?";
		String sql2 = "DELETE from user_roles where username =  ?";
		String sql3 = "DELETE from users where username =  ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, new Object[] { username });
		jdbcTemplate.update(sql1, new Object[] { username });
		jdbcTemplate.update(sql2, new Object[] { username });
		jdbcTemplate.update(sql3, new Object[] { username });

		return false;
	}

	public boolean addAgainInternalUserAccount(String username) {
		System.out.println("here");
		String sql = "INSERT into users SELECT * from deletedusers where username =  ?";
		String sql1 = "INSERT into user_roles SELECT * from delete_user_roles where username =  ?";
		String sql2 = "DELETE from delete_user_roles where username =  ?";
		String sql3 = "DELETE from deletedusers where username =  ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, new Object[] { username });
		jdbcTemplate.update(sql1, new Object[] { username });
		jdbcTemplate.update(sql2, new Object[] { username });
		jdbcTemplate.update(sql3, new Object[] { username });

		return false;
	}

	public void modifyInternalUser(String accountType, String username) {
		String sql = "UPDATE users set AccountType = ? where username =  ?";
		// String sql1 = "UPDATE user_roles set role = ROLE_? where username =
		// ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, new Object[] { accountType, username });
		// jdbcTemplate.update(sql1, new Object[] { accountType,username });

	}

	@Override
	public boolean processEditInfoRequest(String userName) {
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		List<EditInfoDTO> customerInformationToDisplay = new ArrayList<EditInfoDTO>();
		String sql = "SELECT phonenumber, address, sex from edit_info where edit_info.username =?";
		String sql2 = "UPDATE user_requests set completed = true, approvedtime=now() where approved = true  and requestby = ?";
		int status2 = jdbcTemplate.update(sql2, new Object[] { userName });
		customerInformationToDisplay = jdbcTemplate.query(sql, new Object[] { userName }, new EditInfoTableRows());
		String sql3 = "UPDATE users set phonenumber=?, address=?, sex=? where  username = ?";
		String sql4 = "UPDATE edit_info set completed=1 where  username = ?";

		int status4 = jdbcTemplate.update(sql4, new Object[] { userName });
		int status3 = jdbcTemplate.update(sql3,
				new Object[] { customerInformationToDisplay.get(0).getPhoneNumber(),
						customerInformationToDisplay.get(0).getAddress(), customerInformationToDisplay.get(0).getSex(),
						userName });
		if (status2 > 0 && status3 > 0 && status4 > 0) {
			return true;
		}
		return false;
	}

	public void modifyInternalUser1(String accountType, String username) {
		// String sql = "UPDATE users set AccountType = ? where username = ?";
		// System.out.println("Hello "+accountType);
		String sql1 = "UPDATE user_roles set role = ? where username =  ?";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		// jdbcTemplate.update(sql, new Object[] { accountType,username });
		jdbcTemplate.update(sql1, new Object[] { accountType, username });

	}

	public void savelog(Date gettime, String getid, String getcontent) {
		String sql = "INSERT INTO LOGS (DATED,USER_ID,MESSAGE) VALUES(?,?,?)";
		JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);
		jdbcTemplate.update(sql, new Object[] { gettime, getid, getcontent });

	}
}
