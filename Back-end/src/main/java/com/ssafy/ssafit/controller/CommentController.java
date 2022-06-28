package com.ssafy.ssafit.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.Comment;
import com.ssafy.ssafit.model.service.CommentService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/comment")
public class CommentController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private CommentService comService;
	
	@GetMapping("/{videoId}")
	public ResponseEntity<List<Comment>> getCommentByVideo(@PathVariable String videoId){
		List<Comment> result = comService.selectByVideo(videoId);
		
		return new ResponseEntity<List<Comment>>(result, HttpStatus.OK);
	}
	
	@GetMapping("/id/{commentId}")
	public ResponseEntity<Comment> getCommentById(@PathVariable int commentId){
		System.out.println(commentId);
		Comment c = comService.selectById(commentId);
		return new ResponseEntity<Comment>(c, HttpStatus.OK);
	}
	
	
	@PostMapping("/write")  //일단 parentId는 Integer로 설정 (commentId 가리킬 수 있도록)
	public ResponseEntity<String> writeComment(@RequestBody Comment comment){
		comService.addComment(comment);
		return new ResponseEntity<String>(SUCCESS,HttpStatus.CREATED);
	}
	@PostMapping("/edit")
	public ResponseEntity<String> editComment(@RequestBody Comment comment){
		comService.editComment(comment);
		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
	@DeleteMapping("/{commentId}")
	public ResponseEntity<String> deleteComment(@PathVariable int commentId){
		comService.deleteComment(commentId);
		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
}
