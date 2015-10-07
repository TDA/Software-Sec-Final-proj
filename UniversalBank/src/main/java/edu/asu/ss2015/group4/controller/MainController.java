package edu.asu.ss2015.group4.controller;

import java.util.Collection;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
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
				if (grantedAuthority.getAuthority().equals("ROLE_USER")) {
					return new ModelAndView("forward:/account");
				} else if (grantedAuthority.getAuthority().equals("ROLE_ADMIN")) {
					return new ModelAndView("forward:/admin");
				} else if (grantedAuthority.getAuthority().equals("ROLE_EMPLOYEE")) {
					return new ModelAndView("forward:/employee");

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

	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public ModelAndView adminPage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("welcomeAdmin");

		return model;
	}

	@RequestMapping(value = "/employee", method = RequestMethod.GET)
	public ModelAndView employeePage() {

		ModelAndView model = new ModelAndView();
		model.setViewName("welcomeInternal");

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

	@RequestMapping(value = "/addUser", method = RequestMethod.GET)
	public ModelAndView returnAddUserPage() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("addUser");
		return modelAndView;
	}

}
