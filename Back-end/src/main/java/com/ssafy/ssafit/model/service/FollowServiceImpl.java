package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.FollowDao;
import com.ssafy.ssafit.model.dto.Follow;

@Service("followService")
public class FollowServiceImpl implements FollowService {
	
	@Autowired
	private FollowDao fDao;
	
	@Override
	public void addFollow(Follow follow) {
		fDao.insertFollow(follow);
	}

	@Override
	public void deleteFollow(Follow follow) {
		int n = fDao.deleteFollow(follow);
		System.out.println(n);
	}

	@Override
	public List<Follow> selectByUserId(String userId) {
		return fDao.selectByUserId(userId);
	}

	@Override
	public String checkFollow(String[] userId) {
		HashMap<String, String> ids = new HashMap<String, String>();
		ids.put("userId", userId[0]);
		ids.put("follow", userId[1]);
		
		List<Follow> result = fDao.checkFollow(ids);

		if(result.size()!=0) return "TRUE";
		else return "FALSE";
	}
	
}
