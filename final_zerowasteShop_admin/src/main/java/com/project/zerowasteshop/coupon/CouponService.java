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

	public List<CouponVO> searchListPageBlock(String searchKey, String searchWord, int cpage, int pageBlock) {
		int startRow = pageBlock*(cpage-1); //mysql은 limit 처리시 0행부터 시작
		log.info("startRow:{}",startRow);
		log.info("pageBlock:{}",pageBlock);
		
		
		if(searchKey.equals("member_id")) {
			return mapper.searchListPageBlockId("%"+searchWord+"%",startRow,pageBlock);
		}else {
			return mapper.searchListPageBlockUsed("%"+searchWord+"%",startRow,pageBlock);
		}
	}

	public int getSearchTotalRows(String searchKey, String searchWord) {
		if(searchKey.equals("member_id")) {
			return mapper.getSearchTotalRowsId("%"+searchWord+"%");
		}else {
			return mapper.getSearchTotalRowsUsed("%"+searchWord+"%");
		}
	}

	public CouponVO selectOne(CouponVO vo) {
		return mapper.selectOne(vo);
	}

	public int updateOK(CouponVO vo) {
		return mapper.updateOK(vo);
	}

	public int deleteCoupon(CouponVO vo) {
		return mapper.deleteCoupon(vo);
	}
	
}
