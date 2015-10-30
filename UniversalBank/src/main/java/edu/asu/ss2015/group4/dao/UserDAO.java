package edu.asu.ss2015.group4.dao;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.dto.UserRequestsDTO;
import edu.asu.ss2015.group4.model.AccountLoginAttempts;
import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.model.editProfile;
import edu.asu.ss2015.group4.model.log;

public interface UserDAO {

	public String registerExternalUser(UserInformation userInfo) throws FileNotFoundException, NoSuchAlgorithmException;

	public List<UserInformationDTO> retrieveUserDetails(String username);

	public List<UserInformationDTO> retrieveDisabledExternalUserAccounts();

	public boolean enableExternalUserAccount(String username);

	public boolean unlockExternalUserAccount(String username);

	public void updatePassword(String username, String password);

	public void insertOTP(String otp, String otpValidity, String username);

	public String EditUser(String username, editProfile addInfo) throws FileNotFoundException, NoSuchAlgorithmException;

	void updateFailAttempts(String username);

	void resetFailAttempts(String username);

	public AccountLoginAttempts getUserAttempts(String username);

	public List<UserInformationDTO> fetchAllRegularEmployees();

	public void assignSupervisor(String userName, String employeeName);

	public void addDeleteAccountInfoRequest(String requestType, String requestBy, String approveBy);

	public List<UserRequestsDTO> getAllRequests();

	public boolean deleteAccount(String username);

	public List<UserInformationDTO> retrieveDelUserDetails(String username);

	public void modifyInternalUser(String accountType, String username)
			throws FileNotFoundException, NoSuchAlgorithmException;

	public void modifyInternalUser1(String accountType, String username)
			throws FileNotFoundException, NoSuchAlgorithmException;

	public List<UserInformationDTO> retrieveDisabledInternalUserAccounts();

	public List<UserInformationDTO> retrievePiiUserAccounts();

	public List<UserInformationDTO> retrieveIntUserAccounts();

	public List<log> retrievelogAccounts();

	public List<UserInformationDTO> retrieveDelIntUserAccounts();

	public List<UserInformationDTO> retrieveAuthPiiUserAccounts();

	public List<UserInformationDTO> retrieveAuthPiiUserAccounts1();

	public boolean enableInternalUserAccount(String username);

	public boolean addAgainInternalUserAccount(String username);

	public boolean enablePiiUserAccount(String username);

	public boolean disablePiiUserAccount(String username);

	public boolean deleteInternalUserAccount(String username);

	public List<UserInformationDTO> fetchAllManagerEmployees();

	public boolean processEditInfoRequest(String userName);

	public void savelog(Date gettime, String getid, String getcontent);

}
