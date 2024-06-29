package com.palamahen.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palamahen.app.model.Reel;

@RestController
@RequestMapping(path = "/reels")
public class ReelController {
	
	@GetMapping
	public List<Reel> getReels() {
		List<Reel> reels = new ArrayList<Reel>();
		
		return reels;
	}

}
