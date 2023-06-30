package com.green.nowon.service.impl;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.green.nowon.domain.dto.MemberSaveDTO;
import com.green.nowon.domain.mapper.MemberMapper;
import com.green.nowon.service.MemberService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemberServiceProcess implements MemberService {
	
	private final MemberMapper memberMapper;
	
	private final PasswordEncoder encoder;

	@Override
	public void saveProcess(MemberSaveDTO dto) {
		memberMapper.save(dto.toEntity(encoder));
	}
	
}
