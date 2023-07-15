package com.green.nowon.domain.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.green.nowon.domain.entity.PromotionImageEntity;

@Mapper
public interface PromotionImageMapper {

	void save(PromotionImageEntity build);

	String findByPnoAndDefYn(long pno);

}
