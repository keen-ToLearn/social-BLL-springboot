package com.palamahen.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palamahen.app.model.Message;

@RestController
@RequestMapping(path = "/messages")
public class MessageController {
	
	@GetMapping
	public List<Message> getMessages() {
		List<Message> messages = new ArrayList<Message>();
		
		return messages;
	}
	
}
