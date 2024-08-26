package com.kh.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebConfig implements WebMvcConfigurer{
	
	//implements WebMvcConfigurer 작성후 ctrl space 3
	@Override
	public void addCorsMappings(CorsRegistry registry) {
		registry.addMapping("/**") //front 포트로 가져오는 api url 주소 모두 허용
		.allowedOrigins("http://localhost:3000") //주소와 포트 허용
		.allowedMethods("GET", "POST", "PUT", "DELETE", "OPTIONS")// DB와 주고받고 
		.allowCredentials(true); //쿠키나 세션과 같은 자격 허용 "*" 사용 가능
	}
}
