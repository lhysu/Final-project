package com.project.zerowasteshop.donateitem;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class DonateItemVO {

	private int donateItem_num;
	private String member_id;
	private String donateItem_title;
	private String donateItem_content;
	private String donateItem_img;
	private Timestamp donateItem_wdate;
	private String donateItem_address;
	private String donateItem_item;
	private String donate_state;
	private MultipartFile file;
	
}
