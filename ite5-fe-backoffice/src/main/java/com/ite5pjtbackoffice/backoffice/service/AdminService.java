package com.ite5pjtbackoffice.backoffice.service;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.ite5pjtbackoffice.backoffice.vo.Member;

@Service
public class AdminService {
	public Member getAdminInfo(String mid) {
		WebClient webClient = WebClient.create();
		Member member = webClient
				.post()
				.uri("http://kosa1.iptime.org:50515/admin/logincheck")
				.contentType(MediaType.APPLICATION_JSON)
				.accept(MediaType.APPLICATION_JSON)
				.bodyValue(mid)
				.retrieve()
				.bodyToMono(Member.class)
				.block();
		return member;
	}
}
