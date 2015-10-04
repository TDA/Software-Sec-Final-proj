package edu.asu.ss2015.group4.service;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.List;

import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.model.UserInformation;

public interface UserService {

	public String insertUserInformation(UserInformation addInfo) throws NoSuchAlgorithmException, FileNotFoundException;

	public List<UserInformationDTO> fetchUserDetails(String usernameSearch);

}
