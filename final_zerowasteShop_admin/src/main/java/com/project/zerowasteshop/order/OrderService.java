package com.project.zerowasteshop.order;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.project.zerowasteshop.coupon.CouponMapper;
import com.project.zerowasteshop.coupon.CouponVO;

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
		}else if(searchKey.equals("product_name")){
			return mapper.getSearchTotalRowsPname("%"+searchWord+"%");
		}else {
			return mapper.getSearchTotalRowsPayCheck("%"+searchWord+"%");
		}
	}

	public List<CouponVO> getAvailableCouponsForUser(String member_id) {
		// TODO Auto-generated method stub
		return mapper.getAvailableCouponsForUser(member_id);
	}

	public int getOrderAmount(String merchant_uid) {
		// TODO Auto-generated method stub
		return mapper.getOrderAmount(merchant_uid);
	}

	public String createOrder(OrderVO vo) {
		// 상품 금액 및 할인 금액 계산
        int totalPrice = vo.getTotal_price();
        log.info("totalPrice:{}",totalPrice);
        int discount = calculateDiscount(vo.getCoupon_code());
        int finalPrice = (int) (totalPrice * (1 - (discount / 100.0)))- vo.getPoints();

        // 고유한 주문 번호 생성
        String merchantUid = generateMerchantUid();

        // 주문 객체 생성 및 설정
        OrderVO order = new OrderVO();
        order.setMerchant_uid(merchantUid);
        order.setMember_id(vo.getMember_id());
        String couponCode = vo.getCoupon_code();
        if (couponCode == null || couponCode.isEmpty()) {
            couponCode = null;  // 쿠폰 코드가 없으면 null로 설정
        }
        order.setCoupon_code(couponCode);
        order.setPostcode(vo.getPostcode());
        order.setAddress(vo.getAddress());
        order.setAddress_detail(vo.getAddress_detail());
        order.setTel(vo.getTel());
        order.setPoints(vo.getPoints());
        order.setReusing(vo.isReusing());
        order.setDiscount(discount);
        order.setDelivery_fee(vo.getDelivery_fee());
        order.setDelivery_memo(vo.getDelivery_memo());        
        order.setTotal_price(totalPrice);
        order.setFinal_price(finalPrice);
        order.setOrder_state("결제대기");
        
        // DB에 저장
        mapper.saveOrder(order);

        // 주문 번호 반환
        return merchantUid;
	}
	
//	private int calculateTotalPrice(OrderVO vo) {
//        // 상품 총액 계산 로직
//        return 100;  // 예시 값
//    }

	private int calculateDiscount(String couponCode) {
	    log.info("쿠폰 코드: {}", couponCode);	    
	    if (couponCode != null) {
	        CouponVO coupon = couponMapper.getCouponInfo(couponCode);
	        
	        if (coupon != null) {
	            log.info("쿠폰 할인율: {}", coupon.getDiscount_rate());
	            return coupon.getDiscount_rate();
	        } else {
	            log.warn("쿠폰 정보를 찾을 수 없습니다.");
	            return 0;
	        }
	    } else {
	        log.warn("쿠폰 코드가 없습니다.");
	        return 0;
	    }
	}

    private String generateMerchantUid() {
        return "O" + System.currentTimeMillis();  // 고유 주문 번호 생성
    }

	public void updateOrderState(String merchant_uid, String orderState) {
		// 주문 상태 업데이트 로직
	    mapper.updateOrderState(merchant_uid, orderState);
		
	}

	public void saveOrderItems(String merchant_uid, List<OrderItemVO> orderItems) {
		for (OrderItemVO item : orderItems) {
	        // order_item 테이블에 각 상품 정보를 저장
	        item.setMerchant_uid(merchant_uid);  // 주문 번호 설정
	        log.info("product_name:{}",item.getProduct_name());
	        mapper.saveOrderItem(item);  // 각 상품 정보 저장
	    }
		
	}

	public OrderVO selectOneOrder(String merchant_uid) {
		return mapper.selectOneOrder(merchant_uid);
	}

	public void updateReusing(String member_id, boolean reusing) {
		Map<String, Object> params = new HashMap<>();
        params.put("member_id", member_id);
        params.put("reusing", reusing ? "1" : "0"); // boolean을 MySQL의 1과 0으로 변환

        log.info("reusing:{}",params.get("reusing"));
        mapper.updateReusing(params);
		
	}

	public List<OrderItemVO> selectOneItem(String merchant_uid) {
		// TODO Auto-generated method stub
		return mapper.selectOneItem(merchant_uid);
	}

	public List<OrderJoinProductVO> selectAllPageBlockByUser(int cpage, int pageBlock, String userId) {
		int startRow = pageBlock*(cpage-1);
		log.info("startRow:{}",startRow);
		log.info("pageBlock:{}",pageBlock);
		
		return mapper.selectAllPageBlockByUser(startRow,pageBlock,userId);
	}

	public int getTotalRowsByUser(String userId) {
		return mapper.getTotalRowsByUser(userId);
	}

	public List<OrderJoinProductVO> selectAllByUser(String merchant_uid,int product_num) {
		// TODO Auto-generated method stub
		return mapper.selectAllByUser(merchant_uid,product_num);
	}


}
