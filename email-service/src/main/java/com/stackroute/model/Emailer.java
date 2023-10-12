package com.stackroute.model;


import lombok.*;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Emailer{
@NotEmpty(message = "Sender email address cannot be null or empty")
@Email(message = "Please provide a valid sender Email address")
private String senderEmail;

@NotEmpty(message="Receiver Email address cannot be null or empty")
@Email(message = "Please provide a valid receiver Email address")
private String receiverEmail;
@NotEmpty(message = "Cannot send without a subject")
private String subject;
private String 	message;

}
