package com.green.nowon.restBoard;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardSearchDTO {

	private int columnName;
	private String query;
	
	private int page=1;
}
