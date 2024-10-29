package com.project.zerowasteshop.coupon;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class CouponRestController {
	
	@Autowired
	CouponService service;
	
	@PostMapping("/coupon/insertOK") //POST 요청 처리
	public Map<String, String> insertCouponOK(@RequestParam("couponCode") String couponCode,
			@RequestParam("member_id") String member_id) {
	    log.info("/coupon/insertOK");

	    Map<String, String> response = new HashMap<>();

	    // 쿠폰 코드 유효성 확인
	    boolean isValid = service.checkCouponCode(couponCode);

	    if (isValid) {
	        service.registerCoupon(couponCode,member_id);
	        response.put("status", "success");
	        response.put("message", "쿠폰이 성공적으로 등록되었습니다.");
	    } else {
	        response.put("status", "error");
	        response.put("message", "유효하지 않거나 이미 등록된 쿠폰 코드입니다.");
	    }

	    return response; // JSON 형태로 응답
	}
	
	@PostMapping("/coupon/updateOK") //POST 요청 처리
	public Map<String, String> ad_updateCouponOK(CouponVO vo) {
	    log.info("/coupon/updateOK");

	    Map<String, String> response = new HashMap<>();

	    int result = service.updateOK(vo);
	    
	    if (result > 0) {
	        response.put("status", "success");
	        response.put("message", "쿠폰이 성공적으로 수정되었습니다.");
	    } else {
	        response.put("status", "error");
	        response.put("message", "쿠폰 수정에 실패했습니다.");
	    }
	    
	    return response;  // JSON 형태로 응답 반환
	}
	
	
	@PostMapping("/admin/coupon/delete") //POST 요청 처리
	public Map<String, String> ad_deleteCouponOK(CouponVO vo) {
	    log.info("/admin/coupon/delete");

	    Map<String, String> response = new HashMap<>();

	    int result = service.deleteCoupon(vo);
	    
	    if (result > 0) {
	        response.put("status", "success");
	        response.put("message", "쿠폰이 성공적으로 삭제되었습니다.");
	    } else {
	        response.put("status", "error");
	        response.put("message", "쿠폰 삭제에 실패했습니다.");
	    }
	    
	    return response;  // JSON 형태로 응답 반환
	}
	
	@PostMapping("/order/applyCoupon")
    public Map<String, Object> applyCoupon(@RequestParam("couponCode") String couponCode,
                                           @RequestParam("totalPrice") int totalPrice,
                                           @RequestParam("points")int points) {
        // 쿠폰 정보를 가져옴
        CouponVO coupon = service.getCouponInfo(couponCode);

        if (coupon != null) {
            // 할인율 계산
            int discountRate = coupon.getDiscount_rate(); // 할인율 가져오기
            int discountAmount = (totalPrice * discountRate) / 100 + points; // 할인 금액 계산
            int finalPrice = totalPrice - discountAmount; // 최종 결제 금액

            // 응답으로 할인 금액과 최종 결제 금액 반환
            Map<String, Object> response = new HashMap<>();
            response.put("status", "success");
            response.put("discountAmount", discountAmount);
            response.put("finalPrice", finalPrice);

            return response; // 응답 전송
        } else {
            // 쿠폰이 없는 경우 실패 응답
            Map<String, Object> response = new HashMap<>();
            response.put("status", "fail");
            response.put("message", "해당 쿠폰이 없습니다.");

            return response; // 실패 응답 전송
        }
    }
}
