package com.project.zerowasteshop.delivery;

import java.sql.Timestamp;

public class DeliveryVO {

	private int delivery_num;
	private int pay_num;
	private String product_name;
	private String member_id;
	private String merchant_uid;
	private String tracking_num;
	private String courier;
	private String delivery_status;
	private Timestamp shipped_date;
	private Timestamp delivery_date;
	
	@Override
	public String toString() {
		return "DeliveryVO [delivery_num=" + delivery_num + ", pay_num=" + pay_num + ", product_name=" + product_name
				+ ", member_id=" + member_id + ", merchant_uid=" + merchant_uid + ", tracking_num=" + tracking_num
				+ ", courier=" + courier + ", delivery_status=" + delivery_status + ", shipped_date=" + shipped_date
				+ ", delivery_date=" + delivery_date + "]";
	}

	public int getDelivery_num() {
		return delivery_num;
	}

	public void setDelivery_num(int delivery_num) {
		this.delivery_num = delivery_num;
	}

	public int getPay_num() {
		return pay_num;
	}

	public void setPay_num(int pay_num) {
		this.pay_num = pay_num;
	}

	public String getProduct_name() {
		return product_name;
	}

	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}

	public String getMember_id() {
		return member_id;
	}

	public void setMember_id(String member_id) {
		this.member_id = member_id;
	}

	public String getMerchant_uid() {
		return merchant_uid;
	}

	public void setMerchant_uid(String merchant_uid) {
		this.merchant_uid = merchant_uid;
	}

	public String getTracking_num() {
		return tracking_num;
	}

	public void setTracking_num(String tracking_num) {
		this.tracking_num = tracking_num;
	}

	public String getCourier() {
		return courier;
	}

	public void setCourier(String courier) {
		this.courier = courier;
	}

	public String getDelivery_status() {
		return delivery_status;
	}

	public void setDelivery_status(String delivery_status) {
		this.delivery_status = delivery_status;
	}

	public Timestamp getShipped_date() {
		return shipped_date;
	}

	public void setShipped_date(Timestamp shipped_date) {
		this.shipped_date = shipped_date;
	}

	public Timestamp getDelivery_date() {
		return delivery_date;
	}

	public void setDelivery_date(Timestamp delivery_date) {
		this.delivery_date = delivery_date;
	}
	
	
	
	
	
	
}
