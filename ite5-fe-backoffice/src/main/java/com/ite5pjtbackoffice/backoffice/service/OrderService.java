package com.ite5pjtbackoffice.backoffice.service;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ite5pjtbackoffice.backoffice.dto.OrderListFilter;
import com.ite5pjtbackoffice.backoffice.dto.PagerAndOrderList;
import com.ite5pjtbackoffice.backoffice.dto.Statistics;
import com.ite5pjtbackoffice.backoffice.dto.StatisticsList;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	
	public StatisticsList getDailyTotalPrice() {
		WebClient webClient = WebClient.create();		
		StatisticsList dailyTotalPrice = webClient
			.get()
			.uri("http://kosa1.iptime.org:50515/admin/dailystatistics")
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(StatisticsList.class)
			.block();
		
		return dailyTotalPrice;
	}
	
	public StatisticsList getMonthlyTotalPrice() {
		WebClient webClient = WebClient.create();		
		StatisticsList monthlyTotalPrice = webClient
			.get()
			.uri("http://kosa1.iptime.org:50515/admin/monthlystatistics")
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(StatisticsList.class)
			.block();

		return monthlyTotalPrice;
	}
	
	public PagerAndOrderList getOrderList(OrderListFilter filter) {
		WebClient webClient = WebClient.create();		
		PagerAndOrderList pao = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/order/list")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(filter)
			.retrieve()
			.bodyToMono(PagerAndOrderList.class)
			.block();
		
		return pao;
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
