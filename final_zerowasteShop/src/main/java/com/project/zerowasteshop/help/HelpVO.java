package com.project.zerowasteshop.help;

import java.sql.Timestamp;

import org.springframework.web.multipart.MultipartFile;

import lombok.Data;

@Data
public class HelpVO {
	
    private int help_num;            	// 글 번호
    private String member_id;        	// 회원 아이디
    private String inquiry_type;     	// 문의유형
    private String inquiry_text;     	// 문의내용
    private Timestamp created_at;    	// 문의생성 일시
    private String status;          	// 처리상태
    private String response;        	// 답변
    private Timestamp responsed_at;  	// 답변 일시
    private int help_views;          	// 조회수
    private String help_img;         	// 이미지 파일경로
    private MultipartFile file;     	// 이미지
}
