package com.luciana.desafio.services;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import com.luciana.desafio.dto.EmailDTO;

@Service
public class EmailService {
	
	private final JavaMailSender mailSender;
	
	public EmailService(JavaMailSender mailSender) {
		this.mailSender = mailSender;
	}
	
	public void sendEmail(EmailDTO email) {
		var msg = new SimpleMailMessage();
		msg.setFrom("api-desafio@email.com");
		msg.setTo(email.to());
		msg.setSubject(email.subject());
		msg.setText(email.text());
		mailSender.send(msg);
	}

}
