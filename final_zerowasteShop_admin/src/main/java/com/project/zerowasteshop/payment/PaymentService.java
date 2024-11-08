package com.project.zerowasteshop.payment;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class PaymentService {
	
	@Autowired
	PaymentMapper mapper;

	public PaymentVO getPaymentInfo(String merchantUid) {
		PaymentVO payment = mapper.getPaymentInfo(merchantUid);
        return payment;
	}
}
