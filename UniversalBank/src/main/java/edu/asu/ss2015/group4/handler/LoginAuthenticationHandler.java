package edu.asu.ss2015.group4.handler;

import java.util.Date;

import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.LockedException;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;

import edu.asu.ss2015.group4.dao.UserDAO;
import edu.asu.ss2015.group4.model.AccountLoginAttempts;

public class LoginAuthenticationHandler extends DaoAuthenticationProvider {

	UserDAO userDAO;

	@Override
	public Authentication authenticate(Authentication authentication) throws AuthenticationException {
		try {
			Authentication auth = super.authenticate(authentication);

			// if reach here, means login success, else an exception will be
			// thrown
			// reset the user_attempts
			userDAO.resetFailAttempts(authentication.getName());

			return auth;
		} catch (BadCredentialsException e) {

			// invalid login, update to user_attempts
			userDAO.updateFailAttempts(authentication.getName());
			throw e;

		} catch (LockedException e) {

			// this user is locked!
			String error = "";
			AccountLoginAttempts userAttempts = userDAO.getUserAttempts(authentication.getName());

			if (userAttempts != null) {
				Date lastAttempts = userAttempts.getLastModified();
				error = "User account is locked! <br /><br />Username : " + authentication.getName()
						+ "<br />Last Attempts : " + lastAttempts;
			} else {
				error = e.getMessage();
			}

			throw new LockedException(error);
		}
	}

	public UserDAO getUserDAO() {
		return userDAO;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}
}