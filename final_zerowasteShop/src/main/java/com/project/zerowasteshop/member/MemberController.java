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
	@GetMapping({"/member/m_selectOne"})
	public String m_selectOne(Model model,MemberVO vo) {
		log.info("/member/m_selectOne");
		return "user/myPage";
	}
	
	//회원가입페이지로 이동
	@GetMapping({"/member/m_insert"})
	public String m_insert() {
		log.info("/member/m_insert");
		return "user/memberJoin";
		
	}
	
	//회원가입 
	@PostMapping({"/member/m_insertOK"})
	public String m_insertOK(Model model,MemberVO vo) {
		log.info("/member/m_insertOK");
		log.info("vo:{}",vo);
		
		//아이디 중복 체크
		MemberVO vo2 = service.idCheck(vo.getMember_id());
		if(vo2!=null) {
			return "user/security/duplicateId";
		}else {
			int result = service.insertOK(vo);
			
			if(result==1) {
				return "redirect:/member/m_login";		
			}else {
				return "redirect:/member/m_insert";		
				
			}
		}
		
		
		
	}
	
	//회원정보 수정페이지로 이동
	@GetMapping("/member/m_update")
	public String m_update(Model model) {
		log.info("/member/m_update");
		String user_id = (String) session.getAttribute("user_id");
		log.info("user_id:",user_id);
		
		
		MemberVO vo2 = service.selectOne(user_id);
		log.info("vo2:{}",vo2);	
		model.addAttribute("vo2",vo2);
		return "user/myUpdate";	
	}
	
	//회원정보 수정
	@PostMapping("/member/m_updateOK")
	public String m_updateOK(MemberVO vo, String pwCheck){
		log.info("/member/m_updateOK");
		log.info("vo:{}",vo);
		log.info("pwCheck:{}",pwCheck);
		int result=0;
		
		if(vo.getPw().equals(pwCheck)) {
			result = service.updateOK(vo);
		}
		log.info("result:{}",result);
		if(result==1) {
			return "redirect:/member/m_selectOne";			
		}else {
			return "redirect:/member/m_update?member_id="+vo.getMember_id();		
			
		}
	
	}
	
	//회원 탈퇴
	@PostMapping({"/member/m_deleteOK"})
	public String m_deleteOK(Model model,MemberVO vo) {
		log.info("/member/m_deleteOK");
		log.info("vo:{}",vo);
		int result = service.deleteOK(vo);
		log.info("result:{}",result);
		
		if(result==1) {
			return "redirect:/home";			
		}else {
			return "redirect:/member/update?member_num="+vo.getMember_num();	
		}
	}
	
	//로그인 페이지 이동
	@GetMapping({"/member/m_login"})
	public String m_login() {
		log.info("/member/m_login");
		return "user/login";
	}
	
	 
	
	//아이디 찾기 페이지로 이동
	@GetMapping({"/member/selectId"})
	public String selectId() {
		log.info("/member/selectId");
		return "user/selectId";
	}
	
	
	//비밀번호 찾기 페이지로 이동
	@GetMapping({"/member/selectPw"})
	public String selectPw() {
		log.info("/member/selectPw");
		return "user/selectPw";
	}
	

	// 로그인이 필요한 요청경로를 로그인 하지 않은 상태로 요청하면 리다일렉트 되는 요청경로
	@GetMapping("/member/required_login")
	public String required_login() {
		return "user/security/required_login";
	}

	// 로그인 폼을 제출(post) 한 로그인 프로세즈 중에 forward 되는 경로이기 때문에 @PostMapping 임에 주의!
	@PostMapping("/member/login_fail")
	public String login_fail() {
		// 로그인 실패임을 알릴 페이지
		return "user/security/login_fail";
	}

	@PostMapping("/member/login_success")
	public String login_success() {
		// 로그인 성공후 보여질 페이지
		return "user/security/login_success";
	}

	// 세션 허용갯수 초과시
	@GetMapping("/member/expired")
	public String expired() {
		return "user/security/expired";
	}
	
	//권한 없는 페이지에 접근한 경우
	@GetMapping("/member/denied")
	public String denied() {
		return "user/security/denied";
	}
	
	

}
