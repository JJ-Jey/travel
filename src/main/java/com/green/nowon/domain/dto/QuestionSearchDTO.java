package com.green.nowon.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionSearchDTO {
	
	private String colName;

	private String search;
	
	private int page=1;

}
