package com.project.zerowasteshop.payment;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.project.zerowasteshop.delivery.DeliveryService;
import com.project.zerowasteshop.delivery.DeliveryVO;
import com.project.zerowasteshop.member.MemberService;
import com.project.zerowasteshop.order.OrderItemVO;
import com.project.zerowasteshop.order.OrderService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentService {
	
	@Autowired
	PaymentMapper mapper;
	
	@Autowired
    private OrderService orderService;

    @Autowired
    private IamportService iamportService;

    @Autowired
    private MemberService memberService;

    @Autowired
    private DeliveryService deliveryService;
    
	public void savePayment(PaymentVO paymentInfo) {
		mapper.savePayment(paymentInfo);
		
	}

	public PaymentVO getPaymentInfo(String merchantUid) {
		PaymentVO payment = mapper.getPaymentInfo(merchantUid);
        return payment;
	}

	@Transactional
	public Map<String, String> completePayment(String imp_uid, String merchant_uid, int paid_amount, String member_id,
			int points_used) {
		Map<String, String> response = new HashMap<>();

        try {
            // 1. 아임포트에서 발급받은 토큰을 통해 결제 정보를 조회
            String token = iamportService.getToken();
            PaymentVO paymentInfo = iamportService.getPaymentInfo(imp_uid, token);

            // 2. 주문 금액 검증
            int orderAmount = orderService.getOrderAmount(merchant_uid);
            if (paymentInfo.getPaid_amount() == orderAmount) {
                
                // 3. 결제 정보 저장 및 운송장 번호 생성
                Random random = new Random();
                long randomNumber = Math.abs(random.nextLong() % 1000000000000L);
                String trackingNumber = String.format("%012d", randomNumber);

                paymentInfo.setTracking_number(trackingNumber);
                paymentInfo.setPay_status("결제 완료");
                paymentInfo.setPay_method("카드");
                this.savePayment(paymentInfo);

                // 4. 포인트 차감
                memberService.deductPoints(member_id, points_used);

                // 5. 배송 정보 생성 및 저장
                DeliveryVO deliveryInfo = new DeliveryVO();                                    
                deliveryInfo.setPay_num(this.getPaymentInfo(merchant_uid).getPay_num());                
                List<OrderItemVO> list = orderService.selectOneItem(merchant_uid);
                deliveryInfo.setProduct_name(list.get(0).getProduct_name());
                deliveryInfo.setMember_id(member_id);
                deliveryInfo.setMerchant_uid(merchant_uid);
                deliveryInfo.setTracking_num(trackingNumber);
                deliveryInfo.setCourier("롯데택배"); // 실제 서비스 이름으로 설정
                deliveryInfo.setDelivery_status("배송준비중");
                deliveryInfo.setShipped_date(new Timestamp(System.currentTimeMillis()+(24 * 60 * 60 * 1000))); // 배송 시작 시간 설정
                deliveryInfo.setDelivery_date(new Timestamp(System.currentTimeMillis()+(72 * 60 * 60 * 1000))); // 배송 완료 시간 설정
                
                deliveryService.saveDelivery(deliveryInfo); // 배송 정보 저장

                // 6. 주문 상태 업데이트
                orderService.updateOrderState(merchant_uid, "결제완료");

                response.put("status", "success");
                response.put("message", "결제가 성공적으로 처리되었습니다.");
            } else {
                response.put("status", "error");
                response.put("message", "결제 금액이 다릅니다.");
                orderService.updateOrderState(merchant_uid, "결제실패");
                mapper.updatePaymentStatus(merchant_uid, "결제실패");
            }
        } catch (Exception e) {
            e.printStackTrace();
            response.put("status", "error");
            response.put("message", "서버 오류: " + e.getMessage());
            orderService.updateOrderState(merchant_uid, "결제실패");
            mapper.updatePaymentStatus(merchant_uid, "결제실패");
        }

        return response;
    }

}
