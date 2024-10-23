package com.project.zerowasteshop.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.zerowasteshop.coupon.CouponService;
import com.project.zerowasteshop.member.MemberVO;
import com.project.zerowasteshop.payment.IamportService;
import com.project.zerowasteshop.payment.PaymentVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class OrderRestController {
	
	@Autowired
	OrderService orderService;
	
	@Autowired
	IamportService iamportService;
	
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
	    @RequestParam String imp_uid, 
	    @RequestParam String merchant_uid, 
	    @RequestParam int paid_amount
	) {
	    Map<String, String> response = new HashMap<>();

	    try {
	        // 1. 아임포트에서 발급받은 토큰을 통해 결제 정보를 조회
	        String token = iamportService.getToken();  // 아임포트 API 토큰 발급
	        PaymentVO paymentInfo = iamportService.getPaymentInfo(imp_uid, token);
	        log.info("paymentInfo:{}",paymentInfo);

	        // 2. 서버에서 관리하는 주문 금액 가져오기
	        int orderAmount = orderService.getOrderAmount(merchant_uid);
	        log.info("orderAmount:{}",orderAmount);
	        // 3. 결제 금액 검증: 실제 결제된 금액이 주문 금액과 같은지 확인
	        if (paymentInfo.getPaid_amount() == orderAmount) {
	            // 결제 성공 처리 (결제 금액이 일치하고, 결제 상태가 'paid'인 경우)
	        	response.put("status", "success");
	        	response.put("message", "결제가 성공적으로 처리되었습니다.");
	        } else {
	            // 결제 금액 불일치 또는 결제 실패 처리
	        	response.put("status", "error");
	        	response.put("message", "결제 금액이 다릅니다.");
	        }
	    } catch (Exception e) {
	        // 예외 처리
	        e.printStackTrace();
	        response.put("status", "error");
	        response.put("message", "서버 오류: " + e.getMessage());
	    }

	    return response;
	}
	
	// 주문 생성 요청 처리
    @PostMapping("/order/create")
    public Map<String, String> createOrder(@RequestBody OrderVO vo) {
        // 주문을 생성하고, 고유한 주문 번호(merchant_uid)를 반환
        String merchantUid = orderService.createOrder(vo);

        // 응답으로 주문 번호를 반환
        Map<String, String> response = new HashMap<>();
        response.put("merchantUid", merchantUid);
        return response;
    }
	
}
