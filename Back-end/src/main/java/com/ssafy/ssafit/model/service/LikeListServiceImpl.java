package com.ssafy.ssafit.model.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.LikeListDao;
import com.ssafy.ssafit.model.dto.LikeList;

@Service("likeService")
public class LikeListServiceImpl implements LikeListService{
	@Autowired
	private LikeListDao likeDao;
	
	@Override
	public void addLike(LikeList like) {
		likeDao.insertLike(like);
	}

	@Override
	public void deleteLike(LikeList like) {
		likeDao.deleteLike(like);
	}

	@Override
	public List<LikeList> selectByUserId(String userId) {
		return likeDao.selectByUserId(userId);
	}
	
}
