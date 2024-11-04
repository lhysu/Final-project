package com.project.zerowasteshop.payment;

import org.springframework.web.client.RestTemplate;

import com.project.zerowasteshop.order.OrderMapper;
import com.siot.IamportRestClient.IamportClient;
import com.siot.IamportRestClient.exception.IamportResponseException;
import com.siot.IamportRestClient.response.IamportResponse;
import com.siot.IamportRestClient.response.Payment;

import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class IamportService {
	
	@Autowired
	OrderMapper orderMapper;
	
	@Autowired
	PaymentMapper paymentMapper;
	

    private static final String IMP_KEY = "4265656253258734";
    private static final String IMP_SECRET = "HxbnwzrdM42OX38CiaAzfB31sEBgipBHCL8S9QMsrPY0rGdVZSlDcfcEWP07TldRbd60Fl7utGW3Gxdl";
    private final String TOKEN_URL = "https://api.iamport.kr/users/getToken";
    private final String CANCEL_URL = "https://api.iamport.kr/payments/cancel";
    private final String PAYMENT_URL = "https://api.iamport.kr/payments/";
    
    private IamportClient client;

    public IamportService() {
        this.client = new IamportClient(IMP_KEY, IMP_SECRET);
    }

    // 아임포트 API 토큰 발급
    public String getToken() {
        RestTemplate restTemplate = new RestTemplate();
        
        // HTTP 요청 헤더에 토큰 추가
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_JSON);

        String requestJson = "{\"imp_key\": \"" + IMP_KEY + "\", \"imp_secret\": \"" + IMP_SECRET + "\"}";
        HttpEntity<String> entity = new HttpEntity<>(requestJson, headers);

        ResponseEntity<Map> response = restTemplate.exchange(TOKEN_URL, HttpMethod.POST, entity, Map.class);
        Map<String, Object> body = response.getBody();

        Map<String, Object> responseData = (Map<String, Object>) body.get("response");
        return (String) responseData.get("access_token");
    }
    
    // 결제 정보 조회
    public PaymentVO getPaymentInfo(String impUid,String token) throws Exception {
        token = getToken(); // 토큰 발급
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);
        HttpEntity<String> entity = new HttpEntity<>(headers);

        String paymentUrl = PAYMENT_URL + impUid;
        ResponseEntity<Map> response = restTemplate.exchange(paymentUrl, HttpMethod.GET, entity, Map.class);
        Map<String, Object> body = response.getBody();

        Map<String, Object> responseData = (Map<String, Object>) body.get("response");
        
        // 필요한 필드만 추출해서 반환
        PaymentVO payment = new PaymentVO();
        payment.setImp_uid((String) responseData.get("imp_uid"));
        payment.setMerchant_uid((String) responseData.get("merchant_uid"));
        payment.setPaid_amount((Integer) responseData.get("amount"));
        payment.setPay_status((String) responseData.get("status"));
        payment.setPay_method((String) responseData.get("pay_method"));
        payment.setMember_id((String) responseData.get("member_id"));
        return payment;
    }
    
    // 결제 취소 로직
    public boolean cancelPayment(String merchant_uid, int amount,String delivery_state) {
        String token = getToken();
        RestTemplate restTemplate = new RestTemplate();

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", token);

        Map<String, Object> requestMap = new HashMap<>();
        requestMap.put("merchant_uid", merchant_uid);
        requestMap.put("amount", amount); // 부분 취소할 금액 설정

        HttpEntity<Map<String, Object>> entity = new HttpEntity<>(requestMap, headers);

        try {
            ResponseEntity<Map> response = restTemplate.exchange(CANCEL_URL, HttpMethod.POST, entity, Map.class);
            Map<String, Object> body = response.getBody();

            if (body != null) {
                Map<String, Object> responseData = (Map<String, Object>) body.get("response");

                // 아임포트에서 상태가 'cancelled'일 경우에만 취소 성공으로 간주
                if (responseData != null && "cancelled".equals(responseData.get("status"))) {
                    // 결제 취소나 환불이 성공하면 order,order_item,payment 테이블에 상태 반영
                	if (delivery_state.equals("배송준비중")) {
                		updateOrderState(merchant_uid,"결제취소");
                        updatePaymentStatus(merchant_uid,"결제취소");
                	} else if (delivery_state.equals("배송완료")) {
                		updateOrderState(merchant_uid,"환불완료");
                        updatePaymentStatus(merchant_uid,"결제취소");
                	}
                	
                    return true;
                }
            }
        } catch (Exception e) {
        	log.error("Error during payment cancellation", e); // 예외 로그 출력
            return false;
        }

        return false;
    }

    // 부분 환불 성공 시 데이터베이스의 order_item 상태를 업데이트하는 메서드
    private void updateOrderState(String merchant_uid,String orderState) {
    	orderMapper.updateOrderState(merchant_uid,orderState);
    }    
    
    private void updatePaymentStatus(String merchant_uid,String pay_status) {
    	paymentMapper.updatePaymentStatus(merchant_uid,pay_status);
    }
    
    
}
