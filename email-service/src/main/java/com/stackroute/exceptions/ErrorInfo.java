package com.stackroute.exceptions;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;


import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ErrorInfo {
    private LocalDateTime timestamp;
    private HttpStatus status;
    private String message;
    private String path;


}
