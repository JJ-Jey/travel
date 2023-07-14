package com.green.nowon.service.impl;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.MemberSaveDTO;
import com.green.nowon.domain.dto.ReservationSaveDTO;
import com.green.nowon.domain.mapper.MemberMapper;
import com.green.nowon.domain.mapper.ReservationMapper;
import com.green.nowon.domain.repository.MemberEntityRepository;
import com.green.nowon.domain.repository.ReservaionRepository;
import com.green.nowon.security.MyRole;
import com.green.nowon.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceProcess implements MemberService {
	
	private final MemberEntityRepository memberRepo;
	
	private final MemberMapper memberMapper;
	
	private final PasswordEncoder encoder;
	
	private final ReservationMapper resMapper;
	
	private final ReservaionRepository resRepo;

	@Override
	public void saveProcess(MemberSaveDTO dto) {
		memberRepo.save(dto.toEntity(encoder).addRole(MyRole.USER));
	}

	@Override
	public void reservationProcess(ReservationSaveDTO dto) {
		
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetail = (UserDetails) principal; 
		String email = userDetail.getUsername(); 
		long no = memberRepo.findByEmail(email).orElseThrow().getNo();
		 System.out.println(no);
		resMapper.reservation(dto);
	}

	@Override
	public void findAll(Model model) {
		
		model.addAttribute("list", resMapper.findAll());
	}

	@Override
	public void findMember(Model model) {
		Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
		UserDetails userDetail = (UserDetails) principal; 
		String email = userDetail.getUsername(); 
		long mno = memberRepo.findByEmail(email).orElseThrow().getNo();
		
		model.addAttribute("list", resMapper.findByMno(mno));
		
	}
	
}
