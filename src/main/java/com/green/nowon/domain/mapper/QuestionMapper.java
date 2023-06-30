package com.green.nowon.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.nowon.domain.dto.QuestionSaveDTO;
import com.green.nowon.domain.entity.QuestionEntity;

@Mapper
public interface QuestionMapper {

	void save(QuestionSaveDTO dto);

	QuestionEntity findByNo(long no);

	void update(QuestionEntity no);

	void delete(long no);

	List<QuestionEntity> findAll(@Param("limit") int limit, @Param("offset") int offset);

}
