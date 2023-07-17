package com.green.nowon.domain.mapper;

import java.util.Collection;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import com.green.nowon.domain.dto.PageData;
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

	void savePromotion(PromotionEntity entity);
	
	@Select("select count(*) from promotion")
	int countAll();
	
	List<PromotionEntity> findByAllJoinFile(PageData data);

	List<PromotionEntity> findByAll(PageData data);

	List<PromotionEntity> findByJoinFile();

}
