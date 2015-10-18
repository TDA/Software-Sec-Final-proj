package edu.asu.ss2015.group4.service.impl;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.asu.ss2015.group4.dao.UserDAO;
import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.service.UserService;

public class UserServiceImpl implements UserService {
	@Autowired
	UserDAO userDAO;

	public String insertUserInformation(UserInformation addInfo)
			throws NoSuchAlgorithmException, FileNotFoundException {
		return userDAO.registerExternalUser(addInfo);
	}

	public List<UserInformationDTO> fetchUserDetails(String usernameSearch) {
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
	
	public String EditInformation(UserInformation addInfo)
			throws NoSuchAlgorithmException, FileNotFoundException {
		return userDAO.EditUser(addInfo);
	}
	
}
