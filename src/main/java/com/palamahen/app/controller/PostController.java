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
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palamahen.app.dto.StatusResponseDTO;
import com.palamahen.app.model.Post;
import com.palamahen.app.service.PostService;
import com.palamahen.app.service.UserService;

@RestController
@RequestMapping(path = "/posts")
public class PostController {
	
	@Autowired
	PostService postService;
	
	@Autowired
	UserService userService;
	
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
	
	@PostMapping
	public ResponseEntity<Post> savePost(@RequestBody Post post, @RequestHeader("Authorization") String token) {
		
		Integer creatorId = userService.findUserByJwt(token);
		Post newPost = postService.createPost(post, creatorId);
		
		ResponseEntity<Post> newPostResponse = new ResponseEntity<Post>(newPost, HttpStatus.OK);
		
		return newPostResponse;
	}
	
	@DeleteMapping(path = "/{postId}")
	public ResponseEntity<StatusResponseDTO> removePost(@PathVariable("postId") Integer pid, @RequestHeader("Authorization") String token) {
		
		Integer uid = userService.findUserByJwt(token);
		String deleteStatus = postService.deletePost(pid, uid);
		
		StatusResponseDTO deleteStatusBody = new StatusResponseDTO(true, deleteStatus);
		
		ResponseEntity<StatusResponseDTO> deletePostResponse = new ResponseEntity<StatusResponseDTO>(deleteStatusBody, HttpStatus.OK);
		
		return deletePostResponse;
	}
	
	@PutMapping(path = "/{postId}/save")
	public ResponseEntity<Post> savePostForUser(@PathVariable("postId") Integer postId, @RequestHeader("Authorization") String token) {
		
		Integer saverId = userService.findUserByJwt(token);
		Post savedUserPost = postService.savePost(postId, saverId);
		
		ResponseEntity<Post> savePostResponse = new ResponseEntity<Post>(savedUserPost, HttpStatus.OK);
		
		return savePostResponse;
	}
	
	@PutMapping(path = "/{postId}/like")
	public ResponseEntity<Post> likePostForUser(@PathVariable("postId") Integer postId, @RequestHeader("Authorization") String token) {
		
		Integer likerId = userService.findUserByJwt(token);
		Post likedUserPost = postService.likePost(postId, likerId);
		
		ResponseEntity<Post> likePostResponse = new ResponseEntity<Post>(likedUserPost, HttpStatus.OK);
		
		return likePostResponse;
	}

}
