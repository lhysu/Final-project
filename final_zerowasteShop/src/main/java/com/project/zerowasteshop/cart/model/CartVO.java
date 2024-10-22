package com.project.zerowasteshop.cart.model;

import lombok.Data;

@Data
public class CartVO {
	
	private int cart_num;
	private int product_num;
    private String member_id;
    private int count;
    private int price;
    private String product_img;
    private String product_name;
   
}
