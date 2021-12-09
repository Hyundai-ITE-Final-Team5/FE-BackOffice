package com.ite5pjtbackoffice.backoffice.controller;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ite5pjtbackoffice.backoffice.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/order")
public class OrderController {
	@RequestMapping("/management")
	public String management() {
		return "order/management";
	}
	
	@Resource
	private OrderService orderService;

	@PostMapping("/list")
	public String orderList(Model model, @RequestBody JSONObject filter) {

		JSONObject data = orderService.getOrderList(filter);

		model.addAttribute("orderList", data.get("orderList"));
		model.addAttribute("pager", data.get("pager"));

		return "order/management";
	}
	
	@RequestMapping("/detail")
	public String orderList(Model model, String oid) {
		
		JSONObject data = orderService.getOrderDetail(oid);

		model.addAttribute("order", data.get("order"));
		
		return "order/management";
	}
	
	@PostMapping("/updatestatus")
	public String updateStatus(Model model, @RequestBody JSONObject orderStatus) {
		
		JSONObject data = orderService.updateOrderStatus(orderStatus);

		model.addAttribute("result", data.get("result"));
		
		return "order/management";
	}
}
