package com.project.zerowasteshop;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Configuration
public class FileWebConfig implements WebMvcConfigurer{

	//application.properties 에서 설정한 변수(file.dir)를 DI
	@Value("${file.dir}")
	private String realPath;
	
	
	//selectAll.html파일에서 이미지경로를 인식시킬때 사용할 경로
	private String connectPath = "/uploadimgPath/**";
	
	@Override
	public void addResourceHandlers(ResourceHandlerRegistry registry) {
		log.info("realPath:{}",realPath);
		registry.addResourceHandler(connectPath).addResourceLocations("file:///"+realPath);
	}
	
}
