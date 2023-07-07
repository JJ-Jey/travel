package com.green.nowon.domain.entity;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface ItemImageEntityRepository extends JpaRepository<ItemImageEntity, Long> {

	Optional<ItemImageEntity> findByItemAndIsListAndIsDef(ItemEntity item, boolean isList, boolean isDef);
	
}
