package com.palamahen.app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.palamahen.app.model.Comment;

public interface CommentRepository extends JpaRepository<Comment, Integer> {

}
