package com.project.zerowasteshop.coupon;

import java.util.Date;

import lombok.Data;

@Data
public class CouponVO {
	private String coupon_code;
	private String member_id;
	private String coupon_name;
	private Date use_sdate;
	private Date use_edate;
	private int discount_rate;

	
}
