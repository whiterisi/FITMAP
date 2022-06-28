package com.ssafy.ssafit.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Follow;
import com.ssafy.ssafit.model.service.FollowService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/follow")
public class FollowController {
	
	@Autowired
	private FollowService fService;
	
	@PostMapping
	public ResponseEntity<String> addFollow(Follow follow){
		fService.addFollow(follow);
		return new ResponseEntity<String>("success",HttpStatus.CREATED);
	}
	
	@PostMapping("/delete")
	public ResponseEntity<String> deleteFollow(Follow follow){
		fService.deleteFollow(follow);
		return new ResponseEntity<String>("success",HttpStatus.OK);
	}
	
	//"TRUE", "FALSE"
	@GetMapping("/{userids}")
	public ResponseEntity<String> checkFollow(@PathVariable String userids){
		String[] Ids = userids.split("\\s+");
		String result = fService.checkFollow(Ids);
		
		return new ResponseEntity<String>(result,HttpStatus.OK);
	}
}
