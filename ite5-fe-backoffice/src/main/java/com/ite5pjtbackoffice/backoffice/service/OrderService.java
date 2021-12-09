package com.ite5pjtbackoffice.backoffice.service;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	
	public JSONObject getOrderList(JSONObject filter) {
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/order/list")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(filter)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
	
	public JSONObject getOrderDetail(String oid) {
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/order/list"+oid)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
	
	public JSONObject updateOrderStatus(JSONObject orderStatus) {
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/order/updatestatus")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(orderStatus)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
}
