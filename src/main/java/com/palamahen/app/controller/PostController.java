package com.palamahen.app.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palamahen.app.dto.StatusResponseDTO;
import com.palamahen.app.model.Post;
import com.palamahen.app.service.PostService;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@GetMapping
	public List<Post> getPosts() {
		
		return postService.findAllPosts();
	}
	
	@GetMapping(path = "/{postId}")
	public ResponseEntity<Post> fetchPostById(@PathVariable("postId") Integer postId) {
		
		Post postWithId = postService.findPostById(postId);
		ResponseEntity<Post> postIdResponse = new ResponseEntity<Post>(postWithId, HttpStatus.OK);
		
		return postIdResponse;
	}
	
	@GetMapping(path = "/user/{userId}")
	public ResponseEntity<List<Post>> fetchPostByUserId(@PathVariable("userId") Integer userId) {
		
		List<Post> postListByUser = postService.findPostByUserId(userId);
		ResponseEntity<List<Post>> userPostListResponse = new ResponseEntity<List<Post>>(postListByUser, HttpStatus.OK);
		
		return userPostListResponse;
	}
	
	@PostMapping(path = "/user/{userId}")
	public ResponseEntity<Post> savePost(@RequestBody Post post, @PathVariable("userId") Integer userId) {
		
		Post newPost = postService.createPost(post, userId);
		
		ResponseEntity<Post> newPostResponse = new ResponseEntity<Post>(newPost, HttpStatus.OK);
		
		return newPostResponse;
	}
	
	@DeleteMapping(path = "/{postId}/user/{userId}")
	public ResponseEntity<StatusResponseDTO> removePost(@PathVariable("postId") Integer pid, @PathVariable("userId") Integer uid) {
		
		String deleteStatus = postService.deletePost(pid, uid);
		
		StatusResponseDTO deleteStatusBody = new StatusResponseDTO(true, deleteStatus);
		
		ResponseEntity<StatusResponseDTO> deletePostResponse = new ResponseEntity<StatusResponseDTO>(deleteStatusBody, HttpStatus.OK);
		
		return deletePostResponse;
	}
	
	@PutMapping(path = "/{postId}/save/user/{userId}")
	public ResponseEntity<Post> savePostForUser(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId) {
		
		Post savedUserPost = postService.savePost(postId, userId);
		
		ResponseEntity<Post> savePostResponse = new ResponseEntity<Post>(savedUserPost, HttpStatus.OK);
		
		return savePostResponse;
	}
	
	@PutMapping(path = "/{postId}/like/user/{userId}")
	public ResponseEntity<Post> likePostForUser(@PathVariable("postId") Integer postId, @PathVariable("userId") Integer userId) {
		
		Post likedUserPost = postService.likePost(postId, userId);
		
		ResponseEntity<Post> likePostResponse = new ResponseEntity<Post>(likedUserPost, HttpStatus.OK);
		
		return likePostResponse;
	}

}
