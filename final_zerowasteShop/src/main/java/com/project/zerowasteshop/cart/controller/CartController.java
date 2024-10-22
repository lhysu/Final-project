package com.project.zerowasteshop.cart.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.zerowasteshop.cart.model.CartVO;
import com.project.zerowasteshop.cart.service.CartService;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.http.HttpSession;

import javax.imageio.ImageIO;

@Controller
@Slf4j
public class CartController {

    @Autowired
    CartService service;
    
    @Autowired
	private HttpSession session;
    
 // application.properties 에서 설정한 변수(file.dir)를 DI
 	@Value("${file.dir}")
 	private String realPath;

 	@GetMapping("/cart/insert")
 	public String insert() {
 		log.info("/cart/insert");
 		return "cart/insert";
 	}

// 	@GetMapping("/cart/update")
// 	public String update(CartVO vo, Model model) {
// 		log.info("/cart/update");
// 		log.info("vo:{}", vo);
//
// 		CartVO vo2 = service.selectOne(vo);
// 		log.info("vo2:{}", vo2);
//
// 		model.addAttribute("vo2", vo2);
//
// 		return "cart/update";
// 	}

// 	@GetMapping("/cart/delete")
// 	public String delete() {
// 		log.info("/cart/delete");
// 		return "cart/delete";
// 	}

// 	@GetMapping("/product/show")
// 	public String show(Model model, @RequestParam(defaultValue = "1") int cpage,
// 			@RequestParam(defaultValue = "12") int pageBlock) {
// 		log.info("/product/show");
// 		log.info("cpage:{}", cpage);
// 		log.info("pageBlock:{}", pageBlock);
//
//// 		List<MemberVO> list = service.selectAll();
// 		List<CartVO> list = service.selectAllPageBlock(cpage, pageBlock);// 해당페이지에 보여줄 5개행씩만 검색
// 		log.info("list.size():{}", list.size());
//
// 		model.addAttribute("list", list);
//
// 		// 디비로부터 얻은 검색결과의 모든 행수
// 		int total_rows = service.getTotalRows();// select count(*) total_rows from member;
// 		log.info("total_rows:{}", total_rows);
// 		// int pageBlock = 5;//1개페이지에서 보여질 행수,파라메터로 받으면됨.
// 		int totalPageCount = 0;
//
// 		// 총행카운트와 페이지블럭을 나눌때의 알고리즘을 추가하기
// 		if (total_rows / pageBlock == 0) {
// 			totalPageCount = 1;
// 		} else if (total_rows % pageBlock == 0) {
// 			totalPageCount = total_rows / pageBlock;
// 		} else {
// 			totalPageCount = total_rows / pageBlock + 1;
// 		}
// 		log.info("totalPageCount:{}", totalPageCount);
//
// 		model.addAttribute("cpage", cpage);
// 		model.addAttribute("totalPageCount", totalPageCount);
// 		
// 		// 페이지네이션 범위 계산	
// 	    int startPage = Math.max(1, cpage - 4); // 현재 페이지 기준으로 시작 페이지
// 	    int endPage = Math.min(totalPageCount, startPage + 9); // 10개 페이지 표시
//
// 	    model.addAttribute("startPage", startPage);
// 	    model.addAttribute("endPage", endPage);
//
// 		return "product/show";
// 	}
// 	
// 	@GetMapping("/product/showSearchList")
// 	public String showSearchList(Model model, @RequestParam(defaultValue = "") String searchKey,
// 			@RequestParam(defaultValue = "") String searchWord,
// 			@RequestParam(defaultValue = "1") int cpage,
// 			@RequestParam(defaultValue = "12") int pageBlock) {
// 		log.info("/product/showSearchList");
// 		log.info("searchKey:{}", searchKey);
// 		log.info("searchWord:{}", searchWord);
// 		log.info("cpage:{}", cpage);
// 		log.info("pageBlock:{}", pageBlock);
//
//// 		List<MemberVO> list = service.searchList(searchKey, searchWord);
// 		List<CartVO> list = service.searchListPageBlock(searchKey, searchWord,cpage,pageBlock);
// 		log.info("list.size():{}", list.size());
//
// 		model.addAttribute("list", list);
//
// 		// 디비로부터 얻은 검색결과의 모든 행수
//// 		int total_rows = service.getTotalRows();// select count(*) total_rows from member;
// 		// select count(*) total_rows from member where id like '%ad%';
// 		// select count(*) total_rows from member where name like '%ki%';
// 		int total_rows = service.getSearchTotalRows(searchKey, searchWord);
// 		log.info("total_rows:{}", total_rows);
// 		// int pageBlock = 5;//1개페이지에서 보여질 행수,파라메터로 받으면됨.
// 		int totalPageCount = 0;
//
// 		// 총행카운트와 페이지블럭을 나눌때의 알고리즘을 추가기
// 		if (total_rows / pageBlock == 0) {
// 			totalPageCount = 1;
// 		} else if (total_rows % pageBlock == 0) {
// 			totalPageCount = total_rows / pageBlock;
// 		} else {
// 			totalPageCount = total_rows / pageBlock + 1;
// 		}
// 		log.info("totalPageCount:{}", totalPageCount);
//
// 		model.addAttribute("cpage", cpage);
// 		model.addAttribute("totalPageCount", totalPageCount);
//
// 		// 페이지네이션 범위 계산	
// 	    int startPage = Math.max(1, cpage - 4); // 현재 페이지 기준으로 시작 페이지
// 	    int endPage = Math.min(totalPageCount, startPage + 9); // 10개 페이지 표시
//
// 	    model.addAttribute("startPage", startPage);
// 	    model.addAttribute("endPage", endPage);
// 		
// 		return "product/show";
// 	}
 	
