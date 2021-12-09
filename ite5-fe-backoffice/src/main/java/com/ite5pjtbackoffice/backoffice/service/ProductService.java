package com.ite5pjtbackoffice.backoffice.service;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	
	public JSONObject addProduct(JSONObject productCommon){
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/product/registration")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(productCommon)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
	
	public JSONObject getProductCommonByPname(String pname){
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/product/duplicatesearch?pname="+pname)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(pname)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
	
	public JSONObject getProductCommonList(JSONObject filter){
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/product/productlist")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(filter)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
	
	public JSONObject getProductDetail(int pid) {
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/product/" + pid)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(pid)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
	
	public JSONObject modifyProductInfo(JSONObject productCommon) {
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/modifiy")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(productCommon)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
	
	public JSONObject getClassification() {
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/product/classification")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
	
	public JSONObject getCategoryDepth2(String depth1) {
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/product/getcategorydepth2?depth1="+depth1)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(String.class)
			.block();

		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
	
	public JSONObject getCategoryDepth3(String depth1, String depth2) {
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/product/getcategorydepth3?depth1="+depth1+"&depth2="+depth2)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
	
	public JSONObject addBrandName(String brandName) {
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/product/addBrand?brandName="+brandName)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
	
	public JSONObject removeBrandName(int bno) {
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/product/removeBrand?bno="+bno)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
}
