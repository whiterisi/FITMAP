package com.ssafy.ssafit.model.service;

import java.util.HashMap;
import java.util.List;

import com.ssafy.ssafit.model.dto.DailyRecord;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.dto.Video;

public interface DailyRecordService {
	//추가
	DailyRecord selectByRecordId(int recordId);
	
	String getPartStr();
	
	//원래 부분
	List<DailyRecord> selectByUserId(String userId);
	List<DailyRecord> selectSomeByUserId(String userId, int line);
	List<Video> recommendPart(String userId);
	List<String> monthlyRankers();
	HashMap<String, int[]> monthlyRank(List<User> rankers);
	void addRecord(DailyRecord dailyRecord);
	void editRecord(DailyRecord dailyRecord);
	void deleteRecord(int recordId);
}
