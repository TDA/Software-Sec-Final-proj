package edu.asu.ss2015.group4.controller;

import java.util.ArrayList;
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
import edu.asu.ss2015.group4.model.UserRequest;
import edu.asu.ss2015.group4.service.BankAccountService;
import edu.asu.ss2015.group4.service.MailingService;
import edu.asu.ss2015.group4.service.TransactionService;
import edu.asu.ss2015.group4.service.UserService;

@Controller
@SessionAttributes("userName")
@RequestMapping(value = "/manager")
public class ManagerController {

	@Autowired
	UserService userService;

	@Autowired
	BankAccountService accountService;

	@Autowired
	TransactionService transactionService;

	@RequestMapping(method = RequestMethod.GET)
	public ModelAndView managerPage() {

		ModelAndView modelAndView = new ModelAndView();
		List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
		List<UserInformationDTO> disabledCustInfoFromDTO = new ArrayList<UserInformationDTO>();
		List<TransactionDTO> userTransactionsDTO = new ArrayList<TransactionDTO>();
		List<UserRequestsDTO> requests = new ArrayList<UserRequestsDTO>();

		// check if user is login
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);

			// Call the DAOImpl layer
			custInfoFromDTO = userService.fetchUserDetails(loggedInUser);
			disabledCustInfoFromDTO = userService.fetchDisabledExternalUserDetails();
			userTransactionsDTO = transactionService.fetchCriticalTransactions();
			requests = userService.getAllRequests();

			List<UserRequest> listrequests = createRequestList(requests);

			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);
			modelAndView.addObject("disabledCustInfoFromDTO", disabledCustInfoFromDTO);
			modelAndView.addObject("userTransactions", userTransactionsDTO);
			modelAndView.addObject("requestFromUser", listrequests);

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

			ur.setApproverName(approver.get(0).getLastName() + ", " + approver.get(0).getFirstName());
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

		if (approveOrDeny != null && !approveOrDeny.isEmpty()) {
			String[] split = approveOrDeny.split("_");

			if (split[0].equals("approveVal")) {
				switch (split[2]) {
				case "Delete Account":
					userService.deleteAccount(split[1]);
					break;
				case "Edit Profile":
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
		return managerPage();
	}

	@RequestMapping(value = "/critical_transaction", method = RequestMethod.POST)
	public ModelAndView manageCriticalTransactionRequests(@RequestParam("approveParam1") String approveOrDeny) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		String loggedInUser = userDetail.getUsername();

		if (approveOrDeny != null && !approveOrDeny.isEmpty()) {
			String[] split = approveOrDeny.split("_");

			String x = split[0];
			String y = split[1];
			String z = split[2];

			if (split[0].equals("approve")) {
				int x1 = Integer.parseInt(y);
				System.out.println("xhere" + x1);
				double x2 = Double.parseDouble(z);
				String approve = transactionService.RegularEmployeeAprroveTransaction(x1, x2, loggedInUser);
			}

			else {
				int x1 = Integer.parseInt(y);
				String deny = transactionService.RegularEmployeeDeleteTransaction(x1, loggedInUser);
			}

		} else {
			return modelAndView;
		}
		return managerPage();
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView managerExternalUserApproval(@RequestParam("approveParam") String approveOrDeny) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");
		if (approveOrDeny != null && !approveOrDeny.isEmpty()) {
			String[] split = approveOrDeny.split("_");
			List<UserInformationDTO> custInfoFromDTO = new ArrayList<UserInformationDTO>();
			custInfoFromDTO = userService.fetchUserDetails(split[1]);

			if (split[0].equals("approveVal")) {
				userService.activateExternalUserAccount(custInfoFromDTO.get(0).getUserName());
				String employeeName = getRandomRegularEmployee();
				userService.assignSupervisor(custInfoFromDTO.get(0).getUserName(), employeeName);

				generateAccountInformation(custInfoFromDTO.get(0));
				sendEmailToUser(custInfoFromDTO.get(0));
			}
		} else {
			return modelAndView;
		}
		return managerPage();
	}

	private void generateAccountInformation(UserInformationDTO userInformationDTO) {
		long timeSeed = System.nanoTime(); // to get the current date time value

		double randSeed = Math.random() * 1000; // random number generation

		long midSeed = (long) (timeSeed * randSeed); // mixing up the time and
														// rand number.
		String s = midSeed + "";
		String subStr = s.substring(3, 9);

		String checkingAccountNum = subStr + "101";
		String savingsAccountNum = subStr + "102";

		// Checking account
		BankAccount chkAccount = new BankAccount(checkingAccountNum);
		chkAccount.setUserName(userInformationDTO.getUserName());
		chkAccount.setAccountType("Checking");

		// Savings Account
		BankAccount svgAccount = new BankAccount(savingsAccountNum);
		svgAccount.setUserName(userInformationDTO.getUserName());
		svgAccount.setAccountType("Savings");
		svgAccount.setBalance(250.00);

		accountService.createAccount(chkAccount);
		accountService.createAccount(svgAccount);
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

	private void sendEmailToUser(UserInformationDTO custInfo) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

		String message = "Congratulations " + custInfo.getFirstName() + " " + custInfo.getLastName()
				+ ",\n\nYour bank account has been approved, use the following link and one time password mentioned below to unlock your account. \n\n"
				+ "http://localhost:8083/UniversalBankingSystem/unlockAccount"
				+ "\n\nThank you for your business.\n\nUniversal Bank";

		MailingService mm = (MailingService) context.getBean("mailingService");
		mm.sendMail(mm.getFromAddress(), custInfo.getEmailAddress(), "Universal Bank - Registration Successful.",
				message);

	}

}
