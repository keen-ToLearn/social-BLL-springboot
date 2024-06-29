package com.palamahen.app.model;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "post")
public class Post {
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Integer postId;
	
	@ManyToOne
	private User postBy;
	private String caption;
	private String imageURL;
	
	private String videoURL;
	private LocalDateTime timestamp;
	
	@JsonIgnore
	@ManyToMany
	private List<User> likedBy;
	
	public Post() {
		
	}

	public Post(Integer postId, User postBy, String caption, String imageURL, String videoURL,
			LocalDateTime timestamp, List<User> likedBy) {
		super();
		this.postId = postId;
		this.postBy = postBy;
		this.caption = caption;
		this.imageURL = imageURL;
		this.videoURL = videoURL;
		this.timestamp = timestamp;
		this.likedBy = likedBy;
	}

	public Integer getPostId() {
		return postId;
	}

	public void setPostId(Integer postId) {
		this.postId = postId;
	}

	public User getPostBy() {
		return postBy;
	}

	public void setPostBy(User postBy) {
		this.postBy = postBy;
	}

	public String getCaption() {
		return caption;
	}

	public void setCaption(String caption) {
		this.caption = caption;
	}

	public String getImageURL() {
		return imageURL;
	}

	public void setImageURL(String imageURL) {
		this.imageURL = imageURL;
	}

	public String getVideoURL() {
		return videoURL;
	}

	public void setVideoURL(String videoURL) {
		this.videoURL = videoURL;
	}

	public LocalDateTime getTimestamp() {
		return timestamp;
	}

	public void setTimestamp(LocalDateTime timestamp) {
		this.timestamp = timestamp;
	}

	public List<User> getLikedBy() {
		return likedBy;
	}

	public void setLikedBy(List<User> likedBy) {
		this.likedBy = likedBy;
	}
	
}
