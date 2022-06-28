package com.ssafy.ssafit.model.service;

import java.util.List;

import com.ssafy.ssafit.model.dto.Video;

public interface VideoService {
	
	List<Video> selectByPart(int part);
	void addVideo(Video video);
	void addVideos(List<Video> videos);
	Video selectByVideoId(String videoId);
	List<Video> selectByKeyword(String keyword);
}
