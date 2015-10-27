package edu.asu.ss2015.group4.controller;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.security.*;
import edu.asu.ss2015.group4.service.impl.*;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.ss2015.group4.dto.TransactionDTO;
import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.dto.UserRequestsDTO;
import edu.asu.ss2015.group4.model.BankAccount;
import edu.asu.ss2015.group4.model.BankBalance;
import edu.asu.ss2015.group4.model.OTPGenerator;
import edu.asu.ss2015.group4.model.Transactions;
import edu.asu.ss2015.group4.service.BankAccountService;
import edu.asu.ss2015.group4.service.TransactionService;
import edu.asu.ss2015.group4.service.UserService;
import edu.asu.ss2015.group4.service.impl.KeyGeneratorsPKI;

/*
 * ExternalUserController: accountSummary.jsp
 */

@Controller
@SessionAttributes("userName")
public class TransactionController {
	 
     
	@Autowired
	TransactionService trans;
	@Autowired
	BankAccountService bankAccountService;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/balance", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView returnBalance() {
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			BankAccount b = new BankAccount();
			b.setUserName(userDetail.getUsername());
			double saving = bankAccountService.BankBalancesaving(b);
			double checking = bankAccountService.BankBalancechecking(b);
			System.out.println("saving" + saving + "checking" + checking);
			BankBalance bal = new BankBalance();
			bal.setCheckingAccount(checking);
			bal.setSavingsAccount(saving);
			modelAndView.addObject("bal", checking);
			modelAndView.addObject("bal1", saving);

