package com.green.nowon.domain.dto;

import java.util.List;

import com.green.nowon.domain.entity.PromotionEntity;

import groovy.transform.ToString;
import lombok.Getter;
import lombok.Setter;

@ToString
@Setter
@Getter
public class PromotionSaveDTO {
	
	private long pno;
	private String name;
	private int price;
	private String period;
	private String content;
	
	private List<String> imgs;
	private List<String> tempKey;
	
	public PromotionSaveDTO pno(long pno) {
		this.pno = pno;
		return this;
	}

	
	public PromotionEntity toProductEntity() {
		return PromotionEntity.builder()
							.name(name).price(price).content(content).period(period)
							.build();
	}

}
