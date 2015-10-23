package edu.asu.ss2015.group4.dao;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.model.AccountLoginAttempts;
import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.model.editProfile;

public interface UserDAO {

	public String registerExternalUser(UserInformation userInfo) throws FileNotFoundException, NoSuchAlgorithmException;

	public List<UserInformationDTO> retrieveUserDetails(String username);

	public List<UserInformationDTO> retrieveDisabledExternalUserAccounts();

	public boolean enableExternalUserAccount(String username);

	public boolean unlockExternalUserAccount(String username);

	public String EditUser(editProfile addInfo) throws FileNotFoundException, NoSuchAlgorithmException;

	void updateFailAttempts(String username);

	void resetFailAttempts(String username);

	AccountLoginAttempts getUserAttempts(String username);

	public List<UserInformationDTO> fetchAllRegularEmployees();

	public void assignSupervisor(String userName, String employeeName);

	public void addEditInfoRequest(String requestType, String requestBy, String approveBy);

}

