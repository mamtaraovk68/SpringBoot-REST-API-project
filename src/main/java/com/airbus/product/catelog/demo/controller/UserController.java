package com.airbus.product.catelog.demo.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.airbus.product.catelog.demo.exceptions.IncorrectPasswordException;
import com.airbus.product.catelog.demo.exceptions.LoginException;
import com.airbus.product.catelog.demo.exceptions.UserNotFoundException;
import com.airbus.product.catelog.demo.models.LoginDTO;
import com.airbus.product.catelog.demo.models.Response;
import com.airbus.product.catelog.demo.service.UserService;


@RestController
@RequestMapping("/myapp/user")
public class UserController {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);
	
	@Autowired
	UserService userService;
	
	//test endpoint
	@GetMapping("/test")
	public String hello() {
		return "Hello myapp";
	}
	

	@PostMapping("/login")
	public ResponseEntity<Response> login(@RequestBody LoginDTO logindto, HttpServletResponse res) throws LoginException, UserNotFoundException, IncorrectPasswordException {
		
		LOGGER.info("Login initiated for user" + logindto.getEmailId());
		String token = userService.loginUser(logindto);
		Response responseDTO = new Response();
		responseDTO.setMessage("Login Sucessfull");
		responseDTO.setStatus(2);
		res.setHeader(token, token);
		return new ResponseEntity<>(responseDTO,HttpStatus.OK);
		
	}
	
	
	
	@PostMapping("/register")
	public String register(@RequestBody LoginDTO logindto, HttpServletResponse res) {
		System.out.println("Register controller");
		return userService.registerUser(logindto);
		
		
	}
	
	

}
