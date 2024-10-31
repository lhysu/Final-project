package com.project.zerowasteshop.recycletip;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class RecycleTipVO {
    private int recycleTip_num;
    private String member_id;
    private String recycleTip_title;
    private String recycleTip_content;
    private int recycleTip_views;
    private int recycleTip_likes;
    private String recycleTip_img;
    private Timestamp recycleTip_wdate;
    private MultipartFile file;
}