 	@GetMapping("/cart/selectAll")
 	public String selectAll(Model model, @RequestParam(defaultValue = "1") int cpage,
 			@RequestParam(defaultValue = "10") int pageBlock, String user_id) {
 		log.info("/cart/selectAll");
 		log.info("cpage:{}", cpage);
 		log.info("pageBlock:{}", pageBlock);

// 		List<MemberVO> list = service.selectAll();
 		service.updateProductName();
 		String userID = (String) session.getAttribute("user_id"); // 세션에서 user_id 가져오기
 		List<CartVO> list = service.selectAllPageBlock(cpage, pageBlock, userID);// 해당페이지에 보여줄 5개행씩만 검색
 		log.info("list.size():{}", list.size());

 		model.addAttribute("list", list);

 		log.info("List size: {}", list.size());
 		
 		// 디비로부터 얻은 검색결과의 모든 행수
 		int total_rows = service.getTotalRows(user_id);// select count(*) total_rows from member;
 		log.info("total_rows:{}", total_rows);
 		// int pageBlock = 5;//1개페이지에서 보여질 행수,파라메터로 받으면됨.
 		int totalPageCount = 0;

 		// 총행카운트와 페이지블럭을 나눌때의 알고리즘을 추가기
 		if (total_rows / pageBlock == 0) {
 			totalPageCount = 1;
 		} else if (total_rows % pageBlock == 0) {
 			totalPageCount = total_rows / pageBlock;
 		} else {
 			totalPageCount = total_rows / pageBlock + 1;
 		}
 		log.info("totalPageCount:{}", totalPageCount);

 		model.addAttribute("user_id", user_id);
 		model.addAttribute("cpage", cpage);
 		model.addAttribute("totalPageCount", totalPageCount);
 		
 		// 페이지네이션 범위 계산
 	    int startPage = Math.max(1, cpage - 4); // 현재 페이지 기준으로 시작 페이지
 	    int endPage = Math.min(totalPageCount, startPage + 9); // 10개 페이지 표시

 	    model.addAttribute("startPage", startPage);
 	    model.addAttribute("endPage", endPage);

 		return "cart/selectAll";
 	}
 	

// 	@GetMapping("/cart/searchList")
// 	public String searchList(Model model, @RequestParam(defaultValue = "company") String searchKey,
// 			@RequestParam(defaultValue = "") String searchWord,
// 			@RequestParam(defaultValue = "1") int cpage,
// 			@RequestParam(defaultValue = "10") int pageBlock) {
// 		log.info("/cart/searchList");
// 		log.info("searchKey:{}", searchKey);
// 		log.info("searchWord:{}", searchWord);
// 		log.info("cpage:{}", cpage);
// 		log.info("pageBlock:{}", pageBlock);
//
//// 		List<MemberVO> list = service.searchList(searchKey, searchWord);
// 		List<CartVO> list = service.searchListPageBlock(searchKey, searchWord,cpage,pageBlock);
// 		log.info("list.size():{}", list.size());
//
// 		model.addAttribute("list", list);
//
// 		// 디비로부터 얻은 검색결과의 모든 행수
//// 		int total_rows = service.getTotalRows();// select count(*) total_rows from member;
// 		// select count(*) total_rows from member where id like '%ad%';
// 		// select count(*) total_rows from member where name like '%ki%';
// 		int total_rows = service.getSearchTotalRows(searchKey, searchWord);
// 		log.info("total_rows:{}", total_rows);
// 		// int pageBlock = 5;//1개페이지에서 보여질 행수,파라메터로 받으면됨.
// 		int totalPageCount = 0;
//
// 		// 총행카운트와 페이지블럭을 나눌때의 알고리즘을 추가기
// 		if (total_rows / pageBlock == 0) {
// 			totalPageCount = 1;
// 		} else if (total_rows % pageBlock == 0) {
// 			totalPageCount = total_rows / pageBlock;
// 		} else {
// 			totalPageCount = total_rows / pageBlock + 1;
// 		}
// 		log.info("totalPageCount:{}", totalPageCount);
//
// 		model.addAttribute("cpage", cpage);
// 		model.addAttribute("totalPageCount", totalPageCount);
// 		
// 		// 페이지네이션 범위 계산
// 	    int startPage = Math.max(1, cpage - 4); // 현재 페이지 기준으로 시작 페이지
// 	    int endPage = Math.min(totalPageCount, startPage + 9); // 10개 페이지 표시
//
// 	    model.addAttribute("startPage", startPage);
// 	    model.addAttribute("endPage", endPage);
//
// 		return "cart/selectAll";
// 	}

// 	@GetMapping("/cart/selectOne")
// 	public String selectOne(CartVO vo, Model model) {
// 		log.info("/cart/selectOne");
// 		log.info("vo:{}", vo);
//
// 		CartVO vo2 = service.selectOne(vo);
// 		log.info("vo2:{}", vo2);
//
// 		model.addAttribute("vo2", vo2);
//
// 		return "cart/selectOne";
// 	}

