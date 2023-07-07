package com.green.nowon.security;

import java.util.Collection;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;

import com.green.nowon.domain.entity.MemberEntity;

import lombok.Getter;

@Getter
public class MyUserDetails extends User {
	
	private static final long serialVersionUID = 1L;
	
	private String email;
	
	private String name;
	
	private long mno;

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
		
		this.mno = entity.getNo();
	}

	private MyUserDetails(String username, String password, Collection<? extends GrantedAuthority> authorities) {
		super(username, password, authorities);
	}

}
