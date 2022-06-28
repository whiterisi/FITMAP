package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.CommentDao;
import com.ssafy.ssafit.model.dto.Comment;

@Service("commentService")
public class CommentServiceImpl implements CommentService {
	@Autowired
	private CommentDao comDao;
	
	@Override
	public List<Comment> selectByUserId(String userId) {
		return comDao.selectByUserId(userId);
	}

	@Override
	public void editComment(Comment comment) {
		comDao.updateComment(comment);
	}

	@Override
	public void deleteComment(int commentId) {
		comDao.deleteComment(commentId);
	}

	@Override
	public void addComment(Comment comment) {
		comDao.insertComment(comment);
	}

	@Override
	public List<Comment> selectByVideo(String videoId) {
		return comDao.selectByVideo(videoId);
	}

	@Override
	public Comment selectById(int commentId) {
		return comDao.selectById(commentId);
	}

}
