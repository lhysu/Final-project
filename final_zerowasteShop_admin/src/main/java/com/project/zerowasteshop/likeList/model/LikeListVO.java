package com.project.zerowasteshop.likeList.model;

import lombok.Data;

@Data
public class LikeListVO {
	
	private int likeList_num;
	private int product_num;
    private String member_id;
    private int price;
    private String product_img;
    private String product_name;
   
}
