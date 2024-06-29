package com.palamahen.app.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "user")
public class User {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer id;
	private String firstName;
	private String lastName;
	
	@Column(name = "email")
	private String email;
	private String password;
	
	private List<Integer> followers;
	private List<Integer> following;
	private String gender;
	
	@ManyToMany
	@JsonIgnore
	private List<Post> savedPosts;
	
	public User() {
		
	}
	
	public User(Integer id, String firstName, String lastName, String email, String password, List<Integer> followers,
			List<Integer> following, String gender, List<Post> savedPosts) {
		super();
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.email = email;
		this.password = password;
		this.followers = followers;
		this.following = following;
		this.gender = gender;
		this.savedPosts = savedPosts;
	}
	
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public List<Integer> getFollowers() {
		return followers;
	}

	public void setFollowers(List<Integer> followers) {
		this.followers = followers;
	}

	public List<Integer> getFollowing() {
		return following;
	}

	public void setFollowing(List<Integer> following) {
		this.following = following;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public List<Post> getSavedPosts() {
		return savedPosts;
	}

	public void setSavedPosts(List<Post> savedPosts) {
		this.savedPosts = savedPosts;
	}
	
}
