package com.ssafy.ssafit.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.ssafit.model.dto.DailyRecord;
import com.ssafy.ssafit.model.dto.Follow;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.service.DailyRecordService;
import com.ssafy.ssafit.model.service.FollowService;
import com.ssafy.ssafit.model.service.UserService;

import io.swagger.v3.oas.annotations.parameters.RequestBody;

@RestController
@RequestMapping("/api/record")
public class DailyRecordRestController {
	
	private static final String SUCCESS = "success";
	private static final String FAIL = "fail";
	
	@Autowired
	private DailyRecordService recordService;
	@Autowired
	private FollowService followService;
	@Autowired
	private UserService userService;
	
	@PostMapping("/write")
	public ResponseEntity<String> writeRecord(@RequestBody DailyRecord dailyRecord){ //recordId에 0을 넣으면 된다
		recordService.addRecord(dailyRecord);
		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
	
	@PostMapping("/edit")
	public ResponseEntity<String> editRecord(@RequestBody DailyRecord dailyRecord){ //정확한 recordId를 넣어서 보내줘야 한다
		recordService.editRecord(dailyRecord);
		return new ResponseEntity<String>(SUCCESS, HttpStatus.OK);
	}
	
	@DeleteMapping("/{recordId}")
	public ResponseEntity<String> deleteRecord(@PathVariable int recordId){//정확한 recordId를 넣어서 보내줘야 한다
		recordService.deleteRecord(recordId);
		return new ResponseEntity<String>(SUCCESS,HttpStatus.OK);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<List<DailyRecord>> getRecord(@PathVariable String userId){
		return new ResponseEntity<List<DailyRecord>>(recordService.selectByUserId(userId),HttpStatus.OK);
	}
	
	//추가한 부분
	@GetMapping("/edit/{recordId}")
	public ResponseEntity<DailyRecord> getDailyRecord(@PathVariable int recordId){
		return new ResponseEntity<DailyRecord>(recordService.selectByRecordId(recordId),HttpStatus.OK);
	}
	
	//userid -> 이 유저의 친구를 <UserId>이렇게 해서 보내는 함수
	//Map key값을 여기서 받아옴
	
	
	@GetMapping("/follow/{userId}") //userId를 보내면, user의 친구 데일리 레코드 하나만 보여주자
	public ResponseEntity<List<DailyRecord>> getFollowRecord(@PathVariable String userId){
		//Map<User, DailyRecord> result = new HashMap<User, DailyRecord>();
		List<DailyRecord> result = new ArrayList<DailyRecord>();
		
		List<Follow> follows = followService.selectByUserId(userId);
		int n = follows.size();
		
		for(int i=0; i<n; i++) {
			User friend = userService.selectById(follows.get(i).getFollow()); //친구 아이디
			if(recordService.selectByUserId(friend.getUserId()).size()!=0) {
				result.add(recordService.selectByUserId(friend.getUserId()).get(0)); //친구 record 중 가장 최근 것만 택해서 보낸다
			//기록이 있는 친구만 보낸다
			}
		}
		
		return new ResponseEntity<List<DailyRecord>>(result,HttpStatus.OK);
	}
	
	@GetMapping("/rank")
	public ResponseEntity<Map<String,Object>> getMonthlyRank(){
		Map<String,Object> result = new HashMap<String, Object>();
		
		List<String> rankers = recordService.monthlyRankers();  //rankers userIds
		
		List<String> names = new ArrayList<String>();
		int n = rankers.size();
		for(int i=0; i<n; i++) {
			names.add(userService.selectById(rankers.get(i)).getUserId());
		}
		
		result.put("names", names); //key : names -> rankers의 !!nicknames!! list 저장
		
		List<User> rankers2 = new ArrayList<User>(); 
		for(int i=0; i<rankers.size(); i++){
			User u = userService.selectById(rankers.get(i));
			rankers2.add(u);
		}
		
		Map<String,int[]> rankersWithRecord = recordService.monthlyRank(rankers2);
		//key : records 
		//value : key2 - nicknames에 name / value2 - int[0]이 랭킹, int[1]이 monthlySum
		result.put("records", rankersWithRecord);

		return new ResponseEntity<Map<String,Object>>(result,HttpStatus.OK);
	}
	
	
}
