package com.green.nowon.service.impl;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.green.nowon.domain.dto.QuestionSaveDTO;
import com.green.nowon.domain.entity.QuestionEntity;
import com.green.nowon.domain.mapper.QuestionMapper;
import com.green.nowon.domain.repository.QuestionEntityRepository;
import com.green.nowon.service.QuestionService;
import com.green.nowon.util.PageData;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class QuestionServiceProcess implements QuestionService {

	private final QuestionMapper mapper;
	
	private final QuestionEntityRepository questRepo;
	
	@Override
	public void save(QuestionSaveDTO dto) {
		mapper.save(dto);
	}

	@Override
	public void detail(long no, Model model) {
		model.addAttribute("detail", mapper.findByNo(no));
	}

	@Override
	public void update(long no, QuestionEntity entity) {
		mapper.update(entity.no(no));
	}

	@Override
	public void delete(long no) {
		mapper.delete(no);
	}

	@Override
	public void findAllProcess(Model model, int page) {
		if(page<1)page=1;
		int limit=10;
		int offset=(page-1)*limit;
		
		List<QuestionEntity> result = mapper.findAll(limit, offset);
		model.addAttribute("list", result);
		
		int rowCount = (int)questRepo.count();
		
		model.addAttribute("pageData", PageData.create(page, limit, rowCount, 5));
	}

}
