package com.ite5pjtbackoffice.backoffice.controller;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ite5pjtbackoffice.backoffice.dto.BrandAndDepth1;
import com.ite5pjtbackoffice.backoffice.dto.OrderDetail;
import com.ite5pjtbackoffice.backoffice.dto.OrderListFilter;
import com.ite5pjtbackoffice.backoffice.dto.PagerAndOrderList;
import com.ite5pjtbackoffice.backoffice.service.OrderService;
import com.ite5pjtbackoffice.backoffice.vo.Orders;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/order")
public class OrderController {
	@RequestMapping("/orderlist")
	public String management() {
		return "order/orderlist";
	}
	
	@Resource
	private OrderService orderService;
	
	@PostMapping("/orderlist")
	public String orderList(Model model, OrderListFilter filter) {

		log.info(filter.toString());
		
//		if(filter.getOid().equals("")) filter.setOid(null);
//		if(filter.getOphone().equals("")) filter.setOphone(null);
		if(filter.getOstatus().equals("")) filter.setOstatus(null);
		if(filter.getMname().equals("")) filter.setMname(null);
//		if(filter.getMid().equals("")) filter.setMid(null);
		if(filter.getStartdate().equals("")) filter.setStartdate(null);
		if(filter.getEnddate().equals("")) filter.setEnddate(null);
		if(filter.getPsid().equals("")) filter.setPsid(null);
		
		log.info(filter.toString());
		PagerAndOrderList pao = orderService.getOrderList(filter);

		model.addAttribute("orderList", pao.getOrderList());
		model.addAttribute("pager", pao.getPager());
		log.info(pao.getPager().toString());

		return "order/orderlist";
	}
	
	@RequestMapping("/detail")
	public String orderList(Model model, String oid) {
		
		OrderDetail order = orderService.getOrderDetail(oid);

		model.addAttribute("order", order.getOrders());
		model.addAttribute("orderItems", order.getOrders().getOrderitems());
		
		return "order/orderdetail";
	}
	
	@PostMapping("/updatestatus")
	public String updateStatus(Model model, @RequestBody JSONObject orderStatus) {
		
		JSONObject data = orderService.updateOrderStatus(orderStatus);

		model.addAttribute("result", data.get("result"));
		
		return "order/orderlist";
	}
}
