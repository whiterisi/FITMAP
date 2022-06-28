package com.ssafy.ssafit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.LikeList;
import com.ssafy.ssafit.model.dto.Video;
import com.ssafy.ssafit.model.service.DailyRecordService;
import com.ssafy.ssafit.model.service.LikeListService;
import com.ssafy.ssafit.model.service.VideoService;



@RestController
@RequestMapping("/api/video")
public class VideoController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private VideoService videoService;
	@Autowired
	private LikeListService likeService;
	@Autowired
	private DailyRecordService recordService;
	
	
	@GetMapping("/part/{part}") //front에서 선택한 part 숫자를 (2^n)으로 더해서 하나로 보내주세요!
	public ResponseEntity<List<Video>> videoByPart(@PathVariable int part){
		List<Video> videos = videoService.selectByPart(part);
		return new ResponseEntity<List<Video>>(videos,HttpStatus.OK);
	}
	
	@GetMapping("/{videoId}")
	public ResponseEntity<Video> videoByVideoId(@PathVariable String videoId){
		Video video = videoService.selectByVideoId(videoId);
		System.out.println(video.toString());
		return new ResponseEntity<Video>(video,HttpStatus.OK);
	}
	
	@PostMapping //여기 여쭤보기
	public ResponseEntity<String> addVideo(Video video){
		List<Video> videos = new ArrayList<Video>();
		videos.add(video);
		System.out.println(video.toString());
		videoService.addVideos(videos);
		return new ResponseEntity<String>(SUCCESS,HttpStatus.CREATED);
	}
	
	
	
	@GetMapping("/getLike/{userId}")
	public ResponseEntity<List<Video>> getLikeVideo(@PathVariable String userId){
		List<Video> videos = new ArrayList<Video>();
		
		List<LikeList> likes = likeService.selectByUserId(userId);
		int n = likes.size();
		for(int i=0; i<n; i++) {
			videos.add(videoService.selectByVideoId(likes.get(i).getVideoId()));
		}	
		return new ResponseEntity<List<Video>>(videos,HttpStatus.OK);
	}
	
	@GetMapping("/recommend/{userId}")
	public ResponseEntity<Map<String,Object>> recommendVideo(@PathVariable String userId){
		Map<String, Object> result = new HashMap<String, Object>();
		
		List<Video> videos = recordService.recommendPart(userId);
		result.put("videos", videos);
		result.put("recPart",recordService.getPartStr());
		
		return new ResponseEntity<Map<String,Object>>(result,HttpStatus.OK);
	}
	
	@PostMapping("/like")
	public ResponseEntity<String> addLike(LikeList like){
		likeService.addLike(like);
		return new ResponseEntity<String>(SUCCESS,HttpStatus.CREATED);
	}
	
	@PostMapping("/like/delete")
	public ResponseEntity<String> deleteLike(LikeList like){
		likeService.deleteLike(like);
		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
	
}
