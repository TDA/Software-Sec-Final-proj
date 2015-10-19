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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.ss2015.group4.dto.TransactionDTO;
import edu.asu.ss2015.group4.dto.UserInformationDTO;
import edu.asu.ss2015.group4.model.Transactions;
import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.service.TransactionService;
import edu.asu.ss2015.group4.service.UserService;

/*
 * ExternalUserController: accountSummary.jsp
 */

@Controller
public class TransactionController {
	@Autowired
	TransactionService trans;

	@RequestMapping(value = "/transfer", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView returnCustomerPage() {

		Transactions transac = new Transactions();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("transaction", transac);
		modelAndView.setViewName("transfer");
		return modelAndView;
	}
	@RequestMapping(value="/transfer",method = RequestMethod.POST)
	public ModelAndView transfer(@Valid @ModelAttribute("transferForm") Transactions transac, BindingResult result,
			HttpServletRequest request) throws NoSuchAlgorithmException, FileNotFoundException {
		ModelAndView modelAndView = new ModelAndView();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		if (!(auth instanceof AnonymousAuthenticationToken)) {
			UserDetails userDetail = (UserDetails) auth.getPrincipal();
		
		TransferValidator.validateForm(transac, result);
		System.out.println("here"+result);
		if (result.hasErrors()) {
			System.out.println("error");
			modelAndView.setViewName("transfer"); // This prints errors
			
		}  
		else {
			
			String a=trans.TransferUser(transac);
			System.out.println("successtransac11");
			modelAndView.setViewName("success");	
			
				}
		
	}
		
		return modelAndView;
	}

	@RequestMapping(value = "/ViewTransactions", method = RequestMethod.GET)
	public ModelAndView ViewPage() {
		System.out.println("enteredtransac");
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
			custInfoFromDTO = trans.ViewUserInfo(loggedInUser);

			// Add it to the model
			modelAndView.addObject("userInformation", custInfoFromDTO);
			System.out.println("to view");
			modelAndView.setViewName("ViewTransactions");
		}

		return modelAndView;
	}
	@RequestMapping(value = "/Debit", method = RequestMethod.GET)
	public ModelAndView DebitPage() {

		Transactions transac = new Transactions();
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("transaction", transac);
		System.out.println("to transfer");

		modelAndView.setViewName("Debit");

		return modelAndView;
	}
	@RequestMapping(value="/Debit", method= RequestMethod.POST)
	public ModelAndView Debit(@Valid @ModelAttribute("debitForm") Transactions transac, BindingResult result,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if (result.hasErrors()) {
			modelAndView.addObject("errormsg", "enter a value");
			modelAndView.setViewName("debit"); // This prints errors
			return modelAndView;
		} else {
			System.out.println("successtransac");
			trans.DebitUser(transac);
		}
		modelAndView.setViewName("success");
		return modelAndView;
	}
	@RequestMapping(value = "/Credit", method = RequestMethod.GET)
	public ModelAndView CreditPage() {

		Transactions transac = new Transactions();
		ModelAndView modelAndView = new ModelAndView();

		modelAndView.addObject("transaction", transac);
		System.out.println("to transfer");

		modelAndView.setViewName("Credit");

		return modelAndView;
	}
	@RequestMapping(value="/Credit", method= RequestMethod.POST)
	public ModelAndView Credit(@Valid @ModelAttribute("creditForm") Transactions transac, BindingResult result,
			HttpServletRequest request) {
		ModelAndView modelAndView = new ModelAndView();
		if (result.hasErrors()) {
			modelAndView.addObject("errormsg", "enter a value");
			modelAndView.setViewName("credit"); // This prints errors
			return modelAndView;
		} else {
			System.out.println("successtransac");
			trans.CreditUser(transac);
		}
		modelAndView.setViewName("success");
		return modelAndView;
	}
	
	@RequestMapping(value = "/MerchantTransfer", method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView MerchantTransferPage() throws NoSuchAlgorithmException, FileNotFoundException {
			Transactions mtransac = new Transactions();
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("transaction", mtransac);
			modelAndView.setViewName("MerchantTransfer");
			
		
		return modelAndView;
	}

	@RequestMapping(value = "/MerchantTransfer", method = RequestMethod.POST)
	@ResponseBody
	public ModelAndView MerchantTransfer(@Valid @ModelAttribute("MerchantTransferForm") Transactions transac, BindingResult result, HttpServletRequest request) throws NoSuchAlgorithmException, FileNotFoundException{
			System.out.println(transac.toString());
			ModelAndView modelAndView=new ModelAndView();
			modelAndView.addObject("transaction", transac);
			modelAndView.setViewName("MerchantTransfer");
			trans.MerchantPaymentUser(transac);
			return modelAndView;
	}
	
	
}