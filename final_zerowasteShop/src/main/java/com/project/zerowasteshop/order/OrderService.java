package com.project.zerowasteshop.order;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.zerowasteshop.coupon.CouponVO;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
public class OrderService {
	
	@Autowired
	OrderMapper mapper;

	public List<OrderVO> selectAllPageBlock(int cpage, int pageBlock) {
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
		}else if(searchKey.equals("product_name")){
			return mapper.searchListPageBlockPname("%"+searchWord+"%",startRow,pageBlock);
		}else {
			return mapper.searchListPageBlockPayCheck("%"+searchWord+"%",startRow,pageBlock);
		}
	}

	public int getSearchTotalRows(String searchKey, String searchWord) {
		if(searchKey.equals("member_id")) {
			return mapper.getSearchTotalRowsId("%"+searchWord+"%");
		}else if(searchKey.equals("product_name")){
			return mapper.getSearchTotalRowsPname("%"+searchWord+"%");
		}else {
			return mapper.getSearchTotalRowsPayCheck("%"+searchWord+"%");
		}
	}

	public List<OrderJoinCouponVO> getAvailableCouponsForUser(String member_id) {
		// TODO Auto-generated method stub
		return mapper.getAvailableCouponsForUser(member_id);
	}

	public int getOrderAmount(String merchant_uid) {
		// TODO Auto-generated method stub
		return mapper.getOrderAmount(merchant_uid);
	}

	public String createOrder(OrderVO vo) {
		// 상품 금액 및 할인 금액 계산
        int totalPrice = 100;
        int discount = calculateDiscount(vo.getCoupon_code());
        int finalPrice = totalPrice - discount;

        // 고유한 주문 번호 생성
        String merchantUid = generateMerchantUid();

        // 주문 객체 생성 및 설정
        OrderVO order = new OrderVO();
        order.setMerchant_uid(merchantUid);
        order.setTotal_price(totalPrice);
        order.setDiscount(discount);
        order.setFinal_price(finalPrice);
        order.setAddress(vo.getAddress());
        order.setAddress_detail(vo.getAddress_detail());
        order.setPostcode(vo.getPostcode());
        order.setOrder_state("주문대기");

        // DB에 저장
        mapper.saveOrder(order);

        // 주문 번호 반환
        return merchantUid;
	}
	
	private int calculateTotalPrice(OrderVO vo) {
        // 상품 총액 계산 로직
        return 100;  // 예시 값
    }

    private int calculateDiscount(String couponCode) {
        // 할인 금액 계산 로직 (예시)
        return couponCode != null ? 5000 : 0;
    }

    private String generateMerchantUid() {
        return "O" + System.currentTimeMillis();  // 고유 주문 번호 생성
    }
}
