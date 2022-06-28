package com.ssafy.ssafit.model.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.DailyRecordDao;
import com.ssafy.ssafit.model.dao.VideoDao;
import com.ssafy.ssafit.model.dto.DailyRecord;
import com.ssafy.ssafit.model.dto.User;
import com.ssafy.ssafit.model.dto.Video;

@Service("dailyRecordService")
public class DailyRecordServiceImpl implements DailyRecordService {
	
	@Autowired
	private DailyRecordDao recordDao;
	@Autowired
	private VideoDao videoDao;
	
	
	String partStr = new String();
	@Override
	public String getPartStr() {
		return partStr;
	}
	
	//추가분
	@Override
	public DailyRecord selectByRecordId(int recordId) {
		return recordDao.selectByRecordId(recordId);
	}	
	
	
	@Override
	public List<DailyRecord> selectByUserId(String userId) {
		return recordDao.selectByUserId(userId);
	}

	@Override
	public List<DailyRecord> selectSomeByUserId(String userId, int line) {
		List<DailyRecord> result = recordDao.selectByUserId(userId);
		if(result.size()>line) {
			return result.subList(0,line);
		}else {
			return result;
		}
	}

	@Override
	public List<Video> recommendPart(String userId) {
		List<DailyRecord> record = recordDao.selectByUserId(userId);
		
		int p = 0; //지금까지 한 운동 저장
		for(int i=0; i<Math.min(record.size(), 3); i++) { //최근 3일 검색
			int r = record.get(i).getPart();
			for(int j=4; j>0; j--) { 
				if(r/(int)Math.pow(2,j)!=0){
		            System.out.println(j);
		            r = r - (int)Math.pow(2,j);
			        p = p|(1<<j);
		        }
			}
			
		}
		System.out.println("p");
		System.out.println(p);
		
		List<Video> result = new ArrayList<>();
		int n = 0;
		for(int i=1; i<=4; i++) {
			if(((1<<i)&p)==0) {
				n +=Math.pow(2, i); //운동 안한 부위 number
				result.addAll(videoDao.selectByPart(i));
			}
		}
		
		PartNumber(n);
		
		if(result.size()==0) return videoDao.selectByPart(4); //전신 추천
		else return result;
	}
	
	public void PartNumber(int p) {
		this.partStr = "";
		System.out.println(p);
		String[] arr = {"","상체","하체","복근","전신"};
		
		if( p==0 || p==16 ) {
			this.partStr="전신, "; //이미 골고루 다 함 
			return;
		}
		
		for(int i=4; i>0; i--) { 
			if(p/(int)Math.pow(2,i)!=0){
	            System.out.println(i);
	            p = p - (int)Math.pow(2,i);
	            if(i==4) continue; //전신은 세부 부위 추천에서 뺀다 왜냐면 그냥 
	            this.partStr = this.partStr+arr[i]+", ";
	        }
		}
		
		System.out.println(this.partStr);
		
	}
	
	@Override
	public List<String> monthlyRankers(){
		List<String> userIds = recordDao.selectUserByTime();
		//System.out.println(userIds);
		if(userIds.size()>5) userIds=userIds.subList(0,5); //ranker 5명 이하만
		return userIds;
	}
	
	@Override
	public HashMap<String, int[]> monthlyRank(List<User> rankers) {
		HashMap<String, int[]> result = new HashMap<String, int[]>();
		for(int i=0; i<rankers.size(); i++) { //랭킹 5명
			int[] tmp = new int[2];
			tmp[0]=i+1; //랭킹
			tmp[1]=recordDao.selectMonthlySum(rankers.get(i).getUserId());
			result.put(rankers.get(i).getUserId(), tmp);
		}
		return result;
	}
	
	@Override
	public void addRecord(DailyRecord dailyRecord) {
		recordDao.insertRecord(dailyRecord);
	}

	@Override
	public void editRecord(DailyRecord dailyRecord) {
		recordDao.updateRecord(dailyRecord);
	}

	@Override
	public void deleteRecord(int recordId) {
		recordDao.deleteRecord(recordId);
	}
	


}
