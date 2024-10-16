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
	public Map<String, String> cp_insertOK(@RequestParam("couponCode") String couponCode,
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

}
