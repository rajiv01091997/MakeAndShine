package com.stackroute.Controller;



import com.stackroute.Service.UserDetailsService;
import com.stackroute.dto.*;
import com.stackroute.repository.RoleRepository;
import com.stackroute.repository.UserRepository;
import com.stackroute.security.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;


//	@CrossOrigin(origins="*",maxAge = 3600)
	@RestController
	@RequestMapping("/api/auth")
	public class AuthController {

	@Autowired
	private UserRepository userRepository;
	@Autowired
	private RoleRepository roleRepository;
	@Autowired
	private JwtUtils jwtUtils;
	@Autowired
	private AuthenticationManager authenticationManager;
	@Autowired
	private PasswordEncoder encoder;
@Autowired
	private UserDetailsService userDetailsService;
	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		return ResponseEntity.ok(userDetailsService.authenticateUser(loginRequest));

	}

	@PutMapping("/forgetPassword")
	public ResponseEntity<?> forgetPassword(@RequestBody ResetPasswordDto resetPasswordDto) {
       return new ResponseEntity<>(userDetailsService.resetPassword(resetPasswordDto),HttpStatus.OK);

	}
}
