package com.project.zerowasteshop.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class MemberRestController {
	
	@Autowired
	MemberService service;
	
	//아이디 중복 체크
	@GetMapping({"/api/idCheck"})
	public Map<String,String> idCheck(String member_id) {
		log.info("/api/idCheck");
		log.info("member_id:{}",member_id);
		MemberVO vo = service.idCheck(member_id);
		log.info("vo:{}",vo);
		
		Map<String,String> map = new HashMap<String, String>();
		if(vo!=null) {
			map.put("result", "Not OK");
		}else {
			map.put("result", "OK");

		}
		
		return map;
	}
	

}
