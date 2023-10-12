package com.stackroute.service;


import java.util.Properties;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import com.stackroute.model.Emailer;
import org.springframework.stereotype.Service;







@Service
public class EmailService implements IEmailService{

	@Override
	public Session getSession(Emailer emailer) {

		String host = "smtp.gmail.com";

		Properties properties = System.getProperties();

		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", "465");
		properties.setProperty("mail.smtp.ssl.enable", "true");
		properties.setProperty("mail.smtp.auth", "true");

		Session session = Session.getInstance(properties, new Authenticator() {
			protected PasswordAuthentication getPasswordAuthentication() {
				return new PasswordAuthentication(emailer.getSenderEmail(), "uverbojyyuwabypt");
			}
		});
     return session;
	}
	@Override
    public MimeMessage getMimeMessage(Session session, Emailer emailer) {
		session.setDebug(true);

		MimeMessage mimeMessage = new MimeMessage(session);


		try {
			mimeMessage.setFrom(emailer.getSenderEmail());

			mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(emailer.getReceiverEmail()));

			mimeMessage.setSubject(emailer.getSubject());

			mimeMessage.setText(emailer.getMessage());
		} catch (Exception ex) {
			ex.printStackTrace();
		}
      return mimeMessage;
	}


    @Override
    public String sendMail(Emailer emailer)
	{
		Session session=getSession(emailer);
		MimeMessage mimeMessage=getMimeMessage(session, emailer);
		try
		{
			Transport.send(mimeMessage);
			return "Mail sent successfully to :" + emailer.getReceiverEmail();
		}
		catch(Exception ex)
		{
			ex.printStackTrace();
			return "could not send!!!!!";
		}
	}
	
	
	
}