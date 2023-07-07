package com.green.nowon.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.green.nowon.domain.entity.CategoryEntity;

public interface CategoryEntityRepository extends JpaRepository<CategoryEntity, Long> {

	Optional<CategoryEntity> findByNameAndParentIsNull(String cateName);

	Optional<CategoryEntity> findByName(String cateName);

	//1차 카테고리 찾는 쿼리: 부모 객체의 존재 여부 판단
	List<CategoryEntity> findAllByParent(CategoryEntity parent);

	//존재하는 부모의 no값을 확인하여 조건으로
	List<CategoryEntity> findAllByParentNo(long no);

}