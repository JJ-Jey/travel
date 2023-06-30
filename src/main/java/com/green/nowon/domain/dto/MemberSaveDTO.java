package com.green.nowon.domain.dto;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.MemberEntity;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class MemberSaveDTO {
	
	private String email;
	private String password;
	private String name;
	private String birth;
	private String address;

	public MemberEntity toEntity(PasswordEncoder encoder) {
		return MemberEntity.builder()
				.email(email)
				.password(encoder.encode(password))
				.name(name)
				.birth(birth)
				.address(address)
				.build();
	}

}
