package com.ssafy.ssafit.exception;

public class UserAlreadyExistException extends Exception{
	public UserAlreadyExistException() {
		super("이미 있는 아이디입니다");
	}
}
