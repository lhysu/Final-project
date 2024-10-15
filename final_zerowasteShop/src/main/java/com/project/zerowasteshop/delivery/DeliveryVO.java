package com.project.zerowasteshop.delivery;

import java.sql.Timestamp;

public class DeliveryVO {

	private int delivery_num;
	private int pay_num;
	private int product_num;
	private String member_id;
	private int order_num;
	private String tracking_num;
	private String courier;
	private String delivery_status;
	private Timestamp shipped_date;
	private Timestamp delivery_date;
}
