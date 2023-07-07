package com.green.nowon.domain.dto;

import java.time.LocalDateTime;

import com.green.nowon.domain.entity.PromotionEntity;

import lombok.Getter;

@Getter
public class PromotionListDTO {
	
	private long pno;//상품관리번호
	private String name;//상품명
	private int price;//가격
	private String period;
	private LocalDateTime updatedDate;
	
	//list페이지에서 추가로 def이미지 1장
	private String defUrl;
	
	//defUrl 세팅해주는 편의메서드
	public PromotionListDTO defUrl(String defUrl) {
		this.defUrl=defUrl;
		return this;
	}
	
	//Entity클래스에서 일부만 추출해서 생성
	public PromotionListDTO(PromotionEntity e) {
		this.pno = e.getPno();
		this.name = e.getName();
		this.price = e.getPrice();
		this.period = e.getPeriod();
		this.updatedDate = e.getUpdatedDate();
		
		if(e.getImages()!=null) {
			String path="//s3.ap-northeast-2.amazonaws.com/myweb.fileupload.bucket/promotion/images/";
			defUrl= path+e.getImages().get(0).getNewName();
		}
		
		
	}

}
