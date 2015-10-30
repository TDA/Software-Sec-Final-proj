package edu.asu.ss2015.group4.service.impl;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import edu.asu.ss2015.group4.dao.UserDAO;
import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.dto.UserRequestsDTO;
import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.model.editProfile;
import edu.asu.ss2015.group4.model.log;
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

	public void updatePassword(String username, String password) {
		userDAO.updatePassword(username, password);
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

	public String EditInformation(String userName, editProfile addInfo)
			throws NoSuchAlgorithmException, FileNotFoundException {
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
	public void addDeleteAccountInfoRequest(String requestType, String requestBy, String approveBy) {
		userDAO.addDeleteAccountInfoRequest(requestType, requestBy, approveBy);
	}

	@Override
	public List<UserRequestsDTO> getAllRequests() {
		return userDAO.getAllRequests();
	}

	@Override
	public boolean deleteAccount(String username) {
		return userDAO.deleteAccount(username);
	}

	public List<UserInformationDTO> fetchDelUserDetails(String usernameSearch) {
		return userDAO.retrieveDelUserDetails(usernameSearch);
	}

	@Override
	public void modifyInternalUserAccount(String accountType, String username)
			throws NoSuchAlgorithmException, FileNotFoundException {
		userDAO.modifyInternalUser(accountType, username);
	}

	@Override
	public void modifyInternalUserAccount1(String accountType, String username)
			throws NoSuchAlgorithmException, FileNotFoundException {
		userDAO.modifyInternalUser1(accountType, username);
	}

	public List<UserInformationDTO> fetchDisabledInternalUserDetails() {
		return userDAO.retrieveDisabledInternalUserAccounts();
	}

	public List<UserInformationDTO> fetchPiiUserDetails() {
		return userDAO.retrievePiiUserAccounts();
	}

	public List<UserInformationDTO> fetchIntUserDetails() {
		return userDAO.retrieveIntUserAccounts();
	}

	public List<log> fetchlogDetails() {
		return userDAO.retrievelogAccounts();
	}

	public List<UserInformationDTO> fetchDelIntUserDetails() {
		return userDAO.retrieveDelIntUserAccounts();
	}

	public List<UserInformationDTO> fetchAuthPiiUserDetails() {
		return userDAO.retrieveAuthPiiUserAccounts();
	}

	public List<UserInformationDTO> fetchAuthPiiUserDetails1() {
		return userDAO.retrieveAuthPiiUserAccounts1();
	}

	public boolean activateInternalUserAccount(String username) {
		return userDAO.enableInternalUserAccount(username);
	}

	public boolean addDeletedInternalUserAccount(String username) {
		return userDAO.addAgainInternalUserAccount(username);
	}

	public boolean activatePiiUserAccount(String username) {
		return userDAO.enablePiiUserAccount(username);
	}

	public boolean deactivatePiiUserAccount(String username) {
		return userDAO.disablePiiUserAccount(username);
	}

	public boolean deleteInternalUserAccount(String username) {
		return userDAO.deleteInternalUserAccount(username);
	}

	public void insertOTP(String otp, String otpValidity, String username) {
		userDAO.insertOTP(otp, otpValidity, username);

	}

	public void savelog(Date gettime, String getid, String getcontent) {
		userDAO.savelog(gettime, getid, getcontent);
	}

	@Override
	public List<UserInformationDTO> fetchManagerEmployees() {
		return userDAO.fetchAllManagerEmployees();
	}

	@Override
	public boolean processEditInfoRequest(String userName) {
		return userDAO.processEditInfoRequest(userName);
	}

}
