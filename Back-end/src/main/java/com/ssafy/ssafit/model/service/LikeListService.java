package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.LikeList;

public interface LikeListService {
	void addLike(LikeList like);
	void deleteLike(LikeList like);
	List<LikeList> selectByUserId(String userId);
}
