package edu.asu.ss2015.group4.service.impl;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.asu.ss2015.group4.dao.UserDAO;
import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.dto.UserRequestsDTO;
import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.model.editProfile;
import edu.asu.ss2015.group4.service.UserService;

public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;

	public String insertUserInformation(UserInformation addInfo)
			throws NoSuchAlgorithmException, FileNotFoundException {
		return userDAO.registerExternalUser(addInfo);
	}

	public List<UserInformationDTO> fetchUserDetails(String usernameSearch) {
		System.out.println("in fetch user details");
		return userDAO.retrieveUserDetails(usernameSearch);
	}
	
	public List<UserInformationDTO> fetchDisabledExternalUserDetails() {
		return userDAO.retrieveDisabledExternalUserAccounts();
	}

	public boolean activateExternalUserAccount(String username) {
		return userDAO.enableExternalUserAccount(username);
	}

	public boolean unlockExternalUserAccount(String username) {
		return userDAO.unlockExternalUserAccount(username);
	}


	public String EditInformation(String userName, editProfile addInfo) throws NoSuchAlgorithmException, FileNotFoundException {
		return userDAO.EditUser(userName, addInfo);
	}

	@Override
	public List<UserInformationDTO> fetchRegularEmployees() {
		return userDAO.fetchAllRegularEmployees();
	}

	@Override
	public void assignSupervisor(String userName, String employeeName) {
		userDAO.assignSupervisor(userName, employeeName);
	}

	@Override
	public void addEditInfoRequest(String requestType, String requestBy, String approveBy) {
		userDAO.addEditInfoRequest(requestType, requestBy, approveBy);
	}

	@Override
	public List<UserRequestsDTO> getAllRequests() {
		return userDAO.getAllRequests();
	}

	@Override
	public boolean deleteAccount(String username) {
		return userDAO.deleteAccount(username);
	}



	@Override
	public void insertOTP(String otp, String otpValidity, String username) {
		userDAO.insertOTP(otp, otpValidity, username);
		
	}


	
	/*public String EditInformation(String username, editProfile addInfo)
			throws NoSuchAlgorithmException, FileNotFoundException {
		return userDAO.EditUser(String username, editProfile addInfo);
	}*/
	


}
