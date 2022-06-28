package com.ssafy.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.ssafit.model.dto.LikeList;

@Repository
public interface LikeListDao {
	List<LikeList> selectByUserId(String userId);
	void insertLike(LikeList likeList);
	int deleteLike(LikeList like);
}
