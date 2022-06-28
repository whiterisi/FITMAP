package com.ssafy.ssafit.model.dao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.ssafy.ssafit.model.dto.Video;

@Repository
public interface VideoDao {
	
	List<Video> selectByPart(int part);
	void insertVideo(Video video);
	Video selectById(String videoId);
	List<Video> selectByKeyword(String keyword);
}

