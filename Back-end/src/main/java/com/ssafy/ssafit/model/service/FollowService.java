package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.ssafit.model.dto.Follow;

public interface FollowService {
	void addFollow(Follow follow);
	void deleteFollow(Follow follow);
	List<Follow> selectByUserId(String userId);
	String checkFollow(String[] userId);
}
