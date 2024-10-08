package com.palamahen.app.service;

import com.palamahen.app.model.Comment;

public interface CommentService {

	Comment addComment(Comment comment, Integer commentorId, Integer postId);
	
	Comment findCommentById(Integer commentId);
	
	Comment likeComment(Integer commentId, Integer likerId); 
}
