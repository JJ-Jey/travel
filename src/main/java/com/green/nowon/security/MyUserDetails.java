package com.green.nowon.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.green.nowon.domain.entity.MemberEntity;

import lombok.Getter;

@Getter
//super의 기본 생성자(NoArgs)가 존재하지 않기 때문에 처음에 빨간줄
public class MyUserDetails extends User {
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	private String name;

	public MyUserDetails(MemberEntity entity) {
		this(entity.getEmail(),
				entity.getPassword(),
				entity.getRoleSet()
					.stream()
					.map(role -> new SimpleGrantedAuthority(role.roleName()))
					.collect(Collectors.toSet())
		);
		
		this.email = entity.getEmail(); //default: "principal.username" 을 "principal.email"로 바꾸기 위함
		
		this.name = entity.getName();
	}

	private MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

}
