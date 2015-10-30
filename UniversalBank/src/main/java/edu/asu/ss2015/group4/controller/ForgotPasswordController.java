package edu.asu.ss2015.group4.controller;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.service.UserService;

@Controller
public class ForgotPasswordController {

	@Autowired
	UserService userService;

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.GET)
	public ModelAndView forgotPassword() {

		ModelAndView model = new ModelAndView();
		model.setViewName("forgotPassword");
		return model;
	}

	@RequestMapping(value = "/forgotPassword", method = RequestMethod.POST)
	public ModelAndView validateForm(@RequestParam("userName") String userName, @RequestParam("email") String email,
			@RequestParam("new_passowrd") String new_passowrd,
			@RequestParam("confirm_new_passowrd") String confirm_new_passowrd) {

		ModelAndView model = new ModelAndView();
		List<UserInformationDTO> userDTO = userService.fetchUserDetails(userName);
		int number = 0;
		String pass = new_passowrd;
		for (char c : pass.toCharArray()) {
			if (Character.isDigit(c)) {
				number++;
			}
		}

		Pattern p = Pattern.compile("[!@#${},%^&*+_.-]");
		Matcher match = p.matcher(pass.subSequence(0, pass.length()));
		Matcher match_1 = p.matcher(userName.subSequence(0, userName.length()));
		if (pass.length() < 6 || pass.length() > 15 || number <= 0 || match.find() == false || userName.length() > 15
				|| userName.length() == 0 || match_1.find() == true) {
			model.addObject("errorMessage", "Invalid Password format!");
		}

		if (!(new_passowrd).equals(confirm_new_passowrd)) {
			model.addObject("errorMessage", "Password and Confirm Password Not match.");
		}

		if (userDTO != null && !userDTO.isEmpty() && validateEmailAndUserName(userName, userDTO.get(0).getUserName(),
				email, userDTO.get(0).getEmailAddress())) {

			if (new_passowrd != null && confirm_new_passowrd != null && new_passowrd.equals(confirm_new_passowrd)) {
				userService.updatePassword(userName, new_passowrd);
			}

			model.addObject("successMessage",
					"Your password has been changed! and account must be unlocked via homepage");
		} else {
			model.addObject("errorMessage", "Sorry, Unable to locate your account.");
		}

		return model;
	}

	private boolean validateEmailAndUserName(String userName, String dbUserName, String email, String dbEmail) {
		boolean retVal = true;
		if (userName == null || email == null || userName.isEmpty() || email.isEmpty() || !userName.equals(dbUserName)
				|| !email.equals(dbEmail))
			retVal = false;
		return retVal;
	}

}
