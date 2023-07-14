package com.green.nowon.controller;

import java.util.Optional;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.green.nowon.domain.dto.MemberSaveDTO;
import com.green.nowon.domain.dto.ReservationSaveDTO;
import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.repository.MemberEntityRepository;
import com.green.nowon.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Controller
public class MemberController {
	
	private final MemberService service;
	
	private final MemberEntityRepository repository;
	
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
	
	@ResponseBody
	@PostMapping("/sign/email-check")
	public String emailCheck(String email) {
		
		//select * from member where email="?"
		Optional<MemberEntity> result = repository.findByEmail(email);
		
		//입력한 이메일 정보로 DB 조회했더니 존재하지 않음: 사용 가능한 이메일
		if(result.isEmpty()) {
			return "사용 가능한 이메일입니다.";
		}

		//그렇지 않으면 사용 불가능한 이메일
		return "사용 불가능한 이메일입니다.";
	}
	
	//@GetMapping("/reservations")
	public String reservationList(Model model) { 
		service.findAll(model); 
		return "reservation/list"; 
	}
	 
	
	@GetMapping("/reservations")
	public String myReservationList(Model model) {
		service.findMember(model);
		
		return "reservation/list";
	}
	
	@PostMapping("/reservations")
	public String reservationSave(ReservationSaveDTO dto) {
		service.reservationProcess(dto);
		return "redirect:/reservations";
	}

}
