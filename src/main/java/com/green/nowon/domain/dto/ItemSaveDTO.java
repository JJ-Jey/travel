package com.green.nowon.domain.dto;

import com.green.nowon.domain.entity.ItemEntity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemSaveDTO {
	
	private String title;
	
	private int price;
	
	private int stock;
	
	private String content;
	
	////////////
	
	private String bucketKey;
	
	private String orgName;

	private String newName;

	public ItemEntity toItemEntity() {
		return ItemEntity.builder()
				.title(title)
				.price(price)
				.stock(stock)
				.content(content)
				.build();
	}
	
}
