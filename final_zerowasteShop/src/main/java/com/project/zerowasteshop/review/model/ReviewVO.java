package com.project.zerowasteshop.review.model;

import java.sql.Timestamp;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ReviewVO {
	
	private int review_num;
    private String member_id;
    private int product_num;
    private String product_name;
    private String content;
    private double rating;
    private String review_img;
    private String createdDate;
    private MultipartFile file;
   
}
