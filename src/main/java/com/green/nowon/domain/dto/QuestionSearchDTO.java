package com.green.nowon.domain.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class QuestionSearchDTO {
	
	private String columnName;

	private String query;
	
	private int page=1;

}