 	@PostMapping("/cart/insertOK")
 	public String insertOK(CartVO vo) throws IllegalStateException, IOException {
 		log.info("/cart/insertOK");
		/* vo.setPoint(vo.getPrice() / 1000); */
 		log.info("vo:{}", vo);

 		// 스프링프레임워크에서 사용하던 리얼패스사용불가.
 		// String realPath = context.getRealPath("resources/upload_img");


 		int result = service.insertOK(vo);
 		log.info("result:{}", result);
 		if (result == 1) {
 			return "redirect:/cart/selectAll";
 		} else {
 			return "redirect:/cart/insert";
 		}
 	}

// 	@PostMapping("/cart/updateOK")
// 	public String updateOK(CartVO vo) throws IllegalStateException, IOException {
// 		log.info("/cart/updateOK");
//// 		vo.setPoint(vo.getPrice() / 1000);
// 		log.info("vo:{}", vo);
//
// 		// 스프링프레임워크에서 사용하던 리얼패스사용불가.
// 		// String realPath = context.getRealPath("resources/upload_img");
//
// 		// @Value("${file.dir}")로 획득한 절대경로 사용해야함.
// 		log.info(realPath);
//
// 		String originName = vo.getFile().getOriginalFilename();
// 		log.info("originName:{}", originName);
//
// 		if (originName.length() == 0) {// 넘어온 파일이 없을때 default.png 할당
// 			vo.setReview_img(vo.getReview_img());
// 		} else {
// 			// 중복이미지 이름을 배제하기위한 처리
// 			String save_name = "img_" + System.currentTimeMillis() + originName.substring(originName.lastIndexOf("."));
// 			log.info("save_name:{}", save_name);
// 			vo.setReview_img(save_name);
//
// 			File f = new File(realPath, save_name);
// 			vo.getFile().transferTo(f);
// 		}
//
// 		int result = service.updateOK(vo);
// 		log.info("result:{}", result);
// 		if (result == 1) {
// 			return "redirect:/cart/selectOne?review_num=" + vo.getReview_num();
// 		} else {
// 			return "redirect:/cart/update?review_num=" + vo.getReview_num();
// 		}
// 	}

 	@PostMapping("/cart/deleteOK")
 	public String deleteOK(CartVO vo) {
 		log.info("/cart/deleteOK");
 		log.info("vo:{}", vo);

 		int result = service.deleteOK(vo);
 		log.info("result:{}", result);
 		if (result == 1) {
 			return "redirect:/cart/selectAll";
 		} else {
 			return "redirect:/cart/selectAll";
 		}
 	}
 	

}
