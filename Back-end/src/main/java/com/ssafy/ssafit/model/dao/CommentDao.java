package com.ssafy.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.ssafit.model.dto.Comment;

@Repository
public interface CommentDao {
	List<Comment> selectByUserId(String userId);
	void insertComment(Comment comment);
	int updateComment(Comment comment);
	int deleteComment(int commentId);
	List<Comment> selectByVideo(String videoId);
	Comment selectById(int commentId);
}
