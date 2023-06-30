package com.green.nowon.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
public enum MyRole {
	USER("ROLE_USER", "일반유저"),		//0
	ADMIN("ROLE_ADMIN", "관리자")		//1
	;
	
	//enum의 매개변수
	private final String roleName;
	private final String koName;
	
	//getter와 같은 역할
	public String roleName() {
		return roleName;
	}
	
	public String koName() {
		return koName;
	}
}
