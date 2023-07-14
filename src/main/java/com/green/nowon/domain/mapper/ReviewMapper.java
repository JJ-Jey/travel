package com.green.nowon.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.green.nowon.domain.dto.ReviewDTO;

@Mapper
public interface ReviewMapper {

	void save(@Param("pno") long pno, @Param("ReviewDTO") ReviewDTO dto);

	List<ReviewDTO> findByPno(long pno);

}
