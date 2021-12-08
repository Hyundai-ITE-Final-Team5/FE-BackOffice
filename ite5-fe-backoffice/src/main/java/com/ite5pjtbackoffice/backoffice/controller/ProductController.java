package com.ite5pjtbackoffice.backoffice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.dto.ProductListFilter;
import com.ite5pjtbackoffice.backoffice.service.ProductService;
import com.ite5pjtbackoffice.backoffice.vo.Brand;
import com.ite5pjtbackoffice.backoffice.vo.ProductCommon;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/product")
public class ProductController {
	@RequestMapping("/registration")
	public String registration() {
		return "product/registration";
	}	

	@RequestMapping("/list")
	public String list() {
		return "product/list";
	}

	@RequestMapping("/category")
	public String category() {
		return "product/category";
	}	

	@RequestMapping("/display")
	public String display() {
		return "product/display";
	}
}
