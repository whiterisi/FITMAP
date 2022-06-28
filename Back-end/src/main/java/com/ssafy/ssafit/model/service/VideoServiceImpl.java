package com.ssafy.ssafit.model.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ssafy.ssafit.model.dao.VideoDao;
import com.ssafy.ssafit.model.dto.Video;

@Service("videoServide")
public class VideoServiceImpl implements VideoService {
	
	@Autowired
	private VideoDao videoDao;
	
	@Override
	public List<Video> selectByPart(int part) { //중복 파트 들어오는 거 허용
		List<Video> result = new ArrayList<Video>();
		for(int i=1; i<5; i++) { 
			if(((1<<(i)) & part) !=0 ) {
				System.out.println(i);
				result.addAll(videoDao.selectByPart(i));
			}
		}
		return result;
	}

	@Override
	public void addVideo(Video video) {
		videoDao.insertVideo(video);
	}

	@Override
	public void addVideos(List<Video> videos) {
		int n = videos.size();
		//System.out.println(n);
		for(int i=0; i<n; i++) {
			if(videoDao.selectById(videos.get(i).getVideoId())!=null) continue;
			videoDao.insertVideo(videos.get(i));
			//System.out.println(videos.get(i));
		}
	}
	@Override
	public Video selectByVideoId(String videoId) {
		return videoDao.selectById(videoId);
	}

	@Override
	public List<Video> selectByKeyword(String keyword) {
		return videoDao.selectByKeyword(keyword);
	}


}
