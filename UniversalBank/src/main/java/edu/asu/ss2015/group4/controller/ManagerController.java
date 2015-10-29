package edu.asu.ss2015.group4.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

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

import edu.asu.ss2015.group4.dto.TransactionDTO;
import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.dto.UserRequestsDTO;
import edu.asu.ss2015.group4.model.BankAccount;
import edu.asu.ss2015.group4.model.OTPGenerator;
import edu.asu.ss2015.group4.model.UserRequest;
import edu.asu.ss2015.group4.model.log;
import edu.asu.ss2015.group4.service.BankAccountService;
import edu.asu.ss2015.group4.service.MailingService;
import edu.asu.ss2015.group4.service.TransactionService;
import edu.asu.ss2015.group4.service.UserService;

@Controller
@SessionAttributes("userName")
public class ManagerController {

	@Autowired
	UserService userService;

	@Autowired
	BankAccountService accountService;

	@Autowired
	TransactionService transactionService;

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

	private List<UserRequest> createRequestList(List<UserRequestsDTO> requests) {
		List<UserRequest> listrequests = new ArrayList<UserRequest>();

		for (UserRequestsDTO request : requests) {
			UserRequest ur = new UserRequest();
			List<UserInformationDTO> requester = new ArrayList<UserInformationDTO>();
			requester = userService.fetchUserDetails(request.getRequestBy());

			List<UserInformationDTO> approver = new ArrayList<UserInformationDTO>();
			approver = userService.fetchUserDetails(request.getApprovedBy());

			if (!approver.isEmpty())
				ur.setApproverName(approver.get(0).getLastName() + ", " + approver.get(0).getFirstName());
			else
				ur.setApproverName("");
			ur.setRequesterName(requester.get(0).getLastName() + ", " + requester.get(0).getFirstName());
			ur.setRequestByUserName(requester.get(0).getUserName());
			ur.setRequestType(request.getRequestType());

			listrequests.add(ur);
		}

		return listrequests;
	}

