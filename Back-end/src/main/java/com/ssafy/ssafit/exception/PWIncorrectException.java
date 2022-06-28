package com.ssafy.ssafit.exception;

public class PWIncorrectException extends Exception{
	public PWIncorrectException() {
		super("비밀번호가 틀립니다.");
		System.out.println("PW incorrect Exception");
	}
}
