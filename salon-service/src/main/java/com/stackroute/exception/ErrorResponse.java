
package com.stackroute.exception;

import java.time.LocalDateTime;


import lombok.*;
import org.springframework.http.HttpStatus;
@Data
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ErrorResponse {

	private LocalDateTime timestamp;
	private HttpStatus status;
	private String message;
	private String details;


}
