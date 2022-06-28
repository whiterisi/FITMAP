package com.ssafy.ssafit.model.dao;

import java.util.HashMap;
import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.ssafit.model.dto.Follow;
@Repository
public interface FollowDao {
	List<Follow> selectByUserId(String userId);
	void insertFollow(Follow follow);
	int deleteFollow(Follow follow);
	List<Follow> checkFollow(HashMap<String, String> userIds); //follow 중복으로 들어갔을 경우 대비해서 List로 리턴한다 
}
