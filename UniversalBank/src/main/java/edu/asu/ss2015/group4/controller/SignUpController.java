package edu.asu.ss2015.group4.controller;

import java.io.IOException;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import edu.asu.ss2015.group4.model.UserInformation;
import edu.asu.ss2015.group4.service.MailingService;
import edu.asu.ss2015.group4.service.UserService;

@Controller
@RequestMapping(value = "/register")
public class SignUpController {
	@Autowired
	UserService userService;

	@RequestMapping(method = RequestMethod.GET)
	@ResponseBody
	public ModelAndView registrationForm(HttpServletRequest request) {
		UserInformation custInfo = new UserInformation();
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("customer", custInfo);

		String uri = request.getQueryString();
		modelAndView.addObject("selected", uri);

		if (uri == null || uri.isEmpty()) {
			modelAndView.setViewName("forward:/index");
		} else {

			setselection(modelAndView, uri);

			modelAndView.setViewName("register");

		}
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.POST)
	public ModelAndView registerCustomer(@Valid @ModelAttribute("registerForm") UserInformation custInfo,
			BindingResult result, HttpServletRequest request) throws NoSuchAlgorithmException, IOException {
		ModelAndView modelAndView = new ModelAndView();

		SignUpFormValidator.validateForm(custInfo, result);
		String uri = request.getParameter("newfield");
		String gRecaptchaResponse = request.getParameter("g-recaptcha-response");
		boolean verify = ValidateCaptcha.validateCaptchaResponse(gRecaptchaResponse);

		if (!verify) {
			String invalidCaptcha = "<FONT color=\"red\">Captcha Not Matched, Please Try Again.</FONT>";
			modelAndView.addObject("errorMsg", invalidCaptcha);
			setselection(modelAndView, uri);
			modelAndView.setViewName("register");
			return modelAndView;
		}

		if (result.hasErrors()) {
			setselection(modelAndView, uri);
			modelAndView.setViewName("register"); // This prints errors
			return modelAndView;
		} else {
			String error = userService.insertUserInformation(custInfo);
			if (error.equalsIgnoreCase("UserName, Email and SSN must be unique!")) {
				modelAndView.addObject("errorMsg", error);
				setselection(modelAndView, uri);
				modelAndView.setViewName("register");
			} else {
				modelAndView.addObject("successMsg", error);

				// Send email to user
				sendEmailToUser(custInfo);

				ArrayList<String> databaseArrayList = new ArrayList<String>();
				if (custInfo.getAccountType().equals("Individual") || custInfo.getAccountType().equals("Merchant")) {
					databaseArrayList.add("Individual");
					databaseArrayList.add("Merchant");
				} else {
					databaseArrayList.add("Clerk");
					databaseArrayList.add("Manager");
				}
				modelAndView.addObject("myList", databaseArrayList);

				modelAndView.setViewName("register");
			}
			return modelAndView;
		}
	}

	private void setselection(ModelAndView modelAndView, String uri) {
		ArrayList<String> databaseArrayList = new ArrayList<String>();
		if (uri.toString().equals("customer")) {
			databaseArrayList.add("Individual");
			databaseArrayList.add("Merchant");
		} else {
			databaseArrayList.add("Clerk");
			databaseArrayList.add("Manager");
		}
		modelAndView.addObject("myList", databaseArrayList);
	}

	private void sendEmailToUser(UserInformation custInfo) {
		ApplicationContext context = new ClassPathXmlApplicationContext("Spring-Mail.xml");

		String message = "Dear " + custInfo.getFirstName() + " " + custInfo.getLastName()
				+ ",\n\nThank you for registring with Universal Bank. Your account is currently pending approval. "
				+ "You will be notified via email upon your account approval. \n\nOnce again Thank you for your business.\n\nUniversal Bank";

		MailingService mm = (MailingService) context.getBean("mailingService");
		mm.sendMail(mm.getFromAddress(), custInfo.getEmailAddress(), "Universal Bank - Registration Successful.",
				message);

	}
}
