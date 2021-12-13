package com.ite5pjtbackoffice.backoffice.service;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ite5pjtbackoffice.backoffice.dto.CategoryStatistics;
import com.ite5pjtbackoffice.backoffice.dto.DailyTotalPrice;
import com.ite5pjtbackoffice.backoffice.dto.MonthlyTotalPrice;
import com.ite5pjtbackoffice.backoffice.dto.OrderDetail;
import com.ite5pjtbackoffice.backoffice.dto.OrderListFilter;
import com.ite5pjtbackoffice.backoffice.dto.PagerAndOrderList;
import com.ite5pjtbackoffice.backoffice.dto.TimeStatistics;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class OrderService {
	
	public CategoryStatistics getCategorystatistics() {
	WebClient webClient = WebClient.create();		
	CategoryStatistics categoryStatistics = webClient
		.get()
		.uri("http://kosa1.iptime.org:50515/admin/categorystatistics")
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.bodyToMono(CategoryStatistics.class)
		.block();
	
		return categoryStatistics;
	}
	
	public TimeStatistics getTimeStatistics() {
	WebClient webClient = WebClient.create();		
	TimeStatistics timeStatistics = webClient
		.get()
		.uri("http://kosa1.iptime.org:50515/admin/timestatistics")
		.accept(MediaType.APPLICATION_JSON)
		.retrieve()
		.bodyToMono(TimeStatistics.class)
		.block();
	
		return timeStatistics;
	}
	
	public DailyTotalPrice getDailyTotalPrice() {
		WebClient webClient = WebClient.create();		
		DailyTotalPrice dailyTotalPrice = webClient
			.get()
			.uri("http://kosa1.iptime.org:50515/admin/dailystatistics")
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(DailyTotalPrice.class)
			.block();
		
		return dailyTotalPrice;
	}
	
	public MonthlyTotalPrice getMonthlyTotalPrice() {
		WebClient webClient = WebClient.create();		
		MonthlyTotalPrice monthlyTotalPrice = webClient
			.get()
			.uri("http://kosa1.iptime.org:50515/admin/monthlystatistics")
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(MonthlyTotalPrice.class)
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
	
	public OrderDetail getOrderDetail(String oid) {
		WebClient webClient = WebClient.create();		
		OrderDetail orderDetail = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/order/list/"+oid)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(OrderDetail.class)
			.block();
		
		return orderDetail;
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
