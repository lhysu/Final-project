package com.project.zerowasteshop.event;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class EventVO {
	private int event_num;
    private String event_title;
    private String event_content;
    private Timestamp event_wdate;
    private int event_views;
    private String event_img;
    private MultipartFile file;
}
