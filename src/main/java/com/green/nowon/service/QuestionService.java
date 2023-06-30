package com.green.nowon.service;

import org.springframework.ui.Model;

import com.green.nowon.domain.dto.QuestionSaveDTO;
import com.green.nowon.domain.entity.QuestionEntity;

public interface QuestionService {

	void save(QuestionSaveDTO dto);

	void detail(long no, Model model);

	void update(long no, QuestionEntity entity);

	void delete(long no);

	void findAllProcess(Model model, int page);

}
