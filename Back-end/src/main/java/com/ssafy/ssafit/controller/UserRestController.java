package com.ssafy.ssafit.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.UserService;
import com.ssafy.ssafit.util.JWTUtil;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api")
public class UserRestController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private JWTUtil jwtUtil;
	
	@Autowired
	private UserService userService;
	
	
	@PostMapping("/login")
	public ResponseEntity<Map<String, Object>> login(@RequestBody User user){ //여기 parameter User 맞는지

		HttpStatus status = null;
		String userId = user.getUserId();
		
		HashMap<String, Object> result = new HashMap<>();
		try {
			userService.login(user.getUserId(), user.getPw());
			//만약 여기서 로그인 안되면 catch로 넘어간다
			
			result.put("access-token", jwtUtil.createToken("id", userId));
			result.put("message", SUCCESS);
			status = HttpStatus.ACCEPTED;
			
		}catch (Exception e) {
			result.put("message", e.getMessage()); //PW incorrect 또는 User Not found
			//if(e.getClass()==PWIncorrectException.class) status = HttpStatus.BAD_REQUEST;
			//else if(e.getClass()==UserNotFoundException.class) status = HttpStatus.NOT_FOUND;
			status = HttpStatus.BAD_REQUEST;
		}
			
		return new ResponseEntity<Map<String,Object>>(result, status);
	}
	
	@PostMapping("/join")
	public ResponseEntity<Map<String,Object>> join(@RequestBody User user){
		HttpStatus status = null;
		HashMap<String, Object> result = new HashMap<>();
		try {
			userService.join(user);
		} catch (Exception e) {
			result.put("message", e.getMessage()); //user already exist or input null exception
			status = HttpStatus.BAD_REQUEST;
		}
			
		return null;
	}
	
	@GetMapping("/userInfo/{userId}")
	public ResponseEntity<User> getUserInfo(@PathVariable String userId){
		User user = userService.selectById(userId);
		return new ResponseEntity<User>(user,HttpStatus.OK);
	}
	
	
	
	
}
