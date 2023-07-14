package com.green.nowon.service.impl;

import java.util.stream.Collectors;

import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.PromotionListDTO;
import com.green.nowon.domain.entity.PromotionEntity;
import com.green.nowon.domain.mapper.AdminMapper;
import com.green.nowon.service.AdminService;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class AdminServiceProcess implements AdminService {
	
	private final AdminMapper mapper;
	
	@Override
	public void findAll(Model model) {
		//model.addAttribute("list", mapper.findAll());
		model.addAttribute("list", mapper.findAllJoinImage().stream()
				.map(PromotionListDTO::new)
				.collect(Collectors.toList())
				);
	}

	@Override
	public void detailProcess(long pno, Model model) {
		model.addAttribute("detail", mapper.findByPno(pno));
	}

	@Override
	public void update(@Param("pno") long pno, @Param("PromotionEntity") PromotionEntity dto) {
		mapper.update(pno, dto);
	}

	@Override
	public void delete(long pno) {
		mapper.delete(pno);
	}

}
