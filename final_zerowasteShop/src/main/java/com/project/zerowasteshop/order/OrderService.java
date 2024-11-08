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

	//사용가능한 유저의 쿠폰 목록을 불러오기
	public List<CouponVO> getAvailableCouponsForUser(String member_id) {
		return mapper.getAvailableCouponsForUser(member_id);
	}

	public int getOrderAmount(String merchant_uid) {
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

	// 고유 주문 번호 생성
    private String generateMerchantUid() {
        return "O" + System.currentTimeMillis();  
    }

 // 주문 상태 업데이트 로직
	public void updateOrderState(String merchant_uid, String orderState) {		
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

	public List<OrderJoinProductVO> selectAllByUser(String merchant_uid) {
		// TODO Auto-generated method stub
		return mapper.selectAllByUser(merchant_uid);
	}

	public OrderVO getOrderInfo(String merchant_uid) {
		return mapper.getOrderInfo(merchant_uid);
		
	}

	public void deleteOrder(String merchant_uid) {
		mapper.deleteOrder(merchant_uid);
		
	}

	public void deleteOrderItem(String merchant_uid) {
		mapper.deleteOrderItem(merchant_uid);
		
	}

	public int getTotalPrice(String merchant_uid) {
		return mapper.getTotalPrice(merchant_uid);
	}


}
