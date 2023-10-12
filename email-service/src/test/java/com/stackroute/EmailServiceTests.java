package com.stackroute;

import com.stackroute.controller.EmailController;
import com.stackroute.model.Emailer;
import com.stackroute.service.EmailService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;


import javax.mail.*;
import javax.mail.internet.MimeMessage;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;

import static org.junit.Assert.*;
import static org.mockito.Mockito.*;

@SpringBootTest

public class EmailServiceTests {
    @Mock
    private Transport transport;
    @MockBean
    private EmailService emailService;
    @Autowired
    private EmailController emailController;

    @Test
    public void testFor() throws MessagingException {

        Emailer emailer=new Emailer("helloworld@gmail.com","helloworld@gmail.com",
                "this is Subject","hi this is body");
      Session session= emailService.getSession(emailer);

       when(emailService.getSession(emailer)).thenReturn(session);
       assertEquals(session,emailService.getSession(emailer));
    }

    @Test
    public void checkForReceiverMailNullability() throws MessagingException {
        Emailer emailer=new Emailer("rajiv01091997@gmail.com","rajiv01091997@gmail.com",
                "this is Subject","hi this is body");
        assertNotNull(emailer.getReceiverEmail());
        Session session= emailService.getSession(emailer);
        MimeMessage mimeMessage=emailService.getMimeMessage(session,emailer);
        emailService.sendMail(emailer);


    }
    @Test
    public void checkForSenderMailNullability() throws MessagingException {
        Emailer emailer=new Emailer("rajiv01091997@gmail.com","rajiv01091997@gmail.com",
                "this is Subject","hi this is body");
        assertNotNull(emailer.getSenderEmail());
        Session session= emailService.getSession(emailer);
        MimeMessage mimeMessage=emailService.getMimeMessage(session,emailer);
        emailService.sendMail(emailer);

    }

    @Test
    public void checkForSubjectNullability() throws MessagingException {
        Emailer emailer=new Emailer("rajiv01091997@gmail.com","rajiv01091997@gmail.com",
                "this is Subject","hi this is body");
        assertNotNull(emailer.getSubject());
        Session session= emailService.getSession(emailer);
        MimeMessage mimeMessage=emailService.getMimeMessage(session,emailer);
        emailService.sendMail(emailer);

    }









//    @Test
//    public void testForSendEmail() throws MessagingException {
//        Emailer emailer=new Emailer("rajiv01091997@gmail.com","rajiv01091997@gmail.com",
//                "this is Subject","hi this is body");
//        String response="Mail sent successfully to :" + emailer.getReceiverEmail();
//        when(emailService.sendMail(emailer)).thenReturn(response);
//        Assert.assertNotEquals(response,emailController.sendMail(emailer));
//
//
//    }
//
//
//    @Test
//    public void testForGetSession() throws MessagingException {
//        Emailer emailer=new Emailer("helloworld@gmail.com","helloworld@gmail.com",
//                "this is Subject","hi this is body");
//      Session session= emailService.getSession(emailer);
//      MimeMessage mimeMessage=emailService.getMimeMessage(session,emailer);
//
//       when(emailService.sendMail(emailer)).thenReturn("");
//
//    }

}
