package com.green.nowon.openapi.alarm;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;

@Data
public class AlarmInfo {
	
	@JsonProperty("currentCount")
	private int currentCount;
	
	@JsonProperty("data")
	private List<DATA> data;
	
	private int numOfRows;
	
	private int pageNo;
	
	private int resultCode;
	
	private String resultMsg;
	
	private int totalCount;
	
	
}
