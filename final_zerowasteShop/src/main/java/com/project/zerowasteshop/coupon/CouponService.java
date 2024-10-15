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
	
	public List<CouponVO> selectAll(){
		return mapper.selectAll();
	}
	
	public int insertOK(CouponVO vo) {
		return mapper.insertOK(vo);
	}

	public boolean checkCouponCode(String couponCode) {
		return mapper.checkCouponCode(couponCode)>0;
	}

	public void registerCoupon(String couponCode,String member_id) {
		mapper.registerCoupon(couponCode,member_id);
		
	}

	public List<CouponVO> selectAllPageBlock(int cpage, int pageBlock) {
		//mysql인 경우 limit 시작행을 얻어내는 알고리즘이 필요하다.
		//예: 1페이지(0,5), 2페이지(5,5),3페이지(10,5)
		int startRow = pageBlock*(cpage-1);
		log.info("startRow:{}",startRow);
		log.info("pageBlock:{}",pageBlock);
		
		return mapper.selectAllPageBlock(startRow,pageBlock);
	}

	public int getTotalRows() {
		return mapper.getTotalRows();
	}

	public void createCoupons(List<CouponVO> list) {
		for (CouponVO coupon : list) {
			mapper.createCoupons(coupon);
		}
		
	}
	
}