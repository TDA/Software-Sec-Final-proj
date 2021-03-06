package edu.asu.ss2015.group4.service;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.dto.UserRequestsDTO;
import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.model.editProfile;
import edu.asu.ss2015.group4.model.log;

public interface UserService {

	public String insertUserInformation(UserInformation addInfo) throws NoSuchAlgorithmException, FileNotFoundException;

	public List<UserInformationDTO> fetchUserDetails(String usernameSearch);

	public List<UserInformationDTO> fetchDisabledExternalUserDetails();

	public boolean activateExternalUserAccount(String username);

	public boolean unlockExternalUserAccount(String username);

	public String getUserRole(String username);

	public void updatePassword(String username, String password);

	public String EditInformation(String username, editProfile addInfo)
			throws NoSuchAlgorithmException, FileNotFoundException;

	public List<UserInformationDTO> fetchRegularEmployees();

	public void assignSupervisor(String userName, String employeeName);

	public void addDeleteAccountInfoRequest(String requestType, String requestBy, String approveBy);

	public List<UserRequestsDTO> getAllRequests();

	public boolean deleteAccount(String username);

	public List<UserInformationDTO> fetchDisabledInternalUserDetails();

	public List<UserInformationDTO> fetchPiiUserDetails();

	public List<UserInformationDTO> fetchIntUserDetails();

	public List<log> fetchlogDetails();

	public List<UserInformationDTO> fetchDelIntUserDetails();

	public List<UserInformationDTO> fetchAuthPiiUserDetails();

	public List<UserInformationDTO> fetchAuthPiiUserDetails1();

	public List<UserInformationDTO> fetchDelUserDetails(String usernameSearch);

	public boolean activateInternalUserAccount(String username);

	public boolean addDeletedInternalUserAccount(String username);

	public boolean activatePiiUserAccount(String username);

	public boolean deactivatePiiUserAccount(String username);

	public boolean deleteInternalUserAccount(String username);

	public void modifyInternalUserAccount(String accountType, String username)
			throws NoSuchAlgorithmException, FileNotFoundException;

	public void modifyInternalUserAccount1(String accountType, String username)
			throws NoSuchAlgorithmException, FileNotFoundException;

	public void insertOTP(String otp, String otpValidity, String username);

	public List<UserInformationDTO> fetchManagerEmployees();

	public boolean processEditInfoRequest(String string);

	public void savelog(Date gettime, String getid, String getcontent);

}
