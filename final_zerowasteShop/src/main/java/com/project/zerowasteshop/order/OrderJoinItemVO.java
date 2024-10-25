package com.project.zerowasteshop.order;

import java.util.List;

import lombok.Data;

@Data
public class OrderJoinItemVO {
	private String merchant_uid;
	private int product_num;
	private String member_id;	
	private String coupon_code;
	private int points;
	private String postcode;
	private String address;
	private String address_detail;
	private String tel;
	private boolean reusing;
	private int discount;
	private int delivery_fee;
	private String delivery_memo;
	private int total_price;
	private int final_price;
	private String order_state;
	private List<OrderItemVO> orderItems;
	
	private int order_item_id;
	private String product_name;
	private int quantity;
	private int price;
}
