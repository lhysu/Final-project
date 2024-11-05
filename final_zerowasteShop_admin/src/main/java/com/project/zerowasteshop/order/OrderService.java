package com.project.zerowasteshop.order;


import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.project.zerowasteshop.coupon.CouponMapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {
	
	@Autowired
	OrderMapper mapper;
	
	@Autowired
	CouponMapper couponMapper;

	public List<OrderJoinProductVO> selectAllPageBlock(int cpage, int pageBlock) {
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

	public List<OrderVO> searchListPageBlock(String searchKey, String searchWord, int cpage, int pageBlock) {
		int startRow = pageBlock*(cpage-1); //mysql은 limit 처리시 0행부터 시작
		log.info("startRow:{}",startRow);
		log.info("pageBlock:{}",pageBlock);
		
		if(searchKey.equals("member_id")) {
			return mapper.searchListPageBlockId("%"+searchWord+"%",startRow,pageBlock);
		}else {
			return mapper.searchListPageBlockPname("%"+searchWord+"%",startRow,pageBlock);
		}
	}

	public int getSearchTotalRows(String searchKey, String searchWord) {
		if(searchKey.equals("member_id")) {
			return mapper.getSearchTotalRowsId("%"+searchWord+"%");
		}else {
			return mapper.getSearchTotalRowsPname("%"+searchWord+"%");
		}
	}	

	public OrderVO selectOneOrder(String merchant_uid) {
		return mapper.selectOneOrder(merchant_uid);
	}

	public List<OrderItemVO> selectOneItem(String merchant_uid) {
		// TODO Auto-generated method stub
		return mapper.selectOneItem(merchant_uid);
	}
}
