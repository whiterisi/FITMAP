package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DailyRecord {

	private int recordId;
	private String userId;
	private String date;
	private int part;
	private int minutes;
	private String comment;
	 
}
