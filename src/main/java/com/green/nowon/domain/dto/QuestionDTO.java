package com.green.nowon.domain.dto;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class QuestionDTO {
	
	private long no;//자동
	private String subject;//form
	private String content;//form
	private String writer;//form
	private LocalDateTime createdDate;//자동
	private LocalDateTime updatedDate;//자동
	private boolean answered;
	private String memberNo;

}
