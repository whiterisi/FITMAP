package com.ssafy.ssafit.config;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

@Component
public class LoginCheckExcludePath {
	List<String> excludePath = new ArrayList<String>();
	LoginCheckExcludePath() {
		excludePath.add("/api/login");
		excludePath.add("/api/join");
	}
}
