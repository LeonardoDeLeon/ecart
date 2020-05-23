package com.learning.ecart.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import com.learning.ecart.service.MyUserDetails;

@Configuration
@EnableWebMvc
public class MvcConfig {
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
		BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
		return bCryptPasswordEncoder;
	}
	
	@Bean 
	public MyUserDetails userDetails() {
		MyUserDetails userDetails = new MyUserDetails();
		return userDetails;
	}
}
