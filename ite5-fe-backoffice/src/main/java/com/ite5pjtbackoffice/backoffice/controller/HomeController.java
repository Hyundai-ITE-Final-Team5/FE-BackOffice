package com.ite5pjtbackoffice.backoffice.controller;

import javax.servlet.http.HttpSession;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;

import com.ite5pjtbackoffice.backoffice.dto.Auth;
import com.ite5pjtbackoffice.backoffice.vo.Member;

import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class HomeController {
	
	@RequestMapping("/admin")
	public String dashbord() {
		log.info("실행");
		return "home";
	}	
	
	@GetMapping("/login")
	public String loginform() {
		return "common/login";
	}
	
	@PostMapping("/login")
	public String login(Member member, Model model, HttpSession session) {
		
		WebClient webClient = WebClient.create();		
		Auth auth = webClient
			.post()
			.uri("http://localhost:8080/login")
			.contentType(MediaType.APPLICATION_JSON)
			.accept(MediaType.APPLICATION_JSON)
			.bodyValue(member)
			.retrieve()
			.bodyToMono(Auth.class)
			.block();
		if(auth != null) {
			log.info(auth.toString());
		} else {
			log.info("null이다 null");
		}
		session.setAttribute("auth", auth);
		model.addAttribute("auth", auth);
	
		return "home";
	}
}




