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
}
