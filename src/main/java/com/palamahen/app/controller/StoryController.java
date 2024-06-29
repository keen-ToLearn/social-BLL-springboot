package com.palamahen.app.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palamahen.app.model.Story;

@RestController
@RequestMapping(path = "/stories")
public class StoryController {
	
	@GetMapping
	public List<Story> getStories() {
		List<Story> stories = new ArrayList<Story>();
		
		return stories;
	}
	
}
