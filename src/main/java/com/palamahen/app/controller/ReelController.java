package com.palamahen.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palamahen.app.model.Reel;
import com.palamahen.app.service.ReelService;
import com.palamahen.app.service.UserService;

@RestController
@RequestMapping(path = "/reels")
public class ReelController {
	
	@Autowired
	private ReelService reelService;
	
	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<Reel> saveReel(@RequestBody Reel reel, @RequestHeader(name = "Authorization") String token) {
		
		Integer userId = userService.findUserByJwt(token);
		Reel createdReel = reelService.createReel(reel, userId);
		
		ResponseEntity<Reel> saveReelResponse = new ResponseEntity<Reel>(createdReel, HttpStatus.OK);
		
		return saveReelResponse;
		
	}
	
	@GetMapping
	public List<Reel> getReels() {
		
		List<Reel> reels = reelService.findAllReels();
		return reels;
	}
	
	@GetMapping(path = "/user")
	public List<Reel> getReelsByUser(@RequestHeader(name = "Authorization") String token) {
		
		Integer userId = userService.findUserByJwt(token);
		List<Reel> reelsByUser = reelService.findReelsByUser(userId);
		
		return reelsByUser;
	}

}