	@RequestMapping(value = "/process_requests", method = RequestMethod.POST)
	public ModelAndView processRequests(@RequestParam("approveParam2") String approveOrDeny) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			boolean success = true;
			if (approveOrDeny != null && !approveOrDeny.isEmpty()) {
				String[] split = approveOrDeny.split("_");

				if (split[0].equals("approveVal")) {
					switch (split[2]) {
					case "Delete Account":
						String content = "External Account Deletion by " + loggedInUser + " To the customer  "
								+ split[2];
						log lg = new log();
						lg.setid(loggedInUser);
						lg.settime(new Date());
						lg.setcontent(content);
						userService.savelog(lg.gettime(), lg.getid(), lg.getcontent());
						success = userService.deleteAccount(split[1]);
						break;
					case "Edit Profile":
						String content1 = "Edit Account Approval by " + loggedInUser + " To the customer  " + split[2];
						log lg1 = new log();
						lg1.setid(loggedInUser);
						lg1.settime(new Date());
						lg1.setcontent(content1);
						userService.savelog(lg1.gettime(), lg1.getid(), lg1.getcontent());
						success = userService.processEditInfoRequest(split[1]);
						break;
					case "REOPEN_ACCOUNT":
						break;
					default:
						break;
					}
				}

			} else {
				return modelAndView;
			}
		}
		return managerPage();
	}

	@RequestMapping(value = "/process_requests", method = RequestMethod.GET)
	public ModelAndView processUserRequests() {

		ModelAndView modelAndView = new ModelAndView();
		List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
		List<UserRequestsDTO> requests = new ArrayList<UserRequestsDTO>();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);

			// Call the DAOImpl layer
			custInfoFromDTO = userService.fetchUserDetails(loggedInUser);
			requests = userService.getAllRequests();

			List<UserRequest> listrequests = createRequestList(requests);

			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);
			modelAndView.addObject("requestFromUser", listrequests);

			modelAndView.setViewName("process_requests");
		} else {
			modelAndView.setViewName("permission-denied");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/critical_transaction", method = RequestMethod.GET)
	public ModelAndView criticalTransactions(String message) {

		ModelAndView modelAndView = new ModelAndView();
		List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
		List<TransactionDTO> userTransactionsDTO = new ArrayList<TransactionDTO>();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			modelAndView.addObject("someMessage", message);
			// Call the DAOImpl layer
			custInfoFromDTO = userService.fetchUserDetails(loggedInUser);
			userTransactionsDTO = transactionService.fetchCriticalTransactions(loggedInUser);

			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);
			modelAndView.addObject("userTransactions", userTransactionsDTO);

			modelAndView.setViewName("critical_transaction");
		} else {
			modelAndView.setViewName("permission-denied");
		}
		return modelAndView;
	}

	@RequestMapping(value = "/critical_transaction", method = RequestMethod.POST)
	public ModelAndView manageCriticalTransactionRequests(@RequestParam("approveParam1") String approveOrDeny) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		String loggedInUser = userDetail.getUsername();
		String approve = "";

		if (approveOrDeny != null && !approveOrDeny.isEmpty()) {
			String[] split = approveOrDeny.split("_");

			String x = split[0];
			String y = split[1];
			String z = split[2];

			if (split[0].equals("approveVal")) {
				String content = "Critical Transaction Approval from " + loggedInUser + " To Transaction ID " + y
						+ "for amount of " + z;
				log lg = new log();
				lg.setid(loggedInUser);
				lg.settime(new Date());
				lg.setcontent(content);
				userService.savelog(lg.gettime(), lg.getid(), lg.getcontent());
				int x1 = Integer.parseInt(y);
				double x2 = Double.parseDouble(z);
				approve = transactionService.RegularEmployeeAprroveTransaction(x1, x2, loggedInUser);
			}

			else {
				String content = "Critical Transaction Denial from " + loggedInUser + " To Transaction ID " + y
						+ "for amount of " + z;
				log lg = new log();
				lg.setid(loggedInUser);
				lg.settime(new Date());
				lg.setcontent(content);
				userService.savelog(lg.gettime(), lg.getid(), lg.getcontent());
				int x1 = Integer.parseInt(y);
				approve = transactionService.RegularEmployeeDeleteTransaction(x1, loggedInUser);
			}

		} else {
			return modelAndView;
		}
		return criticalTransactions(approve);
	}

	@RequestMapping(value = "/manager", method = RequestMethod.POST)
	public ModelAndView managerExternalUserApproval(@RequestParam("approveParam") String approveOrDeny) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			if (approveOrDeny != null && !approveOrDeny.isEmpty()) {
				String[] split = approveOrDeny.split("_");
				List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
				custInfoFromDTO = userService.fetchUserDetails(split[1]);

				if (split[0].equals("approveVal")) {
					String content = "Employee Approval from " + loggedInUser + " To "
							+ custInfoFromDTO.get(0).getUserName();
					log lg = new log();
					lg.setid(loggedInUser);
					lg.settime(new Date());
					lg.setcontent(content);
					userService.savelog(lg.gettime(), lg.getid(), lg.getcontent());
					userService.activateExternalUserAccount(custInfoFromDTO.get(0).getUserName());
					String employeeName = getRandomRegularEmployee();
					userService.assignSupervisor(custInfoFromDTO.get(0).getUserName(), employeeName);

					List<String> accounts = generateAccountInformation(custInfoFromDTO.get(0));
					/// sendEmailToUser(custInfoFromDTO.get(0));
					OTPGenerator otp = new OTPGenerator();
					Date date = new Date();
					long otpTime = date.getTime() + 600000;
					String otpValidity = Long.toString(otpTime);
					int OTP = otp.generateOTP();
					userService.insertOTP(Integer.toString(OTP), otpValidity, custInfoFromDTO.get(0).getUserName());
					List<UserInformationDTO> custInfoFromDTO1 = new ArrayList<UserInformationDTO>();
					custInfoFromDTO1 = userService.fetchUserDetails(split[1]);
					sendEmailToUser(custInfoFromDTO.get(0), custInfoFromDTO1.get(0).getOTP(),
							custInfoFromDTO1.get(0).getOtpValidity(), accounts);
				}
			} else {
				return modelAndView;
			}
		}
		return managerPage();
	}

	private List<String> generateAccountInformation(UserInformationDTO userInformationDTO) {
		long timeSeed = System.nanoTime(); // to get the current date time value

		double randSeed = Math.random() * 1000; // random number generation

		long midSeed = (long) (timeSeed * randSeed); // mixing up the time and
														// rand number.
		String s = midSeed + "";
		String subStr = s.substring(3, 9);

		List<String> accountNums = new ArrayList<String>();

		String checkingAccountNum = subStr + "101";
		String savingsAccountNum = subStr + "102";
		accountNums.add(checkingAccountNum);
		accountNums.add(savingsAccountNum);
		// Checking account
		BankAccount chkAccount = new BankAccount(checkingAccountNum);
		chkAccount.setUserName(userInformationDTO.getUserName());
		chkAccount.setAccountType("Checking");

		// Savings Account
		BankAccount svgAccount = new BankAccount(savingsAccountNum);
		svgAccount.setUserName(userInformationDTO.getUserName());
		svgAccount.setAccountType("Savings");
		svgAccount.setBalance(0.0);

		accountService.createAccount(chkAccount);
		accountService.createAccount(svgAccount);

		return accountNums;
	}

	private String getRandomRegularEmployee() {
		List<UserInformationDTO> regEmployees = new ArrayList<UserInformationDTO>();
		regEmployees = userService.fetchRegularEmployees();
		int random = randInt(0, regEmployees.size() - 1);
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

	private void sendEmailToUser(UserInformationDTO custInfo, String otp, String otpValidity, List<String> accounts) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
		long otpValid = Long.parseLong(otpValidity);
		Date validDate = new Date(otpValid);
		String message = "Congratulations " + custInfo.getFirstName() + " " + custInfo.getLastName()

		+ ",\n\nYour account has been approved, use the following link and one time password mentioned below to unlock your account. \n\n"
				+ "\n OTP: " + otp + " which is valid till: " + validDate
				+ "\n\n Your Account information are as follows, " + "\n Checking Account Number: " + accounts.get(0)
				+ "\n Savings Account Number: " + accounts.get(1)
				+ "\n https://group4.mobicloud.asu.edu/UniversalBank/unlockAccount"
				+ "\n\nThank you for your business.\n\nUniversal Bank";

		MailingService mm = (MailingService) context.getBean("mailingService");
		mm.sendMail(mm.getFromAddress(), custInfo.getEmailAddress(), "Universal Bank - Registration Successful.",
				message);

	}

	public void sendOTP(String otp, String otpValidity, String email) {
		Date date = new Date(Long.parseLong(otpValidity));
		String message = "\n Your new OTP: " + otp + ". It is valid till : " + date;
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");
		MailingService mm = (MailingService) context.getBean("mailingService");
		mm.sendMail(mm.getFromAddress(), email, "Universal Bank- New OTP", message);
	}

}
