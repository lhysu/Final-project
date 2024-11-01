package com.project.zerowasteshop.product.model;

import java.util.Random;

import org.json.JSONObject;
import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class ProductVO {
	private int product_num;
    private String product_name;
    private int count;
    private int price;
    private int point;
    private String company;
    private String product_img;
    private String category;
    private double rating;
    private MultipartFile file;
    

    public ProductVO(JSONObject itemJson) {
    	Random random = new Random();
    	
        this.product_name = itemJson.getString("title");     
        this.price = itemJson.getInt("lprice");
        this.point = itemJson.getInt("lprice") / 148;
        this.company = itemJson.getString("brand");
        this.product_img = itemJson.getString("image");
        this.category = itemJson.getString("category1");
        this.rating = (double)(random.nextInt(5) + 1);
    }

    public ProductVO() {
    }
}
