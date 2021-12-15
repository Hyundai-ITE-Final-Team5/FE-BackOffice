package com.ite5pjtbackoffice.backoffice.controller;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ite5pjtbackoffice.backoffice.dto.BrandAndDepth1;
import com.ite5pjtbackoffice.backoffice.dto.PagerAndProductList;
import com.ite5pjtbackoffice.backoffice.dto.ProductListFilter;
import com.ite5pjtbackoffice.backoffice.service.ProductService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/product")
public class ProductController {

	@Resource
	private ProductService productService;

	@RequestMapping("/registration")
	public String registration(Model model) {
		
		BrandAndDepth1 data = productService.getClassification();

		model.addAttribute("depth1", data.getDepth1());
		
		return "product/registration";
	}

	@PostMapping("/registration")
	public String registration(Model model, String productCommon) {
		
		JSONObject temp = new JSONObject(productCommon);
		JSONObject data = productService.addProduct(temp);
		model.addAttribute("result", data.get("result"));

		return "product/registration";
	}

	@RequestMapping("/list")
	public String productlist(Model model, ProductListFilter filter) {

		BrandAndDepth1 data = productService.getClassification();
		
		log.info(filter.toString());
		
		if (filter.getDepth1name() != null && filter.getDepth1name().equals(""))
			filter.setDepth1name(null);
		if (filter.getDepth2name() != null && filter.getDepth2name().equals(""))
			filter.setDepth2name(null);
		if (filter.getDepth3name() != null && filter.getDepth3name().equals(""))
			filter.setDepth3name(null);
		if (filter.getStartdate() != null && filter.getStartdate().equals(""))
			filter.setStartdate(null);
		if (filter.getEnddate() != null && filter.getEnddate().equals(""))
			filter.setEnddate(null);
		if (filter.getPid() != null && filter.getPid().equals(""))
			filter.setPid(null);
		if (filter.getPname() != null && filter.getPname().equals(""))
			filter.setPname(null);
		
		log.info(filter.toString());
		
		model.addAttribute("depth1", data.getDepth1());


		PagerAndProductList pap = productService.getProductCommonList(filter);
		
		model.addAttribute("filter", filter);
		model.addAttribute("productList", pap.getProductList());
		model.addAttribute("pager", pap.getPager());

		return "product/list";
	}

	@RequestMapping("/productDetail")
	public String productDetail(Model model, int pid) {

		JSONObject data = productService.getProductDetail(pid);

		model.addAttribute("productCommon", data.get("productCommon"));

		return "product/detail";
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

		BrandAndDepth1 data = productService.getClassification();

		model.addAttribute("depth1", data.getDepth1());
		model.addAttribute("brand", data.getBrand());

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

		log.info(brandName);

		JSONObject data = productService.addBrandName(brandName);

		model.addAttribute("result", data.get("result"));

		BrandAndDepth1 bad = productService.getClassification();

		model.addAttribute("depth1", bad.getDepth1());
		model.addAttribute("brand", bad.getBrand());

		return "product/category";
	}

	@RequestMapping("/removeBrand")
	public String removeBrand(Model model, int bno) {

		log.info(bno + " ");

		JSONObject data = productService.removeBrandName(bno);

		model.addAttribute("result", data.get("result"));

		BrandAndDepth1 bad = productService.getClassification();

		model.addAttribute("depth1", bad.getDepth1());
		model.addAttribute("brand", bad.getBrand());

		return "product/category";
	}

	@RequestMapping("/addcategory")
	public String addCategory(Model model, String depth1, String depth2, String depth3) {

		JSONObject data = productService.addCategory(depth1, depth2, depth3);
		model.addAttribute("result", data.get("result"));

		return "product/category";
	}

	@RequestMapping("/removecategory")
	public String removeCategory(Model model, String depth1, String depth2, String depth3) {

		JSONObject data = productService.removeCategory(depth1, depth2, depth3);
		model.addAttribute("result", data.get("result"));

		return "product/category";
	}

	// 상품진열관리
	@RequestMapping("/display")
	public String display() {
		return "product/display";
	}

}
