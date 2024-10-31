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
	
	@GetMapping("/order/order")
	public String insert(Model model,
			@RequestParam("product_num")int product_num,
			@RequestParam("member_id")String member_id,
			@RequestParam("price")String price,
			@RequestParam("product_name")String product_name,
			@RequestParam(defaultValue = "1")int quantity) {
		
		//배송지 정보 불러오고 넘기기
		MemberVO member = memberService.selectOne(member_id);
		model.addAttribute("name",member.getName());
		model.addAttribute("tel",member.getPhone_number());
		model.addAttribute("postcode",member.getPostcode());
		model.addAttribute("address",member.getAddress());
		model.addAttribute("address_detail",member.getAddress_detail());
		model.addAttribute("points",member.getPoints());
		model.addAttribute("member_id",member.getMember_id());
		
		//상품 정보 불러오고 넘기기
		ProductVO product = new ProductVO();
		product.setProduct_num(product_num);
		product = productService.selectOne(product);
		model.addAttribute("product",product);		
		model.addAttribute("quantity",quantity);				
		
	    List<CouponVO> coupons = service.getAvailableCouponsForUser(member_id); // 유저별 사용 가능한 쿠폰 조회
	    log.info("coupons:{}",coupons);
	    model.addAttribute("coupons", coupons);
		
			
		return "order/order";
	}
	
	@GetMapping("/order/selectAll")
	public String orderSelectAll(Model model,
			@RequestParam(defaultValue = "1")int cpage,
			@RequestParam(defaultValue = "5")int pageBlock,
			HttpSession session,
			RedirectAttributes redirectAttributes) {
		log.info("/order/selectAll");
		log.info("cpage:{}",cpage);
		log.info("pageBlock:{}",pageBlock);
		
		// 세션에서 로그인된 사용자 ID를 가져옴
	    String userId = (String) session.getAttribute("user_id");
	    log.info("userId:{}",userId);
	    if (userId == null) {
	    	// 로그인되지 않은 상태라면 로그인 페이지로 리다이렉트하면서 알림 메시지를 추가
	        redirectAttributes.addFlashAttribute("alertMessage", "회원만 이용할 수 있습니다.");
	        return "redirect:/member/m_login";
	    }
		
		// 주문과 상품 정보 불러오기
		List<OrderJoinProductVO> list = service.selectAllPageBlockByUser(cpage,pageBlock,userId); //해당 페이지에 보여줄 5개행씩 만 검색
		log.info("list.size():{}",list.size());
		
		model.addAttribute("list",list);
		model.addAttribute("cpage",cpage);
		
		// 
		//DB로부터 얻은 검색결과의 모든 행의 수
		int total_rows = service.getTotalRowsByUser(userId);
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
	
	@GetMapping("/order/selectOne")
	public String orderSelectOne(Model model,
			HttpSession session,
			@RequestParam("merchant_uid")String merchant_uid,
			@RequestParam("product_num")int product_num) {
		log.info("/order/selectOne");
		log.info("merchant_uid:{}",merchant_uid);
		
		// 세션에서 로그인된 사용자 ID를 가져옴
	    String userId = (String) session.getAttribute("user_id");
	    log.info("userId:{}",userId);
	    if (userId == null) {
	        // 로그인되지 않은 상태라면 로그인 페이지로 리다이렉트
	        return "redirect:/m_login";
	    }
		
		OrderVO order = service.selectOneOrder(merchant_uid);
		List<OrderItemVO> order_item = service.selectOneItem(merchant_uid);
		PaymentVO payment = paymentService.getPaymentInfo(merchant_uid);
		MemberVO member = memberService.selectOne(userId);
		log.info("order:{}",order);
		log.info("order_item:{}",order_item);
		
		// 상품이미지, 상품개수,상품가격 불러오기
		List<OrderJoinProductVO> list = service.selectAllByUser(merchant_uid,product_num);
		log.info("list.size():{}",list.size());
		
		model.addAttribute("list",list);
		
		model.addAttribute("order",order);
		model.addAttribute("order_item",order_item);
		model.addAttribute("payment",payment);
		model.addAttribute("member",member);
		
		return "order/selectOne";
	}
	
	@GetMapping("/order/cancelSelectOne")
	public String cancleSelectOne(Model model,
			HttpSession session,
			@RequestParam("merchant_uid")String merchant_uid,
			@RequestParam("product_num")int product_num) {
		log.info("/order/selectOne");
		log.info("merchant_uid:{}",merchant_uid);
		
		// 세션에서 로그인된 사용자 ID를 가져옴
	    String userId = (String) session.getAttribute("user_id");
	    log.info("userId:{}",userId);
	    if (userId == null) {
	        // 로그인되지 않은 상태라면 로그인 페이지로 리다이렉트
	        return "redirect:/m_login";
	    }
		
		OrderVO order = service.selectOneOrder(merchant_uid);
		List<OrderItemVO> order_item = service.selectOneItem(merchant_uid);
		PaymentVO payment = paymentService.getPaymentInfo(merchant_uid);
		MemberVO member = memberService.selectOne(userId);
		log.info("order:{}",order);
		log.info("order_item:{}",order_item);
		
		// 상품이미지, 상품개수,상품가격 불러오기
		List<OrderJoinProductVO> list = service.selectAllByUser(merchant_uid,product_num);
		log.info("list.size():{}",list.size());
		
		model.addAttribute("list",list);
		
		model.addAttribute("order",order);
		model.addAttribute("order_item",order_item);
		model.addAttribute("payment",payment);
		model.addAttribute("member",member);
		
		return "order/selectOne";
	}
	
}
