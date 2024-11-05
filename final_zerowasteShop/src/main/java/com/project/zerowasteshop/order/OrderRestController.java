package com.project.zerowasteshop.order;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.zerowasteshop.member.MemberService;
import com.project.zerowasteshop.payment.IamportService;
import com.project.zerowasteshop.payment.PaymentService;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class OrderRestController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	IamportService iamportService;
	
	@Autowired
	PaymentService paymentService;
	
	@Autowired
	MemberService memberService;
	
	@PostMapping("/order/applyPoints")
    public Map<String, Object> applyPoints(@RequestParam("points")int points,
    		@RequestParam("finalPrice")int finalPrice,
    		@RequestParam("currentDiscount")int currentDiscount
    		) {
		

		Map<String, Object> response = new HashMap<>();
		
		
		// 포인트 할인을 적용 (포인트 1:1 비율로 가정)
	    int pointsToUse = Math.min(points, finalPrice); // 사용할 수 있는 최대 포인트는 남은 금액
	    int newFinalPrice  = finalPrice - pointsToUse;

	    // 할인 금액에 포인트 할인을 추가
	    int totalDiscount = currentDiscount + pointsToUse;
	    
	    response.put("pointsToUse", pointsToUse);
	    response.put("finalPrice", newFinalPrice );
	    response.put("totalDiscount", totalDiscount);
		
	    // 결과 반환
	    return response;
    }
	
	@PostMapping("/order/pay")
	public Map<String, String> completePayment(
	    @RequestParam("imp_uid")String imp_uid, 
	    @RequestParam("merchant_uid") String merchant_uid, 
	    @RequestParam("paid_amount") int paid_amount,
	    @RequestParam("member_id") String member_id,      
	    @RequestParam("points_used") int points_used,
	    @RequestParam("coupon_code") String coupon_code
	) {
		 // PaymentService의 completePayment 메서드를 호출하여 결제 처리를 진행
        Map<String, String> response = paymentService.completePayment(imp_uid, merchant_uid, paid_amount, member_id, points_used,coupon_code);

        // 결과에 따라 메시지 추가
        if ("success".equals(response.get("status"))) {
            response.put("statusCode", "200"); // 성공 상태 코드 추가
        } else {
            response.put("statusCode", "400"); // 실패 상태 코드 추가
            orderService.getOrderInfo(merchant_uid);
        }

        return response; // ResponseEntity 없이 Map 직접 반환
    }
	
	
	// 주문 생성 요청 처리
    @PostMapping("/order/create")
    public Map<String, String> createOrder(@RequestBody OrderVO vo) {
        // 1.주문을 생성하고 order 테이블에 저장하고, 고유한 주문 번호(merchant_uid)를 반환
    	log.info("vo:{}",vo);
        String merchantUid = orderService.createOrder(vo);
        
        // 2. order_item 테이블에 주문한 상품 정보 저장 (OrderVO에 포함된 상품 정보 저장)
        List<OrderItemVO> orderItems = vo.getOrderItems(); // 주문한 상품 리스트 (필요한 상품 정보가 포함됨)
        log.info("orderItems:{}",orderItems);
        // 각각의 주문 아이템을 저장하는 로직
        orderService.saveOrderItems(merchantUid, orderItems);

        OrderVO vo2 = orderService.selectOneOrder(merchantUid);
        log.info("vo2.getFinal_price():{}",vo2.getFinal_price());
        // 응답으로 주문 번호를 반환
        Map<String, String> response = new HashMap<>();
        
        response.put("merchantUid", merchantUid);
        response.put("finalAmount", Integer.toString(vo2.getFinal_price()) );
        return response;
    }
    
    // 결제 실패시 주문 삭제 요청 처리
    @PostMapping("/order/delete")
    public Map<String, String> deleteOrder(@RequestParam("merchant_uid") String merchant_uid) {

            orderService.deleteOrderItem(merchant_uid);
            orderService.deleteOrder(merchant_uid);
            
            Map<String, String> response = new HashMap<>();
            
            response.put("success", "주문이 성공적으로 삭제되었습니다.");
            response.put("error", "주문 삭제 중 오류 발생" );
            
            return response;
    }
    
	// 재사용 포장재 반환 신청
    @PostMapping("/order/reusing")
    public String updateReusing(@RequestBody Map<String, Object> requestData) {
        
    	String member_id = (String) requestData.get("member_id"); // 회원 ID 가져오기
        boolean reusing = (Boolean) requestData.get("reusing"); // 재사용 상태 가져오기
        log.info("member_id:{}",member_id);
        log.info("reusing:{}",reusing);
    	orderService.updateReusing(member_id,reusing);
    	
    	return "재사용 포장재 반납 상태가 변경되었습니다.";
    }
	
}
