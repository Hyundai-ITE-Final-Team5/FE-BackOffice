package com.ite5pjtbackoffice.backoffice.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.ite5pjtbackoffice.backoffice.dto.Customer;
import com.ite5pjtbackoffice.backoffice.dto.CustomerSearchOption;
import com.ite5pjtbackoffice.backoffice.dto.Pager;
import com.ite5pjtbackoffice.backoffice.service.CustomerService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/customer")
public class CustomerController {
	
	@Resource
	private CustomerService customerService;
	
	@RequestMapping("/management")
	public String management() {	
		return "customer/management";
	}
	
	@RequestMapping("/customerdetail")
	public String detail(String mid, Model model) {
		Customer customer = customerService.getCustomerInfo(mid);
		model.addAttribute("customer", customer);
		return "customer/customerdetail";
	}
	
	
	@PostMapping("/customerupdate")
	public String update(Customer customer,  RedirectAttributes redirectAttributes) {
		String result = customerService.updateCustomerInfo(customer);
		redirectAttributes.addAttribute("mid", customer.getMid());
		return "redirect:/admin/customer/customerdetail";
	}
	
	
	// 계정 활성화 - html에서 ajax로 호출함
	@PutMapping("/enable")
	@ResponseBody
	public Map<String, Object> enable(String mid) {
		String result = customerService.updateCustomerEnable(mid);
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("result", result);
		return map;
	}
	
}
