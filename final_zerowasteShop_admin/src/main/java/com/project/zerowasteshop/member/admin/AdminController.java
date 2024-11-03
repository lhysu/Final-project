package com.project.zerowasteshop.member.admin;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.zerowasteshop.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@Controller
public class AdminController {
	
	@Autowired
	AdminService service;
	
	@Autowired
	private HttpSession session;
	
	
	@GetMapping({"/admin/ad_selectAll"})
	public String ad_selectAll(Model model,
			@RequestParam(defaultValue = "1")int cpage,
			@RequestParam(defaultValue = "10")int pageBlock) {
		log.info("/admin/ad_selectAll");
		log.info("cpage:{}",cpage);
		log.info("pageBlock:{}",pageBlock);
		

		List<MemberVO> list = service.selectAllPageBlock(cpage,pageBlock);
		log.info("list.size():{}",list.size());
		
		//DB로부터 얻은 검색결과의 모든 행수
		int total_rows=service.getTotalRows();	
		log.info("total_rows:{}",total_rows);
		
		//int pageBlock=5;	//1개페이지에서 보여질 행수-파라미터로 받으면 된다.
		int totalPageCount=0;	
		
		//총 행카운트와 페이지블럭을 나눌 때의 알고리즘을 추가 
		if(total_rows%pageBlock!=0) {
			totalPageCount=total_rows/pageBlock+1;
		}else {
			totalPageCount=total_rows/pageBlock;
		}
		log.info("totalPageCount:{}",totalPageCount);
		
		
		model.addAttribute("totalPageCount",totalPageCount);
		
		model.addAttribute("list", list);
		return "admin/member/selectAll";
	}
	
	@GetMapping({"/admin/ad_searchList"})
	public String ad_searchList(Model model,
			@RequestParam(defaultValue = "member_id")String searchKey,
			@RequestParam(defaultValue = "us")String searchWord,
			@RequestParam(defaultValue = "1")int cpage,
			@RequestParam(defaultValue = "10")int pageBlock) {
		log.info("/admin/ad_searchList");
		log.info("cpage:{}",cpage);
		log.info("pageBlock:{}",pageBlock);

//		List<MemberVO> list = service.searchList(searchKey,searchWord);
		List<MemberVO> list = service.searchListPageBlock(searchKey,searchWord,cpage,pageBlock);
		log.info("list.size():{}",list.size());
		
		//DB로부터 얻은 검색결과의 모든 행수
		int total_rows=service.getSearchTotalRows(searchKey,searchWord);	
		log.info("total_rows:{}",total_rows);
		
		//int pageBlock=5;	//1개페이지에서 보여질 행수-파라미터로 받으면 된다.
		int totalPageCount=0;	
		
		//총 행카운트와 페이지블럭을 나눌 때의 알고리즘을 추가 
		if(total_rows%pageBlock!=0) {
			totalPageCount=total_rows/pageBlock+1;
		}else {
			totalPageCount=total_rows/pageBlock;
		}
		log.info("totalPageCount:{}",totalPageCount);
		
		
		model.addAttribute("totalPageCount",totalPageCount);
		
		model.addAttribute("list", list);
		return "admin/member/selectAll";
	}
	
	@GetMapping({"/admin/ad_selectOne"})
	public String ad_selectOne(Model model,MemberVO vo) {
		log.info("/admin/ad_selectOne");
		log.info("vo:{}",vo);
		MemberVO vo2 = service.selectOne(vo);
		log.info("vo2:{}",vo2);
		
		model.addAttribute("vo2", vo2);
		return "admin/member/selectOne";
	}
	
	//회원 추가
	@GetMapping({"/admin/ad_insert"})
	public String ad_insert() {
		log.info("/admin/ad_insert");
		return "admin/member/insert";
		
	}
	
	
	
	@PostMapping({"/admin/ad_insertOK"})
	public String ad_insertOK(Model model,MemberVO vo) {
		log.info("/admin/ad_insertOK");
		log.info("vo:{}",vo);
		
		int result = service.insertOK(vo);
		
		if(result==1) {
			return "redirect:/admin/ad_selectAll";		
		}else {
			return "redirect:/admin/ad_insert";		
			
		}
	}
	
	@GetMapping("/admin/ad_update")
	public String ad_update(MemberVO vo, Model model) {
		log.info("/admin/ad_update");
		log.info("vo:{}",vo);
		MemberVO vo2 = service.selectOne(vo);
		log.info("vo2:{}",vo2);
		
		model.addAttribute("vo2",vo2);
		return "admin/member/update";	
	}
	
	@PostMapping("/admin/ad_updateOK")
	public String ad_updateOK(MemberVO vo){
		log.info("/admin/ad_updateOK");
		log.info("vo:{}",vo);
		int result=0;
		if(vo.isAdCheck()==true) {
		result = service.adminUpdateOK(vo);
		}else {
			result=service.userUpdateOK(vo);
		}
		log.info("result:{}",result);
		if(result==1) {
			return "redirect:/admin/ad_selectOne?member_num="+vo.getMember_num();			
		}else {
			return "redirect:/admin/ad_update?member_num="+vo.getMember_num();		
			
		}
	
	}
	
	@PostMapping({"/admin/ad_deleteOK"})
	public String ad_deleteOK(Model model,MemberVO vo) {
		log.info("/admin/ad_deleteOK");
		log.info("vo:{}",vo);
		int result = service.deleteOK(vo);
		log.info("result:{}",result);
		
		if(result==1) {
			return "redirect:/admin/ad_selectAll";			
		}else {
			return "redirect:/admin/ad_selectOne?member_num="+vo.getMember_num();	
			
		}
		
	}
	
	//관리자 로그인 페이지로 이동
	@GetMapping({"/admin/ad_login"})
	public String ad_login() {
		log.info("/admin/ad_login");
		return "admin/member/login";
	}
	
	
	//관리자 회원가입
	@GetMapping({"/admin/adminJoin"})
	public String adminJoin() {
		log.info("/admin/adminJoin");
		return "admin/member/adminJoin";
		
	}
	
	//관리자 회원가입
	@PostMapping({"/admin/adminInsert"})
	public String adminInsert(Model model,MemberVO vo) {
		log.info("/admin/adminInsert");
		log.info("vo:{}",vo);
		vo.setAdCheck(true);	//관리자 여부 표시
		//아이디 중복 체크
		MemberVO vo2 = service.idCheck(vo.getMember_id());
		if(vo2!=null) {
			return "user/security/duplicateId";
		}else {
		int result = service.insertOK(vo);
		
		if(result==1) {
			return "redirect:/admin/ad_login";		
		}else {
			return "redirect:/admin/ad_insert";		
		}
		}
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
