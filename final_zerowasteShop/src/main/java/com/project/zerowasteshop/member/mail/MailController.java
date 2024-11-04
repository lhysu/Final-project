package com.project.zerowasteshop.member.mail;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.zerowasteshop.member.MemberService;
import com.project.zerowasteshop.member.MemberVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class MailController {
	
	@Autowired
	MailService mailService;
	
	@Autowired
	MemberService memberservice;
	
	//회원가입 이메일 인증
	@PostMapping("/login/mailConfirm")
	String mailConfirm(@RequestParam("email") String email) throws Exception {
		String code="";
		//이메일 중복 체크
		MemberVO vo = memberservice.emailCheck(email);
		if(vo!=null) {
			code="-1";
		}else {
			code = mailService.sendSimpleMessage(email);
			log.info("인증코드 : " + code);			
		}
		return code;
	}
	
	//임시 비밀번호 전송
	@PostMapping("/login/newPassword")
	int newPassword(@RequestParam("email") String email, 
						@RequestParam("member_id") String member_id,
						@RequestParam("name") String name) throws Exception {
		MemberVO vo = new MemberVO();
		vo.setMember_id(member_id);
		MemberVO vo2 = memberservice.selectOne(member_id);
		log.info("vo2:{}",vo2);
		int result=0;
		
		if(vo2!=null && vo2.isAdCheck()==false) {
			String tmeppw = mailService.sendPasswordMessage(email);
			log.info("임시 비밀번호 : " + tmeppw);
		   
		   result = memberservice.updatePW(member_id,name,email,tmeppw);
		   log.info("result:{}",result);
		}
	   
	   
	   
	   return result;
	}
	
	//비밀번호 찾기 이메일 인증
		@PostMapping("/login/pwmailConfirm")
		String pwmailConfirm(@RequestParam("email") String email) throws Exception {
			String code="";
			//이메일 중복 체크
			MemberVO vo = memberservice.emailCheck(email);
			log.info("vo:{}",vo);
			if(vo!=null) {
				code = mailService.sendSimpleMessage(email);
				
			}else {
				code="-1";		
			}
			log.info("인증코드 : " + code);	
			return code;
		}
	

}
