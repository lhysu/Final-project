package com.project.zerowasteshop.payment;

import org.springframework.web.client.RestTemplate;

import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

@Service
public class IamportService {

    private static final String IMP_KEY = "4265656253258734";
    private static final String IMP_SECRET = "HxbnwzrdM42OX38CiaAzfB31sEBgipBHCL8S9QMsrPY0rGdVZSlDcfcEWP07TldRbd60Fl7utGW3Gxdl";
    private final String TOKEN_URL = "https://api.iamport.kr/users/getToken";
    private final String PAYMENT_URL = "https://api.iamport.kr/payments/";

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
        payment.setStatus((String) responseData.get("status"));

        return payment;
    }
}