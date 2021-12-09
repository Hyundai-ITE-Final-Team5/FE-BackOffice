package com.ite5pjtbackoffice.backoffice.service;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ite5pjtbackoffice.backoffice.dto.Customer;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerService {
	
	public Customer getCustomerInfo(String mid) {
		WebClient webClient = WebClient.create();
		Customer customer = webClient
				.post()
				.uri("http://kosa1.iptime.org:50515/admin/customer/customerdetail")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(mid)
				.retrieve()
				.bodyToMono(Customer.class)
				.block();
		return customer;
	}
	
	
	public String updateCustomerInfo(Customer customer) {
		WebClient webClient = WebClient.create();
		String json = webClient
				.put()
				.uri("http://kosa1.iptime.org:50515/admin/customer/customerupdate")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(customer)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		JSONObject jsonObject = new JSONObject(json);
		String result = jsonObject.getString("result");
		return result;
	}
	
	
	public String updateCustomerEnable(String mid) {
		WebClient webClient = WebClient.create();
		String json = webClient
				.put()
				.uri("http://kosa1.iptime.org:50515/admin/customer/enable")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(mid)
				.retrieve()
				.bodyToMono(String.class)
				.block();
		JSONObject jsonObject = new JSONObject(json);
		String result = jsonObject.getString("result");
		return result;
	}
	
	
}
