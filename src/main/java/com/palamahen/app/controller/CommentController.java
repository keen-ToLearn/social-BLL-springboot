package com.palamahen.app.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.palamahen.app.model.Comment;
import com.palamahen.app.service.CommentService;
import com.palamahen.app.service.UserService;

@RestController
@RequestMapping(path = "/comments")
public class CommentController {

	@Autowired
	private CommentService commentService;
	
	@Autowired
	private UserService userService;
	
	@GetMapping(path = "/{commentId}")
	public Comment fetchCommentById(@PathVariable("commentId") Integer commentId) {
		
		Comment commentWithId = commentService.findCommentById(commentId);
		
		return commentWithId;
	}
	
	@PostMapping(path = "/commentToPost/{postId}")
	public Comment createComment(@RequestBody Comment comment, @PathVariable("postId") Integer postId,
			@RequestHeader("Authorization") String token) {
		
		Integer commentorId = userService.findUserByJwt(token);
		Comment createdComment = commentService.addComment(comment, commentorId, postId);
		
		return createdComment;
	}
	
	@PutMapping(path = "/like/{commentId}")
	public Comment makeUserLikeComment(@PathVariable("commentId") Integer commentId,
			@RequestHeader("Authorization") String token) {
		
		Integer likerId = userService.findUserByJwt(token);
		Comment likedComment = commentService.likeComment(commentId, likerId);
		
		return likedComment;
	}
}
