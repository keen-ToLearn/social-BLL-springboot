package com.palamahen.app.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path = "/home")
public class HomeController {
	
	@GetMapping
	public String homeControllerHandler() {
		return "In home controller";
	}

}
