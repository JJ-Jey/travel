package com.green.nowon.domain.entity;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import lombok.Getter;

//@EntityListeners(AuditingEntityListener.class)
@Getter
@MappedSuperclass
public abstract class BaseDateEntity {
	
	//@CreatedDate
	@CreationTimestamp
	@Column(columnDefinition = "timestamp")
	private LocalDateTime createdDate;

	//@LastModifiedDate
	@UpdateTimestamp
	@Column(columnDefinition = "timestamp")
	private LocalDateTime updatedDate;
}