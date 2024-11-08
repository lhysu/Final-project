package com.project.zerowasteshop.member;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.project.zerowasteshop.member.admin.AdminService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class AdminRestController {
	
	@Autowired
	AdminService service;
	
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
	
	//아이디 찾기
//		@PostMapping({"/api/selectIdCheck"})
//		public String api_selectIdCheck(Model model,@RequestParam("email") String email,@RequestParam("name") String name) {
//			log.info("/api/selectIdCheck");
//			log.info("email:{}", email);
//			log.info("name:{}", name);
//			MemberVO vo = new MemberVO();
//			vo.setEmail(email);
//			vo.setName(name);
//			MemberVO vo2 = service.selectId(vo);
//			log.info("vo2:{}",vo2);
//			String result="";
//			if(vo2!=null) {
//				result= vo2.getMember_id();
//						
//			}
//			
//			return result;	
//			
//		}
	

}
