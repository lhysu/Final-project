package com.project.zerowasteshop.payment;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface PaymentMapper {

	PaymentVO getPaymentInfo(String merchantUid);
}
