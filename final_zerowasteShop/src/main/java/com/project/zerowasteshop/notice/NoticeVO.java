package com.project.zerowasteshop.notice;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class NoticeVO {
    private int notice_num;
    private String notice_title;
    private String notice_content;
    private Timestamp notice_wdate;
    private int notice_views;
    private String notice_img;
    private MultipartFile file;
}