package com.project.zerowasteshop.member.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.zerowasteshop.member.MemberService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MailController {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	MemberService memberservice;
	
	// 이메일 인증
	@PostMapping("login/mailConfirm")
	String mailConfirm(@RequestParam("email") String email) throws Exception {

	   String code = mailService.sendSimpleMessage(email);
	   log.info("인증코드 : " + code);
	   return code;
	}
	
	//임시 비밀번호 전송
//	@PostMapping("login/newPassword")
//	int newPassword(@RequestParam("email") String email, 
//						@RequestParam("member_id") String member_id,
//						@RequestParam("name") String name) throws Exception {
//
//	   String tmeppw = mailService.sendPasswordMessage(email);
//	   log.info("임시 비밀번호 : " + tmeppw);
//	   int result=0;
//	   result = memberservice.updatePW(member_id,name,email,tmeppw);
//	   log.info("result:{}",result);
//	   
//	   return result;
//	}
	

}
