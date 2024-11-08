package com.project.zerowasteshop.product.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.zerowasteshop.product.NaverShopSearch;
import com.project.zerowasteshop.product.model.ProductVO;
import com.project.zerowasteshop.product.service.ProductService;
import com.project.zerowasteshop.review.model.ReviewVO;

import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.imageio.ImageIO;

@RequiredArgsConstructor // final 로 선언된 클래스를 자동으로 생성합니다.
@Controller
@Slf4j
public class ProductController {

    private final NaverShopSearch naverShopSearch;
    private int check = 0;
    
    @Autowired
    ProductService service;
    
    
 // application.properties 에서 설정한 변수(file.dir)를 DI
 	@Value("${file.dir}")
 	private String realPath;

 	@GetMapping("/admin/product/insert")
 	public String insert() {
 		log.info("/admin/product/insert");
 		return "admin/product/insert";
 	}

 	@GetMapping("/admin/product/update")
 	public String update(ProductVO vo, Model model) {
 		log.info("/admin/product/update");
 		log.info("vo:{}", vo);

 		ProductVO vo2 = service.selectOne(vo);
 		vo2.setProduct_name(vo2.getProduct_name().replace("<b>", "").replace("</b>", ""));
 		log.info("vo2:{}", vo2);

 		model.addAttribute("vo2", vo2);

 		return "admin/product/update";
 	}

 	@GetMapping("/admin/product/delete")
 	public String delete() {
 		log.info("/admin/product/delete");
 		return "admin/product/delete";
 	}

// 	@GetMapping("/product/show")
// 	public String show(Model model, @RequestParam(defaultValue = "1") int cpage,
// 			@RequestParam(defaultValue = "12") int pageBlock) {
// 		log.info("/product/show");
// 		log.info("cpage:{}", cpage);
// 		log.info("pageBlock:{}", pageBlock);
//
//// 		List<MemberVO> list = service.selectAll();
// 		List<ProductVO> list = service.selectAllPageBlock(cpage, pageBlock);// 해당페이지에 보여줄 5개행씩만 검색
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
// 	public String showSearchList(Model model, @RequestParam(defaultValue = "product_name") String searchKey,
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
// 		List<ProductVO> list = service.searchListPageBlock(searchKey, searchWord,cpage,pageBlock);
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
 	
 	@GetMapping("/admin/product/selectAll")
 	public String selectAll(Model model, @RequestParam(defaultValue = "1") int cpage,
 			@RequestParam(defaultValue = "10") int pageBlock) {
 		log.info("/admin/product/selectAll");
 		log.info("cpage:{}", cpage);
 		log.info("pageBlock:{}", pageBlock);

// 		List<MemberVO> list = service.selectAll();
 		List<ProductVO> list = service.selectAllPageBlock(cpage, pageBlock);// 해당페이지에 보여줄 5개행씩만 검색
 		log.info("list.size():{}", list.size());

 		model.addAttribute("list", list);

 		// 디비로부터 얻은 검색결과의 모든 행수
 		int total_rows = service.getTotalRows();// select count(*) total_rows from member;
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

 		model.addAttribute("cpage", cpage);
 		model.addAttribute("totalPageCount", totalPageCount);
 		
 		// 페이지네이션 범위 계산
 	    int startPage = Math.max(1, cpage - 4); // 현재 페이지 기준으로 시작 페이지
 	    int endPage = Math.min(totalPageCount, startPage + 9); // 10개 페이지 표시

 	    model.addAttribute("startPage", startPage);
 	    model.addAttribute("endPage", endPage);

 		return "admin/product/selectAll";
 	}
 	

 	@GetMapping("/admin/product/searchList")
 	public String searchList(Model model, @RequestParam(defaultValue = "company") String searchKey,
 			@RequestParam(defaultValue = "") String searchWord,
 			@RequestParam(defaultValue = "1") int cpage,
 			@RequestParam(defaultValue = "10") int pageBlock) {
 		log.info("/admin/product/searchList");
 		log.info("searchKey:{}", searchKey);
 		log.info("searchWord:{}", searchWord);
 		log.info("cpage:{}", cpage);
 		log.info("pageBlock:{}", pageBlock);

// 		List<MemberVO> list = service.searchList(searchKey, searchWord);
 		List<ProductVO> list = service.searchListPageBlock(searchKey, searchWord,cpage,pageBlock);
 		log.info("list.size():{}", list.size());

 		model.addAttribute("list", list);

 		// 디비로부터 얻은 검색결과의 모든 행수
// 		int total_rows = service.getTotalRows();// select count(*) total_rows from member;
 		// select count(*) total_rows from member where id like '%ad%';
 		// select count(*) total_rows from member where name like '%ki%';
 		int total_rows = service.getSearchTotalRows(searchKey, searchWord);
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

 		model.addAttribute("cpage", cpage);
 		model.addAttribute("totalPageCount", totalPageCount);
 		
 		// 페이지네이션 범위 계산
 	    int startPage = Math.max(1, cpage - 4); // 현재 페이지 기준으로 시작 페이지
 	    int endPage = Math.min(totalPageCount, startPage + 9); // 10개 페이지 표시

 	    model.addAttribute("startPage", startPage);
 	    model.addAttribute("endPage", endPage);

 		return "admin/product/selectAll";
 	}

