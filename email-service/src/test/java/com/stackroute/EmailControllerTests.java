package com.stackroute;

import com.stackroute.controller.EmailController;
import com.stackroute.model.Emailer;
import com.stackroute.service.EmailService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.internet.MimeMessage;

import static org.junit.Assert.assertNotNull;
import static org.mockito.Mockito.when;

@SpringBootTest
public class EmailControllerTests {


    @MockBean
    private EmailService emailService;

    @Autowired
    private EmailController emailController;

    @Test
    public void testForSendEmail() throws MessagingException {
        Emailer emailer=new Emailer("rajiv01091997@gmail.com","rajiv01091997@gmail.com",
                "this is Subject","hi this is body");
        String response="Mail sent successfully to :" + emailer.getReceiverEmail();
        when(emailService.sendMail(emailer)).thenReturn(response);
        Assert.assertNotEquals(response,emailController.sendMail(emailer));


    }


}
