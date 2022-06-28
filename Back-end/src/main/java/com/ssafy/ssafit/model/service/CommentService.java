package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Comment;

public interface CommentService {
	List<Comment> selectByUserId(String userId);
	void editComment(Comment comment);
	void deleteComment(int commentId);
	void addComment(Comment comment);
	List<Comment> selectByVideo(String videoId);
	Comment selectById(int commentId);
}
