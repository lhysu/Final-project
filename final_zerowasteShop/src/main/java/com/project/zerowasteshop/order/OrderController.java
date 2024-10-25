package com.project.zerowasteshop.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.zerowasteshop.coupon.CouponVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OrderController {
	
	@Autowired
	OrderService service;

	@GetMapping("/admin/order/selectAll")
	public String ad_orderSelectAll(Model model,
			@RequestParam(defaultValue = "1")int cpage,
			@RequestParam(defaultValue = "10")int pageBlock) {
		log.info("/admin/order/selectAll");
		log.info("cpage:{}",cpage);
		log.info("pageBlock:{}",pageBlock);
		
		List<OrderVO> list = service.selectAllPageBlock(cpage,pageBlock); //해당 페이지에 보여줄 5개행씩 만 검색
		log.info("list.size():{}",list.size());
		
		model.addAttribute("list",list);
		model.addAttribute("cpage",cpage);
		
		//DB로부터 얻은 검색결과의 모든 행의 수
		int total_rows = service.getTotalRows();
		log.info("total_rows:{}",total_rows);
		
		int totalPageCount; 
		
		//총 행카운트와 페이지블럭을 나눌 때의 알고리즘을 추가하기.
		if(total_rows%pageBlock!=0) {
			totalPageCount = (total_rows/pageBlock) +1;
		} else {
			totalPageCount = total_rows/pageBlock;
		}
		
		model.addAttribute("totalPageCount",totalPageCount);
		
		
		return "admin/order/selectAll";
	}
	
	 
	@GetMapping("/admin/order/searchList")
	public String ad_orderSearchList(Model model,
			@RequestParam(defaultValue = "member_id")String searchKey,
			@RequestParam(defaultValue = "9")String searchWord,
			@RequestParam(defaultValue = "1")int cpage,
			@RequestParam(defaultValue = "5")int pageBlock) {
		log.info("/admin/order/searchList");
		log.info("searchKey:{}",searchKey);
		log.info("searchWord:{}",searchWord);
		log.info("cpage:{}",cpage);
		log.info("pageBlock:{}",pageBlock);
		
		List<OrderVO> list = service.searchListPageBlock(searchKey,searchWord,cpage,pageBlock);
		log.info("list.size():{}",list.size());
		
		model.addAttribute("list",list);
		model.addAttribute("cpage",cpage);
		
		int total_rows = service.getSearchTotalRows(searchKey,searchWord); //select count(*) total_rows from member;
		log.info("total_rows:{}",total_rows);
		//int pageBlock =5; //1개 페이지에서 보여질 행의 수. 파라미터로 받으면 됨.
		int totalPageCount; 
		
		//총 행카운트와 페이지블럭을 나눌 때의 알고리즘을 추가하기.
		if(total_rows%pageBlock!=0) {
			totalPageCount = (total_rows/pageBlock) +1;
		} else {
			totalPageCount = total_rows/pageBlock;
		}
		
		model.addAttribute("totalPageCount",totalPageCount);
		
		return "admin/order/selectAll";
	}
	
	@GetMapping("/admin/order/selectOne")
	public String ad_orderSelectOne(Model model,
			@RequestParam("merchant_uid")String merchant_uid) {
		log.info("/admin/order/selectOne");
		log.info("merchant_uid:{}",merchant_uid);
		
		OrderVO vo = service.selectOne(merchant_uid);
		
		model.addAttribute("vo",vo);
		
		return "admin/order/selectOne";
	}
	
	@GetMapping("/order/order")
	public String insert(Model model,
			@RequestParam(defaultValue = "홍길동")String name,
			@RequestParam(defaultValue = "010-3333-3333")String tel,
			@RequestParam(defaultValue = "13529")String postcode,
			@RequestParam(defaultValue = "경기 성남시 분당구 판교역로 166")String address,
			@RequestParam(defaultValue = "101호")String address_detail,
			@RequestParam(defaultValue = "/upload_img/bag.png")String product_img,
			@RequestParam(defaultValue = "가방1")String product_name,
			@RequestParam(defaultValue = "80000")int price,
			@RequestParam(defaultValue = "user19")String member_id,
			@RequestParam(defaultValue = "1000")int points) {
		
		model.addAttribute("name",name);
		model.addAttribute("tel",tel);
		model.addAttribute("postcode",postcode);
		model.addAttribute("address",address);
		model.addAttribute("address_detail",address_detail);
		model.addAttribute("product_img",product_img);
		model.addAttribute("product_name",product_name);
		model.addAttribute("price",price);
		model.addAttribute("points",points);
		
	    List<OrderJoinCouponVO> coupons = service.getAvailableCouponsForUser(member_id); // 유저별 사용 가능한 쿠폰 조회
	    log.info("coupons:{}",coupons);
	    model.addAttribute("coupons", coupons);
		
			
		return "order/order";
	}
	
	@GetMapping("/order/selectAll")
	public String orderSelectAll(Model model,
			@RequestParam(defaultValue = "1")int cpage,
			@RequestParam(defaultValue = "5")int pageBlock) {
		log.info("/order/selectAll");
		log.info("cpage:{}",cpage);
		log.info("pageBlock:{}",pageBlock);
		
		List<OrderVO> list = service.selectAllPageBlock(cpage,pageBlock); //해당 페이지에 보여줄 5개행씩 만 검색
		log.info("list.size():{}",list.size());
		
		model.addAttribute("list",list);
		
		//DB로부터 얻은 검색결과의 모든 행의 수
		int total_rows = service.getTotalRows();
		log.info("total_rows:{}",total_rows);
		
		int totalPageCount; 
		
		//총 행카운트와 페이지블럭을 나눌 때의 알고리즘을 추가하기.
		if(total_rows%pageBlock!=0) {
			totalPageCount = (total_rows/pageBlock) +1;
		} else {
			totalPageCount = total_rows/pageBlock;
		}
		
		model.addAttribute("totalPageCount",totalPageCount);
		
		
		return "order/selectAll";
	}
	
}
