package com.ite5pjtbackoffice.backoffice.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.client.WebClient;

import com.ite5pjtbackoffice.backoffice.dto.Auth;
import com.ite5pjtbackoffice.backoffice.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@RequestMapping("/loginform")
	public String loginform() {
		return "common/login";
	}
	/*
	@PostMapping("/login")
	public String login(Member member, HttpSession session) {
		
		WebClient webClient = WebClient.create();		
		Auth auth = webClient
			.post()
			.uri("http://kosa1.iptime.org:50515/login")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(member)
			.retrieve()
			.bodyToMono(Auth.class)
			.block();
		//로그인이 성공하지 않으면 JWTToken이 return 되지 않는다.
		if(auth == null) {
			return "common/login";
		}
		session.setAttribute("auth", auth);
		return "redirect:/admin";
	}
	*/
	@RequestMapping("/admin")
	public String dashbord() {
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




