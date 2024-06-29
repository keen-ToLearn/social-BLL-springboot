package com.palamahen.app.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palamahen.app.model.Post;

public interface PostRepository extends JpaRepository<Post, Integer> {
	
	public List<Post> findByPostById(Integer postById);
}
