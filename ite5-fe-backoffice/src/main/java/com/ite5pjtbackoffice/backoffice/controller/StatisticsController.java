package com.ite5pjtbackoffice.backoffice.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ite5pjtbackoffice.backoffice.dto.DailyTotalPrice;
import com.ite5pjtbackoffice.backoffice.dto.MonthlyTotalPrice;
import com.ite5pjtbackoffice.backoffice.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/statistics")
public class StatisticsController {
	
	@Resource
	private OrderService orderService;
	
	@RequestMapping("/product")
	public String product(Model model) {
		
		DailyTotalPrice dailyTotalPrice = orderService.getDailyTotalPrice();
		MonthlyTotalPrice monthlyTotalPrice = orderService.getMonthlyTotalPrice();
		
		model.addAttribute("dailyTotalPrice", dailyTotalPrice);
		model.addAttribute("monthlyTotalPrice", monthlyTotalPrice);
		
		return "statistics/product";
	}	
	
	@RequestMapping("/sales")
	public String order() {
		return "statistics/sales";
	}
}
