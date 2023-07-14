package com.green.nowon.domain.dto;

import java.util.List;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class VisualDTO {
	
	private List<String> imgs;
	private List<String> tempKey;
}
