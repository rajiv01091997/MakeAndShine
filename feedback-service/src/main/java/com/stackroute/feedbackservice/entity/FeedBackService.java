package com.stackroute.feedbackservice.entity;


import javax.validation.constraints.Email;

import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;


@Setter
@Getter
@NoArgsConstructor
@Data
@Document(collection = "feedback")
public class FeedBackService {

	@Transient
	public static final String SEQUENCE_NAME="appointment_sequence";

	
	  @Id
	  private long appointmentId;

	  @Email
	  private String customerEmailId;
	  private String customerName;
	  private String comment;
	  private int rating;

	public FeedBackService(long appointmentId, String customerEmailId, String customerName, String comment, int rating) {
		this.appointmentId = appointmentId;
		this.customerEmailId = customerEmailId;
		this.customerName = customerName;
		this.comment = comment;
		this.rating = rating;
	}
}
