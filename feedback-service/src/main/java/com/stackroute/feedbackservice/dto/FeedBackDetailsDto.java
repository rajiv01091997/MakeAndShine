package com.stackroute.feedbackservice.dto;

import lombok.*;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
@Data
public class FeedBackDetailsDto {

	private Long appointmentId;
	private String comment;
	private int rating;

}
