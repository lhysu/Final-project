package com.project.zerowasteshop.payment;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.project.zerowasteshop.coupon.CouponVO;
import com.project.zerowasteshop.order.OrderItemVO;
import com.project.zerowasteshop.order.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class PaymentController {
	
	@Autowired
    PaymentService paymentService;
	
	@Autowired
	OrderService orderService;

    // 결제 완료 페이지로 이동하는 메소드
    @GetMapping("/payment/payment")
    public String completePayment(@RequestParam("merchant_uid") String merchantUid, Model model) {
        // 결제 정보를 가져옴
        PaymentVO paymentInfo = paymentService.getPaymentInfo(merchantUid);

        //주문 상품 정보를 가져옴
        List<OrderItemVO> list = orderService.selectOneItem(merchantUid);
        
        
        // 상품명 리스트를 콤마로 구분된 문자열로 변환
        String productNames = list.stream()
                                  .map(OrderItemVO::getProduct_name) // 각 상품의 이름을 가져옴
                                  .collect(Collectors.joining(", "));
        // 가져온 결제 정보를 모델에 추가
        model.addAttribute("paymentInfo", paymentInfo);
        model.addAttribute("productNames", productNames);
        
        return "payment/payment";  // 결제 완료 화면으로 이동
    }
	
}
