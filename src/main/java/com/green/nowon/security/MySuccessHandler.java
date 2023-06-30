package com.green.nowon.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.SimpleUrlAuthenticationSuccessHandler;

public class MySuccessHandler extends SimpleUrlAuthenticationSuccessHandler {
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
			Authentication authentication) throws IOException, ServletException {
		
		Enumeration<String> headers = request.getHeaderNames();
		
		while(headers.hasMoreElements()) {
			String headerName = headers.nextElement();
			System.out.println(headerName + ": " + request.getHeader(headerName));
			
		}
		
		System.out.println("referer: " + request.getHeader("referer"));
		//login 페이지 버튼을 눌러서 이동한 경우: http://localhost:8080/sign/signin
		
		response.sendRedirect("/"); //인증 후 무조건 인덱스
		
		
	}
	
}
