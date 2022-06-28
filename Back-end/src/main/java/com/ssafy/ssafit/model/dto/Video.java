package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Video {
	private String videoId;
	private String title;
	private int part;
	private String url;
	private String channel;
	
}
