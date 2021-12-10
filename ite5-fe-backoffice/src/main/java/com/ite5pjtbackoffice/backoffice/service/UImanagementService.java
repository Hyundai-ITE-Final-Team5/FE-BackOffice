package com.ite5pjtbackoffice.backoffice.service;

import java.util.List;
import java.util.stream.Collectors;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ite5pjtbackoffice.backoffice.dto.HomeOrderDto;
import com.ite5pjtbackoffice.backoffice.vo.HomeOrder;

import reactor.core.publisher.Flux;

@Service
public class UImanagementService {
	public String changeOrder(HomeOrderDto homeOrderDto) {
		WebClient webClient = WebClient.create();
		String json = webClient
				.put()
				.uri("http://localhost:83/admin/uimanagement/changeorder")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(homeOrderDto)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		JSONObject jsonObject = new JSONObject(json);
		String result = jsonObject.getString("result");
		return result;
	}
	
	public List<HomeOrder> getHomeOrder() {
		WebClient webClient = WebClient.create();
		Flux<HomeOrder> homeOrderFlux = webClient
				.post()
				.uri("http://localhost:83/admin/uimanagement/gethomeorder")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.retrieve()
				.bodyToFlux(HomeOrder.class);
		List<HomeOrder> homeOrders = homeOrderFlux
				.collect(Collectors.toList())
				.share().block();
		return homeOrders;
	}
}
