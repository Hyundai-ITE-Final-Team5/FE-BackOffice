package com.ite5pjtbackoffice.backoffice.service;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ite5pjtbackoffice.backoffice.dto.EventSearchOption;
import com.ite5pjtbackoffice.backoffice.dto.PagerAndEvents;
import com.ite5pjtbackoffice.backoffice.vo.Event;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class PromotionService {
	public PagerAndEvents getEventList(EventSearchOption eventSearchOption){
		WebClient webClient = WebClient.create();		
		PagerAndEvents pagerAndEvents = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/promotion/eventlist")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(eventSearchOption)
			.retrieve()
			.bodyToMono(PagerAndEvents.class)
			.block();
		return pagerAndEvents;
	}
	
	public Event getEvent(String eno) {
		WebClient webClient = WebClient.create();
		Event event = webClient
				.post()
				.uri("http://kosa1.iptime.org:50515/admin/promotion/eventdetail")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(eno)
				.retrieve()
				.bodyToMono(Event.class)
				.block();
		return event;
	}
	
	public String updateEvent(Event event) {
		WebClient webClient = WebClient.create();
		String json = webClient
				.put()
				.uri("http://kosa1.iptime.org:50515/admin/promotion/eventupdate")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(event)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		JSONObject jsonObject = new JSONObject(json);
		String result = jsonObject.getString("result");
		return result;
	}
	
	public String insertEvent(Event event) {
		WebClient webClient = WebClient.create();
		String json = webClient
				.post()
				.uri("http://kosa1.iptime.org:50515/admin/promotion/eventinsert")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(event)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		JSONObject jsonObject = new JSONObject(json);
		String result = jsonObject.getString("result");
		return result;
	}
	
	public String deleteEvent(Event event) {
		WebClient webClient = WebClient.create();
		String json = webClient
				.post()
				.uri("http://kosa1.iptime.org:50515/admin/promotion/eventdelete")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(event.getEno())
				.retrieve()
				.bodyToMono(String.class)
				.block();
		JSONObject jsonObject = new JSONObject(json);
		String result = jsonObject.getString("result");
		return result;		
	}
}
