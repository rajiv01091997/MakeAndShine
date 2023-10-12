package com.stackroute.Controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/test")
public class TestController {
    @GetMapping("/all")
    public String allAccess() {
        return "Public Content.";
    }

    @GetMapping("/customer")
    @PreAuthorize("hasRole('ROLE_CUSTOMER') or hasRole('ROLE_EMPLOYEE')")
    public String customerAccess() {
        return "User Content.";
    }

    @GetMapping("/employee")
    @PreAuthorize("hasRole('ROLE_EMPLOYEE')")
    public String employeeAccess() {
        return "Employee Board.";
    }


}