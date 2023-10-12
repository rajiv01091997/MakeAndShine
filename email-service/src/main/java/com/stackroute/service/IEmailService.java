package com.stackroute.service;




import com.stackroute.model.Emailer;


import javax.mail.Session;
import javax.mail.internet.MimeMessage;

public interface IEmailService {


     Session getSession(Emailer emailer);

     MimeMessage getMimeMessage(Session session, Emailer emailer);

     String sendMail(Emailer emailer);
}
