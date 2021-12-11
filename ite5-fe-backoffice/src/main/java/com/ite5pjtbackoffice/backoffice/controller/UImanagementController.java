package com.ite5pjtbackoffice.backoffice.controller;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ite5pjtbackoffice.backoffice.dto.HomeOrderDto;
import com.ite5pjtbackoffice.backoffice.service.UImanagementService;
import com.ite5pjtbackoffice.backoffice.vo.HomeOrder;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
@RequestMapping("/admin/uimanagement")
public class UImanagementController {
	
	@Resource
	private UImanagementService uimanagementService;
	
	@RequestMapping("/home")
	public String home(Model model) {
		List<HomeOrder> homeOrderList = uimanagementService.getHomeOrder();
		model.addAttribute("homeorderlist",homeOrderList);
		return "uimanagement/home";
	}
	
	@PostMapping("/changeorderimg")
	public String changeOrderImg(HomeOrderDto homeOrderDto) {
		uimanagementService.changeOrderImg(homeOrderDto);
		return "redirect:/admin/uimanagement/home";
	}
}
