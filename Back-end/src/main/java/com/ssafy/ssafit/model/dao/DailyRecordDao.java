package com.ssafy.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.ssafit.model.dto.DailyRecord;
import com.ssafy.ssafit.model.dto.User;

@Repository
public interface DailyRecordDao {
	//추가
	DailyRecord selectByRecordId(int recordId);
	//원래
	List<DailyRecord> selectByUserId(String userId);
	int selectMonthlySum(String userId);
	List<String> selectUserByTime();
	void insertRecord(DailyRecord dailyRecord);
	int updateRecord(DailyRecord dailyRecord);
	int deleteRecord(int recordId);
}
