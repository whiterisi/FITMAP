package com.ssafy.ssafit.model.dao;

import org.springframework.stereotype.Repository;

import com.ssafy.ssafit.model.dto.User;

@Repository
public interface UserDao {
	User selectById(String userId);
	void insertUser(User user);
	
}
