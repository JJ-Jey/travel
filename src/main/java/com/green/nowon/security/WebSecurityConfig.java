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
	/*
	@Bean
	public UserDetailsService userDetailsService() {
		return new CustomUserDetailsService();
	}
	*/
	@Bean
	public AuthenticationSuccessHandler mySuccessHandler() {
		return new MySuccessHandler();
	}
	
	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
		http
			.authorizeRequests(authorize -> authorize
					.antMatchers("/", "/css/**", "/js/**","/images/**", "/sign/*", "/layout/*", "/questions", "/date-time-picker/**").permitAll()
					.antMatchers("/board/*", "/registration").hasAnyRole("USER", "ADMIN")
					.antMatchers("/admin/**").permitAll()//.hasAnyRole("ADMIN")
					.anyRequest().authenticated()
			)
			.formLogin(login -> login
					.loginPage("/sign/signin")
					//.loginProcessingUrl("/login")
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
	         //.csrf(csrf->csrf.disable()
			;
			
		return http.build();
	}

}
