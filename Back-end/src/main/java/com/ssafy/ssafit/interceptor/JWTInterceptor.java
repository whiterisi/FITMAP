package com.ssafy.ssafit.interceptor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import com.ssafy.ssafit.util.JWTUtil;

@Component
public class JWTInterceptor implements HandlerInterceptor{
	private static final String HEADER_AUTH = "access-token";
	
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {

		if (request.getMethod().equals("OPTIONS")) {
			return true;
		}
		
		final String token = request.getHeader(HEADER_AUTH);
		System.out.println(token);
		System.out.println("token : " + token);
		if(token != null){
			jwtUtil.valid(token);
			return true;
		}
		//여기서 redirect해서 컨트롤러로 보내기!
		throw new Exception("유효하지 않은 접근입니다."); //토큰 유효하지 않으면 여기서 걸린다
	}
	
}
