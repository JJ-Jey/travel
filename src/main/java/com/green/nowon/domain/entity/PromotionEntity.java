package com.green.nowon.domain.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import groovy.transform.ToString;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@ToString
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Table(name = "promotion")
@Entity
public class PromotionEntity extends BaseDateEntity {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long pno;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private int price;
	
	@Column(nullable = false)
	private String period;
	
	@JoinColumn
	@OneToMany
	List<PromotionImageEntity> images;
	
	public PromotionEntity pno(long pno) {
		this.pno = pno;
		return this;
	}
}