			modelAndView.setViewName("balance");
		} else {
			modelAndView.setViewName("permission-denied");
		}
		return modelAndView;

	}

	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView returnCustomerPage() {
		ModelAndView modelAndView = new ModelAndView();

		KeyGeneratorsPKI keys = new KeyGeneratorsPKI();
	     keys.generateKeys();

	     // this is the plaintext message that needs to be signed with the private key
	     String message = "hello sai";
	     // sign it!
	     String signedRequest = keys.signRequest(message);
	     System.out.println("Encoded message: " + signedRequest);
	     System.out.println();
	     // check if the message returned is the same? decode using pub key
	     if (keys.verifySignature(message, signedRequest)) {
	         // keys are fine, PKI successful yay!
	         System.out.println("Keys Verified");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			ArrayList<String> arrayList = new ArrayList<String>();
			arrayList.add("checking");
			arrayList.add("savings");
			modelAndView.addObject("mylist", arrayList);
			System.out.println(arrayList.get(0));
			modelAndView.setViewName("transfer");
		} 
		
	     }else {
				modelAndView.setViewName("permission-denied");
	     		}
	     return modelAndView;
	}

	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public ModelAndView transfer(@Valid @ModelAttribute("transferForm") Transactions transac, BindingResult result,
			HttpServletRequest request) throws NoSuchAlgorithmException, FileNotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			BankAccount b = new BankAccount();
			b.setId(transac.getToTransactionAccountID());
			System.out.println("checking" + transac.getAccountType());
			b.setAccountType(transac.getAccountType());
			int i = bankAccountService.BankValidate(b);
			if (i == 1) {
				double b1 = bankAccountService.BankBalanceValidate(b);
				System.out.println("balance" + b1);
				transac.setBalance(b1);
			}
			System.out.println("fialcount" + i);
			transac.setCount(i);
			transfervalidator1.validateForm(transac, result);
			System.out.println("here" + result);
			if (result.hasErrors()) {
				System.out.println("error");
				modelAndView.setViewName("transfer"); // This prints errors

			} else {

				List<UserInformationDTO> info = new ArrayList<UserInformationDTO>();
				info = userService.fetchUserDetails(userDetail.getUsername());
				transac.setSupervisorName(info.get(0).getSupervisorName());
				System.out.println("successtransac11");
				String a = trans.TransferUser(transac);

				modelAndView.setViewName("success");

			}

		} else {
			modelAndView.setViewName("permission-denied");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/ViewTransactions", method = RequestMethod.GET)
	public ModelAndView ViewPage() {
		Transactions transac = new Transactions();
		ModelAndView modelAndView = new ModelAndView();
		List<TransactionDTO> custInfoFromDTO = new ArrayList<TransactionDTO>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);

			// Call the DAOImpl layer
			custInfoFromDTO = trans.ViewUserInfo(loggedInUser);

			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);
			modelAndView.setViewName("ViewTransactions");
		} else {
			modelAndView.setViewName("permission-denied");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/Debit", method = RequestMethod.GET)
	public ModelAndView DebitPage() {

		Transactions transac = new Transactions();
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String username = userDetail.getUsername();
			ArrayList<String> arrayList = new ArrayList<String>();
			arrayList.add("checking");
			arrayList.add("savings");
			modelAndView.addObject("mylist", arrayList);
			modelAndView.addObject("transaction", transac);
			generateOTP(username);
			modelAndView.setViewName("Debit");
		} else {
			modelAndView.setViewName("permission-denied");
		}

		return modelAndView;
	}

	@RequestMapping(value = "/Debit", method = RequestMethod.POST)
	public ModelAndView Debit(@RequestParam("accountType") String accountType, @RequestParam("amount") String amount,
			@RequestParam("otp") String otp) {
		ModelAndView modelAndView = new ModelAndView();
		System.out.println("debit");
		BankAccount b = new BankAccount();
		// b.setAccountType(accountType);
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String username = userDetail.getUsername();
			/* BindingResult result = null; */
			Transactions transac = new Transactions();
			transac.setAccountType(accountType);
			transac.setAmount(amount);
			/*
			 * TransferValidator.validateForm(transac, result);
			 * System.out.println("here"+result);
			 */
			String regex = "[0-9]+|[0-9]+.[0-9]{1,2}";
			Double balance = 0.0;
			if (accountType != null && !accountType.equals("")) {
				b.setAccountType(accountType);
				if (amount != null && amount.length() >= 1 && amount.matches(regex)) {
					balance = bankAccountService.BankBalanceValidate(b);
					System.out.println("balance is: " + balance);
					if (balance >= Double.parseDouble(amount)) {
						if (otp != null && otp.length() >= 1 && isOtpValid(username, otp)) {
							if (!hasOtpExpired(username)) {
								List<UserInformationDTO> info = new ArrayList<UserInformationDTO>();
								info = userService.fetchUserDetails(userDetail.getUsername());
								transac.setSupervisorName(info.get(0).getSupervisorName());
								trans.DebitUser(transac);
								OTPGenerator otp1 = new OTPGenerator();
								Date date = new Date();
								userService.insertOTP(Integer.toString(otp1.generateOTP()),
										Long.toString(date.getTime() + 600000), username);
								modelAndView.setViewName("success");
							} else {
								modelAndView.addObject("errorMsg", "OTP has expired.");
							}
						} else
							modelAndView.addObject("errorMsg", "Incorrect OTP.");

					} else
						modelAndView.addObject("errorMsg", "Insufficient balance.");
				} else
					modelAndView.addObject("errorMsg", "Incorrect amount.");
			} else
				modelAndView.addObject("errorMsg", "Account type must be chosen.");

			/*
			 * if (result!=null && result.hasErrors()) { System.out.println(
			 * ":in debet"); modelAndView.setViewName("Debit"); // This prints
			 * errors
			 * 
			 * } else { trans.DebitUser(transac);
			 * System.out.println("successtransac");
			 * modelAndView.setViewName("success");
			 * 
			 * }
			 */
		}
		return modelAndView;
	}

	@RequestMapping(value = "/Debit", params = "generateOTP", method = RequestMethod.POST)
	public ModelAndView OtpgenerateDebit() {
		System.out.println("Inside debit otp generate");
		ModelAndView modelandview = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String username = userDetail.getUsername();
			generateOTP(username);
		}
		return modelandview;
	}

	@RequestMapping(value = "/Credit", method = RequestMethod.GET)
	public ModelAndView CreditPage() {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			Transactions transac = new Transactions();
			ArrayList<String> arrayList = new ArrayList<String>();
			arrayList.add("checking");
			arrayList.add("savings");
			modelAndView.addObject("mylist", arrayList);
			System.out.println(arrayList.get(0));
			modelAndView.addObject("transaction", transac);
			System.out.println("to transfer");
			modelAndView.setViewName("Credit");
			System.out.println("the username is: " + userDetail.getUsername());
			generateOTP(userDetail.getUsername());
		}
		return modelAndView;
	}

	@RequestMapping(value = "/Credit", params = "Credit", method = RequestMethod.POST)
	public ModelAndView Credit(@RequestParam("accountType") String accountType, @RequestParam("amount") String amount,
			@RequestParam("otp") String otp) {
		ModelAndView modelAndView = new ModelAndView();
		Transactions transac = new Transactions();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			transac.setAccountType(accountType);
			transac.setAmount(amount);
			String username = userDetail.getUsername();
			// TransferValidator.validateForm(transac, result);
			// System.out.println("here"+result);
			String regex = "[0-9]+|[0-9]+.[0-9]{1,2}";
			if (accountType != null && !accountType.equals("")) {
				if (amount != null && amount.length() >= 1 && amount.matches(regex)) {
					if (otp != null && !otp.equals("")) {
						if (isOtpValid(username, otp)) {
							if (!hasOtpExpired(username)) {
								trans.CreditUser(transac);
								OTPGenerator otp1 = new OTPGenerator();
								Date date = new Date();
								userService.insertOTP(Integer.toString(otp1.generateOTP()),
										Long.toString(date.getTime() + 600000), username);
								modelAndView.setViewName("success");
							} else {
								modelAndView.addObject("errorMsg", "OTP has expired.");
								// modelAndView.setViewName("credit");
							}
						} else {
							modelAndView.addObject("errorMsg", "OTP doesn't match.");
							// modelAndView.setViewName("credit");
						}
					} else {
						modelAndView.addObject("errorMsg", "OTP cannot be empty.");
						// modelAndView.setViewName("credit");
					}
				} else {
					modelAndView.addObject("errorMsg", "Amount is incorrect.");
					// modelAndView.setViewName("credit");
				}
			} else {
				System.out.println(":in credit");
				modelAndView.addObject("errorMsg", "Account type should be selected");
				// modelAndView.setViewName("Credit"); // This prints errors
			}
		}
		return modelAndView;
	}

	@RequestMapping(value = "/Credit", params = "generateOTP", method = RequestMethod.POST)
	public ModelAndView Otpgenerate() {
		System.out.println("Inside transaction otp generate");
		ModelAndView modelandview = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String username = userDetail.getUsername();
			generateOTP(username);
		}
		return modelandview;
	}

	public boolean hasOtpExpired(String username) {
		System.out.println("checking otp expiration");
		boolean retVal = true;
		List<UserInformationDTO> user = userService.fetchUserDetails(username);
		Date current = new Date();
		Date checkDate = new Date(Long.parseLong(user.get(0).getOtpValidity()));
		if (current.before(checkDate))
			retVal = false;
		return retVal;
	}

	public boolean isOtpValid(String username, String otp) {
		System.out.println("checking otp validation");
		boolean retVal = false;
		List<UserInformationDTO> user = userService.fetchUserDetails(username);
		if (user.get(0).getOTP().equals(otp))
			retVal = true;
		return retVal;
	}

	public void generateOTP(String username) {
		System.out.println("Inside transaction generateOTP");
		List<UserInformationDTO> user = userService.fetchUserDetails(username);
		OTPGenerator otp = new OTPGenerator();
		Date date = new Date();
		userService.insertOTP(Integer.toString(otp.generateOTP()), Long.toString(date.getTime() + 600000), username);
		ManagerController m = new ManagerController();
		List<UserInformationDTO> user1 = userService.fetchUserDetails(username);
		m.sendOTP(user1.get(0).getOTP(), user1.get(0).getOtpValidity(), user1.get(0).getEmailAddress());
	}

	@RequestMapping(value = "/MerchantTransfer", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView MerchantTransferPage() throws NoSuchAlgorithmException, FileNotFoundException {
		Transactions mtransac = new Transactions();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("transaction", mtransac);
		modelAndView.setViewName("MerchantTransfer");
		return modelAndView;
	}

	@RequestMapping(value = "/MerchantTransfer", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView MerchantTransfer(@Valid @ModelAttribute("MerchantTransferForm") Transactions transac,
			BindingResult result, HttpServletRequest request) throws NoSuchAlgorithmException, FileNotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("transaction", transac);
		modelAndView.setViewName("MerchantTransfer");
		trans.MerchantPaymentUser(transac);
		return modelAndView;
	}

	@RequestMapping(value = "/UserRequest", method = RequestMethod.GET)
	public ModelAndView RequestPage() {

		Transactions transac = new Transactions();
		ModelAndView modelAndView = new ModelAndView();
		List<TransactionDTO> custInfoFromDTO = new ArrayList<TransactionDTO>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);

			// Call the DAOImpl layer
			custInfoFromDTO = trans.ViewUserInfoApprove(loggedInUser);

			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);
			modelAndView.setViewName("UserRequest");
		} else {
			modelAndView.setViewName("permission-denied");
		}
		return modelAndView;

	}

	@RequestMapping(value = "/UserRequest", method = RequestMethod.POST)
	public ModelAndView userApproval(@RequestParam("approveParam") String approveOrDeny) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");
		if (approveOrDeny != null && !approveOrDeny.isEmpty()) {
			String[] split = approveOrDeny.split("_");
			String x = split[0];
			String y = split[1];
			List<TransactionDTO> custInfoFromDTO = new ArrayList<TransactionDTO>();
			custInfoFromDTO = trans.ViewUserInfo(split[1]);

			if (split[0].equals("approve")) {
				int x1 = Integer.parseInt(y);
				String approve = trans.Approve(x1);
			}
		} else {
			return modelAndView;
		}
		return RequestPage();
	}

	// Added By Gaurav
	@RequestMapping(value = "/DeleteTransaction", method = RequestMethod.GET)
	public ModelAndView DeleteTransactionPage() {

		Transactions transac = new Transactions();
		ModelAndView modelAndView = new ModelAndView();
		List<TransactionDTO> custInfoFromDTO = new ArrayList<TransactionDTO>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);

			// Call the DAOImpl layer
			custInfoFromDTO = trans.DisplayTransactionInfo(loggedInUser);

			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);
			modelAndView.setViewName("DeleteTransaction");
		} else {
			modelAndView.setViewName("permission-denied");
		}
		return modelAndView;

	}

	// Added By Gaurav
	@RequestMapping(value = "/DeleteTransaction", method = RequestMethod.POST)
	public ModelAndView deleteTransaction(@RequestParam("deleteParam") String strDelete) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		String loggedInUser = userDetail.getUsername();

		if (strDelete != null && !strDelete.isEmpty()) {
			String[] split = strDelete.split("_");
			String x = split[0];
			String y = split[1];
			List<TransactionDTO> custInfoFromDTO = new ArrayList<TransactionDTO>();
			if (split[0].equals("delete")) {
				int x1 = Integer.parseInt(y);
				String approve = trans.Delete(x1, loggedInUser);
			}
		} else {
			return modelAndView;
		}
		return DeleteTransactionPage();
	}

	// Added By Gaurav
	@RequestMapping(value = "/ViewTransactionRegularEmployee", method = RequestMethod.GET)
	public ModelAndView ViewTransactionRegularEmployeePage() {

		Transactions transac = new Transactions();
		ModelAndView modelAndView = new ModelAndView();
		List<TransactionDTO> custInfoFromDTO = new ArrayList<TransactionDTO>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);

			// Call the DAOImpl layer
			custInfoFromDTO = trans.DisplayTransactionInfoToRegularEmployee(loggedInUser);

			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);
			modelAndView.setViewName("ViewTransactionRegularEmployee");
		} else {
			modelAndView.setViewName("permission-denied");
		}
		return modelAndView;

	}

	// added by gaurav
	@RequestMapping(value = "/ViewTransactionRegularEmployee", method = RequestMethod.POST)
	public ModelAndView approveDenyTransactionRegularEmployee(
			@RequestParam("approveDenyParamRegularEmployee") String approveOrDeny) {
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

			List<TransactionDTO> custInfoFromDTO = new ArrayList<TransactionDTO>();

			if (split[0].equals("approve")) {
				int x1 = Integer.parseInt(y);
				System.out.println("xhere" + x1);
				double x2 = Double.parseDouble(z);
				String approve = trans.RegularEmployeeAprroveTransaction(x1, x2, loggedInUser);
			}

			else {
				int x1 = Integer.parseInt(y);
				String deny = trans.RegularEmployeeDeleteTransaction(x1, loggedInUser);
			}

		} else {
			return modelAndView;
		}
		return ViewTransactionRegularEmployeePage();
	}

	// Added By Gaurav
	@RequestMapping(value = "/ModifyTransaction", method = RequestMethod.GET)

	public ModelAndView ModifyTransactionPage() {

		Transactions transac = new Transactions();
		ModelAndView modelAndView = new ModelAndView();
		List<TransactionDTO> custInfoFromDTO = new ArrayList<TransactionDTO>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			System.out.println(loggedInUser);

			// Call the DAOImpl layer
			custInfoFromDTO = trans.DisplayTransactionInfoToRegularEmployee(loggedInUser);

			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);
			System.out.println(transac.getTransactionId());
			modelAndView.setViewName("ModifyTransaction");
		} else {
			modelAndView.setViewName("permission-denied");
		}
		return modelAndView;

	}

	// Added By Gaurav
	@RequestMapping(value = "/ModifyTransaction", method = RequestMethod.POST)
	public ModelAndView ModifyTransaction(@RequestParam("modifyParamRegularEmployee") String strAmount) {
		System.out.println("Modified amount is: " + strAmount);
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		String loggedInUser = userDetail.getUsername();

		if (strAmount != null && !strAmount.isEmpty()) {

			String[] split = strAmount.split("_");
			String x = split[0];
			String y = split[1];

			List<TransactionDTO> custInfoFromDTO = new ArrayList<TransactionDTO>();
			int x1 = Integer.parseInt(y);
			double x2 = Double.parseDouble(x);
			String submit = trans.RegularEmployeeModifyTransaction(x1, x2, loggedInUser);
		} else {
			return modelAndView;
		}
		return ModifyTransactionPage();
	}

	// Added By Gaurav
	@RequestMapping(value = "/AccountDeleteRequest", method = RequestMethod.GET)
	public ModelAndView AccountDeleteRequestPage() {

		Transactions transac = new Transactions();
		ModelAndView modelAndView = new ModelAndView();
		List<UserRequestsDTO> custInfoFromDTO = new ArrayList<UserRequestsDTO>();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			String loggedInUser = userDetail.getUsername();
			modelAndView.addObject("userName", loggedInUser);
			System.out.println(loggedInUser);

			// Call the DAOImpl layer
			custInfoFromDTO = trans.DisplayUserRequest(loggedInUser);
			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);
			System.out.println(transac.getTransactionId());
			modelAndView.setViewName("AccountDeleteRequest");
		} else {
			modelAndView.setViewName("permission-denied");
		}
		return modelAndView;

	}

	// Added By Gaurav
	@RequestMapping(value = "/AccountDeleteRequest", method = RequestMethod.POST)
	public ModelAndView approveDeleteRequest(@RequestParam("accountDeleteParam") String strApprove) {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("permission-denied");
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		UserDetails userDetail = (UserDetails) auth.getPrincipal();
		String loggedInUser = userDetail.getUsername();

		if (strApprove != null && !strApprove.isEmpty()) {
			String[] split = strApprove.split("_");
			String x = split[0];
			String y = split[1];
			List<TransactionDTO> custInfoFromDTO = new ArrayList<TransactionDTO>();
			if (split[0].equals("approve")) {
				int x1 = Integer.parseInt(y);
				String approve = trans.ApproveUserRequestRegularEmployee(x1, loggedInUser);
			}
		} else {
			return modelAndView;
		}
		return AccountDeleteRequestPage();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}
}
