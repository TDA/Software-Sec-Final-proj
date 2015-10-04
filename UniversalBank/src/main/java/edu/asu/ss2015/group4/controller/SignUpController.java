package edu.asu.ss2015.group4.controller;

import java.io.FileNotFoundException;
import java.security.NoSuchAlgorithmException;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.service.UserService;

@Controller
@RequestMapping(value = "/register")
public class SignUpController {
	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView registrationForm() {
		UserInformation custInfo = new UserInformation();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("customer", custInfo);

		modelAndView.setViewName("register");
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView registerCustomer(@Valid @ModelAttribute("registerForm") UserInformation custInfo,
			BindingResult result, HttpServletRequest request) throws NoSuchAlgorithmException, FileNotFoundException {
		ModelAndView modelAndView = new ModelAndView();

		SignUpFormValidator.validateForm(custInfo, result);

		System.out.println(result);
		if (result.hasErrors()) {
			modelAndView.setViewName("register"); // This prints errors
			return modelAndView;
		} else {
			String error = userService.insertUserInformation(custInfo);
			if (error.equalsIgnoreCase("UserName, Email and SSN must be unique!")) {
				modelAndView.addObject("errorMsg", error);
				modelAndView.setViewName("register");
			} else {
				modelAndView.addObject("successMsg", error);
				modelAndView.setViewName("register");
			}
			return modelAndView;
		}
	}
}
