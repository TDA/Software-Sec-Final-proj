package edu.asu.ss2015.group4.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.service.MailingService;
import edu.asu.ss2015.group4.service.UserService;

@Controller
@SessionAttributes("userName")
public class GovController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/gov", method = RequestMethod.GET)
	public ModelAndView govPage() {

		ModelAndView modelAndView = new ModelAndView();
		List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
		List<UserInformationDTO> PiiCustInfoFromDTO = new ArrayList<UserInformationDTO>();
		List<UserInformationDTO> AuthPiiCustInfoFromDTO = new ArrayList<UserInformationDTO>();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);

			// Call the DAOImpl layer
			custInfoFromDTO = userService.fetchUserDetails(loggedInUser);

			PiiCustInfoFromDTO = userService.fetchPiiUserDetails();

			AuthPiiCustInfoFromDTO = userService.fetchAuthPiiUserDetails();

			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);
			modelAndView.addObject("PiiCustInfoFromDTO", PiiCustInfoFromDTO);
			modelAndView.addObject("AuthPiiCustInfoFromDTO", AuthPiiCustInfoFromDTO);

			modelAndView.setViewName("welcomeGov");
		} else {
			modelAndView.setViewName("permission-denied");
		}
		return modelAndView;

	}

	@RequestMapping(value = "/gov", method = RequestMethod.POST)
	public ModelAndView managerExternalUserApproval(@RequestParam("approveParam") String approveOrDeny) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");
		if (approveOrDeny != null && !approveOrDeny.isEmpty()) {
			String[] split = approveOrDeny.split("_");
			List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
			custInfoFromDTO = userService.fetchUserDetails(split[1]);

			if (split[0].equals("approveVal")) {

				userService.activatePiiUserAccount(custInfoFromDTO.get(0).getUserName());
			}
		} else {
			return modelAndView;
		}
		return govPage();
	}

@RequestMapping(value = "/view_piiusers", method = RequestMethod.GET)
	public ModelAndView viewPiiUsers_get() {

		ModelAndView modelAndView = new ModelAndView();
		List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
		List<UserInformationDTO> PiiCustInfoFromDTO = new ArrayList<UserInformationDTO>();
		List<UserInformationDTO> AuthPiiCustInfoFromDTO = new ArrayList<UserInformationDTO>();
		

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);

			// Call the DAOImpl layer
			custInfoFromDTO = userService.fetchUserDetails(loggedInUser);
			
			PiiCustInfoFromDTO = userService.fetchPiiUserDetails();
			
			AuthPiiCustInfoFromDTO = userService.fetchAuthPiiUserDetails();
			

			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);
			modelAndView.addObject("PiiCustInfoFromDTO", PiiCustInfoFromDTO);
			modelAndView.addObject("AuthPiiCustInfoFromDTO", AuthPiiCustInfoFromDTO);
			

			modelAndView.setViewName("view_piiusers");
		} else {
			modelAndView.setViewName("permission-denied");
		}
		return modelAndView;

	}

	@RequestMapping(value = "/view_piiusers", method = RequestMethod.POST)
	public ModelAndView viewPiiUsers(@RequestParam("approveParam1") String approveOrDeny) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");
		if (approveOrDeny != null && !approveOrDeny.isEmpty()) {
			String[] split = approveOrDeny.split("_");
			List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
			custInfoFromDTO = userService.fetchUserDetails(split[1]);

			if (split[0].equals("denyVal")) {

				userService.deactivatePiiUserAccount(custInfoFromDTO.get(0).getUserName());
			}
		} else {
			return modelAndView;
		}
		return viewPiiUsers_get();
	}

}
