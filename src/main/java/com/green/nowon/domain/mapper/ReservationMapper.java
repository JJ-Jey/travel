package com.green.nowon.domain.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.green.nowon.domain.dto.ReservationSaveDTO;
import com.green.nowon.domain.entity.ReservationEntity;

@Mapper
public interface ReservationMapper {

	void reservation(ReservationSaveDTO dto);

	List<ReservationEntity> findAll();

	List<ReservationEntity> findByMno(long mno);

}
