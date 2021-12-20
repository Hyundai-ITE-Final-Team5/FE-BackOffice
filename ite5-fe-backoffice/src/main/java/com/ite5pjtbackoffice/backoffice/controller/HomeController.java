package com.ite5pjtbackoffice.backoffice.controller;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ite5pjtbackoffice.backoffice.dto.CategoryStatistics;
import com.ite5pjtbackoffice.backoffice.dto.DailyTotalPrice;
import com.ite5pjtbackoffice.backoffice.dto.MonthlyTotalPrice;
import com.ite5pjtbackoffice.backoffice.dto.Statistics;
import com.ite5pjtbackoffice.backoffice.dto.TimeStatistics;
import com.ite5pjtbackoffice.backoffice.service.OrderService;
import com.ite5pjtbackoffice.backoffice.service.StatisticsService;

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
	@Resource
	private StatisticsService statisticsService; 

	@RequestMapping("/admin")
	public String dashbord(Model model) {
		
		DailyTotalPrice dailyTotalPrice = orderService.getDailyTotalPrice();
		MonthlyTotalPrice monthlyTotalPrice = orderService.getMonthlyTotalPrice();
		TimeStatistics timeStatistics = orderService.getTimeStatistics();
		CategoryStatistics categoryStatistics = orderService.getCategorystatistics();
		
		int todayVisitCount = statisticsService.getTodayCount();
		
		if(dailyTotalPrice.getCancelTodatStatistics() == null) {
			dailyTotalPrice.setCancelTodatStatistics(new Statistics());
		}
		if(dailyTotalPrice.getTodayStatistics() == null) {
			dailyTotalPrice.setTodayStatistics(new Statistics());
		}
		int maxCateCount = 0;
			
		model.addAttribute("dailyTotalPrice", dailyTotalPrice);
		model.addAttribute("monthlyTotalPrice", monthlyTotalPrice);
		model.addAttribute("todayVisitCount", todayVisitCount);
		for(int i=0; i<5; i++) {
			model.addAttribute("otime"+i, timeStatistics.getTimeStatistics().get(i));
			int cateCount = categoryStatistics.getCategoryStatistics().get(i).getOcount();
			if(maxCateCount < cateCount) {
				maxCateCount = cateCount;
			}
			model.addAttribute("ocate"+i, categoryStatistics.getCategoryStatistics().get(i));
		}
		
		model.addAttribute("maxocate", maxCateCount);
		
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




