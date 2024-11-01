package com.project.zerowasteshop.payment;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Options;

@Mapper
public interface PaymentMapper {

	@Options(useGeneratedKeys = true, keyProperty = "pay_num")
	void savePayment(PaymentVO paymentInfo);

	PaymentVO getPaymentInfo(String merchantUid);

	void updatePaymentStatus(String merchant_uid, String pay_status);
}
