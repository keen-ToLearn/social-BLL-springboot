package com.palamahen.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.DataBinder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.palamahen.app.model.User;
import com.palamahen.app.repository.UserRepository;
import com.palamahen.app.service.UserService;
import com.palamahen.app.utility.FirstNamePropertyEditor;

@RestController
@RequestMapping(path = "/users")
public class UserController {
	
	@Autowired
	UserRepository urepo;
	
	@Autowired
	UserService userService;
	
//	function which binds incoming request parameter to a processing class
	@InitBinder
	protected void initBinder(DataBinder binder) {
		binder.registerCustomEditor(String.class, "firstName", new FirstNamePropertyEditor());
	}
	
	@GetMapping
	public List<User> getUsers() {
		
		List<User> users = urepo.findAll();
		
		return users;
	}
	
	@GetMapping(path = "/{id}")
	public User getUser(@PathVariable("id") Integer id) {
		
		User fetchedUser = userService.findUserById(id);
		return fetchedUser;
	}
	
	@PostMapping
	public User createUser(@RequestBody User user) {
		
		User savedUser = userService.registerUser(user);
		return savedUser;
	}
	
	@PutMapping(path = "/{id}")
	public User updateUser(@RequestBody User user, @PathVariable("id") Integer id) {
		
		if(id != null) {
			User updatedUser = userService.updateUser(user, id);
			return updatedUser;
		}
		
		return null;
	}
	
	@DeleteMapping(path = "/{id}")
	public String deleteUser(@PathVariable("id") Integer id) {
		if(id != null) {
			urepo.deleteById(id);
			
			return "Success: User with id: " + id + " deleted";
		}
		return "Error: User with id: " + id + " does not exist";
	}
	
	@PutMapping(path = "/follow/{followerId}/{followingId}")
	public User followUserHandler(@PathVariable("followerId") Integer followerId, @PathVariable Integer followingId) {
		
		User followingUser = userService.followUser(followerId, followingId);
		return followingUser;
	}
	
	@GetMapping(path = "/search")
	public List<User> searchUserHandler(@RequestParam(name = "query") String query) {
		
		List<User> filteredUsers = userService.searchUser(query);
		return filteredUsers;
	}
}
