package edu.asu.ss2015.group4.service;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.model.UserInformation;

public interface UserService {

	public String insertUserInformation(UserInformation addInfo) throws NoSuchAlgorithmException, FileNotFoundException;
	public List<UserInformationDTO> fetchUserDetails(String usernameSearch);
	public List<UserInformationDTO> fetchDisabledExternalUserDetails();
	public boolean activateExternalUserAccount(String username);
	public boolean unlockExternalUserAccount(String username);
<<<<<<< HEAD
	
	public void insertOTP(String otp,String otpValidity, String username);
=======
	public String EditInformation(UserInformation addInfo) throws NoSuchAlgorithmException, FileNotFoundException;
>>>>>>> refs/remotes/origin/master
}
