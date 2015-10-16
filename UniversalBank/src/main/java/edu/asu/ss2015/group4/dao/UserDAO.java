package edu.asu.ss2015.group4.dao;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.model.UserInformation;

public interface UserDAO {

	public String registerExternalUser(UserInformation userInfo) throws FileNotFoundException, NoSuchAlgorithmException;
	public List<UserInformationDTO> retrieveUserDetails(String username);
	public String EditUser(UserInformation userInfo) throws FileNotFoundException, NoSuchAlgorithmException;

}
