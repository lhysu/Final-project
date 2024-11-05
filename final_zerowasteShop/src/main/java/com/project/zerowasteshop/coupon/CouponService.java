package com.project.zerowasteshop.coupon;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class CouponService {

	@Autowired
	CouponMapper mapper;
	
	public List<CouponVO> selectAll(String user_id){
		return mapper.selectAll(user_id);
	}	

	public boolean checkCouponCode(String couponCode) {
		return mapper.checkCouponCode(couponCode)>0;
	}

	public void registerCoupon(String couponCode,String member_id) {
		mapper.registerCoupon(couponCode,member_id);
		
	}

	public CouponVO getCouponInfo(String couponCode) {
		// TODO Auto-generated method stub
		return mapper.getCouponInfo(couponCode);
	}

	public void updateCouponStatus(String coupon_code, int i) {
		mapper.updateCouponStatus(coupon_code,i);
	}
	
}
