package edu.asu.ss2015.group4.controller;

import java.util.List;

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
	public ModelAndView validateForm(@RequestParam("email") String email, @RequestParam("userName") String userName) {

		ModelAndView model = new ModelAndView();
		List<UserInformationDTO> userDTO = userService.fetchUserDetails(userName);
		if (userDTO != null && !userDTO.isEmpty() && validateEmailAndUserName(userName, userDTO.get(0).getUserName(),
				email, userDTO.get(0).getEmailAddress())) {
			model.addObject("successMessage", "Password has been sent to your email!");
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
