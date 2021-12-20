package com.ite5pjtbackoffice.backoffice.controller;

import javax.annotation.Resource;

import org.json.JSONObject;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
	
	@Resource
	private OrderService orderService;
	
	@RequestMapping("/orderlist")
	public String orderList(Model model, OrderListFilter filter) {

		log.info(filter.toString());
		
//		if(filter.getOid().equals("")) filter.setOid(null);
		if(filter.getOphone() != null && filter.getOphone().equals("")) filter.setOphone(null);
		if(filter.getOstatus() != null && filter.getOstatus().equals("")) filter.setOstatus(null);
		if(filter.getMname() != null && filter.getMname().equals("")) filter.setMname(null);
//		if(filter.getMid().equals("")) filter.setMid(null);
		if(filter.getStartdate() != null && filter.getStartdate().equals("")) filter.setStartdate(null);
		if(filter.getEnddate() != null && filter.getEnddate().equals("")) filter.setEnddate(null);
		if(filter.getPsid() != null && filter.getPsid().equals("")) filter.setPsid(null);
		
		log.info(filter.toString());
		PagerAndOrderList pao = orderService.getOrderList(filter);

		model.addAttribute("filter", filter);
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
		
		return "order/orderdetail";
	}
	
	@RequestMapping("/cancelorder")
	public String cancelOrder(Model model, String oid, RedirectAttributes redirectAttributes) {
		
		JSONObject data = orderService.cancelOrder(oid);
		redirectAttributes.addAttribute("oid", oid);
		
		return "redirect:/admin/order/detail";
	}
}
