package com.green.nowon.domain.mapper;

import org.apache.ibatis.annotations.Mapper;

import com.green.nowon.domain.entity.MemberEntity;

@Mapper
public interface MemberMapper {

	void save(MemberEntity memberEntity);

}
