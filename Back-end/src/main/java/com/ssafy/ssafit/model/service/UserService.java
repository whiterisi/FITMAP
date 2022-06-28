package com.ssafy.ssafit.model.service;

import com.ssafy.ssafit.model.dto.User;

public interface UserService {
	void join(User user) throws Exception;
	User login(String userId, String pw) throws Exception;
	User selectById(String userId);
}
