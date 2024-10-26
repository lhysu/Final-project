package com.project.zerowasteshop.order;

import lombok.Data;

@Data
public class OrderItemVO {
	private int order_item_id;
	private String merchant_uid;
	private int product_num;
	private String product_name;
	private int quantity;
	private int price;
}
