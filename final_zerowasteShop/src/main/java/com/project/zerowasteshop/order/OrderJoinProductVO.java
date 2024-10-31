package com.project.zerowasteshop.order;

import java.sql.Timestamp;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class OrderJoinProductVO {
	private String merchant_uid;
	private int product_num;
	private String product_name;
	private String member_id;
	private String coupon_code;
	private int quantity;
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
	private int final_price;
	private String order_state;
    private int price;
    private int point;
    private String company;
    private String product_img;
    private String category;
    private double rating;
    private MultipartFile file;
    private String order_date;
    private String delivery_status;
    private String delivery_date;
    
    public OrderJoinProductVO(JSONObject itemJson) {
        this.product_name = itemJson.getString("title");     
        this.price = itemJson.getInt("lprice");
        this.point = itemJson.getInt("lprice") / 1000;
        this.company = itemJson.getString("brand");
        this.product_img = itemJson.getString("image");
        this.category = itemJson.getString("category1");
    }

    public OrderJoinProductVO() {
    }
}
