package edu.asu.ss2015.group4.controller;

import java.util.Collection;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AccountExpiredException;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.DisabledException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.service.UserService;

@Controller
public class MainController {
	@Autowired
	UserService custService;

	@RequestMapping(value = { "/", "/index" }, method = RequestMethod.GET)
	public ModelAndView indexPage(@RequestParam(value = "error", required = false) String error,
			HttpServletRequest request) {
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();

		if (!(auth instanceof AnonymousAuthenticationToken)) {

			Collection<? extends GrantedAuthority> authorities = auth.getAuthorities();
			for (GrantedAuthority grantedAuthority : authorities) {
				if (grantedAuthority.getAuthority().equals("ROLE_INDIVIDUAL")) {
					return new ModelAndView("forward:/account");
				} else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
					return new ModelAndView("forward:/admin");
				} else if (grantedAuthority.getAuthority().equals("ROLE_MANAGER")) {
					return new ModelAndView("forward:/manager");
				}
				else if (grantedAuthority.getAuthority().equals("ROLE_CLERK")) {
					return new ModelAndView("forward:/clerk");
				}
			}
			return new ModelAndView("forward:/welcome");
		}

		ModelAndView model = new ModelAndView();
		if (error != null) {
			model.addObject("error", getErrorMessage(request, "SPRING_SECURITY_LAST_EXCEPTION"));
		}

		model.setViewName("index");

		return model;
	}

	// customize the error message
	private String getErrorMessage(HttpServletRequest request, String key) {

		Exception exception = (Exception) request.getSession().getAttribute(key);

		String error = "";
		if (exception instanceof BadCredentialsException) {
			error = "Invalid username and password!";
		} else if (exception instanceof LockedException) {
			error = exception.getMessage();
		} else if (exception instanceof SessionAuthenticationException) {
			error = exception.getMessage();
		} else if (exception instanceof DisabledException) {
			error = "Your account is in process of approval.";
		} else if (exception instanceof AccountExpiredException) {
			error = "Your account is locked.";
		} else {

			error = "Invalid username and password!";
		}

		return error;
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public ModelAndView welcomePage() {

		ModelAndView model = new ModelAndView();
		model.addObject("title", "You should not be landing here");
		model.addObject("message", "This is default page!");
		model.setViewName("customer-home");
		return model;
	}

	@RequestMapping(value = "/unlockAccount", method = RequestMethod.GET)
	public ModelAndView unlockAccount() {

		ModelAndView model = new ModelAndView();
		model.setViewName("unlockAccount");
		return model;
	}
	
	@RequestMapping(value = "/unlockAccount",params ="Unlock", method = RequestMethod.POST)
	public ModelAndView validateForm(@RequestParam("userName") String userName, @RequestParam("otp") String otp) {
		ModelAndView model = new ModelAndView();
		if(userName !=null && !userName.equals("")){
			List<UserInformationDTO> userDTO = custService.fetchUserDetails(userName);
			if (userDTO != null && !userDTO.isEmpty()) {
				if(validateOTP(userName, userDTO.get(0).getUserName(),
						otp, userDTO.get(0).getOTP(),userDTO.get(0).getOtpValidity())){
				custService.unlockExternalUserAccount(userName);
				model.addObject("successMessage", "account Unlocked, <a href=\'/UniversalBankingSystem/index\'>login</a> to continue banking.");
			
				}
				else
					model.addObject("errorMessage","OTP is incorrect or it has expired.");
			}
			else
				model.addObject("errorMessage","Incorrect Username");
		}
		else {
			model.addObject("errorMessage", "Username cannot be empty.");
		}
		return model;
	}
	public boolean validateOTP(String username, String dbusername, String otp, String dbotp, String otpValidity){
		boolean retVal = false;
		System.out.println("in ValidateOTP");
		System.out.println("username: "+username+", from db: "+dbusername);
		System.out.println("otp: "+otp+", from db: "+dbotp);
		if(username.equals(dbusername) && otp.equals(dbotp)){
			System.out.println("inside checking");
			Date date = new Date();
			Date checkDate = new Date(Long.parseLong(otpValidity));
			if(date.before(checkDate)){
				retVal = true;
				OTPGenerator OTP = new OTPGenerator();
				int newOTP = OTP.generateOTP();
				otpValidity = Long.toString(date.getTime()+600000); 
				custService.insertOTP(Integer.toString(newOTP),otpValidity, dbusername);
				System.out.println(date+" is before "+checkDate);
			}
			else
				System.out.println(checkDate+" is before "+date);
		}
		return retVal;
	}
	@RequestMapping(value = "/unlockAccount", params = "generateOTP", method = RequestMethod.POST)
	public ModelAndView generateOTP(@RequestParam("userName") String UserName) {
		ModelAndView model = new ModelAndView();
		if(UserName!=null && !UserName.equals("") ){
			List<UserInformationDTO> user = custService.fetchUserDetails(UserName);
			if(user!=null && !user.isEmpty() && UserName.equals(user.get(0).getUserName())){
				Date date = new Date();
				OTPGenerator OTP = new OTPGenerator();
				int newOTP = OTP.generateOTP();
				String otpValidity = Long.toString(date.getTime()+600000); 
				custService.insertOTP(Integer.toString(newOTP),otpValidity, UserName);
				ManagerController m = new ManagerController();
				m.sendOTP(Integer.toString(newOTP), otpValidity, user.get(0).getEmailAddress());
				model.addObject("successMessage", "New OTP has been sent to your email");
			}
			else
				model.addObject("errorMessage", "Incorrect Username");
			
		}
		else {
			model.addObject("errorMessage", "Username cannot be empty");
		}
		return model;
	}


	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("welcomeAdmin");

		return model;
	}

	// for 403 access denied page
	@RequestMapping(value = "/403", method = RequestMethod.GET)
	public ModelAndView accessDenied() {

		ModelAndView model = new ModelAndView();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println(userDetail);

			model.addObject("userName", userDetail.getUsername());
		}
		model.setViewName("permission-denied");
		return model;
	}

}
