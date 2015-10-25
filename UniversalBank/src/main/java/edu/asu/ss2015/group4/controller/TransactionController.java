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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.ss2015.group4.dto.TransactionDTO;
import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.dto.UserRequestsDTO;
import edu.asu.ss2015.group4.model.BankAccount;
import edu.asu.ss2015.group4.model.Transactions;
import edu.asu.ss2015.group4.service.BankAccountService;
import edu.asu.ss2015.group4.service.TransactionService;
import edu.asu.ss2015.group4.service.UserService;

/*
 * ExternalUserController: accountSummary.jsp
 */

@Controller
public class TransactionController {

	@Autowired
	TransactionService trans;
	@Autowired
	BankAccountService service;
	@Autowired
	UserService userService;

	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView returnCustomerPage() {

		Transactions transac = new Transactions();
		ModelAndView modelAndView = new ModelAndView();
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("checking");
		arrayList.add("savings");
		modelAndView.addObject("mylist", arrayList);
		modelAndView.setViewName("transfer");
		return modelAndView;
	}

	@RequestMapping(value = "/transfer", method = RequestMethod.POST)
	public ModelAndView transfer(@Valid @ModelAttribute("transferForm") Transactions transac, BindingResult result,
			HttpServletRequest request) throws NoSuchAlgorithmException, FileNotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			System.out.println("checking" + transac.getAccountType());
			transfervalidator1.validateForm(transac, result);
			System.out.println("here" + result);
			if (result.hasErrors()) {
				System.out.println("error");
				BankAccount b = new BankAccount();
				b.setId(transac.getToTransactionAccountID());
				b.setAccountType(transac.getAccountType());
				int i = service.BankValidate(b);
				if (i == 1) {
					double b1 = service.BankBalanceValidate(b);
					transac.setBalance(b1);
				}
				transac.setCount(i);
				if (result.hasErrors()) {
					modelAndView.setViewName("transfer"); // This prints errors

				} else {

					String a = trans.TransferUser(transac);
					List<UserInformationDTO> info = new ArrayList<UserInformationDTO>();
					info = userService.fetchUserDetails(userDetail.getUsername());
					transac.setSupervisorName(info.get(0).getSupervisorName());
					modelAndView.setViewName("success");

				}

			}

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
		}

		return modelAndView;
	}

	@RequestMapping(value = "/Debit", method = RequestMethod.GET)
	public ModelAndView DebitPage() {

		Transactions transac = new Transactions();
		ModelAndView modelAndView = new ModelAndView();
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("checking");
		arrayList.add("savings");
		modelAndView.addObject("mylist", arrayList);
		modelAndView.addObject("transaction", transac);

		modelAndView.setViewName("Debit");

		return modelAndView;
	}

	@RequestMapping(value = "/Debit", method = RequestMethod.POST)
	public ModelAndView Debit(@Valid @ModelAttribute("DebitForm") Transactions transac, BindingResult result,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			TransferValidator.validateForm(transac, result);
			System.out.println("here" + result);

			if (result.hasErrors()) {
				System.out.println(":in debet");
				modelAndView.setViewName("Debit"); // This prints errors

			} else {
				trans.DebitUser(transac);
				System.out.println("successtransac");
				modelAndView.setViewName("success");

			}

			BankAccount b = new BankAccount();
			b.setAccountType(transac.getAccountType());

			double b1 = service.BankBalanceValidate(b);
			transac.setBalance(b1);

			List<UserInformationDTO> info = new ArrayList<UserInformationDTO>();
			info = userService.fetchUserDetails(userDetail.getUsername());
			transac.setSupervisorName(info.get(0).getSupervisorName());
		}

		TransferValidator.validateForm1(transac, result);

		if (result.hasErrors())

		{
			modelAndView.setViewName("Debit"); // This prints errors

		} else

		{
			trans.DebitUser(transac);
			modelAndView.setViewName("success");

		}
		return modelAndView;
	}

	@RequestMapping(value = "/Credit", method = RequestMethod.GET)
	public ModelAndView CreditPage() {

		Transactions transac = new Transactions();
		ModelAndView modelAndView = new ModelAndView();
		ArrayList<String> arrayList = new ArrayList<String>();
		arrayList.add("checking");
		arrayList.add("savings");
		modelAndView.addObject("mylist", arrayList);
		modelAndView.addObject("transaction", transac);

		modelAndView.setViewName("Credit");

		return modelAndView;
	}

	@RequestMapping(value = "/Credit", method = RequestMethod.POST)
	public ModelAndView Credit(@Valid @ModelAttribute("CreditForm") Transactions transac, BindingResult result,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();

		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
			TransferValidator.validateForm(transac, result);

			if (result.hasErrors()) {
				System.out.println(":in credit");
				modelAndView.setViewName("Credit"); // This prints errors

			} else {
				trans.CreditUser(transac);
				System.out.println("successtransac");

				if (result.hasErrors()) {
					modelAndView.setViewName("Credit"); // This prints errors

				} else {
					List<UserInformationDTO> info = new ArrayList<UserInformationDTO>();
					info = userService.fetchUserDetails(userDetail.getUsername());
					transac.setSupervisorName(info.get(0).getSupervisorName());
					trans.CreditUser(transac);
					modelAndView.setViewName("success");

				}
			}

		}
		return modelAndView;
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