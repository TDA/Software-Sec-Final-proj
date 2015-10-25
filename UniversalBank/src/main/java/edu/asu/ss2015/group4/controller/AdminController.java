package edu.asu.ss2015.group4.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.model.modifyIntUsers;
import edu.asu.ss2015.group4.service.MailingService;
import edu.asu.ss2015.group4.service.UserService;

@Controller
@SessionAttributes("userName")
@RequestMapping(value = "/admin")
public class AdminController {

	@Autowired
	UserService userService;

	@RequestMapping (method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView modelAndView = new ModelAndView();
		//List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
		
		List<UserInformationDTO> disabledIntInfoFromDTO = new ArrayList<UserInformationDTO>();
		List<UserInformationDTO> AuthPiiCustInfoFromDTO1 = new ArrayList<UserInformationDTO>();
		List<UserInformationDTO> IntInfoFromDTO = new ArrayList<UserInformationDTO>();
		List<UserInformationDTO> DelIntInfoFromDTO = new ArrayList<UserInformationDTO>();
		ArrayList<String> databaseArrayList1 = new ArrayList<String>();
		databaseArrayList1.add("Clerk");
		databaseArrayList1.add("Manager");

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);

			// Call the DAOImpl layer
			//custInfoFromDTO = userService.fetchUserDetails(loggedInUser);
			
			disabledIntInfoFromDTO = userService.fetchDisabledInternalUserDetails();
			
			AuthPiiCustInfoFromDTO1 = userService.fetchAuthPiiUserDetails1();
			DelIntInfoFromDTO = userService.fetchDelIntUserDetails();
			IntInfoFromDTO = userService.fetchIntUserDetails();
			//System.out.println(IntInfoFromDTO.getUserName());
			

			// Add it to the model
			//modelAndView.addObject("userInformation", custInfoFromDTO);
			modelAndView.addObject("DelIntInfoFromDTO", DelIntInfoFromDTO);
			modelAndView.addObject("disabledIntInfoFromDTO", disabledIntInfoFromDTO);
			modelAndView.addObject("AuthPiiCustInfoFromDTO1", AuthPiiCustInfoFromDTO1);
			modelAndView.addObject("IntInfoFromDTO", IntInfoFromDTO);
			modelAndView.addObject("myList1", databaseArrayList1);
			
			
			modelAndView.setViewName("welcomeAdmin");
			//System.out.println(IntInfoFromDTO);
		} else {
			modelAndView.setViewName("permission-denied");
		}
		return modelAndView;

	}

	@RequestMapping( method = RequestMethod.POST)
	public ModelAndView adminInternalUserApproval(@RequestParam("approveParam") String approveOrDeny) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");
		if (approveOrDeny != null && !approveOrDeny.isEmpty()) {
			String[] split = approveOrDeny.split("_");
			List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
			custInfoFromDTO = userService.fetchUserDetails(split[1]);

			if (split[0].equals("approveVal")) {
				System.out.println("=============> Account Approved <=================");
				
				userService.activateInternalUserAccount(custInfoFromDTO.get(0).getUserName());
				String employeeName = getRandomRegularEmployee();
				userService.assignSupervisor(custInfoFromDTO.get(0).getUserName(), employeeName);
				
			}

		} else {
			return modelAndView;
		}
		System.out.println("=============> RESULT = " + approveOrDeny + "<=================");
		return adminPage();
	}

	@RequestMapping(value = "/pii_users", method = RequestMethod.POST)
	public ModelAndView pii_users(@RequestParam("approveParam1") String approveOrDeny) {
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
		System.out.println("=============> RESULT = " + approveOrDeny + "<=================");
		return adminPage();
	}

	@RequestMapping(value = "/modify_users", method = RequestMethod.POST)
	public ModelAndView modify_users(@RequestParam("approveParam2") String approveOrDeny) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");
		if (approveOrDeny != null && !approveOrDeny.isEmpty()) {
			String[] split = approveOrDeny.split("_");
			List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
			custInfoFromDTO = userService.fetchUserDetails(split[1]);

			
			if (split[0].equals("denyVal1")) {
				
				
				userService.deleteInternalUserAccount(custInfoFromDTO.get(0).getUserName());
				return adminPage();
			}

		} else {
			return modelAndView;
		}
		System.out.println("=============> RESULT = " + approveOrDeny + "<=================");
		return adminPage();
	}

	@RequestMapping(value = "/deleted_users", method = RequestMethod.POST)
	public ModelAndView delete_intUsers(@RequestParam("approveParam3") String approveOrDeny) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");
		if (approveOrDeny != null && !approveOrDeny.isEmpty()) {
			String[] split = approveOrDeny.split("_");
			List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
			custInfoFromDTO = userService.fetchDelUserDetails(split[1]);

			
				if (split[0].equals("approveVal1")) {
					System.out.println("=============> Account Approved <=================");
					
					userService.addDeletedInternalUserAccount(custInfoFromDTO.get(0).getUserName());
					
				}
		} else {
			return modelAndView;
		}
		System.out.println("=============> RESULT = " + approveOrDeny + "<=================");
		return adminPage();
	}
	
	@RequestMapping(value = "/change_users", method = RequestMethod.POST)
	public ModelAndView modify_intUsers(@RequestParam("approveParam4") String approveOrDeny,modifyIntUsers custInfo,
			BindingResult result, HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");
		if (approveOrDeny != null && !approveOrDeny.isEmpty()) {
			String[] split = approveOrDeny.split("_");
			List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
			custInfoFromDTO = userService.fetchUserDetails(split[1]);
			String var = custInfo.getAccountType();
			 var = var.replaceAll("[-+.^:,]","");

			
				if (split[0].equals("approveVal2")) {
					System.out.println(custInfo.getAccountType());
					System.out.println(var);
					
					userService.modifyInternalUserAccount(var,custInfoFromDTO.get(0).getUserName());
					
				}
		} else {
			return modelAndView;
		}
		System.out.println("=============> RESULT = " + approveOrDeny + "<=================");
		return adminPage();
	}

	
	private String getRandomRegularEmployee() {
		List<UserInformationDTO> regEmployees = new ArrayList<UserInformationDTO>();
		regEmployees = userService.fetchRegularEmployees();
		int random = randInt(0, regEmployees.size());
		System.out.println(random);
		return regEmployees.get(random).getUserName();
	}

	/**
	 * Returns a pseudo-random number between min and max, inclusive. The
	 * difference between min and max can be at most
	 * <code>Integer.MAX_VALUE - 1</code>.
	 *
	 * @param min
	 *            Minimum value
	 * @param max
	 *            Maximum value. Must be greater than min.
	 * @return Integer between min and max, inclusive.
	 * @see java.util.Random#nextInt(int)
	 */
	public static int randInt(int min, int max) {

		Random rand = new Random();

		// nextInt is normally exclusive of the top value,
		// so add 1 to make it inclusive
		int randomNum = rand.nextInt((max - min) + 1) + min;

		return randomNum;
	}

	

}
