package com.palamahen.app.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palamahen.app.config.JwtProvider;
import com.palamahen.app.dto.AuthResponseDTO;
import com.palamahen.app.dto.LoginRequestDTO;
import com.palamahen.app.model.User;
import com.palamahen.app.service.CustomUserDetailsService;
import com.palamahen.app.service.UserService;

@RestController
@RequestMapping(path = "/auth")
public class AuthController {

	@Autowired
	private UserService userService;
	
	@Autowired
	private PasswordEncoder passEncoder;
	
	@Autowired
	private CustomUserDetailsService customUserService;
	
	@PostMapping(path = "/signup")
	public ResponseEntity<AuthResponseDTO> createUser(@RequestBody User user) {
		
		User newUser = userService.registerUser(user);
		
		Authentication auth = new UsernamePasswordAuthenticationToken(newUser.getEmail(), newUser.getPassword());
		
		String token = JwtProvider.generateToken(auth);
		
		AuthResponseDTO createResponse = new AuthResponseDTO(token, "Register Success");
		
		ResponseEntity<AuthResponseDTO> newUserResponse = new ResponseEntity<AuthResponseDTO>(createResponse, HttpStatus.OK);
		
		return newUserResponse;
	}
	
	@PostMapping(path = "/signin")
	public ResponseEntity<AuthResponseDTO> signinUser(@RequestBody LoginRequestDTO loginRequest) {
		
		Authentication auth = authenticate(loginRequest.getEmail(), loginRequest.getPassword()).orElse(null);
		
		if(auth != null) {
			
			String token = JwtProvider.generateToken(auth);
			
			AuthResponseDTO signinUserResponse = new AuthResponseDTO(token, "Signin Success");
			
			ResponseEntity<AuthResponseDTO> signinResponse = new ResponseEntity<AuthResponseDTO>(signinUserResponse, HttpStatus.OK);
			
			return signinResponse;
		}
		
		return null;
	}

	private Optional<Authentication> authenticate(String email, String password) {
		
		UserDetails authenticatedUser = customUserService.loadUserByUsername(email);
		
		if(authenticatedUser != null && passEncoder.matches(password, authenticatedUser.getPassword())) {
			
			return Optional.ofNullable(new UsernamePasswordAuthenticationToken(authenticatedUser, password, authenticatedUser.getAuthorities()));
		}
		
		return null;
	}
}
