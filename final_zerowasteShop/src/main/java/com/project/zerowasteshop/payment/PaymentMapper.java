package com.project.zerowasteshop.payment;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

	void savePayment(PaymentVO paymentInfo);

	PaymentVO getPaymentInfo(String merchantUid);
}
