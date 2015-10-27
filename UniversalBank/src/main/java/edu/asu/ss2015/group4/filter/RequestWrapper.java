package edu.asu.ss2015.group4.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;

import org.apache.log4j.Logger;
/* Reference for preventing xss attack https://jeevanpatil.wordpress.com/2011/07/22/prevention_of_xss/ */

public final class RequestWrapper extends HttpServletRequestWrapper {
	private static Logger logger = Logger.getLogger(RequestWrapper.class);
	public RequestWrapper(HttpServletRequest servletRequest) {
		super(servletRequest);
	}

	public String[] getParameterValues(String parameter) {
		logger.info("InarameterValues .. parameter .......");
		String[] values = super.getParameterValues(parameter);
		if (values == null) {
			return null;
		}
		int count = values.length;
		String[] encodedValues = new String[count];
		for (int i = 0; i < count; i++) {
			encodedValues[i] = cleanXSS(values[i]);
		}
		return encodedValues;
	}

	public String getParameter(String parameter) {
		logger.info("Inarameter .. parameter .......");
		String value = super.getParameter(parameter);
		if (value == null) {
			return null;
		}
		logger.info("Inarameter RequestWrapper ........ value .......");
		return cleanXSS(value);
	}

	public String getHeader(String name) {
		logger.info("Ineader .. parameter .......");
		String value = super.getHeader(name);
		if (value == null)
			return null;
		logger.info("Ineader RequestWrapper ........... value ....");
		return cleanXSS(value);
	}

	private String cleanXSS(String value) {
		// You'll need to remove the spaces from the html entities below
		logger.info("InnXSS RequestWrapper ..............." + value);
		value = value.replaceAll("<", "lol").replaceAll(">", "lol");
		value = value.replaceAll("\\(", "lol").replaceAll("\\)", "lol");
		value = value.replaceAll("'", "lol");
		value = value.replaceAll("eval\\((.*)\\)", "lol");
		value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "lol");
		System.out.println("out"+value);
		value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "lol");
		value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "lol");
		value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "lol");
		value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "lol");
		value = value.replaceAll("<script>", "lol");
		value = value.replaceAll("</script>", "lol");
		System.out.println("out");

		logger.info("OutnXSS RequestWrapper ........ value ......." + value);
		return value;
	}
}