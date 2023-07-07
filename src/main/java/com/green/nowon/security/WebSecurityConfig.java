package com.green.nowon.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

	@Bean
	public AuthenticationSuccessHandler mySuccessHandler() {
		return new MySuccessHandler();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(authorize -> authorize
					.antMatchers("/", "/css/**", "/js/**","/image/**", "/sign/*", "/layout/*", "/date-time-picker/**", "/alert", "/questions").permitAll()
					.antMatchers("/board/*", "/reservations", "/questions/search").hasAnyRole("USER", "ADMIN")
					.antMatchers("/admin/**").hasAnyRole("ADMIN")
					.anyRequest().authenticated()
			)
			.formLogin(login -> login
					.loginPage("/sign/signin")
					.usernameParameter("email")
					.passwordParameter("password")
					.defaultSuccessUrl("/")
					.successHandler(mySuccessHandler())
					.permitAll()
			)
			.logout(logout->logout
	        		 .logoutUrl("/logout")
	        		 .logoutSuccessUrl("/")
	        		 .deleteCookies("JSESSIONID"))
			;
			
		return http.build();
	}

}
