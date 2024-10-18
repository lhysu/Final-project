package com.project.zerowasteshop.order;

import lombok.Data;

@Data
public class OrderVO {
	private int order_num;
	private int product_num;
	private String product_name;
	private String member_id;
	private String coupon_code;
	private int count;
	private String postcode;
	private String address;
	private String address_detail;
	private String tel;
	private boolean reusing;
	private int discount;
	private int delivery_fee;
	private String delivery_memo;
	private boolean payCheck;
	private int total_price;
	private String order_state;
}
