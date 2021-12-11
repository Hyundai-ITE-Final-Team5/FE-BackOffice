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
	public String changeOrderImg(HomeOrderDto homeOrderDto) {
		
		if(homeOrderDto.getBestnewimg().equals("")) {
			homeOrderDto.setBestnewimg(null);
		}
		if(homeOrderDto.getGoshopeventimg().equals("")) {
			homeOrderDto.setGoshopeventimg(null);
		}
		if(homeOrderDto.getMembershipimg().equals("")) {
			homeOrderDto.setMembershipimg(null);
		}
		
		WebClient webClient = WebClient.create();
		String json = webClient
				.put()
				.uri("http://kosa1.iptime.org:50515/admin/uimanagement/changeorderimg")
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
				.uri("http://kosa1.iptime.org:50515/admin/uimanagement/gethomeorderimg")
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
