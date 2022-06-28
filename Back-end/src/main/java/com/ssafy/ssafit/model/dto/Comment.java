package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class Comment {

	private int commentId;
	private String userId;
	private String videoId;
	private String parentId;
	private String date;
	private String content;
	
}
