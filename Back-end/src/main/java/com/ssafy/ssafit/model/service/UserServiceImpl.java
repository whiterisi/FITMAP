package com.ssafy.ssafit.model.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.ssafy.ssafit.exception.InputNullException;
import com.ssafy.ssafit.exception.PWIncorrectException;
import com.ssafy.ssafit.exception.UserAlreadyExistException;
import com.ssafy.ssafit.exception.UserNotFoundException;
import com.ssafy.ssafit.model.dao.UserDao;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.util.SHA256;

@Service("userService")
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserDao userDao;
	
	@Transactional
	@Override
	public void join(User user) throws Exception {
		if(user.getUserId().length()==0 || user.getPw().length()==0) throw new InputNullException();
		if(userDao.selectById(user.getUserId())!=null) throw new UserAlreadyExistException();
		
		user.setPw(new SHA256().getHash(user.getPw()) );
		userDao.insertUser(user);
	}

	@Override
	public User login(String userId, String pw) throws Exception{
	
		User user = userDao.selectById(userId);
		if( user == null )
			throw new UserNotFoundException();
		if( !user.getPw().equals( new SHA256().getHash(pw) ) )
			throw new PWIncorrectException();
		else
			return user;
		
	}

	@Override
	public User selectById(String userId) {
		return userDao.selectById(userId);
	}
	

}
