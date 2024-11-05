package com.project.zerowasteshop.order;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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
	
	@PostMapping("/order/order")
	public String insert(Model model,
			@RequestParam("product_num")int product_num,
			@RequestParam("price")String price,
			@RequestParam("product_name")String product_name,
			@RequestParam("quantity")int quantity,
			HttpSession session) {
		//로그인 정보 불러오기
		String user_id = (String) session.getAttribute("user_id");
		
		//배송지 정보 불러오고 넘기기
		MemberVO member = memberService.selectOne(user_id);
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
		
	    List<CouponVO> coupons = service.getAvailableCouponsForUser(user_id); // 유저별 사용 가능한 쿠폰 조회
	    log.info("coupons:{}",coupons);
	    model.addAttribute("coupons", coupons);
		
			
		return "order/order";
	}
	
	@PostMapping("/order/cartOrder")
	public String insertMultiProducts(Model model,
			@RequestParam("productNums") List<Integer> productNums,
			@RequestParam("member_id")String member_id,
			@RequestParam("prices") List<Integer> prices, // 가격 목록 추가
	        @RequestParam("quantities") List<Integer> quantities // 수량 목록 추가
	        ) {
		
		//배송지 정보 불러오고 넘기기
		MemberVO member = memberService.selectOne(member_id);
		model.addAttribute("name",member.getName());
		model.addAttribute("tel",member.getPhone_number());
		model.addAttribute("postcode",member.getPostcode());
		model.addAttribute("address",member.getAddress());
		model.addAttribute("address_detail",member.getAddress_detail());
		model.addAttribute("points",member.getPoints());
		model.addAttribute("member_id",member.getMember_id());
		
		// 여러 상품 정보를 저장할 리스트 생성
	    List<ProductVO> products = new ArrayList<>();
	    
	    for (int i = 0; i < productNums.size(); i++) {
	        ProductVO product = new ProductVO();
	        product.setProduct_num(productNums.get(i));
	        product = productService.selectOne(product);
	        product.setProduct_name(product.getProduct_name()); // 제품명 설정
	        product.setPrice(prices.get(i)); // 개별 가격 설정
	        product.setCount(quantities.get(i)); // 개별 수량 설정
	        
	        log.info("product:{}", product);
	        products.add(product);
	    }

	    model.addAttribute("products", products);	
	    
	    int totalPrice = products.stream()
	    	    .mapToInt(item -> item.getPrice())
	    	    .sum();
	    
	    model.addAttribute("totalPrice", totalPrice);
		
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
		log.info("list:{}",list);
		
		// 주문 번호별로 데이터를 그룹화
		Map<String, List<OrderJoinProductVO>> groupedOrders = list.stream()
			    .collect(Collectors.groupingBy(
			        OrderJoinProductVO::getMerchant_uid,
			        LinkedHashMap::new, // 순서를 유지하기 위해 LinkedHashMap 사용
			        Collectors.toList()
			    ));
		
	    model.addAttribute("groupedOrders", groupedOrders);
	    model.addAttribute("list", list);
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
			@RequestParam("merchant_uid")String merchant_uid
			) {
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
		List<OrderJoinProductVO> list = service.selectAllByUser(merchant_uid);
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
		List<OrderJoinProductVO> list = service.selectAllByUser(merchant_uid);
		log.info("list.size():{}",list.size());
		
		model.addAttribute("list",list);
		
		model.addAttribute("order",order);
		model.addAttribute("order_item",order_item);
		model.addAttribute("payment",payment);
		model.addAttribute("member",member);
		
		return "order/selectOne";
	}
	
}
