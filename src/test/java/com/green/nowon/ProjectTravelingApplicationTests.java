package com.green.nowon;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.crypto.password.PasswordEncoder;

import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.repository.MemberEntityRepository;
import com.green.nowon.security.MyRole;

@SpringBootTest
class ProjectTravelingApplicationTests {

	//test에서는 requiredConstructor 를 사용하면 안된다!
	
	@Autowired
	private MemberEntityRepository memberRepo;
	
	@Autowired
	private PasswordEncoder encoder;
	
	//@Test
	void 관리자계정추가() {
		memberRepo.save(MemberEntity.builder()
				.email("admin02")
				.password(encoder.encode("1111"))
				.name("관리자")
				.birth("123456")
				.address("노원구")
				.build()
					.addRole(MyRole.ADMIN)
				);
	}

}