 	@GetMapping("/admin/product/selectOne")
 	public String selectOne(ProductVO vo, Model model) {
 		log.info("/admin/product/selectOne");
 		log.info("vo:{}", vo);

 		ProductVO vo2 = service.selectOne(vo);
 		log.info("vo2:{}", vo2);

 		model.addAttribute("vo2", vo2);

 		return "admin/product/selectOne";
 	}
 	
// 	@GetMapping("/product/detail")
// 	public String detail(ProductVO vo, Model model) {
// 		log.info("/product/detail");
// 		log.info("vo:{}", vo);
//
// 		ProductVO vo2 = service.selectOne(vo);
// 		List<ReviewVO> list = service.selectAllReview(vo);
// 		log.info("vo2:{}", vo2);
// 		
// 		model.addAttribute("vo2", vo2);
// 		
// 		log.info("list.size():{}", list.size());
//
// 		model.addAttribute("list", list);
//
//
// 		return "product/detail";
// 	}
 	
 	@PostMapping("/product/updateQuantity")
    public ResponseEntity<Void> updateQuantity(@RequestBody ProductVO request) {
        // 수량이 0보다 큰지 확인
        if (request.getCount() <= 0) {
            return ResponseEntity.badRequest().build(); // 잘못된 요청
        }
        
        service.updateQuantity(request.getProduct_num(), request.getCount());
        return ResponseEntity.ok().build(); // 성공적으로 처리됨
    }

 	@PostMapping("/admin/product/insertOK")
 	public String insertOK(ProductVO vo) throws IllegalStateException, IOException {
 		log.info("/admin/product/insertOK");
 		vo.setPoint(vo.getPrice() / 1000);
 		log.info("vo:{}", vo);

 		// 스프링프레임워크에서 사용하던 리얼패스사용불가.
 		// String realPath = context.getRealPath("resources/upload_img");

 		// @Value("${file.dir}")로 획득한 절대경로 사용해야함.
 		log.info(realPath);

 		String originName = vo.getFile().getOriginalFilename();
 		log.info("originName:{}", originName);

 		if (originName.length() == 0) {// 넘어온 파일이 없을때 default.png 할당
 			vo.setProduct_img("default.png");
 		} else {
 			// 중복이미지 이름을 배제하기위한 처리
 			String save_name = "img_" + System.currentTimeMillis() + originName.substring(originName.lastIndexOf("."));
 			log.info("save_name:{}", save_name);
 			vo.setProduct_img(save_name);

 			File f = new File(realPath, save_name);
 			vo.getFile().transferTo(f);
 		}

 		int result = service.insertOK(vo);
 		log.info("result:{}", result);
 		if (result == 1) {
 			return "redirect:/admin/product/selectAll";
 		} else {
 			return "redirect:/admin/product/insert";
 		}
 	}

 	@PostMapping("/admin/product/updateOK")
 	public String updateOK(ProductVO vo) throws IllegalStateException, IOException {
 		log.info("/admin/product/updateOK");
 		vo.setPoint(vo.getPrice() / 1000);
 		log.info("vo:{}", vo);

 		// 스프링프레임워크에서 사용하던 리얼패스사용불가.
 		// String realPath = context.getRealPath("resources/upload_img");

 		// @Value("${file.dir}")로 획득한 절대경로 사용해야함.
 		log.info(realPath);

 		String originName = vo.getFile().getOriginalFilename();
 		log.info("originName.length():{}", originName.length());
 		log.info("vo.getProduct_img:{}", vo.getProduct_img());
 		if (originName.length() == 0) {// 넘어온 파일이 없을때 default.png 할당
 			vo.setProduct_img(vo.getProduct_img());
 		} else {
 			// 중복이미지 이름을 배제하기위한 처리
 			String save_name = "img_" + System.currentTimeMillis() + originName.substring(originName.lastIndexOf("."));
 			log.info("save_name:{}", save_name);
 			vo.setProduct_img(save_name);

 			File f = new File(realPath, save_name);
 			vo.getFile().transferTo(f);
 		}

 		int result = service.updateOK(vo);
 		log.info("result:{}", result);
 		if (result == 1) {
 			return "redirect:/admin/product/selectOne?product_num=" + vo.getProduct_num();
 		} else {
 			return "redirect:/admin/product/update?product_num=" + vo.getProduct_num();
 		}
 	}

 	@PostMapping("/admin/product/deleteOK")
 	public String deleteOK(ProductVO vo) {
 		log.info("/admin/product/deleteOK");
 		log.info("vo:{}", vo);

 		int result = service.deleteOK(vo);
 		log.info("result:{}", result);
 		if (result == 1) {
 			return "redirect:/admin/product/selectAll";
 		} else {
 			return "redirect:/admin/product/delete?Product_num=" + vo.getProduct_num();
 		}
 	}
}
