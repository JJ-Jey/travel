package com.green.nowon.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.nowon.domain.entity.ItemEntity;
import com.green.nowon.domain.entity.PromotionEntity;

@Mapper
public interface AdminMapper {

	List<PromotionEntity> findAll();

	List<PromotionEntity> findAllJoinImage();

	PromotionEntity findByPno(long pno);

	void save(ItemEntity entity);

}
