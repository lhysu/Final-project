package com.project.zerowasteshop.member;


import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;


import jakarta.servlet.ServletContext;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@Autowired
	private HttpSession session;
	
	@Autowired
	ServletContext context;
	
	
	//myPage로 연결
//	@GetMapping({"/member/m_selectOne"})
//	public String m_selectOne(Model model,MemberVO vo) {
//		log.info("/member/m_selectOne");
//		return "user/myPage";
//	}
	
	//회원가입페이지로 이동
//	@GetMapping({"/member/m_insert"})
//	public String m_insert() {
//		log.info("/member/m_insert");
//		return "user/memberJoin";
//		
//	}
	
	//회원가입 
//	@PostMapping({"/member/m_insertOK"})
//	public String m_insertOK(Model model,MemberVO vo) {
//		log.info("/member/m_insertOK");
//		log.info("vo:{}",vo);
//		
//		int result = service.insertOK(vo);
//		
//		if(result==1) {
//			return "redirect:/member/m_login";		
//		}else {
//			return "redirect:/member/m_insert";		
//			
//		}
//	}
	
	//회원정보 수정페이지로 이동
//	@GetMapping("/member/m_update")
//	public String m_update(Model model) {
//		log.info("/member/m_update");
//		String user_id = (String) session.getAttribute("user_id");
//		log.info("user_id:",user_id);
//		
//		
//		MemberVO vo2 = service.selectOne(user_id);
//		log.info("vo2:{}",vo2);	
//		model.addAttribute("vo2",vo2);
//		return "user/myUpdate";	
//	}
	
	//회원정보 수정
//	@PostMapping("/member/m_updateOK")
//	public String m_updateOK(MemberVO vo, String pwCheck){
//		log.info("/member/m_updateOK");
//		log.info("vo:{}",vo);
//		log.info("pwCheck:{}",pwCheck);
//		int result=0;
//		
//		if(vo.getPw().equals(pwCheck)) {
//			result = service.updateOK(vo);
//		}
//		log.info("result:{}",result);
//		if(result==1) {
//			return "redirect:/member/m_selectOne";			
//		}else {
//			return "redirect:/member/m_update?member_id="+vo.getMember_id();		
//			
//		}
//	
//	}
//	
//	//회원 탈퇴
//	@PostMapping({"/member/m_deleteOK"})
//	public String m_deleteOK(Model model,MemberVO vo) {
//		log.info("/member/m_deleteOK");
//		log.info("vo:{}",vo);
//		int result = service.deleteOK(vo);
//		log.info("result:{}",result);
//		
//		if(result==1) {
//			return "redirect:/home";			
//		}else {
//			return "redirect:/member/update?member_num="+vo.getMember_num();	
//		}
//	}
//	
//	//로그인 페이지 이동
//	@GetMapping({"/member/m_login"})
//	public String m_login() {
//		log.info("/member/m_login");
//		return "user/login";
//	}
//	
//	
//	  //로그인
//	  
//	  @PostMapping({"/member/m_loginOK"}) public String m_loginOK(MemberVO vo) {
//	  log.info("/member/m_loginOK"); log.info("{}",vo);
//	  
//	  MemberVO vo2 = new MemberVO(); vo2 = service.login(vo); 
//	  if(vo2!=null) {
//	  session.setAttribute("user_id", vo2.getMember_id()); 
//	  return "redirect:/home";
//	  }else { return "redirect:/member/m_login"; } }
//	 
//	
//	//로그아웃
//	
//	  @GetMapping({"/member/m_logout"}) public String m_logout() {
//	  log.info("/member/m_logout"); session.removeAttribute("user_id");
//	  //session.invalidate();
//	  
//	  return "redirect:/home"; }
//	 
//	
//	//아이디 찾기 페이지로 이동
//	@GetMapping({"/member/selectId"})
//	public String selectId() {
//		log.info("/member/selectId");
//		return "user/selectId";
//	}
//	
//	
//	//비밀번호 찾기 페이지로 이동
//	@GetMapping({"/member/selectPw"})
//	public String selectPw() {
//		log.info("/member/selectPw");
//		return "user/selectPw";
//	}
		
	
	

}
