package com.project.zerowasteshop.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.project.zerowasteshop.coupon.CouponVO;
import com.project.zerowasteshop.member.MemberService;
import com.project.zerowasteshop.member.MemberVO;
import com.project.zerowasteshop.payment.PaymentService;
import com.project.zerowasteshop.payment.PaymentVO;
import com.project.zerowasteshop.product.model.ProductVO;
import com.project.zerowasteshop.product.service.ProductService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class OrderController {
	
	@Autowired
	OrderService service;
	
	@Autowired
	MemberService memberService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	PaymentService paymentService;

	@GetMapping("/admin/order/selectAll")
	public String ad_orderSelectAll(Model model,
			@RequestParam(defaultValue = "1")int cpage,
			@RequestParam(defaultValue = "10")int pageBlock) {
		log.info("/admin/order/selectAll");
		log.info("cpage:{}",cpage);
		log.info("pageBlock:{}",pageBlock);
		
		List<OrderJoinProductVO> list = service.selectAllPageBlock(cpage,pageBlock); //해당 페이지에 보여줄 5개행씩 만 검색
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
		
		OrderVO order = service.selectOneOrder(merchant_uid);
		List<OrderItemVO> order_item = service.selectOneItem(merchant_uid);
		PaymentVO payment = paymentService.getPaymentInfo(merchant_uid);
		log.info("order:{}",order);
		log.info("order_item:{}",order_item);
		
		model.addAttribute("order",order);
		model.addAttribute("order_item",order_item);
		model.addAttribute("payment",payment);
		
		return "admin/order/selectOne";
	}	
}
