package com.green.nowon.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import com.green.nowon.domain.entity.MemberEntity;
import com.green.nowon.domain.repository.MemberEntityRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Component //@Service로도 설정 가능
public class CustomUserDetailsService implements UserDetailsService {
	
	//로그인 정보를 DB에 넘겨서 유저가 존재하는지 확인
	//유저가 존재하면 UserDetails 정보를 넘겨주면 됨
	
	//물리 DB에 접근하기 위한 repository
	private final MemberEntityRepository repository;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		
		//social 유저가 아닌 경우에만 유저 존재 확인
		//메소드 명명 규칙: 물리 DB가 아닌 entity의 컬럼명을 기준으로 한다.
		MemberEntity result = 
				repository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Bad User"));
		
		
		//Authentication: (principal, credentials, authorities)
		//result.getEmail() 대신 메소드의 파라미터 email을 바로 가져와도 됨
		//마지막 파라미터 authorities는 Collection<? extends GrantedAuthority> authorities 형식
		
		/*
		return new User(result.getEmail(),
				result.getPass(),
				result.getRoleSet()
					.stream()
					.map(role -> new SimpleGrantedAuthority(role.roleName()))
					.collect(Collectors.toSet())
				);
		*/
		
		//원래 위 타입을 리턴하였지만, principal에 존재하지 않은 닉네임 값을 가져오기 위해서 MyUserDetails(dto의 일종)을 만들어서 새로운 생성자를 만들어서 사용
		return new MyUserDetails(result); //변수에 저장하지 않고 바로 파라미터로 가져오도 됨
		
	}
	
	//강제적으로 세션을 삭제하는 로직??
	
}
