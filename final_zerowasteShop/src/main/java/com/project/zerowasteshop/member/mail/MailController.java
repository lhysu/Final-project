package com.project.zerowasteshop.member.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MailController {
	
	@Autowired
	MailService mailService;
	
	// 이메일 인증
	@PostMapping("login/mailConfirm")
	String mailConfirm(@RequestParam("email") String email) throws Exception {

	   String code = mailService.sendSimpleMessage(email);
	   log.info("인증코드 : " + code);
	   return code;
	}
	

}
