package edu.asu.ss2015.group4.controller;


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
			ModelAndView modelAndView=new ModelAndView();
			
			
			modelAndView.addObject("transaction", transac);
			System.out.println("to transfer");

			modelAndView.setViewName("transfer");
		
		
		return modelAndView;
	}
	
	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView transfer(@Valid @ModelAttribute("transferForm") Transactions transac,
			BindingResult result, HttpServletRequest request){
ModelAndView modelAndView = new ModelAndView();
			if (result.hasErrors()) {
					modelAndView.addObject("errormsg","enter a value");
					modelAndView.setViewName("transfer"); // This prints errors
					return modelAndView;
			}
			else{
			System.out.println("successtransac");
			trans.insertUserTransaction(transac);
			}
			modelAndView.setViewName("success");
			return modelAndView;
		}
	}

