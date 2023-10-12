package com.stackroute.feedbackservice.dto;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@ToString
@Data
public class FeedBackServiceDto {


	private long appointmentId;
	private String customerEmailId;
	private String customerName;
	private String comment;
	private int rating;

	public FeedBackServiceDto(long appointmentId, String customerEmailId, String customerName, String comment, int rating) {
		this.appointmentId = appointmentId;
		this.customerEmailId = customerEmailId;
		this.customerName = customerName;
		this.comment = comment;
		this.rating = rating;
	}


}


