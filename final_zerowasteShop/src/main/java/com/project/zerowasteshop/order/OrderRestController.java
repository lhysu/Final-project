package com.project.zerowasteshop.order;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.project.zerowasteshop.coupon.CouponService;
import com.project.zerowasteshop.member.MemberVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
public class OrderRestController {
	
	@Autowired
	OrderService orderService;
	
	
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
	
}
