package com.shopping.esoshop.utils;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

@Service
public class EmailSenderService {
	@Autowired
	public JavaMailSender mailSender;
	
	public void sendEmail(String toEmail,String subject,String body) {
		MimeMessage message = mailSender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(message);
		try {
			helper.setFrom("devlinh932@gmail.com");
			helper.setTo(toEmail);
			message.setSubject(subject);
			message.setText(body);
			
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		mailSender.send(message);
		
		System.out.println("mail sent successful...");
		
	}

}
