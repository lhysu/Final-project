package com.project.zerowasteshop;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class HomeController {
	
	@GetMapping({"/","/home"})//웰컴파일없이 제어가능(index.html무시)
	public String home() {
		log.info("/home");
		return "home";//resources/templates폴더에서 찾는다
	}
	
	@GetMapping("/user/myPage")
	public String myPage() {
		log.info("/myPage");
		return "user/myPage";
	}
	
}
