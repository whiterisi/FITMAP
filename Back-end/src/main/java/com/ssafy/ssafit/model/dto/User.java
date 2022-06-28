package com.ssafy.ssafit.model.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

	private String userId;
	private String pw;
	private String nickName;
	private String name;
	private String email;
	
}
