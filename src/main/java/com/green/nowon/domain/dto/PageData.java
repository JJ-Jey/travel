package com.green.nowon.domain.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

@Builder
@AllArgsConstructor
@NoArgsConstructor
public class PageData {
	
	private int offset;	//offset 부터
	private int limit; //limit 개수만큼 가져오기

}
