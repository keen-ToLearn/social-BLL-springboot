package com.palamahen.app.service;

import java.util.List;

import com.palamahen.app.model.Post;

public interface PostService {

	public Post createPost(Post post, Integer userId);
	public String deletePost(Integer postId, Integer userId);
	
	public List<Post> findPostByUserId(Integer userId);
	public Post findPostById(Integer postId);
	public List<Post> findAllPosts();
	
	public Post savePost(Integer postId, Integer userId);
	public Post likePost(Integer postId, Integer userId);
}
