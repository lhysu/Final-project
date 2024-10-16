package com.project.zerowasteshop.coupon;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface CouponMapper {
	public List<CouponVO> selectAll();
	
	public int insertOK(CouponVO vo);

	public int checkCouponCode(String couponCode);

	public void registerCoupon(String couponCode,String member_id);

	public List<CouponVO> selectAllPageBlock(int startRow, int pageBlock);

	public int getTotalRows();

	public void createCoupons(CouponVO coupon);

}
