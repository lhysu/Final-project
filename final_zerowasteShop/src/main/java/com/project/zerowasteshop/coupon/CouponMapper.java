package com.project.zerowasteshop.coupon;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper {
	
	public List<CouponVO> selectAll(String user_id);

	public int checkCouponCode(String couponCode);

	public void registerCoupon(String couponCode,String member_id);

	public CouponVO getCouponInfo(String couponCode);

	public void updateCouponStatus(String coupon_code, int i);

}
