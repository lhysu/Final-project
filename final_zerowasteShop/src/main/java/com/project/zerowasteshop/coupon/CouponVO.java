package com.project.zerowasteshop.coupon;

import org.springframework.web.multipart.MultipartFile;
import lombok.Data;

@Data
public class CouponVO {
	private String coupon_code;
	private String member_id;
	private String coupon_name;
	private String use_sdate;
	private String use_edate;
	private int discount_rate;
	private boolean used;
	private MultipartFile file;
}
