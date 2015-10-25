package edu.asu.ss2015.group4.service;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;

public class MailingService {
	private MailSender mailSender;
	private final String fromAddress = "unibank.group4.2015@gmail.com";

	public String getFromAddress() {
		return fromAddress;
	}

	public void setMailSender(MailSender mailSender) {
		this.mailSender = mailSender;
	}

	public void sendMail(String from, String to, String subject, String msg) {

		SimpleMailMessage message = new SimpleMailMessage();

		message.setFrom(from);
		message.setTo(to);
		message.setSubject(subject);
		message.setText(msg);
		mailSender.send(message);
	}
}
