package com.ite5pjtbackoffice.backoffice.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ite5pjtbackoffice.backoffice.dto.EventSearchOption;
import com.ite5pjtbackoffice.backoffice.dto.PagerAndEvents;

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
}
