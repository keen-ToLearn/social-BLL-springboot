package com.palamahen.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.palamahen.app.model.User;

public interface UserRepository extends JpaRepository<User, Integer> {
	public User findByEmail(String email);
	
	@Query("SELECT u FROM User u WHERE "
			+ "u.firstName LIKE %:query% OR "
			+ "u.lastName LIKE %:query% OR "
			+ "u.email LIKE %:query%")
	public List<User> searchUser(@Param("query") String query);
}
