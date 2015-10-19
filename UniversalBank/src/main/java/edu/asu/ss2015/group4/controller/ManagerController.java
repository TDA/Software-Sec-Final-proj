package edu.asu.ss2015.group4.controller;

import java.util.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.*;

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
public class ManagerController {

	@Autowired
	UserService userService;

	@RequestMapping(value = "/manager", method = RequestMethod.GET)
	public ModelAndView managerPage() {

		ModelAndView modelAndView = new ModelAndView();
		List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
		List<UserInformationDTO> disabledCustInfoFromDTO = new ArrayList<UserInformationDTO>();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);

			// Call the DAOImpl layer
			custInfoFromDTO = userService.fetchUserDetails(loggedInUser);
			disabledCustInfoFromDTO = userService.fetchDisabledExternalUserDetails();

			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);
			modelAndView.addObject("disabledCustInfoFromDTO", disabledCustInfoFromDTO);

			modelAndView.setViewName("welcomeManager");
		} else {
			modelAndView.setViewName("permission-denied");
		}
		return modelAndView;

	}

	@RequestMapping(value = "/manager", method = RequestMethod.POST)
	public ModelAndView managerExternalUserApproval(@RequestParam("approveParam") String approveOrDeny) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");
		if (approveOrDeny != null && !approveOrDeny.isEmpty()) {
			String[] split = approveOrDeny.split("_");
			List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
			custInfoFromDTO = userService.fetchUserDetails(split[1]);

			if (split[0].equals("approveVal")) {
				System.out.println("=============> Account Approved <=================");
				userService.activateExternalUserAccount(custInfoFromDTO.get(0).getUserName());
				OTPGenerator otp = new OTPGenerator();
				Date date = new Date();
				long otpTime = date.getTime() + 600000;
				String otpValidity = Long.toString(otpTime);
				int OTP = otp.generateOTP();
				userService.insertOTP(Integer.toString(OTP), otpValidity, custInfoFromDTO.get(0).getUserName());
				List<UserInformationDTO> custInfoFromDTO1 = new ArrayList<UserInformationDTO>();
				custInfoFromDTO1 = userService.fetchUserDetails(split[1]);
				sendEmailToUser(custInfoFromDTO.get(0),custInfoFromDTO1.get(0).getOTP(), custInfoFromDTO1.get(0).getOtpValidity());
			}
		} else {
			return modelAndView;
		}
		System.out.println("=============> RESULT = " + approveOrDeny + "<=================");
		return managerPage();
	}

	private void sendEmailToUser(UserInformationDTO custInfo, String otp, String otpValidity) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
		System.out.println(otp);
		Date date = new Date();
		long otpValid = Long.parseLong(otpValidity);
		Date validDate = new Date(otpValid);
		//System.out.println("current date: "+date);
		//System.out.println("validity date: "+validDate);
		String message = "Congratulations " + custInfo.getFirstName() + " " + custInfo.getLastName()
				+ ",\n\nYour account has been approved, use the following link and one time password mentioned below to unlock your account. \n\n"
				+"\n OTP: "+otp+" which is valid till: "+validDate+"\n http://localhost:8083/UniversalBankingSystem/unlockAccount"
				+ "\n\nThank you for your business.\n\nUniversal Bank";

		MailingService mm = (MailingService) context.getBean("mailingService");
		mm.sendMail(mm.getFromAddress(), custInfo.getEmailAddress(), "Universal Bank - Registration Successful.",
				message);

	}

}
