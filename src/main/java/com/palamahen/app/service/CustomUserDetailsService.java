package com.palamahen.app.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.palamahen.app.model.User;
import com.palamahen.app.repository.UserRepository;

@Service
public class CustomUserDetailsService implements UserDetailsService {

	@Autowired
	private UserRepository urepo;
	
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		
		User newUser = urepo.findByEmail(username);
		
		if(newUser == null)
			throw new UsernameNotFoundException("User with username: " + username + " not found");
		
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		
		return new org.springframework.security.core.userdetails.User(newUser.getEmail(), newUser.getPassword(), authorities);
	}

}
