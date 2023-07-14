package com.green.nowon.controller;

import java.util.Random;

import javax.mail.internet.MimeMessage;

import org.springframework.http.ResponseEntity;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController //response body 자동으로 구성
public class MailController {
	
	private final JavaMailSender sender; //서비스라고 생각하기
	
	//서비스 만들지 않고 사용
	@PostMapping("/email")
	public ResponseEntity<String> mailSend(String toEmail) { //파라미터 명을 jquery의 key와 동일해야 함
		
		//html 형식으로 보내기
		//MimeMessage mime = sender.createMimeMessage();

		
		//텍스트만 지원
		SimpleMailMessage message = new SimpleMailMessage();
		
		String code = createCode();
		
		//발신자 주소
		message.setFrom("qwer20945733@gmail.com");
		
		//수신자 주소: 여러명 지정 가능
		message.setTo(toEmail);
		
		//메일 제목
		message.setSubject("회원가입을 위한 인증메일입니다.");
		
		//메일 내용
		message.setText("인증번호: " + code);
		
		
		sender.send(message);
		
		
		
		//String - return String; 으로 해도 상관 없음
		return ResponseEntity.ok().body(code); //인증번호 그대로 받을 수 있음
		
	}

	
	//6자리 숫자로 이루어진 랜덤 문자열
	private String createCode() {
		
		return new Random()
				.ints(48, 57+1) //ASCII: 0~9
				.limit(6) //6자리
				.collect(StringBuilder::new, StringBuilder::appendCodePoint, StringBuilder::append)
				.toString();
		
	}

}
