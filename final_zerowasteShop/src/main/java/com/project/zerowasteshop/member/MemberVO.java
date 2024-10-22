package com.project.zerowasteshop.member;

import java.sql.Timestamp;

import lombok.Data;

@Data
public class MemberVO {

	public String member_id;
	public int member_num;
	public String name;
	public String pw;
	public String email;
	public String phone_number;
	public String postcode;
	public String address;
	public String address_detail;
	public Timestamp signup_date;
	public int points;
	public boolean adCheck;
}
