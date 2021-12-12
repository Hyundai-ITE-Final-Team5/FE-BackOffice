package com.ite5pjtbackoffice.backoffice.service;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

@Service
public class StatisticsService {
	public int getTodayCount() {
		WebClient webClient = WebClient.create();		
		String json = webClient
			.get()
			.uri("http://kosa1.iptime.org:50515/admin/todayCount")
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		JSONObject jsonObject = new JSONObject(json);
		int result = jsonObject.getInt("todayVisitCount");
		return result;
	}
}
