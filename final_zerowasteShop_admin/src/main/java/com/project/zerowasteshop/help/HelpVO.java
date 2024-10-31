package com.project.zerowasteshop.help;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class HelpVO {
    private int help_num;
    private String member_id;
    private String inquiry_type;
    private String inquiry_text;
    private Timestamp created_at;
    private String status;
    private String response;
    private Timestamp responsed_at;
    private int help_views;
    private String help_img;
    private MultipartFile file;
}
