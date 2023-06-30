package com.green.nowon.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import com.green.nowon.domain.dto.MemberSaveDTO;
import com.green.nowon.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService service;
	
	@GetMapping("/sign/signup")
	public String joinin() {
		return "member/joinin";
	}
	
	@GetMapping("/sign/signin")
	public String login() {
		return "member/login";
	}
	
	@PostMapping("/sign/signup")
	public String signup(MemberSaveDTO dto) {
		service.saveProcess(dto);
		return "redirect:/sign/signin";
	}

}
