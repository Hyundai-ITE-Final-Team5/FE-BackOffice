package com.ite5pjtbackoffice.backoffice.service;

import org.json.JSONObject;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ite5pjtbackoffice.backoffice.dto.BrandAndDepth1;
import com.ite5pjtbackoffice.backoffice.dto.PagerAndProductList;
import com.ite5pjtbackoffice.backoffice.dto.ProductDetail;
import com.ite5pjtbackoffice.backoffice.dto.ProductListFilter;

import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class ProductService {
	
	public JSONObject addProduct(String productCommon){
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
	
	public PagerAndProductList getProductCommonList(ProductListFilter filter){
		WebClient webClient = WebClient.create();		
		PagerAndProductList productList = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/product/productlist")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(filter)
			.retrieve()
			.bodyToMono(PagerAndProductList.class)
			.block();
		
		return productList;
	}
	
	public ProductDetail getProductDetail(String pid) {
		WebClient webClient = WebClient.create();		
		ProductDetail productCommon = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/product/" + pid)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(ProductDetail.class)
			.block();
		
		
		return productCommon;
	}
	
	public JSONObject modifyProductInfo(String productCommon) {
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
//			.uri("http://kosa1.iptime.org:50515/admin/product/modifiy")
			.uri("http://localhost:83/admin/product/modifiy")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(productCommon)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
	
	public BrandAndDepth1 getClassification() {
		WebClient webClient = WebClient.create();		
		BrandAndDepth1 brandAndDepth1 = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/product/classification")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(BrandAndDepth1.class)
			.block();
		
		return brandAndDepth1;
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
	
	public JSONObject addCategory(String depth1, String depth2, String depth3) {
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/product/addcategory?depth1="+depth1+"&depth2="+depth2+"&depth3="+depth3 )
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
	
	public JSONObject removeCategory(String depth1, String depth2, String depth3) {
		WebClient webClient = WebClient.create();		
		String json = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/admin/product/removecategory?depth1="+depth1+"&depth2="+depth2+"&depth3="+depth3)
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.retrieve()
			.bodyToMono(String.class)
			.block();
		
		JSONObject jsonObject = new JSONObject(json);
		
		return jsonObject;
	}
}
