package com.palamahen.app.service;

import java.util.List;

import com.palamahen.app.model.User;

public interface UserService {
	
	public User registerUser(User user);
	
	public User findUserById(Integer userId);
	
	public User findUserByEmail(String email);
	
	public User followUser(Integer followerId, Integer followingId);
	
	public User updateUser(User modifiedUser, Integer id);
	
	public List<User> searchUser(String query);
	
}
