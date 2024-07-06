package com.palamahen.app.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.palamahen.app.config.JwtProvider;
import com.palamahen.app.model.User;
import com.palamahen.app.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {
	
	@Autowired
	UserRepository urepo;
	
	@Autowired
	private PasswordEncoder passwordEncoder;

	@Override
	public User registerUser(User user) {
		
		User userExists = findUserByEmail(user.getEmail());
		
		if(userExists == null) {
			
			User newUser = new User();
			newUser.setEmail(user.getEmail());
			newUser.setFirstName(user.getFirstName());
			newUser.setLastName(user.getLastName());
			newUser.setPassword(passwordEncoder.encode(user.getPassword()));
			newUser.setGender(user.getGender());
			
			User registeredUser = urepo.save(newUser);
			
			return registeredUser;
		}
		
		return userExists;
	}
	
	@Override
	public User findUserById(Integer userId) {
//		User fetchedUser =  urepo.findById(userId).orElse(null);
		
		Optional<User> fetchedUser = urepo.findById(userId);
		
		if(fetchedUser.isPresent())
			return fetchedUser.get();
		
		return null;
	}

	@Override
	public User findUserByEmail(String email) {
		
		User emailWiseUser = urepo.findByEmail(email);
		return emailWiseUser;
	}
	
	@Override
	public User followUser(Integer followerId, Integer followingId) {
		
		User userWithFollower = findUserById(followingId);
		
		List<Integer> followerList = userWithFollower.getFollowers();
		if(followerList != null)
			followerList.add(followerId);
		else
			followerList = new ArrayList<Integer>(Arrays.asList(followerId));
		
		userWithFollower.setFollowers(followerList);
		
		User userFollowing = findUserById(followerId);
		
		List<Integer> followingList = userFollowing.getFollowing();
		if(followingList != null)
			followingList.add(followingId);
		else
			followingList = new ArrayList<Integer>(Arrays.asList(followingId));
		
		userFollowing.setFollowing(followingList);
		
		urepo.save(userFollowing);
		User savedUserWithFollower = urepo.save(userWithFollower);
		
		return savedUserWithFollower;
	}

	@Override
	public User updateUser(User modifiedUser, Integer id) {
		User updatedUser = new User();
		
		updatedUser.setEmail(modifiedUser.getEmail());
		updatedUser.setFirstName(modifiedUser.getFirstName());
		updatedUser.setId(id);
		updatedUser.setLastName(modifiedUser.getLastName());
		updatedUser.setPassword(modifiedUser.getPassword());
		updatedUser.setGender(modifiedUser.getGender());
		
		urepo.save(updatedUser);
		
		return updatedUser;
	}

	@Override
	public List<User> searchUser(String query) {
		return urepo.searchUser(query);
	}
	
	@Override
	public Integer findUserByJwt(String jwt) {
		
		String email = JwtProvider.getEmailFromJwt(jwt);
		User jwtUser = findUserByEmail(email);
		
		return jwtUser.getId();
	}

}
