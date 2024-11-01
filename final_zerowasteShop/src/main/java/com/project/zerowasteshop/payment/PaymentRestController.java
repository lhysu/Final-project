package com.project.zerowasteshop.payment;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.project.zerowasteshop.coupon.CouponService;
import com.project.zerowasteshop.member.MemberService;
import com.project.zerowasteshop.member.MemberVO;
import com.project.zerowasteshop.payment.IamportService;
import com.project.zerowasteshop.payment.PaymentService;
import com.project.zerowasteshop.payment.PaymentVO;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
public class PaymentRestController {

	@Autowired
	IamportService iamportService;

	@PostMapping("/payment/refund")
	public ResponseEntity<Map<String, Object>> refund(@RequestBody Map<String, Object> requestData) {
		
		String merchant_uid = (String) requestData.get("merchant_uid");
	    int amount = Integer.parseInt(requestData.get("amount").toString());
	    String delivery_state = (String) requestData.get("delivery_state");
		Map<String, Object> response = new HashMap<>();

		log.info("merchant_uid:{}", merchant_uid);
		log.info("amount:{}", amount);
		log.info("delivery_state:{}", delivery_state);
		
		try {
			// 포트원 결제 취소 API 호출
			boolean refundResult = iamportService.cancelPayment(merchant_uid, amount,delivery_state);
			response.put("success", refundResult);
			
			return ResponseEntity.ok(response);
		} catch (Exception e) {
			response.put("success", false);
			response.put("error", e.getMessage());
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
	}

}
