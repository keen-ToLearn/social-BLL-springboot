package com.palamahen.app.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palamahen.app.model.Post;
import com.palamahen.app.model.User;
import com.palamahen.app.repository.PostRepository;
import com.palamahen.app.repository.UserRepository;

@Service
public class PostServiceImpl implements PostService {
	
	@Autowired
	PostRepository prepo;
	
	@Autowired
	UserRepository urepo;

	@Override
	public Post createPost(Post post, Integer userId) {
		
		User postBy = urepo.findById(userId).orElse(null);
		
		Post newPost = new Post();
		newPost.setCaption(post.getCaption());
		newPost.setImageURL(post.getImageURL());
		newPost.setPostBy(postBy);
		newPost.setTimestamp(LocalDateTime.now());
		newPost.setVideoURL(post.getVideoURL());
		
		Post savedPost = prepo.save(newPost);
		
		return savedPost;
	}

	@Override
	public String deletePost(Integer postId, Integer userId) {
		
		Post postToDelete = findPostById(postId);
		
		if(postToDelete.getPostBy().getId() == userId) {
			prepo.deleteById(postId);
			
			return "Post with id: " + postId + " has been deleted";
		}
		return "Cannot delete Post by another user";
	}

	@Override
	public List<Post> findPostByUserId(Integer userId) {
		
		List<Post> allPostsByUserId = prepo.findByPostById(userId);
		return allPostsByUserId;
	}

	@Override
	public Post findPostById(Integer postId) {
		
		Post postWithId = prepo.findById(postId).orElse(null);
		return postWithId;
	}

	@Override
	public List<Post> findAllPosts() {
		
		return prepo.findAll();
	}

	@Override
	public Post savePost(Integer postId, Integer userId) {
		
		Optional<User> userWithSavedPost = urepo.findById(userId);
		
		if(userWithSavedPost.isPresent()) {
			User user = userWithSavedPost.get();
			List<Post> userSavedPosts = user.getSavedPosts();
			
			if(userSavedPosts == null)
				userSavedPosts = new ArrayList<Post>();
			
			Post post = findPostById(postId);
			
			if(userSavedPosts.contains(post))
				userSavedPosts.remove(post);
			else
				userSavedPosts.add(post);
			
			user.setSavedPosts(userSavedPosts);
			urepo.save(user);
			
			return post;
		}
		
		return null;
	}

	@Override
	public Post likePost(Integer postId, Integer userId) {
		
		Post postToLike = findPostById(postId);
		List<User> likedList = postToLike.getLikedBy();
		
		if(likedList == null)
			likedList = new ArrayList<User>();
		
		User likedBy = urepo.findById(userId).orElse(null);
		
		if(likedList.contains(likedBy))
			likedList.remove(likedBy);
		else
			likedList.add(likedBy);
		
		postToLike.setLikedBy(likedList);
		Post updatedPostWithLike = prepo.save(postToLike);
		
		return updatedPostWithLike;
	}

}
