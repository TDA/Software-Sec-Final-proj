package edu.asu.ss2015.group4.controller;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.service.UserService;

/*
 * ExternalUserController: accountSummary.jsp
 */

@Controller
@SessionAttributes("userName")
public class AccountController {
	@Autowired
	UserService userService;

	@RequestMapping(value = "/account", method = RequestMethod.GET)
	public ModelAndView returnCustomerPage() {
		ModelAndView modelAndView = new ModelAndView();
		List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			System.out.println(loggedInUser);
			
			// Call the DAOImpl layer
			custInfoFromDTO = userService.fetchUserDetails(loggedInUser);

			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);

			modelAndView.setViewName("account");
		} else {
			modelAndView.setViewName("permission-denied");
		}
		return modelAndView;
	}
	@RequestMapping(value = "/DisplaySignUp", method = RequestMethod.GET)
	public ModelAndView EditPage() {
		ModelAndView modelAndView = new ModelAndView();
		List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			System.out.println(loggedInUser);
			
			// Call the DAOImpl layer
			custInfoFromDTO = userService.fetchUserDetails(loggedInUser);

			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);

			modelAndView.setViewName("DisplaySignUp");
		} else {
			modelAndView.setViewName("permission-denied");
		}
		return modelAndView;
	}
	@RequestMapping(value = "/DisplaySignUp", method = RequestMethod.POST)
	public ModelAndView EditPageUpdate(@Valid @ModelAttribute("editForm") UserInformation custInfo,
		 HttpServletRequest request) throws NoSuchAlgorithmException, FileNotFoundException {
			ModelAndView modelAndView = new ModelAndView();
					
			//SignUpFormValidator.validateForm(custInfo, result);

		userService.EditInformation(custInfo);
				
					modelAndView.setViewName("success");
				
				return modelAndView;
			}
		}

	


