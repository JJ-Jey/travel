package com.green.nowon.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.nowon.domain.dto.QuestionSaveDTO;
import com.green.nowon.domain.entity.QuestionEntity;

public interface QuestionEntityRepository extends JpaRepository<QuestionEntity, Long> {

	void save(QuestionSaveDTO dto);

}
