package com.palamahen.app.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.palamahen.app.model.Comment;
import com.palamahen.app.model.Post;
import com.palamahen.app.model.User;
import com.palamahen.app.repository.CommentRepository;
import com.palamahen.app.repository.PostRepository;
import com.palamahen.app.repository.UserRepository;

@Service
public class CommentServiceImpl implements CommentService {
	
	@Autowired
	CommentRepository crepo;
	
	@Autowired
	UserRepository urepo;
	
	@Autowired
	PostRepository prepo;

	@Override
	public Comment addComment(Comment comment, Integer commentorId, Integer postId) {
		
		User commentor = urepo.findById(commentorId).orElse(null);
		
		Post commentToPost = prepo.findById(postId).orElse(null);
		
		Comment newComment = new Comment();
		newComment.setCommentAt(LocalDateTime.now());
		newComment.setCommentBy(commentor);
		newComment.setContent(comment.getContent());
		
		Comment addedComment = crepo.save(newComment);
		
		List<Comment> postComments = commentToPost.getComments();
		
		postComments.add(addedComment);
		commentToPost.setComments(postComments);
		
		prepo.save(commentToPost);
		
		return addedComment;
	}

	@Override
	public Comment findCommentById(Integer commentId) {
		
		Optional<Comment> comment = crepo.findById(commentId);
		
		if(comment.isPresent()) {
			return comment.get();
		}
		
		return null;
	}

	@Override
	public Comment likeComment(Integer commentId, Integer likerId) {
		
		Comment commentToLike = findCommentById(commentId);
		
		if(commentToLike != null) {
			Optional<User> liker = urepo.findById(likerId);
			
			if(liker.isPresent()) {
				List<User> commentLikers = commentToLike.getLikedBy();
				
				if(commentLikers.contains(liker.get()))
					commentLikers.remove(liker.get());
				else
					commentLikers.add(liker.get());
				
				commentToLike.setLikedBy(commentLikers);
				
				crepo.save(commentToLike);
			}
		}
		
		return commentToLike;
	}

}
