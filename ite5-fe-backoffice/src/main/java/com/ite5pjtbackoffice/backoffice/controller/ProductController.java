package com.ite5pjtbackoffice.backoffice.controller;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ite5pjtbackoffice.backoffice.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/product")
public class ProductController {

	@Resource
	private ProductService productService;

	@RequestMapping("/registration")
	public String registration() {
		return "product/registration";
	}
	
	@PostMapping("/registration")
	public String registration(Model model, @RequestBody JSONObject productCommon){
		
		JSONObject data = productService.addProduct(productCommon);
		
		model.addAttribute("result", data.get("result"));
		
		return "product/registration";
	}
	
	@RequestMapping("/productDetail")
	public String productDetail(Model model, String pname) {

		JSONObject data = productService.getProductCommonByPname(pname);

		model.addAttribute("productCommon", data.get("productCommon"));

		return "product/registration";
	}

	@PostMapping("/list")
	public String list(Model model, @RequestBody JSONObject filter) {

		JSONObject data = productService.getProductCommonList(filter);

		model.addAttribute("productList", data.get("productList"));
		model.addAttribute("pager", data.get("pager"));

		return "product/list";
	}

	@RequestMapping("/productDetail")
	public String productDetail(Model model, int pid) {

		JSONObject data = productService.getProductDetail(pid);

		model.addAttribute("productCommon", data.get("productCommon"));

		return "product/list";
	}

	@PostMapping("/modifiy")
	public String productModify(Model model, @RequestBody JSONObject productCommon) {

		JSONObject data = productService.modifyProductInfo(productCommon);

		model.addAttribute("result", data.get("result"));

		return "product/list";
	}

	// 상품 분류관리(브랜드, 카테고리)
	@RequestMapping("/category")
	public String category(Model model) {

		JSONObject data = productService.getClassification();

		model.addAttribute("depth1", data.get("depth1"));
		model.addAttribute("brand", data.get("brand"));

		return "product/category";
	}

	@RequestMapping("/getcategorydepth2")
	public String getCategoryDepth2(Model model, String depth1) {

		JSONObject data = productService.getCategoryDepth2(depth1);
		log.info(data.toString());
		model.addAttribute("depth2", data.get("depth2"));

		return "product/category";
	}

	@RequestMapping("/getcategorydepth3")
	public String getCategoryDepth3(Model model, String depth1, String depth2) {

		JSONObject data = productService.getCategoryDepth3(depth1, depth2);

		model.addAttribute("depth3", data.get("depth3"));

		return "product/category";
	}

	@RequestMapping("/addBrand")
	public String addBrand(Model model, String brandName) {

		JSONObject data = productService.addBrandName(brandName);

		model.addAttribute("result", data.get("result"));

		return "product/category";
	}

	@RequestMapping("/removeBrand")
	public String removeBrand(Model model, int bno) {

		JSONObject data = productService.removeBrandName(bno);

		model.addAttribute("result", data.get("result"));

		return "product/category";
	}

	// 상품진열관리
	@RequestMapping("/display")
	public String display() {
		return "product/display";
	}

}
