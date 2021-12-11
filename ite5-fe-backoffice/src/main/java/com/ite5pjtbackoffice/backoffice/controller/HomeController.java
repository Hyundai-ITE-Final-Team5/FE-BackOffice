package com.ite5pjtbackoffice.backoffice.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ite5pjtbackoffice.backoffice.dto.StatisticsList;
import com.ite5pjtbackoffice.backoffice.service.OrderService;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@RequestMapping("/loginform")
	public String loginform() {
		return "common/login";
	}
	
	@Resource
	private OrderService orderService;
	
	@RequestMapping("/admin")
	public String dashbord(Model model) {
		
		StatisticsList dailyTotalPrice = orderService.getDailyTotalPrice();
		StatisticsList monthlyTotalPrice = orderService.getMonthlyTotalPrice();
		
		log.info(dailyTotalPrice.toString());
		log.info(monthlyTotalPrice.toString());
		
		model.addAttribute("dailyTotalPrice", dailyTotalPrice.getStatisticsList());
		model.addAttribute("monthlyTotalPrice", monthlyTotalPrice.getStatisticsList());
		
		return "home";
	}
	
	@RequestMapping("/")
	public String home() {
		return "redirect:/admin";
	}
	
	@RequestMapping("/loginError")
	public String loginError(Model model) {
		model.addAttribute("loginError",true);
		return "common/login";
	}
	
	@RequestMapping("/accessDenied")
	public String accessDenied(Model model) {
		return "common/accessdenied";
	}
}




