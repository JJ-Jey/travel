package com.green.nowon.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import com.green.nowon.domain.dto.MemberSaveDTO;
import com.green.nowon.domain.dto.ReservationSaveDTO;
import com.green.nowon.domain.entity.MemberEntity;
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
	public String login(HttpServletRequest request) {
		String uri = request.getHeader("Referer");
	    if (uri != null && !uri.contains("/sign")) {
	        request.getSession().setAttribute("prevPage", uri);
	    }
		return "member/login";
	}
	
	@PostMapping("/sign/signup")
	public String signup(MemberSaveDTO dto) {
		service.saveProcess(dto);
		return "redirect:/sign/signin";
	}
	
	//@GetMapping("/reservations")
	/*
	 * public String reservationList(Model model) { service.findAll(model); return
	 * "reservation/list"; }
	 */
	
	@GetMapping("/reservations")
	public String myReservationList(Model model, HttpSession session) {
		
		System.out.println(session);
		System.out.println(session.getId());
		MemberEntity member = (MemberEntity) session.getAttribute("loginUser");
		System.out.println(member);
		//String memberId = member.getEmail();
		//System.out.println(memberId);
		
		
		
		//service.findMember(sessionId);
		
		return "reservation/list";
	}
	
	@PostMapping("/reservations")
	public String reservationSave(ReservationSaveDTO dto) {
		service.reservationProcess(dto);
		return "redirect:/";
	}

}
