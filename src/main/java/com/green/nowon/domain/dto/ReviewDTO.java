package com.green.nowon.domain.dto;

import java.time.LocalDateTime;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class ReviewDTO {
	
	private long rno;
	private String content;
	private String writer = "관리자";
	private long no;
	private LocalDateTime createdDate;
	private LocalDateTime updatedDate;
	
	//현재 유저명 가져오기
		public static String currentUserName() {
			
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			
			User user = (User) authentication.getPrincipal();
			
			return user.getUsername();
		}
	

}
