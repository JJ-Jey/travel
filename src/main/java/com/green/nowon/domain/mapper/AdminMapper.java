package com.green.nowon.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.nowon.domain.dto.VisualDTO;
import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.PromotionEntity;
import com.green.nowon.domain.entity.VisualEntity;

@Mapper
public interface AdminMapper {

	List<PromotionEntity> findAll();

	List<PromotionEntity> findAllJoinImage();

	PromotionEntity findByPno(long pno);

	void save(VisualEntity visualEntity);

	void update(@Param("pno") long pno, @Param("PromotionEntity") PromotionEntity dto);

	void delete(long pno);

}
